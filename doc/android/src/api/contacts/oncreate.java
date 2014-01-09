@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_contact_details);
	
	Intent intent = getIntent();
	Uri u = intent.getParcelableExtra("URI");
	long i = intent.getLongExtra("ID", 0);
	setContact(u, i);
	
	container = (ViewGroup) findViewById(R.id.viewgroup);
	
	getSupportLoaderManager().initLoader(0, null, this);
}