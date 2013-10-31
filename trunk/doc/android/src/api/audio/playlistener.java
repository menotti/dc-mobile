mPlayButton.setOnClickListener(new OnClickListener() {
		
	@Override
	public void onClick(View v) {
		if(isPlaying){
			stopPlaying();
			isPlaying = false;
			mPlayButton.setText("Play");
		} else {
			startPlaying();
			isPlaying = true;
			mPlayButton.setText("Stop");
		}
	}
});