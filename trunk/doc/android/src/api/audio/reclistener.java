mRecordButton.setOnClickListener(new OnClickListener() {
			
	@Override
	public void onClick(View v) {
		if(isRecording){
			recorder.stop();
			releaseRecorder();
			mRecordButton.setText("Record");
			isRecording = false;
		} else {
			if(prepareRecording()){
				recorder.start();
				mRecordButton.setText("Stop");
				isRecording = true;
			} else {
				releaseRecorder();
			}
		}
	}
});