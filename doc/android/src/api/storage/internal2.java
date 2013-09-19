...
.setPositiveButton("Save", new DialogInterface.OnClickListener() {
					
	@Override
	public void onClick(DialogInterface dialog, int which) {
		EditText fnameEt = (EditText)fnameEntry.findViewById(R.id.saveas);
		String fname = fnameEt.getText().toString();
		if(fname.isEmpty())
			fname = "untitled";
		String text = textEt.getText().toString();
		
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
}).setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
...