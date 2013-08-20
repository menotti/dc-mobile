@Override
public void onTabChanged(String tag) {
	int pos = mTabHost.getCurrentTab();
	mViewPager.setCurrentItem(pos);
}