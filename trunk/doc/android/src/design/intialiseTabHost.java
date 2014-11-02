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
		tabInfo.fragment = 
			this.getSupportFragmentManager().findFragmentByTag(tag);
		mTabHost.addTab(tabSpec);
		mapTabInfo.put(tabInfo.tag, tabInfo);
		
		//Cria Tab2
		tabSpec = mTabHost.newTabSpec("Tab2");
		tabSpec.setIndicator("Tab 2");
		tabInfo = new TabInfo("Tab2", Tab2Fragment.class, args);
		tabSpec.setContent(new TabFactory(this));
		tag = tabSpec.getTag();
		tabInfo.fragment = 
			this.getSupportFragmentManager().findFragmentByTag(tag);
		mTabHost.addTab(tabSpec);
		mapTabInfo.put(tabInfo.tag, tabInfo);
		
		//Cria Tab3
		tabSpec = mTabHost.newTabSpec("Tab3");
		tabSpec.setIndicator("Tab 3");
		tabInfo = new TabInfo("Tab3", Tab3Fragment.class, args);
		tabSpec.setContent(new TabFactory(this));
		tag = tabSpec.getTag();
		tabInfo.fragment = 
			this.getSupportFragmentManager().findFragmentByTag(tag);
		mTabHost.addTab(tabSpec);
		
		//addTab(this, mTabHost, tabSpec, tabInfo);
		mapTabInfo.put(tabInfo.tag, tabInfo);
		
		
		//Primeira aba como default
		this.onTabChanged("Tab1");
		mTabHost.setOnTabChangedListener(this);
		
	}