package com.example.afliesapp_basic_homescreen;

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

    public int getMonth(){return month;}
    public int getDay(){return day;}
    public int getYear(){return year;}

    public void setYear(int year)
    {
        this.year = year;
    }

    public void setMonth(int month)
    {
        if( (month >= 1) && (month <= 12))
        {
            this.month = month;
        }
    }

    public void setDay(int day)
    {
        switch(month)
        {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                if ( (day >= 1) && (day <= 31) )
                {
                    this.day = day;
                }
                break;

            case 4:
            case 6:
            case 9:
            case 11:
                if ( (day >= 1) && (day <= 30) )
                {
                    this.day = day;
                }
                break;

            case 2:
                if ( (year % 4) == 0)
                {
                    if ( (day >= 1) && (day <= 29) )
                    {
                        this.day = day;
                    }
                }
                else
                {
                    if ( (day >= 1) && (day <= 28) )
                    {
                        this.day = day;
                    }
                }
                break;

        }
    }
}
