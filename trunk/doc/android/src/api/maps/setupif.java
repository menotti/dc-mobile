private void setUpMapIfNeeded() {
	if (mMap == null) {
		mMap = mMapFragment.getMap();
		
		if (mMap != null)
			 marker = mMap.addMarker
				(new MarkerOptions().position(new LatLng(0,0)));
	}
}
