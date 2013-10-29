private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;

@Override
protected void onActivityResult
		(int requestCode, int resultCode, Intent data) {
		
	if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
		if (resultCode == RESULT_OK) {
			Toast.makeText(this, "Image saved to:\n" +
					fileUri.toString(), Toast.LENGTH_LONG).show();
		} else if (resultCode == RESULT_CANCELED) {
			// Opcional
		} else {
			Toast.makeText(this, "Error capturing image...",
					Toast.LENGTH_LONG).show();
		}
	}

	if (requestCode == CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE) {
		if (resultCode == RESULT_OK) {
			Toast.makeText(this, "Video saved to:\n" +
					fileUri.toString(), Toast.LENGTH_LONG).show();
		} else if (resultCode == RESULT_CANCELED) {
			// Opcional
		} else {
			Toast.makeText(this, "Error recording video...", 
					Toast.LENGTH_LONG).show();
		}
	}
}