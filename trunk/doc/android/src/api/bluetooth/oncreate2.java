deviceList = (ListView) findViewById(R.id.deviceList);

View v = getLayoutInflater().inflate(R.layout.device_list_footer, null);
Button scan = (Button) v.findViewById(R.id.button_scan);

scan.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		mBluetoothAdapter.startDiscovery();
	}
});

deviceList.addFooterView(v);