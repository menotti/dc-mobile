package com.example.swipeabletabs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

public class SwipeTabActivity extends FragmentActivity implements OnTabChangeListener, OnPageChangeListener{
	private TabHost mTabHost;
	private HashMap<String, TabInfo> mapTabInfo = new HashMap<String, TabInfo>();
	private PagerAdapter mPageAdapter;
	private ViewPager mViewPager;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_swipe_tab);
		
		initialiseTabHost(savedInstanceState);
		initialiseViewPager();
	}
	
	private void initialiseViewPager(){
		List<Fragment> fragments = new ArrayList<Fragment>();
		fragments.add(Fragment.instantiate(this, Tab1Fragment.class.getName()));
		fragments.add(Fragment.instantiate(this, Tab2Fragment.class.getName()));
		fragments.add(Fragment.instantiate(this, Tab3Fragment.class.getName()));
		
		mPageAdapter = new PagerAdapter(getSupportFragmentManager(), fragments);
		
		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		mViewPager.setAdapter(mPageAdapter);
		mViewPager.setOnPageChangeListener(this);
	}

	private void initialiseTabHost(Bundle args) {
		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup();
		
		TabInfo tabInfo = null;
		TabHost.TabSpec tabSpec;
		
		//Cria Tab1
		tabSpec = mTabHost.newTabSpec("Tab1");
		tabSpec.setIndicator("Tab 1");
		tabInfo = new TabInfo("Tab1", Tab1Fragment.class, args);
		tabSpec.setContent(new TabFactory(this));
		mTabHost.addTab(tabSpec);
		mapTabInfo.put(tabInfo.tag, tabInfo);
		
		//Cria Tab2
		tabSpec = mTabHost.newTabSpec("Tab2");
		tabSpec.setIndicator("Tab 2");
		tabInfo = new TabInfo("Tab2", Tab2Fragment.class, args);
		tabSpec.setContent(new TabFactory(this));
		mTabHost.addTab(tabSpec);
		mapTabInfo.put(tabInfo.tag, tabInfo);
		
		//Cria Tab3
		tabSpec = mTabHost.newTabSpec("Tab3");
		tabSpec.setIndicator("Tab 3");
		tabInfo = new TabInfo("Tab3", Tab3Fragment.class, args);
		tabSpec.setContent(new TabFactory(this));
		mTabHost.addTab(tabSpec);
		mapTabInfo.put(tabInfo.tag, tabInfo);
		
		mTabHost.setOnTabChangedListener(this);	
	}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.swipe_tab, menu);
		return true;
	}
	
	//protected void onSaveInstanceState(Bundle outState){
	//	outState.putString("tab", mTabHost.getCurrentTabTag());
	//	super.onSaveInstanceState(outState);
	//}

	@Override
	public void onTabChanged(String tag) {
		int pos = mTabHost.getCurrentTab();
		mViewPager.setCurrentItem(pos);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		//Nada	
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		//Nada
	}

	@Override
	public void onPageSelected(int position) {
		mTabHost.setCurrentTab(position);
	}
}
