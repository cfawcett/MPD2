package org.me.gcu.weathermpdcfaw;

import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Calendar;

// CHRIS FAWCETT S1622925



public class DataViewModel extends ViewModel {


    ArrayList<DaySummary> daysList = new ArrayList<DaySummary>();
    int currentSet = 0;
    int currentFragment = 0;
    Calendar c = Calendar.getInstance();


    public DataViewModel() { // Sets default data update time to 20:00
        c.set(Calendar.HOUR, 20);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
    }

    public Calendar getC() {
        return c;
    }

    public void setC(Calendar c) {
        this.c = c;
    }

    public int getCurrentFragment() {
        return currentFragment;
    }

    public void setCurrentFragment(int currentFragment) {
        this.currentFragment = currentFragment;
    }

    public int getCurrentSet() {
        return currentSet;
    }





    public void addDay(DaySummary dayIn) {
        daysList.add(dayIn);

    }
    public DaySummary getDay(int dayNo) {

        return daysList.get(dayNo);
    }

    public void nextLocation() {
        if (currentSet < 15) {
            currentSet = currentSet + 3; // Each location has three objects in the ArrayList - One for each day. This moves forward by one location. If it has reached the last location, it loops back to the first.
        }
        else {
            currentSet = 0;
        }
    }

    public void prevLocation() {
        if (currentSet > 0 ) {
            currentSet = currentSet - 3; // Each location has three objects in the ArrayList - One for each day. This moves back by one location. If it has reached the first location, it loops back to the last.
        }
        else {
            currentSet = 15;
        }
    }


}