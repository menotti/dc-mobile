protected void onResume(){
	super.onResume();
	mSensorMan.registerListener
		(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
}
	
protected void onPause(){
	super.onPause();
	mSensorMan.unregisterListener(this);
}