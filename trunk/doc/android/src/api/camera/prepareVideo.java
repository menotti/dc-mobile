private boolean prepareForRecording() {
	recorder = new MediaRecorder();
	mCam.unlock();
	recorder.setCamera(mCam);
	
	recorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
	recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);

	CamcorderProfile profile =
			CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH);
	recorder.setProfile(profile);
	
	recorder.setOutputFile(getOutputMediaFile(MEDIA_TYPE_VIDEO).toString());
	recorder.setPreviewDisplay(mPreview.getHolder().getSurface());
	
	try{
		recorder.prepare();
	} catch (IllegalStateException e){
		releaseMediaRecorder();
		return false;
	} catch (IOException e) {
		releaseMediaRecorder();
		return false;
	}
	
	return true;
}
