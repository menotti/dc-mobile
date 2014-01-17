private class ConnectThread extends Thread {
	private final BluetoothSocket mSocket;
	private final BluetoothDevice mDevice;
	
	public ConnectThread(BluetoothDevice device){
		BluetoothSocket tmp = null;
		mDevice = device;
		
		try {
			tmp = device.createRfcommSocketToServiceRecord(
										UUID.fromString(mUUID));
		} catch (IOException e) {}
		mSocket = tmp;
	}
	
	public void run() {
		mBluetoothAdapter.cancelDiscovery();
		
		try {
			mSocket.connect();
		} catch (IOException openExc) {
			try {
				mSocket.close();
			} catch (IOException closeExc) { }
			return;
		}
		
		manageConnection(mSocket);
		mHandler.post(mShowDialog);
	}
}