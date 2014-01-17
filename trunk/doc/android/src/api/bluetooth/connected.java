private class ConnectedThread extends Thread {
	private final BluetoothSocket mmSocket;
	private final InputStream mmInput;
	private final OutputStream mmOutput;
	
	public ConnectedThread(BluetoothSocket socket){
		mmSocket = socket;
		InputStream tmpIn = null;
		OutputStream tmpOut = null;
		
		try {
			tmpIn = socket.getInputStream();
			tmpOut = socket.getOutputStream(); 
		} catch (IOException e) { }
		
		mmInput = tmpIn;
		mmOutput = tmpOut;
	}
	
	public void run() {
		byte[] buffer = new byte[1024];
		int bytes;
		
		while(true) {
			try {
				bytes = mmInput.read(buffer);
				mMessage = new String(buffer);
				mHandler.post(mShowMessage);
			} catch(IOException e) { break; }
		}
	}
	
	public void write(byte[] bytes) {
		try {
			mmOutput.write(bytes);
		} catch(IOException e) { }
	}
	
	public void cancel() {
		try{
			mmSocket.close();
		} catch (IOException e) { }
	}
}