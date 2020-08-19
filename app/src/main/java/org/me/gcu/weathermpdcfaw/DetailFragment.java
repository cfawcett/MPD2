package org.me.gcu.weathermpdcfaw;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends  android.support.v4.app.Fragment {
       // private DataViewModel mViewModel;

    public DetailFragment() {
        // Required empty public constructor
    }



    public static DetailFragment newInstance() {
        DetailFragment fragment = new DetailFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DataViewModel mViewModel = ViewModelProviders.of(getActivity()).get(DataViewModel.class);

        setText(mViewModel);


        Button returnButton = (Button) getActivity().findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).returnDetails();
            }
        });

    }











    public void setText(DataViewModel mViewModel) {

        int currentFragment = mViewModel.getCurrentFragment();
        int currentDay = mViewModel.getCurrentSet();
        DaySummary dayInfo = mViewModel.getDay(currentFragment);

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
        Calendar d = Calendar.getInstance();
        switch (mViewModel.getCurrentFragment()) {
            case 0:
                break;
            case 1:
                d.add(Calendar.DAY_OF_YEAR,1);
                break;
            case 2:
                d.add(Calendar.DAY_OF_YEAR,2);
                break;
        }

        Date c = d.getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);

        int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        String formattedDay = new SimpleDateFormat("EEEE", Locale.getDefault()).format(c.getTime());

        dayName1.setText(formattedDay + " " + formattedDate);
        String[] temp2 = dayInfo.getDayTitle().split(":", 2);
        String[] temp3 = temp2[1].split(",",2);
        TextView dayWeather = gv.findViewById(R.id.dayTitleText);
        dayWeather.setText(temp3[0]);

        TextView coldTemp = gv.findViewById(R.id.coldTempText);
        if (dayInfo.getMinTemp() != null ) {
            temp2 = dayInfo.getMinTemp().split("°", 2);
            coldTemp.setText(temp2[0] + "°");
        }
        else {
            coldTemp.setText("--°");
        }

        TextView hotTemp = gv.findViewById(R.id.hotTempText);

        if (dayInfo.getMaxTemp() != null ) {
            temp2 = dayInfo.getMaxTemp().split("°", 2);
            hotTemp.setText(temp2[0] + "°");
        }
        else {
            hotTemp.setText("--°");
        }
        TextView windSpeed = gv.findViewById(R.id.windSpeed);
        if (dayInfo.getWindSpeed() != null ) {
            windSpeed.setText(dayInfo.getWindSpeed());
        }
        else {
            windSpeed.setText("--°");
        }

        ImageView windDir = gv.findViewById(R.id.windArrow);

        if (dayInfo.getWindDir() != null ) {
            switch (dayInfo.getWindDir()) {
                case " Easterly":
                    windDir.setRotation(90);
                    break;
                case " South Easterly":
                    windDir.setRotation(135);
                    break;
                case " Southerly":
                    windDir.setRotation(180);
                    break;
                case " South Westerly":
                    windDir.setRotation(225);
                    break;
                case " Westerly":
                    windDir.setRotation(270);
                    break;
                case " Northerly":
                    windDir.setRotation(0);
                    break;
                case " North Easterly":
                    windDir.setRotation(45);
                    break;
                case " North Westerly":
                    windDir.setRotation(315);
                    break;
            }
        }
        else {
            windSpeed.setVisibility(View.INVISIBLE);
        }

        TextView visibility = gv.findViewById(R.id.visibilityData);
        if (dayInfo.getVisibility() != null ) {
            visibility.setText(dayInfo.getVisibility());
        }
        else {
            visibility.setText("--");
        }

        TextView pressure = gv.findViewById(R.id.pressureData);
        if (dayInfo.getPressure() != null ) {
            pressure.setText(dayInfo.getPressure());
        }
        else {
            pressure.setText("--");
        }
        TextView humidity = gv.findViewById(R.id.humidityData);
        if (dayInfo.getHumidity() != null ) {
            humidity.setText(dayInfo.getHumidity());
        }
        else {
            humidity.setText("--");
        }
        TextView uvData = gv.findViewById(R.id.uvData);
        if (dayInfo.getUvRisk() != null ) {
            uvData.setText(dayInfo.getUvRisk());
        }
        else {
            uvData.setText("--");
        }
        TextView pollution = gv.findViewById(R.id.pollutionData);
        if (dayInfo.getPollution() != null ) {
            pollution.setText(dayInfo.getPollution());
        }
        else {
            pollution.setText("--");
        }
        TextView sunrise = gv.findViewById(R.id.sunriseData);
        TextView sunset = gv.findViewById(R.id.sunsetData);
        if (dayInfo.getSunrise() != null ) {
            sunrise.setText(dayInfo.getSunrise());
        }
        else {
            sunrise.setText("--");
        }
        if (dayInfo.getSunset() != null ) {
            sunset.setText(dayInfo.getSunset());
        }
        else {
            sunset.setText("--");
        }



    }





}