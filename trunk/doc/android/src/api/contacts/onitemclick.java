public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {	
		Cursor cursor = mCursorAdapter.getCursor();
		cursor.moveToPosition(position);
		mContactId =  cursor.getLong(CONTACT_ID_INDEX);
		mContactKey = cursor.getString(LOOKUP_KEY_INDEX);
		
		mContactUri = Contacts.getLookupUri(mContactId, mContactKey);
		Intent intent = new Intent(this, ContactDetailsActivity.class);
		intent.putExtra("URI", mContactUri);
		startActivity(intent);
	}