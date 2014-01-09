public interface ContactDetailsQuery {
	final static int QUERY_ID = 1;
	@SuppressLint("InlinedApi")
	final static String[] PROJECTION = {
		Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
				Contacts.DISPLAY_NAME_PRIMARY : Contacts.DISPLAY_NAME,
	};
	final static int DISPLAY_NAME = 0;
}

public interface ContactPhoneQuery {
	final static int QUERY_ID = 2;
	final static String[] PROJECTION = {
		Phone.NUMBER
	};	
	final static String SELECTION =
		Phone.CONTACT_ID + " = ?";
	
	final static int NUMBER = 0;
}

public interface ContactAddressQuery {
	final static int QUERY_ID = 3;
	final static String[] PROJECTION = {
		StructuredPostal.FORMATTED_ADDRESS
	};
	final static String SELECTION =
		StructuredPostal.CONTACT_ID + " = ?";
	
	final static int ADDRESS = 0;
}