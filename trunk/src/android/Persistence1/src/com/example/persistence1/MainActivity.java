package com.example.persistence1;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;

public class MainActivity extends Activity {
	private EditText mEditText;
	private SeekBar mSeekBar;
	private Switch mSwitch;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mEditText = (EditText) findViewById(R.id.editText1);
		mSeekBar = (SeekBar) findViewById(R.id.seekBar1);
		mSwitch = (Switch) findViewById(R.id.switch1);
		
		SharedPreferences prefs = getPreferences(MODE_PRIVATE);
		String text = prefs.getString("text", "");
		int progress = prefs.getInt("seek", 0);
		boolean checked = prefs.getBoolean("switch", false);
		
		mEditText.setText(text);
		mSeekBar.setProgress(progress);
		mSwitch.setChecked(checked);
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		
		SharedPreferences prefs = getPreferences(MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		
		editor.putString("text", mEditText.getText().toString());
		editor.putInt("seek", mSeekBar.getProgress());
		editor.putBoolean("switch", mSwitch.isChecked());
		editor.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
