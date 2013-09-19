package com.example.swipeabletabs;

import android.content.Context;
import android.view.View;
import android.widget.TabHost.TabContentFactory;

public class TabFactory implements TabContentFactory{
	private final Context mContext;

	public TabFactory(Context context){
		mContext = context;
	}
	
	@Override
	public View createTabContent(String tag) {
		View v = new View(mContext);
		v.setMinimumHeight(0);
		v.setMinimumWidth(0);
		return v;
	}
}
