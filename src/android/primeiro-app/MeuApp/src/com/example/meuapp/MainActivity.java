package com.example.meuapp;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint("NewApi")
public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.example.meuapp.EXTRA_MESSAGE"; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
		if(item.getItemId() == R.id.action_settings){
			startActivity(new Intent(this, SettingsActivity.class));
		} else if(item.getItemId() == R.id.action_search){
			LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			EditText search = new EditText(this);
			search.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
			search.setTextColor(Color.WHITE);
			search.setInputType(InputType.TYPE_CLASS_TEXT);
			getActionBar().setCustomView(search, lp);
			search.requestFocus();
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.showSoftInput(search, InputMethodManager.SHOW_IMPLICIT);
			search.setOnEditorActionListener(new OnEditorActionListener() {
				
				@Override
				public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
					Toast.makeText(MainActivity.this, v.getText(), Toast.LENGTH_SHORT).show();
					return true;
				}
			});
		}
		return true;
	}
	
	public void sendMessage(View view){
		Intent intent = new Intent(this, DisplayMessageActivity.class);
		EditText textBox = (EditText) findViewById(R.id.nameField);
		String message = textBox.getText().toString();
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
	}
}
