package com.example.alfie_s_app;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import android.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import static com.google.android.gms.location.Geofence.NEVER_EXPIRE;

public class GeofenceService extends Service implements ActivityCompat.OnRequestPermissionsResultCallback {

    private Location lastLocation;
    private static final String TAG = HomePage.class.getSimpleName();
    private GeofencingClient client;
    private Geofence area;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationManager locManager;
    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Log.d(TAG, "onLocationChanged [" + location + "]");
            lastLocation = location;
        }

        public void onStatusChanged(String s, int i, Bundle bundle) {
            Log.v(TAG, "Status changed: " + s);
        }

        public void onProviderEnabled(String s) {
            Log.e(TAG, "PROVIDER DISABLED: " + s);
        }

        public void onProviderDisabled(String s) {
            Log.e(TAG, "PROVIDER DISABLED: " + s);
        }
    };

    private static final int NOTIF_ID = 1;
    private static final String NOTIF_CHANNEL_ID = "Channel_Id";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "OnStartCommand started");

        client = LocationServices.getGeofencingClient(this);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        //test locations
        setGeofence("Ellis Hall", 40.500409,  -78.014388, 90.0f);
        setGeofence("Brumbaugh Academic Center", 40.501924,  -78.018400, 200.0f);
        setGeofence("Suzzane von Liebig Center for Science", 40.499332,  -78.015895, 100.0f);
        setGeofence("Kennedy Sports and Recreation Center", 40.500743,  -78.015450, 100.0f);
        setGeofence("Hallbriter", 40.501049,  -78.016748, 70.0f);
        setGeofence("Good Hall", 40.499438,  -78.017843, 90.0f);
        setGeofence("Founders Hall", 40.499418,  -78.016673, 60.0f);
        setGeofence("Beeghly Library", 40.500490,  -78.017312, 70.0f);
        setGeofence("Juniata College Museum of Arts", 40.498912,  -78.017531, 60.0f);
        setGeofence("Gibbel Stadium", 40.503150,  -78.015246, 100.0f);
        setGeofence("Knox Stadium", 40.498924,  -78.014629, 120.0f);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            startMyOwnForeground();
        else
            startForeground(1, new Notification());

        locManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if(checkPermission())
            locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, locationListener);

        return super.onStartCommand(intent, flags, startId);
    }

    private void startMyOwnForeground(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String NOTIFICATION_CHANNEL_ID = "com.example.myapplication";
            String channelName = "Background Service";
            int importance = NotificationManager. IMPORTANCE_HIGH ;
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel serviceChan = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, importance);
            serviceChan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            assert manager != null;
            manager.createNotificationChannel(serviceChan);

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
            Notification notification = notificationBuilder.setOngoing(true)
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentTitle("App is running in background")
                    .setPriority(NotificationManager.IMPORTANCE_MIN)
                    .setCategory(Notification.CATEGORY_SERVICE)
                    .build();
            startForeground(2, notification);
        }
    }

    private void startForeground() {
        Intent notificationIntent = new Intent(this, HomePage.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        startForeground(NOTIF_ID, new NotificationCompat.Builder(this,
                NOTIF_CHANNEL_ID) // don't forget create a notification channel first
                .setOngoing(true)
                .setSmallIcon(R.drawable.event_image)
                .setContentTitle(getString(R.string.app_name))
                .setContentText("Service is running background")
                .setContentIntent(pendingIntent)
                .build());
    }

    //Setter
    public void setGeofence(String id, double lat, double lng, float radius) {
        Log.d(TAG, "setGeofence");

        // Start Geofence creation process
        area = new Geofence.Builder()
                .setRequestId(id)
                .setCircularRegion(lat, lng, radius)
                .setExpirationDuration(NEVER_EXPIRE)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER)
                .build();

        GeofencingRequest geofenceRequest = createGeofenceRequest(area);

        // Add the created GeofenceRequest to the device's monitoring list
        if (checkPermission())
            client.addGeofences(geofenceRequest, createGeofencePendingIntent())
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "Geofence added");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "Geofence addition failed");
                        }
                    });
    }

    private static final String NOTIFICATION_MSG = "NOTIFICATION MSG";

    // Create a Intent send by the notification
    public static Intent makeNotificationIntent(Context context, String msg) {
        Intent intent = new Intent(context, HomePage.class);
        intent.putExtra(NOTIFICATION_MSG, msg);
        return intent;
    }

    private final int REQ_PERMISSION = 999;

    // Check for permission to access Location
    private boolean checkPermission() {
        Log.d(TAG, "checkPermission()");
        // Ask for permission if it wasn't granted yet
        return (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED);
    }

    // Verify user's response of the permission requested
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult()");
        if (requestCode == REQ_PERMISSION) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
                getLastKnownLocation();
            } else {
                // Permission denied
                permissionsDenied();
            }
        }
    }

    // App cannot work without the permissions
    private void permissionsDenied() {
        Log.w(TAG, "permissionsDenied()");
    }

    // Start location Updates
    private void startLocationUpdates() {

        // Defined in milli seconds.
        LocationRequest locationRequest;
        final int UPDATE_INTERVAL = 60000*60; //interval set for 1 minute
        final int FASTEST_INTERVAL = 30000; //30 second interval is the fastest

        Log.i(TAG, "startLocationUpdates()");
        locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(UPDATE_INTERVAL)
                .setFastestInterval(FASTEST_INTERVAL);

        if (checkPermission())
            fusedLocationClient.requestLocationUpdates(locationRequest, createGeofencePendingIntent());
    }

    // Get last known location
    private void getLastKnownLocation() {
        Log.d(TAG, "getLastKnownLocation()");

        if (checkPermission()) {
            fusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        Log.d(TAG, "Location found");
                    } else {
                        Log.d(TAG, "Location is null");

                    }
                }
            });

            if (lastLocation != null) {
                Log.i(TAG, "Last Known location. " +
                        "Long: " + lastLocation.getLongitude() +
                        " | Lat: " + lastLocation.getLatitude());
                startLocationUpdates();
            } else {
                Log.w(TAG, "No location retrieved yet");
                startLocationUpdates();
            }
        }
        else{
            Log.d(TAG, "Need permission");
            //TODO Notification that user needs to allow location permission
        }
    }

    // Create a Geofence Request
    private GeofencingRequest createGeofenceRequest(Geofence geofence) {
        Log.d(TAG, "createGeofenceRequest");
        return new GeofencingRequest.Builder()
                .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
                .addGeofence(geofence)
                .build();
    }

    private PendingIntent geoFencePendingIntent;

    private PendingIntent createGeofencePendingIntent() {
        Log.d(TAG, "createGeofencePendingIntent");
        final int GEOFENCE_REQ_CODE = 0;
        if (geoFencePendingIntent != null)
            return geoFencePendingIntent;

        Intent intent = new Intent(this, GeofenceTransition.class);
        return PendingIntent.getService(
                this, GEOFENCE_REQ_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
