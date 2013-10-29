private File getOutputMediaFile(int type){
	String folder =
			getResources().getString(R.string.app_name) + "_PICS";
		
	File mediaStorageDir = 
			new File(Environment.getExternalStoragePublicDirectory(
					Environment.DIRECTORY_PICTURES), folder);
	
	if (! mediaStorageDir.exists()){
	    if (! mediaStorageDir.mkdirs()){
	        Log.d("ERROR", "failed to create directory: " + folder);
	        return null;
	    }
	}
	String timeStamp = 
			new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	
	File mediaFile;
	if (type == MEDIA_TYPE_IMAGE){
	    mediaFile = new File(mediaStorageDir.getPath() + File.separator +
	    "IMG_"+ timeStamp + ".jpg");
	} else if(type == MEDIA_TYPE_VIDEO) {
	    mediaFile = new File(mediaStorageDir.getPath() + File.separator +
	    "VID_"+ timeStamp + ".mp4");
	    } else {
	        return null;
	    }	
	
	    return mediaFile;
	}
