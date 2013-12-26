final Intent shareIntent = new Intent();
shareIntent.setAction(Intent.ACTION_SEND);

shareText.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		String text = textField.getText().toString();
		shareIntent.setType("text/plain");
		shareIntent.putExtra(Intent.EXTRA_TEXT, text);
		startActivity(Intent.createChooser(shareIntent, "Send to..."));
	}
});

sharePic.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		shareIntent.setType("image/png");
		shareIntent.putExtra(Intent.EXTRA_STREAM, picUri);
		startActivity(Intent.createChooser(shareIntent, "Send to..."));
	}
});