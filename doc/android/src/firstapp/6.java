public void enviarMensagem(View view) {
	// Fazer alguma coisa em resposta ao clique do botao
	Intent intent = new Intent(this, DisplayMessageActivity.class);
	EditText caixaTexto = (EditText) findViewById(R.id.caixaTextoNome);
	String mensagem = caixaTexto.getText().toString();
	intent.putExtra(EXTRA_MESSAGE, mensagem);
	startActivity(intent);
}