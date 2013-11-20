public List<String> fetchCarList(){
	SQLiteDatabase mSQLite = mDatabase.getReadableDatabase();
	String[] column = {"Model"};
	Cursor cursor =
		mSQLite.query("Cars", column, 
		"Year='"+selYear+"' AND Manufacturer='"+selManufacturer + "'",
		null, null, null, null, null);
	
	List<String> list1 = new ArrayList<String>();
	while(cursor.moveToNext()){
		list1.add(cursor.getString(0));
	}
	
	return list1;
	}