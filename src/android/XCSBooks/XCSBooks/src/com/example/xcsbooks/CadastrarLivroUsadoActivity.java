package com.example.xcsbooks;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.example.xcsbooks.control.BuscaControl;
import com.example.xcsbooks.control.CadastrarLivroUsadaControl;
import com.example.xcsbooks.model.LivroNovo;
import com.example.xcsbooks.model.LivroUsado;

public class CadastrarLivroUsadoActivity extends BaseActivity {

	private Button mBtnCadastrar;
	private EditText mEditIsbn;
	private EditText mEditTitulo;
	private EditText mEditAutor;
	private EditText mEditEditora;
	private EditText mPreco;
	
	private LivroUsado livrousado;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar_livro_usado);
		
		mBtnCadastrar = (Button) findViewById(R.id.cadastrarLivroUsado_btnCadastrar);
		mEditIsbn = (EditText) findViewById(R.id.cadastrarLivroUsado_txtIsbn);
		mEditTitulo = (EditText) findViewById(R.id.cadastrarLivroUsado_txtTitulo);
		mEditAutor = (EditText) findViewById(R.id.cadastrarLivroUsado_txtAutor);
		mEditEditora = (EditText) findViewById(R.id.cadastrarLivroUsado_txtEditora);
		mPreco = (EditText) findViewById(R.id.cadastrarLivroUsado_txtPreco);
		
		
		mBtnCadastrar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// Envia dados para o controller! 
				Intent intent = getIntent();
				List<NameValuePair> list = new ArrayList<NameValuePair>();
				
				boolean teste = true;
				String isbn = mEditIsbn.getText().toString();
				String titulo = mEditTitulo.getText().toString();
				String autor = mEditAutor.getText().toString();
				String editora = mEditEditora.getText().toString();
				String preco = mPreco.getText().toString();
				
				if(isbn.isEmpty()){
					teste = false;
					mEditIsbn.setError("Digite o ISBN do livro");
				} 
				if(titulo.isEmpty()){
					teste = false;
					mEditTitulo.setError("Digite o título do livro");
				}
				if(autor.isEmpty()){
					teste = false;
					mEditAutor.setError("Digite o autor do livro");
				}
				if(editora.isEmpty()){
					teste = false;
					mEditEditora.setError("Digite a editora");
				}
				if(preco.isEmpty()){
					teste = false;
					mPreco.setError("Digite um preço");
				}

				list.add(new BasicNameValuePair("isbn", isbn));
				list.add(new BasicNameValuePair("titulo", titulo));
				list.add(new BasicNameValuePair("autor", autor));
				list.add(new BasicNameValuePair("editora", editora));
				list.add(new BasicNameValuePair("preco", preco));
				
				if(teste){
					int r = CadastrarLivroUsadaControl.cadastrar(list);
					
					if(r >= 0){
						Toast.makeText(getApplicationContext(), "Cadastro realizado com sucesso", Toast.LENGTH_LONG).show();
						intent 	= new Intent(CadastrarLivroUsadoActivity.this, HomeActivity.class);
						startActivity(intent);
					} else {
						Toast.makeText(getApplicationContext(), "Erro no cadastro", Toast.LENGTH_LONG).show();
					}
				}
			}
		});
		
		mEditIsbn.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				String isbn = v.getText().toString();
				atualizaCampos(isbn);
				return true;
			}
		});
	}
	
	public void atualizaCampos(String isbn){
		LivroNovo livro = (LivroNovo) BuscaControl.buscaISBN(isbn);
		if(livro != null){
			mEditTitulo.setText(livro.getTitulo());
			mEditAutor.setText(livro.getAutor());
			mEditEditora.setText(livro.getEditora());
		} else {
			Toast.makeText(this, "Não foi possível obter os dados do livro", Toast.LENGTH_SHORT).show();
		}
	}
}
