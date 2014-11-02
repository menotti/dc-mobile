package com.example.tabhostrelativelayout;

import java.util.HashMap;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.TabHost;

public class TabLayoutActivity extends FragmentActivity implements 
				TabHost.OnTabChangeListener {
	private TabHost mTabHost;
	private HashMap<String, TabInfo> mapTabInfo = 
		new HashMap<String, TabInfo>();
	private TabInfo mLastTab = null;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab_layout);
		
		initialiseTabHost(savedInstanceState);
		if(savedInstanceState != null){
			mTabHost.setCurrentTabByTag(
				savedInstanceState.getString("tab"));
		}
	}
}