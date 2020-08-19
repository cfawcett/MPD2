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
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

// CHRIS FAWCETT S1622925
public class ThreeDayFragment extends android.support.v4.app.Fragment {

    private DataViewModel mViewModel;

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


        View day0View = (View) getActivity().findViewById(R.id.day0View);
        mViewModel = ViewModelProviders.of(getActivity()).get(DataViewModel.class);

        day0View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(mViewModel.getCurrentFragment());
                System.out.println(mViewModel.getCurrentSet());
                mViewModel.setCurrentFragment(mViewModel.getCurrentSet());
                ((MainActivity)getActivity()).showDetails(0);
            }
        });

        View day1View = (View) getActivity().findViewById(R.id.day2View);
        day1View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(mViewModel.getCurrentFragment());
                System.out.println(mViewModel.getCurrentSet());
                mViewModel.setCurrentFragment(mViewModel.getCurrentSet() + 1);
                ((MainActivity)getActivity()).showDetails(0);
            }
        });

        View day2View = (View) getActivity().findViewById(R.id.day3View);
        day2View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(mViewModel.getCurrentFragment());
                System.out.println(mViewModel.getCurrentSet());
                mViewModel.setCurrentFragment(mViewModel.getCurrentSet() + 2);
                ((MainActivity)getActivity()).showDetails(0);
            }
        });


        Button nextButton = (Button) getActivity().findViewById(R.id.button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.nextLocation();
                setText(mViewModel);
            }
        });

        Button prevButton = (Button) getActivity().findViewById(R.id.button2);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.prevLocation();
                setText(mViewModel);
            }
        });








        setText(mViewModel);



        

    }


    public void setText(DataViewModel mViewModel){

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

        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);

        int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        String formattedDay = new SimpleDateFormat("EEEE",Locale.getDefault()).format(c.getTime());

        dayName1.setText(formattedDay + " " + formattedDate);



        String[] temp2 = dayInfo1.getDayTitle().split(":", 2);
        String[] temp3 = temp2[1].split(",",2);
        TextView dayWeather1 = gv.findViewById(R.id.dayTitleText);
        dayWeather1.setText(temp3[0]);

        TextView coldTemp1 = gv.findViewById(R.id.coldTempText);
        if (dayInfo1.getMinTemp() != null ) {
            temp2 = dayInfo1.getMinTemp().split("°", 2);
            coldTemp1.setText(temp2[0] + "°");
        }
        else {
            coldTemp1.setText("--°");
        }

        TextView hotTemp1 = gv.findViewById(R.id.hotTempText);

        if (dayInfo1.getMaxTemp() != null ) {
            temp2 = dayInfo1.getMaxTemp().split("°", 2);
            hotTemp1.setText(temp2[0] + "°");
        }
        else {
            hotTemp1.setText("--°");
        }
        TextView windSpeed1 = gv.findViewById(R.id.windSpeed);

        if (dayInfo1.getWindSpeed() != null ) {
            windSpeed1.setText(dayInfo1.getWindSpeed());
        }
        else {
            windSpeed1.setText("--");
        }
        ImageView windDir1 = gv.findViewById(R.id.windArrow);

        if (dayInfo1.getWindDir() != null ) {

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
        }
        else {
            windSpeed1.setVisibility(View.INVISIBLE);
        }

        TextView dayName2 = gv.findViewById(R.id.day2Title);
        Calendar d = Calendar.getInstance();
        d.add(Calendar.DAY_OF_YEAR,1);
        c = d.getTime();
         df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        formattedDate = df.format(c);

        day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        formattedDay = new SimpleDateFormat("EEEE",Locale.getDefault()).format(c.getTime());

        dayName2.setText(formattedDay + " " + formattedDate);

        temp2 = dayInfo2.getDayTitle().split(":", 2);
        temp3 = temp2[1].split(",",2);
        TextView dayWeather2 = gv.findViewById(R.id.day2TitleTxt);
        dayWeather2.setText(temp3[0]);

        TextView coldTemp2 = gv.findViewById(R.id.coldTempText2);
        if (dayInfo2.getMinTemp() != null ) {
            temp2 = dayInfo2.getMinTemp().split("°", 2);
            coldTemp2.setText(temp2[0] + "°");
        }
        else {
            coldTemp2.setText("--°");
        }

        TextView hotTemp2 = gv.findViewById(R.id.hotTempText2);

        if (dayInfo2.getMaxTemp() != null ) {
            temp2 = dayInfo2.getMaxTemp().split("°", 2);
            hotTemp2.setText(temp2[0] + "°");
        }
        else {
            hotTemp2.setText("--°");
        }

        TextView windSpeed2 = gv.findViewById(R.id.windSpeed2);

        if (dayInfo2.getWindSpeed() != null ) {
            windSpeed2.setText(dayInfo2.getWindSpeed());
        }
        else {
            windSpeed2.setText("--");
        }

        ImageView windDir2 = gv.findViewById(R.id.windArrow2);

        if (dayInfo2.getWindDir() != null ) {

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
        }
        else {
            windSpeed2.setVisibility(View.INVISIBLE);
        }
        TextView dayName3 = gv.findViewById(R.id.day3Title);

        d = Calendar.getInstance();
        d.add(Calendar.DAY_OF_YEAR,2);
        c = d.getTime();
        df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        formattedDate = df.format(c);

        day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        formattedDay = new SimpleDateFormat("EEEE",Locale.getDefault()).format(c.getTime());

        dayName3.setText(formattedDay + " " + formattedDate);



        temp2 = dayInfo3.getDayTitle().split(":", 2);
        temp3 = temp2[1].split(",",2);
        TextView dayWeather3 = gv.findViewById(R.id.day3TitleTxt);
        dayWeather3.setText(temp3[0]);

        TextView coldTemp3 = gv.findViewById(R.id.coldTempText3);
        if (dayInfo3.getMinTemp() != null ) {
            temp2 = dayInfo3.getMinTemp().split("°", 2);
            coldTemp3.setText(temp2[0] + "°");
        }
        else {
            coldTemp3.setText("--°");
        }
        TextView hotTemp3 = gv.findViewById(R.id.hotTempText3);

        if (dayInfo3.getMinTemp() != null ) {
            temp2 = dayInfo3.getMaxTemp().split("°", 2);
            hotTemp3.setText(temp2[0] + "°");
        }
        else {
            hotTemp3.setText("--°");
        }
        TextView windSpeed3 = gv.findViewById(R.id.windSpeed3);

        if (dayInfo3.getWindSpeed() != null ) {
            windSpeed3.setText(dayInfo3.getWindSpeed());
        }
        else {
            windSpeed3.setText("--");
        }

        ImageView windDir3 = gv.findViewById(R.id.windArrow3);

        if (dayInfo3.getWindDir() != null ) {
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
        else {
            windSpeed3.setVisibility(View.INVISIBLE);
        }



    }



}


