public void setContact(Uri contactUri, long contactId){
	if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
		mContactUri = contactUri;
	} else {
		mContactUri = Contacts.lookupContact
						(getContentResolver(), contactUri);
	}
	mContactId = contactId;
	
	getSupportLoaderManager().
			restartLoader(ContactDetailsQuery.QUERY_ID, null, this);
	getSupportLoaderManager().
			restartLoader(ContactAddressQuery.QUERY_ID, null, this);
	if(hasPhone())
		getSupportLoaderManager().
				restartLoader(ContactPhoneQuery.QUERY_ID, null, this);	
}