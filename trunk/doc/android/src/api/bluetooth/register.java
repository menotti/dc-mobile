@Override
protected void onDestroy(){
	super.onDestroy();
	unregisterReceiver(mReceiver);
}

@Override
protected void onResume(){
	super.onResume();
	filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
	registerReceiver(mReceiver, filter);
}