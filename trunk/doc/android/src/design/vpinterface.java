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