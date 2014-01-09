@Override
public Loader<Cursor> onCreateLoader(int id, Bundle arg1) {
	switch (id) {
	case ContactDetailsQuery.QUERY_ID:
		return new CursorLoader(this, 
				mContactUri,
				ContactDetailsQuery.PROJECTION,
				null, null, null);
	case ContactAddressQuery.QUERY_ID:
		String addressArgs[] = {String.valueOf(mContactId)};
		return new CursorLoader(this,
				StructuredPostal.CONTENT_URI,
				ContactAddressQuery.PROJECTION,
				ContactAddressQuery.SELECTION,
				addressArgs, null);
	case ContactPhoneQuery.QUERY_ID:
		String phoneArgs[] = { String.valueOf(mContactId) };
		return new CursorLoader(this,
				Phone.CONTENT_URI,
				ContactPhoneQuery.PROJECTION,
				ContactPhoneQuery.SELECTION,
				phoneArgs, null);
	default:
		break;
	}
	return null;
}