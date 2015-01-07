package com.example.persistence2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("NewApi")
public class MainActivity extends Activity {
	private EditText textEt;
	private Button saveBtn;
	private Button openBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		textEt = (EditText) findViewById(R.id.editText2);
		saveBtn = (Button) findViewById(R.id.button2);
		openBtn = (Button) findViewById(R.id.button1);
		
		saveBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
		
				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
				LayoutInflater inflater = getLayoutInflater();
				final View fnameEntry = inflater.inflate(R.layout.save_dialog, null);
				builder.setView(fnameEntry).setTitle("Save as...")
				.setPositiveButton("Save", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						EditText fnameEt = (EditText)fnameEntry.findViewById(R.id.saveas);
						String fname = fnameEt.getText().toString();
						if(fname.isEmpty())
							fname = "untitled";
						String text = textEt.getText().toString();
						
						try {
							FileOutputStream fos = openFileOutput(fname, MODE_PRIVATE);
							fos.write(text.getBytes());
							fos.close();
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
						
						Toast.makeText(getApplicationContext(), fname + " saved", Toast.LENGTH_SHORT).show();
					}
				}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});
				
				builder.create().show();
				
			}
		});
		
		openBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
				builder.setTitle("Choose a File").setItems(fileList(), new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						try {	
							File f = getFilesDir().listFiles()[which];
							BufferedReader in = new BufferedReader(new FileReader(f));
							StringBuilder text = new StringBuilder();
							
							try{
								String line = null;
								while ((line = in.readLine()) != null){
									text.append(line);
									text.append(System.getProperty("line.separator"));
								}
							} finally {
								in.close();
							}
							
							textEt.setText(text);
							in.close();
						} catch(FileNotFoundException e){
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				});
				
				builder.create().show();
			}
		});
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
