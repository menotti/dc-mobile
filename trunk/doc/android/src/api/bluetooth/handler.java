private String mMessage;
private final Handler mHandler = new Handler();
private final Runnable mShowMessage = new Runnable() {
	public void run() {
		showMessage(mMessage);
	}
};
private final Runnable mShowDialog = new Runnable() {
	public void run() {
		writeMessage();
		tConnected.cancel();
	}
};