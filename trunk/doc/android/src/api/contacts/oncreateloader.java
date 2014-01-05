@Override
public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
	mSelectionArgs[0] = "%" + mSearchString + "%";
	return new CursorLoader(
			getActivity(),
			Contacts.CONTENT_URI,
			PROJECTION,
			SELECTION,
			mSelectionArgs,
			null);
}
