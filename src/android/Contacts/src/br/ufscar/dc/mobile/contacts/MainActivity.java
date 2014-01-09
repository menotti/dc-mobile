package br.ufscar.dc.mobile.contacts;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends FragmentActivity implements
LoaderCallbacks<Cursor>, OnItemClickListener{

	@SuppressLint("InlinedApi")
	private final static String[] FROM_COLUMNS = {Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
													Contacts.DISPLAY_NAME_PRIMARY : Contacts.DISPLAY_NAME };
	private final static int[] TO_IDS = {android.R.id.text1};
	
	@SuppressLint("InlinedApi")
	private final static String[] PROJECTION = {
		Contacts._ID,
		Contacts.LOOKUP_KEY,
		Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
			Contacts.DISPLAY_NAME_PRIMARY : Contacts.DISPLAY_NAME,
	};
	private static final int CONTACT_ID_INDEX = 0;
	private static final int LOOKUP_KEY_INDEX = 1;
	
	// Define o Select
    @SuppressLint("InlinedApi")
    private static final String SELECTION =
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
            Contacts.DISPLAY_NAME_PRIMARY + " LIKE ?" :
            Contacts.DISPLAY_NAME + " LIKE ?";
            
    // Defines a variable for the search string
    private String mSearchString = "";
    // Defines the array to hold values that replace the ?
    private String[] mSelectionArgs = { mSearchString };
	
	private ListView mContactList;
	private long mContactId;	
	private String mContactKey;
	private Uri mContactUri;
	private SimpleCursorAdapter mCursorAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main); 
		
		mContactList = (ListView) findViewById(R.id.contactList);
		mCursorAdapter = new SimpleCursorAdapter(
				this,
				R.layout.list_item,
				null, 
				FROM_COLUMNS, 
				TO_IDS, 0);
		mContactList.setAdapter(mCursorAdapter);
		mContactList.setOnItemClickListener(this);
		
		getSupportLoaderManager().initLoader(0, null, this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		mSelectionArgs[0] = "%" + mSearchString + "%";
		return new CursorLoader(
				this,
				Contacts.CONTENT_URI,
				PROJECTION,
				SELECTION,
				mSelectionArgs,
				null);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		mCursorAdapter.swapCursor(cursor);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> cursor) {
		mCursorAdapter.swapCursor(null);
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {	
		Cursor cursor = mCursorAdapter.getCursor();
		cursor.moveToPosition(position);
		mContactId =  cursor.getLong(CONTACT_ID_INDEX);
		mContactKey = cursor.getString(LOOKUP_KEY_INDEX);
		//Você pode usar a URI para obter mais informações do contato posteriormente
		mContactUri = Contacts.getLookupUri(mContactId, mContactKey);
		Intent intent = new Intent(this, ContactDetailsActivity.class);
		intent.putExtra("URI", mContactUri);
		intent.putExtra("ID", mContactId);
		intent.putExtra("KEY", mContactKey);
		Log.d("DATA", "URI = " + mContactUri + "\nID = " + mContactId + "\nKEY = " + mContactKey);
		startActivity(intent);
	}
	
}
