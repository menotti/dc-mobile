openBtn.setOnClickListener(new OnClickListener() {
			
	@Override
	public void onClick(View v) {
		
		AlertDialog.Builder builder =
			new AlertDialog.Builder(MainActivity.this);
			
		builder.setTitle("Choose a File")
		.setItems(fileList(), new DialogInterface.OnClickListener() {
			
					...
		});
		
		builder.create().show();
	}
});