public class CameraActivity extends Activity {
	private Camera mCam;
	private CameraPreview mPreview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera);
		
		CameraAccess ca = new CameraAccess(this);
		mCam = ca.getCameraInstance();
		
		mPreview = new CameraPreview(this, mCam);
		FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
		preview.addView(mPreview);
	}