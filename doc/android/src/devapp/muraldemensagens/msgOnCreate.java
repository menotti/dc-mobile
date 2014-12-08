...
protected void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 setContentView(R.layout.activity_mensagens);
new 
 HttpAsyncTask().execute("http://mobile.dc.ufscar.br/backend/msgShow.php");
}
...