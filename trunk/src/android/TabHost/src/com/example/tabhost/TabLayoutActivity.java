package com.example.tabhost;

import java.util.HashMap;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.TabHost;

public class TabLayoutActivity extends FragmentActivity implements TabHost.OnTabChangeListener {
	private TabHost mTabHost;
	private HashMap<String, TabInfo> mapTabInfo = new HashMap<String, TabInfo>();
	private TabInfo mLastTab = null;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab_layout);
		
		initialiseTabHost(savedInstanceState);
		if(savedInstanceState != null){
			mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
		}
	}

	private void initialiseTabHost(Bundle args) {
		mTabHost = (TabHost)findViewById(android.R.id.tabhost);
		mTabHost.setup();
		
		TabInfo tabInfo = null;
		String tag;
		TabHost.TabSpec tabSpec;
		
		//Cria Tab1
		tabSpec = mTabHost.newTabSpec("Tab1");
		tabSpec.setIndicator("Tab 1");
		tabInfo = new TabInfo("Tab1", Tab1Fragment.class, args);
		tabSpec.setContent(new TabFactory(this));
		tag = tabSpec.getTag();
		tabInfo.fragment = this.getSupportFragmentManager().findFragmentByTag(tag);
		mTabHost.addTab(tabSpec);
		mapTabInfo.put(tabInfo.tag, tabInfo);
		
		//Cria Tab2
		tabSpec = mTabHost.newTabSpec("Tab2");
		tabSpec.setIndicator("Tab 2");
		tabInfo = new TabInfo("Tab2", Tab2Fragment.class, args);
		tabSpec.setContent(new TabFactory(this));
		tag = tabSpec.getTag();
		tabInfo.fragment = this.getSupportFragmentManager().findFragmentByTag(tag);
		mTabHost.addTab(tabSpec);
		mapTabInfo.put(tabInfo.tag, tabInfo);
		
		//Cria Tab3
		tabSpec = mTabHost.newTabSpec("Tab3");
		tabSpec.setIndicator("Tab 3");
		tabInfo = new TabInfo("Tab3", Tab3Fragment.class, args);
		tabSpec.setContent(new TabFactory(this));
		tag = tabSpec.getTag();
		tabInfo.fragment = this.getSupportFragmentManager().findFragmentByTag(tag);
		mTabHost.addTab(tabSpec);
		
		//addTab(this, mTabHost, tabSpec, tabInfo);
		mapTabInfo.put(tabInfo.tag, tabInfo);
		
		
		//Primeira aba como default
		this.onTabChanged("Tab1");
		mTabHost.setOnTabChangedListener(this);
		
	}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tab_layout, menu);
		return true;
	}
	
	protected void onSaveInstanceState(Bundle outState){
		outState.putString("tab", mTabHost.getCurrentTabTag());
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onTabChanged(String tag) {
		TabInfo newTab = mapTabInfo.get(tag);
		
		if(mLastTab != newTab){
			FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
			if(mLastTab != null){
				if(mLastTab.fragment != null){
					ft.detach(mLastTab.fragment);
				}
			}
			if(newTab != null){
				if(newTab.fragment == null){
					//Fragmento nao foi instanciado ainda
					newTab.fragment = Fragment.instantiate(this,
							newTab.klass.getName(), newTab.args);
					ft.add(android.R.id.tabcontent, newTab.fragment, newTab.tag);
				} else {
					//Fragmento ja foi instanciado
					ft.attach(newTab.fragment);
				}
			}
			mLastTab = newTab;
			ft.commit();
			this.getSupportFragmentManager().executePendingTransactions();
		}
	}

}
