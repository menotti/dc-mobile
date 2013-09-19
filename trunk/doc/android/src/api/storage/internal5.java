...
.setItems(fileList(), new DialogInterface.OnClickListener() {
			
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
			
			textEt.setText(text);
			in.close();
		} catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
});
...