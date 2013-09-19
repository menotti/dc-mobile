saveBtn.setOnClickListener(new OnClickListener() {
			
	@Override
	public void onClick(View v) {

		AlertDialog.Builder builder = 
			new AlertDialog.Builder(MainActivity.this);
		LayoutInflater inflater = getLayoutInflater();
		final View fnameEntry = 
			inflater.inflate(R.layout.save_dialog, null);
		builder.setView(fnameEntry).setTitle("Save as...")
		.setPositiveButton("Save", new DialogInterface.OnClickListener(){
			
		...		