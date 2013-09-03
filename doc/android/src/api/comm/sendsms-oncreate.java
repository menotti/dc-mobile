@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	
	Button btnSend = (Button) findViewById(R.id.buttonSend);
	btnSend.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			EditText editMsg = (EditText) findViewById(R.id.editMessage);
			String message = editMsg.getText().toString();
			EditText editPhone = (EditText) findViewById(R.id.editPhone);
			String phone = editPhone.getText().toString();
			
			sendSMS(message, phone);
			
			//Clean text fields and show toast
			editMsg.setText("");
			editPhone.setText("");
			Toast.makeText
			(getApplicationContext(), "Message sent", Toast.LENGTH_SHORT)
				.show();
		}
	});
}