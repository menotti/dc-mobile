mRecord = (Button) findViewById(R.id.button_record);
mRecord.setOnClickListener(new OnClickListener() {
			
	@Override
	public void onClick(View v) {
		if(isRecording){
			recorder.stop();
			releaseMediaRecorder();
			setRecordButtonText("Record");
			isRecording = false;	 			
		} else {
			if(prepareForRecording()) {
				recorder.start();
				setRecordButtonText("Stop");
				isRecording = true;
			} else {
				releaseMediaRecorder();
			}
		}	
	}
});