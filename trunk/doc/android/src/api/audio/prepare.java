private boolean prepareRecording(){
	recorder = new MediaRecorder();
	recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
	recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
	recorder.setOutputFile(testFilename);
	recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
	
	try{
		recorder.prepare();
	} catch (IOException e) {
		Log.e("AudioRecorder", "prepareRecording() failed");
		return false;
	}
	return true;
}