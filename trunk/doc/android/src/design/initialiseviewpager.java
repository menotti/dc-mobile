private void initialiseViewPager(){
	List<Fragment> fragments = new ArrayList<Fragment>();
	fragments.add(Fragment.instantiate
					(this, Tab1Fragment.class.getName()));
	fragments.add(Fragment.instantiate
					(this, Tab2Fragment.class.getName()));
	fragments.add(Fragment.instantiate
					(this, Tab3Fragment.class.getName()));
	
	mPageAdapter = new PagerAdapter(getSupportFragmentManager(), fragments);
	
	mViewPager = (ViewPager) findViewById(R.id.viewpager);
	mViewPager.setAdapter(mPageAdapter);
	mViewPager.setOnPageChangeListener(this);
}