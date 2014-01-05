package br.ufscar.dc.mobile.receivecontent;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private ImageView picView;
	private TextView sharedTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		picView = (ImageView) findViewById(R.id.imageView1);
		sharedTextView = (TextView) findViewById(R.id.textView2);
		
		Intent intent = getIntent();
		String action = intent.getAction();
		String type = intent.getType();
		Log.d("RECEIVE_CONTENT", "Action: " + action);
		Log.d("RECEIVE_CONTENT", "Type: " + type);
		
		if(Intent.ACTION_SEND.equals(action) && type != null){
			if("text/plain".equals(type)){
				String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
				if(sharedText != null){
					sharedTextView.setText(sharedText);
				}
			} else if (type.startsWith("image/")) {
				Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
				Log.d("RECEIVE_CONTENT", "Uri: " + imageUri.getPath());
				if(imageUri != null){
					picView.setImageBitmap(decodeSampledBitmapFromResource(getResources(), imageUri.getPath(), 200, 200));
				}
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public static Bitmap decodeSampledBitmapFromResource(Resources res, String path,
	        int reqWidth, int reqHeight) {

	    // First decode with inJustDecodeBounds=true to check dimensions
	    final BitmapFactory.Options options = new BitmapFactory.Options();
	    options.inJustDecodeBounds = true;
	    BitmapFactory.decodeFile(path, options);

	    // Calculate inSampleSize
	    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

	    // Decode bitmap with inSampleSize set
	    options.inJustDecodeBounds = false;
	    return BitmapFactory.decodeFile(path, options);
	}
	
	public static int calculateInSampleSize(
        BitmapFactory.Options options, int reqWidth, int reqHeight) {
	    // Raw height and width of image
	    final int height = options.outHeight;
	    final int width = options.outWidth;
	    int inSampleSize = 1;
	
	    if (height > reqHeight || width > reqWidth) {
	
	        final int halfHeight = height / 2;
	        final int halfWidth = width / 2;
	
	        // Calculate the largest inSampleSize value that is a power of 2 and keeps both
	        // height and width larger than the requested height and width.
	        while ((halfHeight / inSampleSize) > reqHeight
	                && (halfWidth / inSampleSize) > reqWidth) {
	            inSampleSize *= 2;
	        }
	    }

    	return inSampleSize;
	}

}
