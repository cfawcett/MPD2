package org.me.gcu.weathermpdcfaw;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Calendar;

public class ThreeDayFragment extends android.support.v4.app.Fragment {

    private BlankViewModel mViewModel;

    public static ThreeDayFragment newInstance() {
        return new ThreeDayFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.three_day_fragment, container, false);



    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button nextButton = (Button) getActivity().findViewById(R.id.button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.nextLocation();
                setText();
            }
        });

        Button prevButton = (Button) getActivity().findViewById(R.id.button2);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.prevLocation();
                setText();
            }
        });







        mViewModel = ViewModelProviders.of(getActivity()).get(BlankViewModel.class);

        // TODO: Use the ViewModel


        

    }


    public void setText(){

        int currentDay = mViewModel.getCurrentSet();
        DaySummary dayInfo1 = mViewModel.getDay(currentDay);
        DaySummary dayInfo2 = mViewModel.getDay(currentDay + 1);
        DaySummary dayInfo3 = mViewModel.getDay(currentDay + 2);


        View gv = getView();

        TextView cityName = gv.findViewById(R.id.cityTitle);
        if (currentDay < 3) {
            cityName.setText("Glasgow, Scotland");
        }
        else if (currentDay >= 3 & currentDay < 6) {
            cityName.setText("London, England");
        }
        else if (currentDay >= 6 & currentDay < 9) {
            cityName.setText("New York, United States");
        }
        else if (currentDay >= 9 & currentDay < 12) {
            cityName.setText("Muscat, Oman");
        }
        else if (currentDay >= 12 & currentDay < 15) {
            cityName.setText("Port Louis, Mauritus");
        }
        else if (currentDay >=15 & currentDay < 18) {
            cityName.setText("Dhaka, Bangladesh");
        }



        TextView dayName1 = gv.findViewById(R.id.day1);


        String[] temp2 = dayInfo1.getDayTitle().split(":", 2);
        String[] temp3 = temp2[1].split(",",2);
        TextView dayWeather1 = gv.findViewById(R.id.dayTitleText);
        dayWeather1.setText(temp3[0]);

        TextView coldTemp1 = gv.findViewById(R.id.coldTempText);
        temp2 = dayInfo1.getMinTemp().split("°", 2);
        coldTemp1.setText(temp2[0] + "°");

        TextView hotTemp1 = gv.findViewById(R.id.hotTempText);
        temp2 = dayInfo1.getMaxTemp().split("°", 2);
        hotTemp1.setText(temp2[0] + "°");

        TextView windSpeed1 = gv.findViewById(R.id.windSpeed);
        windSpeed1.setText(dayInfo1.getWindSpeed());

        ImageView windDir1 = gv.findViewById(R.id.windArrow);
        System.out.println("H:" +dayInfo1.getWindDir());
        switch (dayInfo1.getWindDir()) {
            case " Easterly":
                windDir1.setRotation(90);
                break;
            case " South Easterly":
                windDir1.setRotation(135);
                break;
            case " Southerly":
                windDir1.setRotation(180);
                break;
            case " South Westerly":
                windDir1.setRotation(225);
                break;
            case " Westerly":
                windDir1.setRotation(270);
                break;
            case " Northerly":
                windDir1.setRotation(0);
                break;
            case " North Easterly":
                windDir1.setRotation(45);
                break;
            case " North Westerly":
                windDir1.setRotation(315);
                break;
        }



        TextView dayName2 = gv.findViewById(R.id.day2Title);


        temp2 = dayInfo2.getDayTitle().split(":", 2);
        temp3 = temp2[1].split(",",2);
        TextView dayWeather2 = gv.findViewById(R.id.day2TitleTxt);
        dayWeather2.setText(temp3[0]);

        TextView coldTemp2 = gv.findViewById(R.id.coldTempText2);
        temp2 = dayInfo2.getMinTemp().split("°", 2);
        coldTemp2.setText(temp2[0] + "°");

        TextView hotTemp2 = gv.findViewById(R.id.hotTempText2);
        temp2 = dayInfo2.getMaxTemp().split("°", 2);
        hotTemp2.setText(temp2[0] + "°");

        TextView windSpeed2 = gv.findViewById(R.id.windSpeed2);
        windSpeed2.setText(dayInfo2.getWindSpeed());

        ImageView windDir2 = gv.findViewById(R.id.windArrow2);
        System.out.println("H:" +dayInfo2.getWindDir());
        switch (dayInfo2.getWindDir()) {
            case " Easterly":
                windDir2.setRotation(90);
                break;
            case " South Easterly":
                windDir2.setRotation(135);
                break;
            case " Southerly":
                windDir2.setRotation(180);
                break;
            case " South Westerly":
                windDir2.setRotation(225);
                break;
            case " Westerly":
                windDir2.setRotation(270);
                break;
            case " Northerly":
                windDir2.setRotation(0);
                break;
            case " North Easterly":
                windDir2.setRotation(45);
                break;
            case " North Westerly":
                windDir2.setRotation(315);
                break;
        }

        TextView dayName3 = gv.findViewById(R.id.day3Title);


        temp2 = dayInfo3.getDayTitle().split(":", 2);
        temp3 = temp2[1].split(",",2);
        TextView dayWeather3 = gv.findViewById(R.id.day3TitleTxt);
        dayWeather3.setText(temp3[0]);

        TextView coldTemp3 = gv.findViewById(R.id.coldTempText3);
        temp2 = dayInfo3.getMinTemp().split("°", 2);
        coldTemp3.setText(temp2[0] + "°");

        TextView hotTemp3 = gv.findViewById(R.id.hotTempText3);
        temp2 = dayInfo3.getMaxTemp().split("°", 2);
        hotTemp3.setText(temp2[0] + "°");

        TextView windSpeed3 = gv.findViewById(R.id.windSpeed3);
        windSpeed3.setText(dayInfo3.getWindSpeed());

        ImageView windDir3 = gv.findViewById(R.id.windArrow3);
        System.out.println("H:" +dayInfo3.getWindDir());
        switch (dayInfo3.getWindDir()) {
            case " Easterly":
                windDir3.setRotation(90);
                break;
            case " South Easterly":
                windDir3.setRotation(135);
                break;
            case " Southerly":
                windDir3.setRotation(180);
                break;
            case " South Westerly":
                windDir3.setRotation(225);
                break;
            case " Westerly":
                windDir3.setRotation(270);
                break;
            case " Northerly":
                windDir3.setRotation(0);
                break;
            case " North Easterly":
                windDir3.setRotation(45);
                break;
            case " North Westerly":
                windDir3.setRotation(315);
                break;
        }





    }

    public void setTextTest(){
        TextView textView = (TextView) getView().findViewById(R.id.cityTitle);
        textView.setText(mViewModel.getDay(0).getPollution());
        mViewModel.tester();
    }


}


