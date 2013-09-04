@Override
public void onTabChanged(String tag) {
	TabInfo newTab = mapTabInfo.get(tag);
	
	if(mLastTab != newTab){
		FragmentTransaction ft = 
			getSupportFragmentManager().beginTransaction();
			
		if(mLastTab != null){
			if(mLastTab.fragment != null){
				ft.detach(mLastTab.fragment);
			}
		}
		
		if(newTab != null){
			if(newTab.fragment == null){
				newTab.fragment = Fragment.instantiate(this,
						newTab.klass.getName(), newTab.args);
				ft.add(android.R.id.tabcontent, newTab.fragment, newTab.tag);
			} else {
				ft.attach(newTab.fragment);
			}
		}
		mLastTab = newTab;
		ft.commit();
		getSupportFragmentManager().
				executePendingTransactions();
	}
}