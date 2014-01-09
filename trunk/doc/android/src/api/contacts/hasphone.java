public boolean hasPhone(){
	String projection[] = { Phone.HAS_PHONE_NUMBER };
	String selection = Phone._ID + " = ?";
	String selectionArgs[] = { mContactUri.getLastPathSegment() };
	int hasPhone_index = 0;
	Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
			projection, selection, selectionArgs, null);
	
	if(cursor.moveToFirst()){
		int has = Integer.parseInt(cursor.getString(hasPhone_index));
		return has > 0;
	}
	return false;
}