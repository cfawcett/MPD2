package org.me.gcu.weathermpdcfaw;


import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private TextView rawDataDisplay;
    private String result = "";
    private Button startButton;
    // Traffic Scotland URLs
    //private String urlSource = "https://trafficscotland.org/rss/feeds/roadworks.aspx";
    //private String urlSource = "https://trafficscotland.org/rss/feeds/plannedroadworks.aspx";
    private String urlSource = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/2643123";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rawDataDisplay = (TextView)findViewById(R.id.rawDataDisplay);
        startButton = (Button)findViewById(R.id.startButton);
        startButton.setOnClickListener(this);
        BlankViewModel mViewModel = ViewModelProviders.of(this).get(BlankViewModel.class);
        mViewModel.setStringText("Has this worked");
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up
            // with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            ThreeDayFragment firstFragment = new ThreeDayFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }






      
    }

    public void onClick(View aview)
    {
        startProgress();
    }

    public void startProgress()
    {
        // Run network access on a separate thread;
        new Thread(new Task(urlSource)).start();
    } //

    // Need separate thread to access the internet resource over network
    // Other neater solutions should be adopted in later iterations.
    private class Task implements Runnable
    {
        private String url;

        public Task(String aurl)
        {
            url = aurl;
        }
        @Override
        public void run()
        {

            URL aurl;
            URLConnection yc;
            BufferedReader in = null;
            String inputLine = "";


            Log.e("MyTag","in run");

            try
            {
                Log.e("MyTag","in try");
                aurl = new URL(url);
                yc = aurl.openConnection();
                in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
                //
                // Throw away the first 2 header lines before parsing

                for(int x = 0;x<18;x++) {
                    in.readLine();
                }
                //
                //
                //
                System.out.println(result);
                int counter = 0;
                int testInt = 0;
                while ((inputLine = in.readLine()) != null && counter < 27)
                {
                    String[] testDC = inputLine.split(":",2);
                    System.out.println(Arrays.toString(testDC));
                    if(testDC.length > 1) {
                        if(testDC[0].toLowerCase().contains("dc") || testDC[0].toLowerCase().contains("georss")) {
                            System.out.println("THIS HAS BEEN INTERCEPTED");
                        }
                        else {
                            System.out.println("l" + testDC[0] + "m");
                            result = result + inputLine;
                            Log.e("MyTag", testInt + " " + inputLine);
                            testInt++;
                        }
                    }
                    else {
                        System.out.println("LENGTH NOT TRIGGERED");
                        result = result + inputLine;
                        Log.e("MyTag", testInt + " " + inputLine);
                        testInt++;
                    }
                    counter++;
                }
                in.close();
            }
            catch (IOException ae)
            {
                Log.e("MyTag", "ioexception");
            }

            //
            // Now that you have the xml data you can parse it
            //

            // Now update the TextView to display raw XML data
            // Probably not the best way to update TextView
            // but we are just getting started !
            System.out.println(result);

            ArrayList<DaySummary> daysList = null;
            DaySummary dayHolder = null;

            try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

            factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();

                xpp.setInput( new StringReader ( result ) );
                int eventType = xpp.getEventType();
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if(eventType == XmlPullParser.START_DOCUMENT) {
                        System.out.println("Start document");
                        daysList = new ArrayList<DaySummary>();
                    }

                     else if(eventType == XmlPullParser.START_TAG) {
                        System.out.println("Start tag "+xpp.getName());

                        if (xpp.getName().equalsIgnoreCase("item")) {
                            dayHolder = new DaySummary();
                        }
                        else if(xpp.getName().equalsIgnoreCase("title")) {
                            String temp = xpp.nextText();

                            dayHolder.setDayTitle(temp);
                        }

                        else if(xpp.getName().equalsIgnoreCase("description")) {

                            String temp = xpp.nextText();
                            System.out.println(temp);
                            String[] seperated = temp.split(",");
                            String[] seperated2;
                            int i;
                            for(i = 0; i < seperated.length; i++){
                            System.out.println("This is the split string " + i + " and the text is " + seperated[i] );
                                String[] temp2 = seperated[i].split(":",2);
                                System.out.println(temp2[0]);
                                switch (i) {
                                    case 0:
                                        dayHolder.setMaxTemp(temp2[1]);
                                        break;
                                    case 1:
                                        dayHolder.setMinTemp(temp2[1]);
                                        break;
                                    case 2:
                                        dayHolder.setWindDir(temp2[1]);
                                        break;
                                    case 3:
                                        dayHolder.setWindSpeed(temp2[1]);
                                        break;
                                    case 4:
                                        dayHolder.setVisibility(temp2[1]);
                                        break;
                                    case 5:
                                        dayHolder.setPressure(temp2[1]);
                                        break;
                                    case 6:
                                        dayHolder.setHumidity(temp2[1]);
                                        break;
                                    case 7:
                                        dayHolder.setUvRisk(temp2[1]);
                                        break;
                                    case 8:
                                        dayHolder.setPollution(temp2[1]);
                                        break;
                                    case 9:
                                        dayHolder.setSunrise(temp2[1]);
                                        break;
                                    case 10:
                                        dayHolder.setSunset(temp2[1]);
                                        break;
                                    default:
                                        System.out.println("Error with " + temp2[0]);

                                }

                            }

                        }


                    } else if(eventType == XmlPullParser.END_TAG) {
                        System.out.println("End tag "+xpp.getName());
                        if(xpp.getName().equalsIgnoreCase("item")) {
                            daysList.add(dayHolder);
                        }

                    } else if(eventType == XmlPullParser.TEXT) {
                        System.out.println("Text "+xpp.getText());

                    }
                    else{}

                    eventType = xpp.next();
                }
                System.out.println("End document");
                Iterator i = daysList.iterator();
                System.out.println("The ArrayList elements are:");
                for (int b=0; b<3;b++){
                    System.out.println(daysList.get(b).getDayTitle());
                }

            } catch (XmlPullParserException | IOException e) {
                e.printStackTrace();
            }


            MainActivity.this.runOnUiThread(new Runnable()
            {
                public void run() {
                    Log.d("UI thread", "I am the UI thread");
                    rawDataDisplay.setText(result);
                }
            });
        }

    }

} // End of MainActivity
