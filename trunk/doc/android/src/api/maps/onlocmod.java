@Override
	public void onLocationChanged(Location location) {
		c_lat = location.getLatitude();
		c_long = location.getLongitude();
		mLocation = new LatLng(c_lat, c_long);
		
		latTv.setText("Lat: " + c_lat);
		longTv.setText("Long: " + c_long);
	
		marker.remove();
		marker = mMap.addMarker
			(new MarkerOptions().position(mLocation).title("You are here"));
		mMap.moveCamera
			(CameraUpdateFactory.newLatLngZoom(mLocation, 14.0f));
	}