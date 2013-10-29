package com.example.persistence3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends Activity {
	private Spinner mSpManufacturer;
	private Spinner mSpYear;
	private Button mBtnSearch;
	private CarOpenHelper mDatabase;
	private String selManufacturer;
	private String selYear;
	
	public final static String CARLIST = "com.example.persistence3.carlist";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
				
		mSpManufacturer = (Spinner) findViewById(R.id.spinner1);
		mSpYear = (Spinner) findViewById(R.id.spinner2);
		mBtnSearch = (Button) findViewById(R.id.button1);
		
		mDatabase = new CarOpenHelper(this);
		String[] mColumns = {"Manufacturer", "Year"};
		
		SQLiteDatabase mSQLite = mDatabase.getReadableDatabase();
		Cursor cursor = mSQLite.query("Cars", mColumns, null, null, null, null, null);
		
		Set<String> set1 = new HashSet<String>();
		Set<String> set2 = new HashSet<String>();
		while(cursor.moveToNext()){
			set1.add(cursor.getString(0));
			set2.add(cursor.getString(1));
		}
		List<String> list1 = new ArrayList<String>(set1);
		List<String> list2 = new ArrayList<String>(set2);
		
		ArrayAdapter<String> manuAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list1);
		manuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mSpManufacturer.setAdapter(manuAdapter);
		
		ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list2);
		manuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mSpYear.setAdapter(yearAdapter);
		
		mSpManufacturer.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				selManufacturer = mSpManufacturer.getSelectedItem().toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});		
		
		mSpYear.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				selYear = mSpYear.getSelectedItem().toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		
		//Let's open a new activity with the car we found
		mBtnSearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				openCarList();
			}
		});
	}
	
	public void openCarList(){
		Intent intent = new Intent(this, CarDetailActivity.class);
		List<String> list = fetchCarList();
		String[] carList = list.toArray(new String[list.size()]);
		intent.putExtra(CARLIST, carList);
		startActivity(intent);
	}
	
	public List<String> fetchCarList(){
		SQLiteDatabase mSQLite = mDatabase.getReadableDatabase();
		String[] column = {"Model"};
		Cursor cursor = mSQLite.query("Cars", column, "Year='"+selYear+"' AND Manufacturer='"+selManufacturer + "'", null, null, null, null, null);
		
		List<String> list1 = new ArrayList<String>();
		while(cursor.moveToNext()){
			list1.add(cursor.getString(0));
		}
		
		return list1;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
 
