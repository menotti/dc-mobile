@Override
protected void onPause() {
	super.onPause();
	releaseMediaRecorder(); 
	if(mCam != null){
		mCam.release();
		mCam = null;
	}
}