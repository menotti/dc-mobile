public void sendMessage(View view) {
	Intent intent = new Intent(this, DisplayMessageActivity.class);
	EditText textBox = (EditText) findViewById(R.id.nameField);
	String message = textBox.getText().toString();
	intent.putExtra(EXTRA_MESSAGE, message);
	startActivity(intent);
}