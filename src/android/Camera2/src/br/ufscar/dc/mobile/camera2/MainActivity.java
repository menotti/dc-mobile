package br.ufscar.dc.mobile.camera2;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;
	private static final int MEDIA_TYPE_IMAGE = 1;
	private static final int MEDIA_TYPE_VIDEO = 2;
	private Uri fileUri;

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);

	    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

	    fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
	    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

	    startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
	        if (resultCode == RESULT_OK) {
	            Toast.makeText(this, "Image saved to:\n" +
	                     fileUri.toString(), Toast.LENGTH_LONG).show();
	        } else if (resultCode == RESULT_CANCELED) {
	        	// Opcional
	        } else {
	            Toast.makeText(this, "Error capturing image...", Toast.LENGTH_LONG).show();
	        }
	    }

	    if (requestCode == CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE) {
	        if (resultCode == RESULT_OK) {
	            Toast.makeText(this, "Video saved to:\n" +
	                     fileUri.toString(), Toast.LENGTH_LONG).show();
	        } else if (resultCode == RESULT_CANCELED) {
	            // Opcional
	        } else {
	        	Toast.makeText(this, "Error recording video...", Toast.LENGTH_LONG).show();
	        }
	    }
	}
	
	private Uri getOutputMediaFileUri(int type){
	      return Uri.fromFile(getOutputMediaFile(type));
	}
	
	private File getOutputMediaFile(int type){
		String folder =
				getResources().getString(R.string.app_name) + "_PICS";
			
		File mediaStorageDir = 
				new File(Environment.getExternalStoragePublicDirectory(
						Environment.DIRECTORY_PICTURES), folder);
		
		if (! mediaStorageDir.exists()){
		    if (! mediaStorageDir.mkdirs()){
		        Log.d("ERROR", "failed to create directory: " + folder);
		        return null;
		    }
		}
		String timeStamp = 
				new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		
		File mediaFile;
		if (type == MEDIA_TYPE_IMAGE){
		    mediaFile = new File(mediaStorageDir.getPath() + File.separator +
		    "IMG_"+ timeStamp + ".jpg");
		} else if(type == MEDIA_TYPE_VIDEO) {
		    mediaFile = new File(mediaStorageDir.getPath() + File.separator +
		    "VID_"+ timeStamp + ".mp4");
		    } else {
		        return null;
		    }	
			
		    return mediaFile;
		}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
