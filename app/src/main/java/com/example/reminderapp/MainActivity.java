package com.example.reminderapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    TextView text;
    Button Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Btn=(Button)findViewById(R.id.button_time);
        text=(TextView)findViewById(R.id.textView);


        Calendar c=Calendar.getInstance();
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
                        +"day: "+day+"\n"+"dayofWeekinMonth :"+dayofWeekinMonth+"\n");

                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(),"timepicker"); //this will show time picker in the screen.
            }
        });
    }

    /**
     * this method will send the selected time form time_picker to our Main_Activity*/
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        text.setText("hour :"+hourOfDay+"\n"+"minute :"+minute+"\n");
    }
}
