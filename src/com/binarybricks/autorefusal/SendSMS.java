package com.binarybricks.autorefusal;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.widget.Toast;

/**
 * Copyright (C) 2012
 * 
 * @author Pranay Airan
 * This util class will be used for sending sms.
 */
public class SendSMS {

	private BroadcastReceiver rc;

	public void sendSMS(String msg, final Context ctx) {
		
		PendingIntent sentPI = PendingIntent.getBroadcast(ctx, 0, new Intent("SMS_SENT"), 0);
		
		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage("+919663952225", null, msg, sentPI, null);
		
		rc = new BroadcastReceiver() {
			@Override
			public void onReceive(Context arg0, Intent arg1) {
				switch (getResultCode()) {
				case Activity.RESULT_OK:
					Toast.makeText(ctx, "SMS sent", Toast.LENGTH_SHORT).show();
					break;

				}
			}
		};
		ctx.registerReceiver(rc, new IntentFilter("SMS_SENT"));

	}

	public void deregesterSMS(Context ctx) {
		ctx.unregisterReceiver(rc);
	}
}
