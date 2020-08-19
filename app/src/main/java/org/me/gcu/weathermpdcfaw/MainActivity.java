package org.me.gcu.weathermpdcfaw;

// CHRIS FAWCETT S1622925
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.arch.lifecycle.ViewModelProviders;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity{





    private  String urlSource = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/";

     DataViewModel mViewModel;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
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


        }

        ActionBar actionBar = getSupportActionBar(); // or getActionBar();
        getSupportActionBar().setTitle("GCU Weather App"); // set the top title



        IntentFilter filter = new IntentFilter();
        filter.addAction("intent_myaction");
        this.registerReceiver(this.receiver, filter);

        startAlarm(mViewModel.getC());
        startProgress();









    }

    public void startProgress() {
        // Run network access on a separate thread;
        new Thread(new Task(this, urlSource, mViewModel)).start();
    } //

    public void showDetails(int dayChoice) {
        DetailFragment details = new DetailFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
               transaction.replace(R.id.fragment_container, details);
               transaction.addToBackStack(null);
               transaction.commit();


        }

    public void returnDetails() {
        ThreeDayFragment firstFragment = new ThreeDayFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, firstFragment);
        transaction.commit();


    }

    private void startAlarm(Calendar c) { // This is the system for allowing the data to be updated upon the time defined by the user (default 8pm)
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent("intent_myaction"); // Dynamically assigns the broadcast reciever for the alarmmanager
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
           startProgress();

        }
    };

    public void onSettingsAction(MenuItem mi) {
        // handle click here
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter the time for daily data updates (HH:MM)");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_DATETIME);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String dateText = input.getText().toString();
                if (dateText.matches("..:..")) {
                    String[] dateSplit = dateText.split(":", 2);
                    int hour = Integer.parseInt(dateSplit[0]);
                    int minute = Integer.parseInt(dateSplit[1]);
                    Calendar c = Calendar.getInstance();
                    c.set(Calendar.HOUR, hour);
                    c.set(Calendar.MINUTE,minute);
                    c.set(Calendar.SECOND,0);
                    mViewModel.setC(c);

                }

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

} // End of MainActivity
