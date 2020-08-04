package org.me.gcu.weathermpdcfaw;

import android.arch.lifecycle.ViewModel;

public class BlankViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    String testText = new String("yes");


    public void setStringText(String text) {
        testText = text;
    }

    public String getStringText() {
        return testText;
    }
}