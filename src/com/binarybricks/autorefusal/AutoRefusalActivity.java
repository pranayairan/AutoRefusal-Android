package com.binarybricks.autorefusal;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

/**
 * Copyright (C) 2012
 * 
 * @author Pranay Airan
 * This is the main activity for this app
 */

public class AutoRefusalActivity extends Activity {
	
	static final int TIME_DIALOG_ID = 999;
	int hour;
	int minute;
	String zone ="AM";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
    }
    
    public void SendComplain(View v)
    {
    	String query = "";
    	Spinner options = (Spinner)findViewById(R.id.options);
    	EditText AutoNo = (EditText)findViewById(R.id.autoNumber);
    	EditText GoingFrom = (EditText)findViewById(R.id.from);
    	EditText Goingto = (EditText)findViewById(R.id.to);
    	
    	
    	if(options.getSelectedItem().equals("OverCharge"))
    	{
    		query = "AUTO OVR "+AutoNo.getText().toString()+" "+GoingFrom.getText().toString()+" TO "+Goingto.getText().toString()+" "+hour+"."+minute+" "+zone;
    				
    	}
    	else
    	{
    		query = "AUTO REF "+AutoNo.getText().toString()+" "+GoingFrom.getText().toString()+" TO "+Goingto.getText().toString()+" "+hour+"."+minute+" "+zone;
    	}
    	SendSMS sm = new SendSMS();
    	sm.sendSMS(query, this);
    	Toast.makeText(this, "Your Compalin has been sent, thankyou", Toast.LENGTH_LONG).show();
    	
    }
    
    public void chooseTime(View v)
    {
    	showDialog(TIME_DIALOG_ID);
    }
    
    @Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case TIME_DIALOG_ID:
			// set time picker as current time
			return new TimePickerDialog(this, 
                                        timePickerListener, hour, minute,false);
 
		}
		return null;
	}
 
	private TimePickerDialog.OnTimeSetListener timePickerListener = 
            new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int selectedHour,
				int selectedMinute) {
			hour = selectedHour;
			minute = selectedMinute;
			
			if(hour>12)
			{
				zone = "PM";
				hour = hour-12;
			}
			// set current time into textview
			TextView time = (TextView)findViewById(R.id.time);
			time.setText("Time Set is" +hour+":"+minute+" "+zone);
 
 
		}
	};
 
    
}