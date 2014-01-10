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
