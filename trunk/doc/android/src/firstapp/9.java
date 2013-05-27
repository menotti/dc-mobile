@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	
	// Show the Up button in the action bar.
	setupActionBar();
	
	//Obtem o conteudo da Intent
	Intent intent = getIntent();
	String mensagem = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
	
	//Cria o TextView
	TextView textView = new TextView(this);
	textView.setTextSize(40);
	textView.setText("Hello " + mensagem);
	
	//Estabelece o text view como o layout da atividade
	setContentView(textView);
}