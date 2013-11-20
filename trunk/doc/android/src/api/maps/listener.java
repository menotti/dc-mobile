public class MyLocationListener implements LocationListener {
	private double c_lat;
	private double c_long;

	@Override
	public void onLocationChanged(Location location) {
		c_lat = location.getLatitude();
		c_long = location.getLongitude();
	}

	@Override
	public void onProviderDisabled(String provider) {
		Toast.makeText(getApplicationContext(),
			 provider + " provider disabled", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onProviderEnabled(String provider) {
		Toast.makeText(getApplicationContext(),
			 provider + " provider enabled", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onStatusChanged
			(String provider, int status, Bundle extras) {
	}
	
	...
}