@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	
	String uri = "http://www.dc.ufscar.br";
	AsyncTask<String,String,String> task;
	String response = null;
	
	try {
		task = new RequestTask(this).execute(uri);
		response = task.get();
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	if(response != null){
		WebView webview = new WebView(this);
		setContentView(webview);
		
		webview.loadData(response, "text/html; charset=UTF-8", null);
	}
}