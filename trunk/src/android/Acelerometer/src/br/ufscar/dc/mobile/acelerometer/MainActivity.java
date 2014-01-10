package br.ufscar.dc.mobile.acelerometer;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity implements
SensorEventListener {
	private TextView xAxis;
	private TextView yAxis;
	private TextView zAxis;
	
	private SensorManager mSensorMan;
	private Sensor mSensor;
	
	double gravity[] = {1, 1, 1};
	double acceleration[] = {1, 1, 1};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		xAxis = (TextView) findViewById(R.id.xAxis);
		yAxis = (TextView) findViewById(R.id.yAxis);
		zAxis = (TextView) findViewById(R.id.zAxis);
				
		mSensorMan = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mSensor = mSensorMan.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);		
	}
	
	protected void onResume(){
		super.onResume();
		mSensorMan.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	protected void onPause(){
		super.onPause();
		mSensorMan.unregisterListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() != Sensor.TYPE_ACCELEROMETER)
            return;
		
		final double alpha = 0.8;
		
		gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
		gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
		gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];
		
		acceleration[0] = event.values[0] - gravity[0];
		acceleration[1] = event.values[1] - gravity[1];
		acceleration[2] = event.values[2] - gravity[2];
		
		DecimalFormat df = new DecimalFormat("##.####");
		xAxis.setText("X Axis: " + df.format(acceleration[0]));
		yAxis.setText("Y Axis: " + df.format(acceleration[1]));
		zAxis.setText("Z Axis: " + df.format(acceleration[2]));
	}

}
