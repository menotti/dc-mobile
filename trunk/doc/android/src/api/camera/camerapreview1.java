public class CameraPreview extends SurfaceView implements Callback {
	private SurfaceHolder mHolder;
	private Camera mCamera;
	
	public CameraPreview(Context c, Camera cam) {
		super(c);
		mCamera = cam;
		
		mHolder = getHolder();
		mHolder.addCallback(this);
		mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		try{
			mCamera.setPreviewDisplay(holder);
			mCamera.startPreview();
		} catch (IOException e) {
			Log.d("CAM", "Error setting camera preview: " + e.getMessage());
		}
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		if(mHolder.getSurface() == null){
			return;
		}
		
		try {
			mCamera.stopPreview();
		} catch (Exception e) {
			// Irrelevante
		}
		
		try{
			mCamera.setPreviewDisplay(mHolder);
			mCamera.startPreview();
		} catch (Exception e) {
			Log.d("CAM", "Error setting camera preview: " + e.getMessage());
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// Nada, tomar conta de liberar a camera na activity
	}
}