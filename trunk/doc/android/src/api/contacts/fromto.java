@SuppressLint("InlinedApi")
private final static String[] FROM_COLUMNS = 
		{Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
			Contacts.DISPLAY_NAME_PRIMARY : Contacts.DISPLAY_NAME };
private final static int[] TO_IDS = {android.R.id.text1};