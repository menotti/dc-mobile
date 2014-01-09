package br.ufscar.dc.mobile.contacts;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredPostal;
import android.provider.ContactsContract.Contacts;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.TextView;

public class ContactDetailsActivity extends FragmentActivity implements
LoaderCallbacks<Cursor> {
	private Uri mContactUri;
	private long mContactId;

	private ViewGroup container;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_details);
		
		Intent intent = getIntent();
		Uri u = intent.getParcelableExtra("URI");
		long i = intent.getLongExtra("ID", 0);
		setContact(u, i);
		
		//Remove todos os elementos internos do layout
		container = (ViewGroup) findViewById(R.id.viewgroup);
		container.removeAllViewsInLayout();
		
		getSupportLoaderManager().initLoader(0, null, this);
	}
	
	public void setContact(Uri contactUri, long contactId){
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
			mContactUri = contactUri;
		} else {
			mContactUri = Contacts.lookupContact(getContentResolver(), contactUri);
		}
		mContactId = contactId;
		
		getSupportLoaderManager().restartLoader(ContactDetailsQuery.QUERY_ID, null, this);
		getSupportLoaderManager().restartLoader(ContactAddressQuery.QUERY_ID, null, this);
		if(hasPhone())
			getSupportLoaderManager().restartLoader(ContactPhoneQuery.QUERY_ID, null, this);
		
	}
	

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

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		if(mContactUri == null){
			return;
		}
		
		switch(loader.getId()){
			case ContactDetailsQuery.QUERY_ID:
				if(cursor.moveToFirst()){
					final String contactName = cursor.getString(ContactDetailsQuery.DISPLAY_NAME);
					Log.d("NAME", contactName);
					TextView nameView = new TextView(this);
					nameView.setText(contactName);
					nameView.setTextSize(30);
					container.addView(nameView);
				}
				break;
			case ContactPhoneQuery.QUERY_ID:
				if(cursor.moveToFirst()){
					do {
						String contactPhone = cursor.getString(ContactPhoneQuery.NUMBER);
						Log.d("PHONE#", "#: " + contactPhone);
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
						String address = cursor.getString(ContactAddressQuery.ADDRESS);
						Log.d("ADDRESS", "Adr: " + address);
						TextView addrView = new TextView(this);
						addrView.setText(address);
						addrView.setTextSize(15);
						container.addView(addrView);
					} while(cursor.moveToNext());
				}
				break;
		}
		
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		// Nada precisa ser feito	
	}
	
	public boolean hasPhone(){
		// Testa se o contato tem pelo menos um número de telefone
		String projection[] = { Phone.HAS_PHONE_NUMBER };
		String selection = Phone._ID + " = ?";
		String selectionArgs[] = { mContactUri.getLastPathSegment() };
		int hasPhone_index = 0;
		Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
				projection, selection, selectionArgs, null);
		
		if(cursor.moveToFirst()){
			int has = Integer.parseInt(cursor.getString(hasPhone_index));
			return has > 0;
		}
		return false;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contact_details, menu);
		return true;
	}

	public interface ContactDetailsQuery {
		//Identificador da consulta
		final static int QUERY_ID = 1;
		//Projeção
		@SuppressLint("InlinedApi")
		final static String[] PROJECTION = {
			Contacts._ID,
			Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
					Contacts.DISPLAY_NAME_PRIMARY : Contacts.DISPLAY_NAME,
		};
		//Campos da projeção
		final static int ID = 0;
		final static int DISPLAY_NAME = 1;
		
	}
	
	public interface ContactPhoneQuery {
		final static int QUERY_ID = 2;
		final static String[] PROJECTION = {
			Phone._ID,
			Phone.NUMBER
		};
		
		final static String SELECTION =
			Phone.CONTACT_ID + " = ?";
		
		final static int ID = 0;
		final static int NUMBER = 1;
	}
	
	public interface ContactAddressQuery {
		final static int QUERY_ID = 4;
		final static String[] PROJECTION = {
			StructuredPostal._ID,
			StructuredPostal.FORMATTED_ADDRESS
		};

		final static String SELECTION =
			StructuredPostal.CONTACT_ID + " = ?";
		
		final static int ID = 0;
		final static int ADDRESS = 1;
	}
}
	