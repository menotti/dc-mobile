LocationManager mlocManager = 
	(LocationManager) getSystemService(Context.LOCATION_SERVICE);
MyLocationListener mlocListener = new MyLocationListener();
mlocManager.requestLocationUpdates
	(LocationManager.GPS_PROVIDER, 1000, 0, mlocListener);