public void writeMessage() {
	final EditText input = new EditText(this);
	AlertDialog.Builder dialog = new AlertDialog.Builder(this)
		.setTitle("Send a message")
		.setView(input)
		.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String msg = input.getText().toString();
				try{
					tConnected.write(msg.getBytes());
				} catch (NullPointerException e) {}
			}
		}
	});
	dialog
	.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
	}).show();
}