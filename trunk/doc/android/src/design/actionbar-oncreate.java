@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	
	getActionBar().setDisplayOptions
		(ActionBar.DISPLAY_SHOW_CUSTOM | 
		 ActionBar.DISPLAY_SHOW_HOME);
}