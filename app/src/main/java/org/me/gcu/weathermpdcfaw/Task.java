package org.me.gcu.weathermpdcfaw;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
// CHRIS FAWCETT S1622925
// Need separate thread to access the internet resource over network
// Other neater solutions should be adopted in later iterations.
class Task implements Runnable {
    private final MainActivity mainActivity;
    private String url;


    public Task(MainActivity mainActivity, String aurl, DataViewModel mViewModel) {
        this.mainActivity = mainActivity;
        url = aurl;


    }

    @Override
    public void run() {
        getData();


        mainActivity.runOnUiThread(new Runnable() {
                                            public void run() {
                                                Log.d("UI thread", "I am the UI thread");
                                                ThreeDayFragment firstFragment = (ThreeDayFragment) mainActivity.getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                                                //firstFragment.setText(mViewModel);
                                                mainActivity.returnDetails();

                                            }
                                        }

        );
    }

    private void getData() {
        String[] urlLocation = {"2648579", "2643743", "5128581", "287286", "934154", "1185241"};


        URL aurl;
        URLConnection yc;
        BufferedReader in = null;
        String inputLine = "";


        //Log.e("MyTag", "in run");
        for (String s : urlLocation) {
            String result = "";

            try {
                // Log.e("MyTag", "in try");
                aurl = new URL(url + s);
                yc = aurl.openConnection();
                in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
                //
                // Throw away the first 2 header lines before parsing

                for (int x = 0; x < 18; x++) {
                    in.readLine();
                }
                //
                //
                //

                int counter = 0;
                int testInt = 0;
                while ((inputLine = in.readLine()) != null && counter < 27) {
                    String[] testDC = inputLine.split(":", 2);

                    if (testDC.length > 1) {
                        if (testDC[0].toLowerCase().contains("dc") || testDC[0].toLowerCase().contains("georss")) {
                            //System.out.println("THIS HAS BEEN INTERCEPTED");
                        } else {
                            //System.out.println("l" + testDC[0] + "m");
                            result = result + inputLine;
                            //Log.e("MyTag", testInt + " " + inputLine);
                            testInt++;
                        }
                    } else {
                        //System.out.println("LENGTH NOT TRIGGERED");
                        result = result + inputLine;
                        //Log.e("MyTag", testInt + " " + inputLine);
                        testInt++;
                    }
                    counter++;
                }
                in.close();
            } catch (IOException ae) {
                //Log.e("MyTag", "ioexception");
            }

            //
            // Now that you have the xml data you can parse it
            //

            // Now update the TextView to display raw XML data
            // Probably not the best way to update TextView
            // but we are just getting started !
            //System.out.println(result);


            DaySummary dayHolder = null;


            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();

                xpp.setInput(new StringReader(result));
                int eventType = xpp.getEventType();
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_DOCUMENT) {
                        //System.out.println("Start document");

                    } else if (eventType == XmlPullParser.START_TAG) {
                        //System.out.println("Start tag " + xpp.getName());

                        if (xpp.getName().equalsIgnoreCase("item")) {
                            dayHolder = new DaySummary();
                        } else if (xpp.getName().equalsIgnoreCase("title")) {
                            String temp = xpp.nextText();

                            dayHolder.setDayTitle(temp);
                        } else if (xpp.getName().equalsIgnoreCase("description")) {

                            String temp = xpp.nextText();
                            //System.out.println(temp);
                            String[] seperated = temp.split(",");

                            String[] seperated2;
                            int i;
                            for (i = 0; i < seperated.length; i++) {
                                //System.out.println("This is the split string " + i + " and the text is " + seperated[i]);
                                String[] temp2 = seperated[i].split(":", 2);
                                //System.out.println("h"+temp2[0] + "k");
                                switch (temp2[0]) {
                                    case "Maximum Temperature":
                                        dayHolder.setMaxTemp(temp2[1]);
                                        break;
                                    case " Minimum Temperature":
                                        dayHolder.setMinTemp(temp2[1]);
                                        break;
                                    case "Minimum Temperature":
                                        dayHolder.setMinTemp(temp2[1]);
                                        break;
                                    case " Wind Direction":
                                        dayHolder.setWindDir(temp2[1]);
                                        break;
                                    case " Wind Speed":
                                        dayHolder.setWindSpeed(temp2[1]);
                                        break;
                                    case " Visibility":
                                        dayHolder.setVisibility(temp2[1]);
                                        break;
                                    case " Pressure":
                                        dayHolder.setPressure(temp2[1]);
                                        break;
                                    case " Humidity":
                                        dayHolder.setHumidity(temp2[1]);
                                        break;
                                    case " UV Risk":
                                        dayHolder.setUvRisk(temp2[1]);
                                        break;
                                    case " Pollution":
                                        dayHolder.setPollution(temp2[1]);
                                        break;
                                    case " Sunrise":
                                        dayHolder.setSunrise(temp2[1]);
                                        break;
                                    case " Sunset":
                                        dayHolder.setSunset(temp2[1]);
                                        break;
                                    default:
                                        System.out.println("Error with " + temp2[0]);

                                }

                            }

                        }


                    } else if (eventType == XmlPullParser.END_TAG) {
                        //System.out.println("End tag " + xpp.getName());
                        if (xpp.getName().equalsIgnoreCase("item")) {
                            mainActivity.mViewModel.addDay(dayHolder);
                        }

                    } else if (eventType == XmlPullParser.TEXT) {
                        //System.out.println("Text " + xpp.getText());

                    } else {
                    }

                    eventType = xpp.next();
                }
                //System.out.println("End document");


            } catch (XmlPullParserException | IOException e) {
                e.printStackTrace();
            }

        }
    }

}
