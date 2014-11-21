@SuppressLint("NewApi")
private void callServer(){
	new Thread(){
	public void run(){
	String answer = 
	ttpConnection.getSetDataWeb("http://mobile.dc.ufscar.br/backend/index.php");
		degenerateJSON(answer);
	}
}.start();
}