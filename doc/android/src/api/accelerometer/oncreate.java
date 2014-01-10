protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_accel);
	
	xAxis = (TextView) findViewById(R.id.xAxis);
	yAxis = (TextView) findViewById(R.id.yAxis);
	zAxis = (TextView) findViewById(R.id.zAxis);
			
	mSensorMan = 
		(SensorManager) getSystemService(Context.SENSOR_SERVICE);
	mSensor = mSensorMan.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);		
}