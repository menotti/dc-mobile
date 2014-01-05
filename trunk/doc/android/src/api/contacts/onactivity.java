protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main); 
	
	mContactList = (ListView) findViewById(R.id.contactList);
	mCursorAdapter = new SimpleCursorAdapter(
			this,
			R.layout.list_item,
			null, 
			FROM_COLUMNS, 
			TO_IDS, 0);
	mContactList.setAdapter(mCursorAdapter);
	mContactList.setOnItemClickListener(this);
	
	getSupportLoaderManager().initLoader(0, null, this);
}