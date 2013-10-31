private void startPlaying(){
	player = new MediaPlayer();
	
	player.setOnCompletionListener(new OnCompletionListener() {
		
		@Override
		public void onCompletion(MediaPlayer mp) {
			mPlayButton.setText("Play");
			stopPlaying();
			isPlaying = false;
		}
	});
	
	try{
		player.setDataSource(testFilename);
		player.prepare();
		player.start();
	} catch (IOException e){
		Log.e("AudioRecorder", "file not found");
	}
}