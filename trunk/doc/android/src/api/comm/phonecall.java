@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	
	Button call = (Button) findViewById(R.id.button1);
	call.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			EditText tv = (EditText) findViewById(R.id.editNumber);
			String phone = tv.getText().toString();
			
			Intent callIntent = new Intent(Intent.ACTION_CALL);
			callIntent.setData(Uri.parse("tel:" + phone));
			startActivity(callIntent);
		}
	});
}