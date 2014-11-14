...
btnSave.setOnClickListener(new OnClickListener() {
			
	@Override
	public void onClick(View v) {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		LayoutInflater inflater = getLayoutInflater();
		final View fnameEntry = inflater.inflate(R.layout.save_dialog, null);
		builder.setView(fnameEntry).setTitle("Save as...")
		.setPositiveButton("Save", new DialogInterface.OnClickListener() {
					
			@Override
			public void onClick(DialogInterface dialog, int which) {
				EditText fnameEt = (EditText)fnameEntry.findViewById(R.id.saveas);
				String fname = fnameEt.getText().toString();
				if(fname == "")
					fname = "untitled";
				String text = inputText.getText().toString();
						
				try {
					FileOutputStream fos = openFileOutput(fname, MODE_PRIVATE);
					fos.write(text.getBytes());
					fos.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
						
				Toast.makeText(getApplicationContext(), 
					fname + " saved", Toast.LENGTH_SHORT).show();
			}
		}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
				
		builder.create().show();		
	}
});

...