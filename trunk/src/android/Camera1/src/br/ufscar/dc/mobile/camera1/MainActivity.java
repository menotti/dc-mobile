package br.ufscar.dc.mobile.camera1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.Size;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.camera1.R;

public class MainActivity extends Activity {
	private Camera mCam;
	private CameraPreview mPreview;
	private PictureCallback mJPEGCallback;
	private Button mCapture;
	private Button mRecord;
	private MediaRecorder recorder = null;
	private boolean isRecording = false;
	private boolean isParamSet = false;
	
	public static final int MEDIA_TYPE_IMAGE = 1;
	public static final int MEDIA_TYPE_VIDEO = 2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		CameraAccess ca = new CameraAccess(this);
		mCam = ca.getCameraInstance();
		Parameters params = mCam.getParameters();
		params.setJpegQuality(100);
		
		List<Size> sizes = params.getSupportedPictureSizes();
		Camera.Size size = sizes.get(0);
		for(int i=0;i<sizes.size();i++)
		{
		    if(sizes.get(i).width > size.width)
		        size = sizes.get(i);
		}

		params.setPictureSize(size.width, size.height);
		
		mCam.setParameters(params);
		
		mPreview = new CameraPreview(this, mCam);
		FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
		preview.addView(mPreview);
		
		mJPEGCallback = new PictureCallback() {
			
			@Override
			public void onPictureTaken(byte[] data, Camera camera) {
				File picFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
				if(picFile == null){
					Log.d("ERROR", "Error creating media file, check storage permissions");
					return;
				}
				
				try {
					FileOutputStream fos = new FileOutputStream(picFile);
					fos.write(data);
					fos.close();
				} catch(FileNotFoundException e) {
					Log.d("ERROR", "File not found: " + e.getMessage());
				} catch (IOException e){
					Log.d("ERROR", "Error accessing file: " + e.getMessage());
				}
			}
		};
			
		mCapture = (Button) findViewById(R.id.button_capture);
		mRecord = (Button) findViewById(R.id.button_record);
		
		mCapture.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mCam.takePicture(null, null, mJPEGCallback);
			}
		});
		
		mRecord.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(isRecording){
					recorder.stop();
					releaseMediaRecorder();
					setRecordButtonText("Record");
					isRecording = false;	 			
				} else {
					if(prepareForRecording()) {
						recorder.start();
						setRecordButtonText("Stop");
						isRecording = true;
					} else {
						releaseMediaRecorder();
					}
				}
				
			}
		});
	}
	
	 @Override
	 protected void onPause() {
		 super.onPause();
	     releaseMediaRecorder();
	     releaseCamera();
    }
	
	private boolean prepareForRecording() {
		recorder = new MediaRecorder();
		mCam.unlock();
		recorder.setCamera(mCam);
		
		recorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
		recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);

		CamcorderProfile profile = CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH);
		recorder.setProfile(profile);
		
		recorder.setOutputFile(getOutputMediaFile(MEDIA_TYPE_VIDEO).toString());
		
		recorder.setPreviewDisplay(mPreview.getHolder().getSurface());
		
		try{
			recorder.prepare();
		} catch (IllegalStateException e){
			releaseMediaRecorder();
			return false;
		} catch (IOException e) {
			releaseMediaRecorder();
			return false;
		}
		
		return true;
	}
	
	private void releaseMediaRecorder() {
		if(recorder != null){
			recorder.reset();
			recorder.release();
			recorder = null;
			mCam.lock();
		}
	}
	
	private void releaseCamera(){
		if(mCam != null){
			mCam.release();
			mCam = null;
		}
	}
	
	private void setRecordButtonText(String text){
		mRecord.setText(text);
	}

	private File getOutputMediaFile(int type){
		String folder = getResources().getString(R.string.app_name) + "_PICS";
		
	File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
	          Environment.DIRECTORY_PICTURES), folder);
	
	if (! mediaStorageDir.exists()){
	    if (! mediaStorageDir.mkdirs()){
	        Log.d("ERROR", "failed to create directory: " + folder);
	        return null;
	    }
	}
	String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	
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
