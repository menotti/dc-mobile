package com.example.smssend;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btnSend = (Button) findViewById(R.id.buttonSend);
		btnSend.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText editMsg = (EditText) findViewById(R.id.editMessage);
				String message = editMsg.getText().toString();
				EditText editPhone = (EditText) findViewById(R.id.editPhone);
				String phone = editPhone.getText().toString();
				
				sendSMS(message, phone);
				
				//Clean text fields and show toast
				editMsg.setText("");
				editPhone.setText("");
				Toast.makeText(getApplicationContext(), "Message sent", Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	private void sendSMS(String message, String phone){
		try{
			SmsManager smsMan = SmsManager.getDefault();
			smsMan.sendTextMessage(phone, null, message, null, null);
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			Toast.makeText(this, "Error sending message", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
