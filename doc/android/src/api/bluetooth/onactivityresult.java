@Override
protected void onActivityResult(int requestCode,
						int resultCode, Intent data) {
	super.onActivityResult(requestCode, resultCode, data);
	if(requestCode == RESULT_OK){
		Toast.makeText(this, "Bluetooth is on", Toast.LENGTH_LONG)
		.show();
	} else if(requestCode == RESULT_CANCELED){
		Toast.makeText(this, "Bluetooth is off", Toast.LENGTH_LONG)
		.show();
	}
}