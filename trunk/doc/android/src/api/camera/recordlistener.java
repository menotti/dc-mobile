mRecord = (Button) findViewById(R.id.button_record);
mRecord.setOnClickListener(new OnClickListener() {
			
	@Override
	public void onClick(View v) {
		if(isRecording){
			recorder.stop();
			releaseMediaRecorder();
			((Button) v).setText("Record");
			isRecording = false;	 			
		} else {
			if(prepareForRecording()) {
				recorder.start();
				((Button) v).setText("Stop");
				isRecording = true;
			} else {
				releaseMediaRecorder();
			}
		}	
	}
});