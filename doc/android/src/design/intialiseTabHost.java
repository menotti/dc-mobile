private void initialiseTabHost(Bundle args) {
	mTabHost = (TabHost)findViewById(android.R.id.tabhost);
	mTabHost.setup();
	TabInfo tabInfo = null;
	String tag;
	
	//Cria Tab1
	tabSpec = mTabHost.newTabSpec("Tab1");
	tabSpec.setIndicator("Tab 1");
	tabSpec.setContent(new TabFactory(this));
	tag = tabSpec.getTag();
	tabInfo = new TabInfo("Tab1", Tab1Fragment.class, args);
	tabInfo.fragment = getSupportFragmentManager().
									findFragmentByTag(tag);
	mTabHost.addTab(tabSpec);
	mapTabInfo.put(tabInfo.tag, tabInfo);
	/* Repete para Tab2 e Tab3 */
	
	//Ajusta primeira aba como default
	onTabChanged("Tab1");
	mTabHost.setOnTabChangedListener(this);
}