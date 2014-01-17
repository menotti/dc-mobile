@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_device_list);
		
	/* 1 */
	mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
	if(mBluetoothAdapter == null) {
		// Bluetooth nao suportado
	}
	
	/* 2 */
	if(!mBluetoothAdapter.isEnabled()){
		Intent enableBluetooth = new Intent(
				BluetoothAdapter.ACTION_REQUEST_ENABLE);
		startActivityForResult(enableBluetooth, REQUEST_ENABLE_BT);	
	}
	
	mArrayAdapter = new ArrayAdapter<String>(
		this, android.R.layout.simple_list_item_1);
	
	/* 3 */
	Set<BluetoothDevice> pairedDevices = 
		mBluetoothAdapter.getBondedDevices();
	if(pairedDevices.size() > 0){
		for(BluetoothDevice device : pairedDevices){
			mArrayAdapter.add(device.getName() + "\n" 
								+ device.getAddress());
		}
	}		
	... //[1] 
	deviceList.setAdapter(mArrayAdapter);
	... //[2]
}