package br.ufscar.dc.mobile.audiorecorder;

import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private String testFilename;
	private Button mRecordButton;
	private Button mPlayButton;
	private MediaRecorder recorder;
	private MediaPlayer player;
	private boolean isRecording = false;
	private boolean isPlaying = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		mRecordButton = (Button) findViewById(R.id.start);
		mPlayButton = (Button) findViewById(R.id.listen);
		
		testFilename = Environment.getExternalStorageDirectory().getAbsolutePath()
				+ "/audiorecordtest.3gp";
		
		mRecordButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(isRecording){
					recorder.stop();
					releaseRecorder();
					mRecordButton.setText("Record");
					isRecording = false;
				} else {
					if(prepareRecording()){
						recorder.start();
						mRecordButton.setText("Stop");
						isRecording = true;
					} else {
						releaseRecorder();
					}
				}
			}
		});
		
		mPlayButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(isPlaying){
					stopPlaying();
					isPlaying = false;
					mPlayButton.setText("Play");
				} else {
					startPlaying();
					isPlaying = true;
					mPlayButton.setText("Stop");
				}
			}
		});
	}
	
	private boolean prepareRecording(){
		recorder = new MediaRecorder();
		recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		recorder.setOutputFile(testFilename);
		recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
		
		try{
			recorder.prepare();
		} catch (IOException e) {
			Log.e("AudioRecorder", "prepareRecording() failed");
			return false;
		}
		return true;
	}
	
	private void releaseRecorder(){
		recorder.release();
		recorder = null;
	}
	
	private void startPlaying(){
		player = new MediaPlayer();
		
		player.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
				mPlayButton.setText("Play");
				stopPlaying();
				isPlaying = false;
			}
		});
		
		try{
			player.setDataSource(testFilename);
			player.prepare();
			player.start();
		} catch (IOException e){
			Log.e("AudioRecorder", "startPlaying() failed");
		}
	}
	
	private void stopPlaying(){
		player.stop();
		player.release();
		player = null;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
