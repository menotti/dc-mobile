...
public void atualizarDados(View view){
		LocationResult locationResult = new LocationResult(){
		    @Override
		    public void gotLocation(Location location){

		    TextView lbl_latitude = (TextView) findViewById(R.id.lbl_latidude);
		    TextView lbl_longitude = (TextView) findViewById(R.id.lbl_longitude);
		    	
		    Double latitude = location.getLatitude();
		    Double longitude = location.getLongitude();
		    	
		    lbl_latitude.setText(latitude.toString());
		    lbl_longitude.setText(longitude.toString());
		   }
		};
	MyLocation myLocation = new MyLocation();
	myLocation.getLocation(this, locationResult);
	}
...