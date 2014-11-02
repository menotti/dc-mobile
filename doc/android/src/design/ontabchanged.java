public void onTabChanged(String tag) {
		TabInfo newTab = mapTabInfo.get(tag);
		
		if(mLastTab != newTab){
			FragmentTransaction ft = 
				this.getSupportFragmentManager().beginTransaction();
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