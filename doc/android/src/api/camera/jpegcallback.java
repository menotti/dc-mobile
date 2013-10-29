PictureCallback mJPEGCallback = new PictureCallback() {
	
	@Override
	public void onPictureTaken(byte[] data, Camera camera) {
		File picFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
		if(picFile == null){
			Log.d("ERROR",
			"Error creating media file, check storage permissions");
			return;
		}
		
		try {
			FileOutputStream fos = new FileOutputStream(picFile);
			fos.write(data);
			fos.close();
		} catch(FileNotFoundException e) {
			Log.d("ERROR", "File not found: " + e.getMessage());
		} catch (IOException e){
			Log.d("ERROR", "Error accessing file: " + e.getMessage());
		}
	}
};
