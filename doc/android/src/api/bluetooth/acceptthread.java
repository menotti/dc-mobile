public static String MY_UUID = "d17deaaa-94e9-45ae-8ab0-0f73ef29e417";
public static String NAME = "myBluetoothChat";

private class AcceptThread extends Thread {
	private final BluetoothServerSocket mServerSocket;
	
	public AcceptThread(){
		BluetoothServerSocket tmp = null;
		try {
			tmp = mBluetoothAdapter.listenUsingRfcommWithServiceRecord(
											NAME, UUID.fromString(MY_UUID));
		} catch(IOException e) { }
		mServerSocket = tmp;
	}
	
	public void run() {
		BluetoothSocket socket = null;
		while(true){
			try {
				socket = mServerSocket.accept();
			} catch(IOException e) { break; }
			
			if (socket != null) {
				manageConnection(socket);
				try {
					mServerSocket.close();
					break;
				} catch (IOException e) { }
			}
		}
	}
}