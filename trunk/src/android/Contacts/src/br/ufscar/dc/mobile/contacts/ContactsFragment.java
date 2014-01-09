package br.ufscar.dc.mobile.contacts;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class ContactsFragment extends ListFragment implements
	LoaderCallbacks<Cursor> {

	ListFragmentItemClickListener listItemClickListener;
	/*
	 * Testar a versão do SDK é necessário pois antes do Android 3.0 o nome do contato estava em Contacts.DISPLAY_NAME e
	 * depois do 3.0, está em Contacts.DISPLAY_NAME_PRIMARY
	 */
	
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
	

	//public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle){
	//	return inflater.inflate(R.layout.list_view, container, false);
	//}
	
	public void onActivityCreated(Bundle bundle){
		super.onActivityCreated(bundle);
		mContactList = getListView();
		mCursorAdapter = new SimpleCursorAdapter(
				getActivity(),
				R.layout.list_item,
				null, 
				FROM_COLUMNS, 
				TO_IDS, 0);
		mContactList.setAdapter(mCursorAdapter);
		
		getLoaderManager().initLoader(0, null, this);
	}
	
	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		Log.d("TAG", "ENTROU");
		try {
			listItemClickListener = (ListFragmentItemClickListener) activity;
		} catch (Exception e) {
			Log.e("CONTACT_FRAGMENT", "Error: " + e.getMessage());
		}
		
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Log.d("A", "Aaaa");
		Cursor cursor = mCursorAdapter.getCursor();
		cursor.moveToPosition(position);
		mContactId =  cursor.getLong(CONTACT_ID_INDEX);
		mContactKey = cursor.getString(LOOKUP_KEY_INDEX);
		//Você pode usar a URI para obter mais informações do contato posteriormente
		mContactUri = Contacts.getLookupUri(mContactId, mContactKey);
		Bundle bundle = new Bundle();
		bundle.putParcelable("URI", mContactUri);
		listItemClickListener.onListFragmentItemClick(position, bundle);	
	}

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

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		mCursorAdapter.swapCursor(cursor);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		mCursorAdapter.swapCursor(null);
	}

	public interface ListFragmentItemClickListener {
		void onListFragmentItemClick(int position, Bundle bundle);
	}
}

