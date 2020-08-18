package org.me.gcu.weathermpdcfaw;

import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;

public class BlankViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    String testText = new String("yes");
    ArrayList<DaySummary> daysList = new ArrayList<DaySummary>();
    int currentSet = 0;


    public int getCurrentSet() {
        return currentSet;
    }



    public void setStringText(String text) {
        testText = text;
    }

    public String getStringText() {
        return testText;
    }

    public void addDay(DaySummary dayIn) {
        daysList.add(dayIn);
        System.out.println("THIS HAS WORKED");
    }
    public DaySummary getDay(int dayNo) {

        return daysList.get(dayNo);
    }

    public void nextLocation() {
        if (currentSet < 15) {
            currentSet = currentSet + 3; // Each day has three objects in the ArrayList - One for each day.
        }
        else {
            currentSet = 0;
        }
    }

    public void prevLocation() {
        if (currentSet > 0 ) {
            currentSet = currentSet - 3; // Each day has three objects in the ArrayList - One for each day.
        }
        else {
            currentSet = 15;
        }
    }


    public void tester(){
        System.out.println(daysList.size());
        for(DaySummary days : daysList) {

            System.out.println(days.getDayTitle());



        }
    }
}