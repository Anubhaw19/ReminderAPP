package com.example.reminderapp;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar c=Calendar.getInstance();      //for getting current status of time and date.
        int hour=c.get(Calendar.HOUR_OF_DAY);// this will return hour
        int minute=c.get(Calendar.MINUTE);// this will return minute
        int date=c.get(Calendar.DAY_OF_MONTH);// this will return date(1,2,3,4.....30,31)
        int month=c.get(Calendar.MONTH);// this will return month
        int year=c.get(Calendar.YEAR);// this will return year
        int day=c.get(Calendar.DAY_OF_WEEK); //this will retur SUNDAY(0),MONDAY(1).....
        int dayofWeekinMonth=c.get(Calendar.DAY_OF_WEEK_IN_MONTH);// this will return week(1st,2nd,3rd,4th)


        //returning time picker dialog .[DateFormat.is24Hour] is checking wether the system id set to 12 hour or 24 hour format.
        return new TimePickerDialog(getActivity(),(TimePickerDialog.OnTimeSetListener) getActivity(),hour,minute, DateFormat.is24HourFormat(getActivity()));
    }
}
