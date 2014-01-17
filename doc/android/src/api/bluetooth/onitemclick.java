@Override
public void onItemClick(AdapterView<?> arg0, View v, int pos, long id) {
	String address = mArrayAdapter.getItem(pos).split("\n")[1];
	BluetoothDevice mDevice = mBluetoothAdapter.getRemoteDevice(address);
	
	ConnectThread tConnect = new ConnectThread(mDevice);
	tConnect.start();
}