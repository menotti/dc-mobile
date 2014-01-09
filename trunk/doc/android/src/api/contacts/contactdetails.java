public class ContactDetailsActivity extends FragmentActivity 
implements 	LoaderCallbacks<Cursor> {
	private Uri mContactUri;
	private long mContactId;
	private ViewGroup container;