package br.ufscar.dc.mobile.contentsharing;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private byte[] byteArray;
	private Bitmap picture;
	private EditText textField;
	private Button takePicture;
	private ImageView picView;
	private Button shareText;
	private Button sharePic;
	private Uri picUri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		textField = (EditText) findViewById(R.id.editText1);
		takePicture = (Button) findViewById(R.id.button1);
		
		picView = (ImageView) findViewById(R.id.imageView1);
		shareText = (Button) findViewById(R.id.buttonShareText);
		sharePic = (Button) findViewById(R.id.buttonSharePic);
		
		takePicture.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				picUri = Uri.fromFile(getOutputMediaFile());
				intent.putExtra(MediaStore.EXTRA_OUTPUT, picUri);
				startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
			}
		});
		
		final Intent shareIntent = new Intent();
		shareIntent.setAction(Intent.ACTION_SEND);
		
		shareText.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String text = textField.getText().toString();
				shareIntent.setType("text/plain");
				shareIntent.putExtra(Intent.EXTRA_TEXT, text);
				startActivity(Intent.createChooser(shareIntent, "Send to..."));
			}
		});
		
		sharePic.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				shareIntent.setType("image/*");
				shareIntent.putExtra(Intent.EXTRA_STREAM, picUri);
				startActivity(Intent.createChooser(shareIntent, "Send to..."));
			}
		});
		
	}
	
	/* Método para tratar o resultado da activity camera */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
	        if (resultCode == RESULT_OK) {
	            Toast.makeText(this, "Picture taken", Toast.LENGTH_LONG).show();
				picView.setImageBitmap(decodeSampledBitmapFromResource(getResources(), picUri.getPath(), 200, 200));
	        } else if (resultCode == RESULT_CANCELED) {
	        	// Usuário cancelou 
	        } else {
	        	// Deu errado então mostra toast avisando que deu errado
	            Toast.makeText(this, "Error" ,Toast.LENGTH_LONG).show();
	        }
	    }
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
	
	private File getOutputMediaFile(){
		String folder = getResources().getString(R.string.app_name) + "_PIC";
		
		File mediaStorageDir =
				new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), folder);
		
		if(!mediaStorageDir.exists()){
			if(!mediaStorageDir.mkdirs()){
				Log.e("SHARING", "Failed to create directory: " + folder);
				return null;
			}
		}
		File mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_TMP.jpg");
		return mediaFile;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {	
		getMenuInflater().inflate(R.menu.main, menu);		
		return super.onCreateOptionsMenu(menu);
	}
}
