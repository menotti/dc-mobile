Intent intent = getIntent();
String action = intent.getAction();
String type = intent.getType();

if(Intent.ACTION_SEND.equals(action) && type != null){
	if("text/plain".equals(type)){
		String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
		if(sharedText != null)
			sharedTextView.setText(sharedText);
	} else if (type.startsWith("image/")) {
		Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
		if(imageUri != null){
			picView.setImageBitmap
			(decodeSampledBitmapFromResource(
				getResources(), imageUri.getPath(), 200, 200));
		}
	}
}