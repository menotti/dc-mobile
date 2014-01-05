@SuppressLint("InlinedApi")
private final static String[] PROJECTION = {
	Contacts._ID,
	Contacts.LOOKUP_KEY,
	Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
		Contacts.DISPLAY_NAME_PRIMARY : Contacts.DISPLAY_NAME,
};
private static final int CONTACT_ID_INDEX = 0;
private static final int LOOKUP_KEY_INDEX = 1;

@SuppressLint("InlinedApi")
private static final String SELECTION =
		Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
		Contacts.DISPLAY_NAME_PRIMARY + " LIKE ?" :
		Contacts.DISPLAY_NAME + " LIKE ?";
		
private String mSearchString = "";
private String[] mSelectionArgs = { mSearchString };