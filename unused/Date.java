package com.example.alfie_s_app;
/*
This may have been a complete waste of time
*/

public class Date {

    private int day, month, year;

    //Default Empty Constructor
    public Date(){}

    //Populated Constructor
    public Date(int month, int day, int year)
    {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    //GETS
    public int getMonth(){return month;}
    public int getDay(){return day;}
    public int getYear(){return year;}

    //SETS
    public void setYear(int year)
    {
        this.year = year;
    }

    public void setMonth(int month)
    {
        //Ensures month range of 1-12
        if( (month >= 1) && (month <= 12))
        {
            this.month = month;
        }
    }

    //Looking at this now, I realize doing a switch probably was not the best idea for it.
    //If necessary, I can change it into if statements. I think making a class variable on the states of
    //the months may be a better choice. (0 for no month set, 1 for 30 day months, etc...)


    public void setDay(int day)
    {
        switch(month)
        {
            case 1:     //January
            case 3:     //March
            case 5:     //May
            case 7:     //July
            case 8:     //August
            case 10:    //October
            case 12:    //December
                //31 Day Months
                if ( (day >= 1) && (day <= 31) )
                {
                    this.day = day;
                }
                break;

            case 4:     //April
            case 6:     //June
            case 9:     //September
            case 11:    //November
                //30 Day Months
                if ( (day >= 1) && (day <= 30) )
                {
                    this.day = day;
                }
                break;

            case 2:     //February
                if ( (year % 4) == 0)       //Leap Year
                {
                    if ( (day >= 1) && (day <= 29) )
                    {
                        this.day = day;
                    }
                }
                else                        //Non-Leap Year
                {
                    if ( (day >= 1) && (day <= 28) )
                    {
                        this.day = day;
                    }
                }
                break;

            default:
                //Month not yet set
                break;

        }
    }
}
