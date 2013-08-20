public class ViewPagerLayout extends FragmentActivity {
	private PagerAdapter mPageAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewpager_layout);
		
		List<Fragment> fragments = new ArrayList<Fragment>();
		fragments.add(Fragment.instantiate
						(this, Tab1Fragment.class.getName()));
		fragments.add(Fragment.instantiate
						(this, Tab2Fragment.class.getName()));
		fragments.add(Fragment.instantiate
						(this, Tab3Fragment.class.getName()));
		
		mPageAdapter = new PagerAdapter(getSupportFragmentManager(), fragments);
		
		ViewPager pager = (ViewPager) findViewById(R.id.viewpager);
		pager.setAdapter(mPageAdapter);
	}
...
}