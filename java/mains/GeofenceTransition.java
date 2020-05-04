package com.example.alfie_s_app;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import androidx.core.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofenceStatusCodes;
import com.google.android.gms.location.GeofencingEvent;

import java.util.ArrayList;
import java.util.List;

public class GeofenceTransition extends IntentService {

    private static final String TAG = GeofenceTransition.class.getSimpleName();

    private String triggeredGeofence;

    public static final int GEOFENCE_NOTIFICATION_ID = 0;
    private static final String CHANNEL_ID = "event_notif";
    private NotificationCompat.Builder notificationBuilder = new NotificationCompat
            .Builder(this, CHANNEL_ID);
    private List<Geofence> geofenceList;

    public GeofenceTransition() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        // Handling errors
        if ( geofencingEvent.hasError() ) {
            String errorMsg = getErrorString(geofencingEvent.getErrorCode() );
            Log.e( TAG, errorMsg );
            return;
        }

        int geoFenceTransition = geofencingEvent.getGeofenceTransition();
        // Check if the transition type is of interest
        if ( geoFenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER ) {
            // Get the geofence that were triggered
            geofenceList = geofencingEvent.getTriggeringGeofences();

            //get the single geofence from the list and search for the information in the database
            triggeredGeofence = geofenceList.get(0).getRequestId();

            String geofenceTransitionDetails = getGeofenceTransitionDetails(geoFenceTransition,
                    geofenceList );

            // Send notification details as a String
            sendNotification( geofenceTransitionDetails );
        }
    }

    private String getGeofenceTransitionDetails(int geoFenceTransition,
                                                List<Geofence> triggeringGeofences) {
        // get the ID of each geofence triggered
        ArrayList<String> triggeringGeofencesList = new ArrayList<>();
        for ( Geofence geofence : triggeringGeofences ) {
            triggeringGeofencesList.add( geofence.getRequestId() );
        }

        String status = null;
        if ( geoFenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER )
            status = "You are close to ";
        return status + TextUtils.join( ", ", triggeringGeofencesList);
    }

    private void sendNotification( String msg ) {
        Log.i(TAG, "sendNotification: " + msg );

        // Intent to start the main Activity
        Intent notificationIntent = GeofenceService.makeNotificationIntent(
                getApplicationContext(), msg
        );

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(HomePage.class);
        stackBuilder.addNextIntent(notificationIntent);
        PendingIntent notificationPendingIntent = stackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);


        // Creating and sending Notification
        NotificationManager notificatioMng =
                (NotificationManager) getSystemService( Context.NOTIFICATION_SERVICE );

        if (android.os.Build.VERSION. SDK_INT >= android.os.Build.VERSION_CODES. O ) {
            int importance = NotificationManager. IMPORTANCE_HIGH ;
            NotificationChannel notificationChannel = new
                    NotificationChannel( CHANNEL_ID , "Notification Service" , importance) ;
            notificationChannel.enableLights( true ) ;
            notificationChannel.setLightColor(Color. RED );
            notificationChannel.enableVibration( true ) ;
            notificationChannel.setVibrationPattern( new long []{ 100 , 200 , 300 , 400 , 500 , 400
                    , 300 , 200 , 400 }) ;
            notificationBuilder.setChannelId( CHANNEL_ID ) ;
            assert notificatioMng != null;
            notificatioMng.createNotificationChannel(notificationChannel) ;
        }
        assert notificatioMng != null;

        notificatioMng.notify(
                GEOFENCE_NOTIFICATION_ID,
                createNotification(msg, notificationPendingIntent));
    }

    // Create notification
    private Notification createNotification(final String msg, final PendingIntent notificationPendingIntent) {
        notificationBuilder
                .setColor(Color.RED)
                .setContentTitle(msg)
                .setContentText("There will be event(s) at "+ triggeredGeofence +" in the next 24 hours! Click for more details.")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentIntent(notificationPendingIntent)
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE |
                        Notification.DEFAULT_SOUND)
                .setAutoCancel(true);

        return notificationBuilder.build();
    }

    private static String getErrorString(int errorCode) {
        switch (errorCode) {
            case GeofenceStatusCodes.GEOFENCE_NOT_AVAILABLE:
                return "GeoFence not available";
            case GeofenceStatusCodes.GEOFENCE_TOO_MANY_GEOFENCES:
                return "Too many GeoFences";
            case GeofenceStatusCodes.GEOFENCE_TOO_MANY_PENDING_INTENTS:
                return "Too many pending intents";
            default:
                return "Unknown error.";
        }
    }
}
