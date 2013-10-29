package com.example.persistence3;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CarDetailActivity extends Activity {
	private ListView lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_car_detail);
		
		String carList[] = getIntent().getStringArrayExtra(MainActivity.CARLIST);
		
		for(String s: carList){
			System.out.println(s);
		}
		
		ListView lv = (ListView) findViewById(R.id.listView1);
		
		lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, carList));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.car_detail, menu);
		return true;
	}

}
