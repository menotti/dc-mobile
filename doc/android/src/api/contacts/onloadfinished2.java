public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
	if(mContactUri == null){
		return;
	}
	switch(loader.getId()){
		case ContactDetailsQuery.QUERY_ID:
			if(cursor.moveToFirst()){
				String contactName = 
					cursor.getString(ContactDetailsQuery.DISPLAY_NAME);
				TextView nameView = new TextView(this);
				nameView.setText(contactName);
				nameView.setTextSize(30);
				container.addView(nameView);
			}
			break;
		case ContactPhoneQuery.QUERY_ID:
			if(cursor.moveToFirst()){
				do {
					String contactPhone = 
						cursor.getString(ContactPhoneQuery.NUMBER);
					TextView phoneView = new TextView(this);
					phoneView.setText(contactPhone);
					phoneView.setTextSize(20);
					container.addView(phoneView);
				} while(cursor.moveToNext());
			}
			break;
		case ContactAddressQuery.QUERY_ID:
			if(cursor.moveToFirst()){
				do {
					String address = 
						cursor.getString(ContactAddressQuery.ADDRESS);
					TextView addrView = new TextView(this);
					addrView.setText(address);
					addrView.setTextSize(15);
					container.addView(addrView);
				} while(cursor.moveToNext());
			}
			break;
	}
}