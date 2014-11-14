...
btnOpen.setOnClickListener(new OnClickListener() {
			
	@Override
	public void onClick(View v) {
				
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		builder.setTitle("Choose a File").setItems(fileList(), 
									new DialogInterface.OnClickListener() {
					
			@Override
			public void onClick(DialogInterface dialog, int which) {
						
				try {	
					File f = getFilesDir().listFiles()[which];
					BufferedReader in = new BufferedReader(new FileReader(f));
					StringBuilder text = new StringBuilder();
							
					try{
						String line = null;
						while ((line = in.readLine()) != null){
							text.append(line);
							text.append(System.getProperty("line.separator"));
						}
					} finally {
						in.close();
					}
							
					outputText.setText(text);
					in.close();
				} catch(FileNotFoundException e){
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
				
		builder.create().show();
	}
});
}