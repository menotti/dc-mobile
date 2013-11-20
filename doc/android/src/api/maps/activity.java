public class MainActivity extends FragmentActivity {
	private LatLng mLocation;
	private TextView latTv;
	private TextView longTv;
	private FrameLayout mapFrame;
	
	private static final String MAP_FRAGMENT_TAG = "map";
	private GoogleMap mMap;
	private SupportMapFragment mMapFragment;
	private Marker marker;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		LocationManager mlocManager = 
			(LocationManager) getSystemService(Context.LOCATION_SERVICE);
		MyLocationListener mlocListener = new MyLocationListener();
		mlocManager.requestLocationUpdates
			(LocationManager.GPS_PROVIDER, 0, 0, mlocListener);
		
		latTv = (TextView) findViewById(R.id.latitude);
		longTv = (TextView) findViewById(R.id.longitude);
		
	    mMapFragment = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentByTag(MAP_FRAGMENT_TAG);


		if (mMapFragment == null) {
			mMapFragment = SupportMapFragment.newInstance();

			FragmentTransaction fragmentTransaction =
					getSupportFragmentManager().beginTransaction();
			fragmentTransaction.add
				(R.id.mapFrame, mMapFragment, MAP_FRAGMENT_TAG);
			fragmentTransaction.commit();
		}
		
		setUpMapIfNeeded();
	}