public void gerarNotificacao(View view){
	Thread t = new Thread(new Runnable() {
	public void run() {
	EditText mEditTextMensagem= (EditText) findViewById(R.id.txt_mensagem);
	String txt_mensagem = mEditTextMensagem.getText().toString();  
	txt_mensagem = txt_mensagem.replace(" ", "%20");
					
	EditText mEditTextNome= (EditText) findViewById(R.id.txt_nome);
	String txt_nome = mEditTextNome.getText().toString();  
	txt_nome = txt_nome.replace(" ", "%20");
					
	HttpClient httpclient = new DefaultHttpClient();
	HttpPost httppost = new 
		HttpPost("http://mobile.dc.ufscar.br/backend/msgSaver.php?" 
		    	+ "txt_msg=" + txt_mensagem + "&txt_nome=" + txt_nome);
	try {
		HttpResponse response = httpclient.execute(httppost);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	});
	t.start();
...