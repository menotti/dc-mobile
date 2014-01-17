public void manageConnection(BluetoothSocket socket){
	tConnected = new ConnectedThread(socket);
	tConnected.start();
}