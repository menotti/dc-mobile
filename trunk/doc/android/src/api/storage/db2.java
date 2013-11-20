@Override
protected void onCreate(Bundle savedInstanceBundle){
	...
	
	mDatabase = new CarOpenHelper(this);
	String[] mColumns = {"Manufacturer", "Year"};
	
	SQLiteDatabase mSQLite = mDatabase.getReadableDatabase();
	Cursor cursor = 
		mSQLite.query("Cars", mColumns, null, null, null, null, null);
	
	Set<String> set1 = new HashSet<String>();
	Set<String> set2 = new HashSet<String>();
	while(cursor.moveToNext()){
		set1.add(cursor.getString(0));
		set2.add(cursor.getString(1));
	}
	List<String> list1 = new ArrayList<String>(set1);
	List<String> list2 = new ArrayList<String>(set2);
	
	ArrayAdapter<String> manuAdapter = 
		new ArrayAdapter<String>
		(this, android.R.layout.simple_spinner_item, list1);
	manuAdapter.
		setDropDownViewResource
		(android.R.layout.simple_spinner_dropdown_item);
	mSpManufacturer.setAdapter(manuAdapter);
	
	ArrayAdapter<String> yearAdapter =
		new ArrayAdapter<String>
		(this, android.R.layout.simple_spinner_item, list2);
	manuAdapter.
		setDropDownViewResource
		(android.R.layout.simple_spinner_dropdown_item);
	mSpYear.setAdapter(yearAdapter);