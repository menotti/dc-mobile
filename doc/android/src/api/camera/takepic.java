mCapture = (Button) findViewById(R.id.button_capture);
mCapture.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		mCam.takePicture(null, null, mJPEGCallback);
	}
});