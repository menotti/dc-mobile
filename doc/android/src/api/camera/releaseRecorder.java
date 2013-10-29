private void releaseMediaRecorder() {
	if(recorder != null){
		recorder.reset();
		recorder.release();
		recorder = null;
		mCam.lock();
	}
}
