package com.example.reminderapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    TextView text;
    Button Btn ,cancel;
    /** TODO: IMPORTANT ADD THIS IN MANIFEST:  <receiver android:name=".AlertReceiver"/>*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Btn=findViewById(R.id.button_time);
        text=findViewById(R.id.textView);
        cancel=findViewById(R.id.button_cancel);



        Calendar c=Calendar.getInstance();/** created calender instanse for getting current date and time*/
        final int hour=c.get(Calendar.HOUR_OF_DAY);
        final int minute=c.get(Calendar.MINUTE);
        final int date=c.get(Calendar.DAY_OF_MONTH);
        final int month=c.get(Calendar.MONTH);
        final int year=c.get(Calendar.YEAR);
        final int day=c.get(Calendar.DAY_OF_WEEK);
        final int dayofWeekinMonth=c.get(Calendar.DAY_OF_WEEK_IN_MONTH);

        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                text.setText("date: "+date+"\n"+"month: "+month+"\n"+"year: "+year+"\n"
                        +"day: "+day+"\n"+"dayofWeekinMonth :"+dayofWeekinMonth+"\n");// for checking the output of these functions,can be deleted.

                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(),"timepicker"); //this will show time picker in the screen.
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAlarm();
            }
        });
    }

    /**
     * this method will send the selected time form time_picker to our Main_Activity*/
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        /**TODO: EVERTHING RELATED TO DATE AND TIMING WILL BE DONE HERE,see below code for refrence*/

        text.setText("hour :"+hourOfDay+"\n"+"minute :"+minute+"\n");
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay); //setting the hours
        calendar.set(Calendar.MINUTE,minute);       //setting the minutes
        calendar.set(Calendar.SECOND,0);//setting the second
//        calendar.set(Calendar.DAY_OF_MONTH,22);
//        calendar.set(Calendar.MONTH,5);
//        calendar.set(Calendar.YEAR,2020);
//

        /** in similar way we can set DATE also
         * like :
         *         calendar.set(Calendar.DAY_OF_MONTH,22);
         *         calendar.set(Calendar.MONTH,5);
         *         calendar.set(Calendar.YEAR,2020);
         *
         * calendar.set(Calendar*/

        updateTimeText(calendar);
        startAlarm(calendar);
    }

    private  void  updateTimeText(Calendar c){  /** keep this as it is*/
        String timeText="Alarm set for :";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime()); //this will turn it to a formated string of selected date and time from time picker.
        //[DatFormat.SHORT] will give time only in Hours and Minutes.
        text.setText(timeText);
    }
    private  void  startAlarm(Calendar c)   /** keep this as it is*/
    {
        AlarmManager alarmManager=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent=new Intent(this,AlertReceiver.class);
        PendingIntent pendingIntent= PendingIntent.getBroadcast(this,1,intent,0);/**request code for each pending intent should be different.*/

        alarmManager.setExact(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pendingIntent);
    }
    private  void cancelAlarm() /** keep this as it is*/
    {
        AlarmManager alarmManager=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent=new Intent(this,AlertReceiver.class);
        PendingIntent pendingIntent= PendingIntent.getBroadcast(this,1,intent,0);/**request code for each pending intent should be different.*/

        alarmManager.cancel(pendingIntent);
    }
}
