@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	
	//Obtem o conteudo da Intent
	Intent intent = getIntent();
	String mensagem = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
	
	//Cria o TextView
	TextView textView = new TextView(this);
	textView.setTextSize(40);
	textView.setText("Ola " + mensagem);
	
	//Estabelece o text view como o layout da atividade
	setContentView(textView);
}