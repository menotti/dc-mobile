package com.example.xcsbooks;

import java.util.List;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xcsbooks.control.GetBookCover;
import com.example.xcsbooks.control.JSONParser;
import com.example.xcsbooks.model.Dinheiro;
import com.example.xcsbooks.model.ItemPedido;
import com.example.xcsbooks.model.LivroNovo;

public class DetalhesLivroActivity extends BaseActivity {
	
	private TextView mTxtTituloLivro;
	private TextView mTxtAutorLivro;
	private TextView mTxtEditoraLivro;
	private TextView mTxtPrecoLivro;
	private TextView mTxtIsbaLivro;
	private TextView mTxtUsadoLivro;
	private Button mBtnAdicionarCarrinho;
	private LivroNovo livro;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalhes_livro);
		
		mTxtTituloLivro = (TextView) findViewById(R.id.detalheLivro_txtTitulo);
		mTxtAutorLivro = (TextView) findViewById(R.id.detalheLivro_txtAutor);
		mTxtEditoraLivro = (TextView) findViewById(R.id.detalheLivro_txtEditora);
		mTxtPrecoLivro = (TextView) findViewById(R.id.detalheLivro_txtPreco);
		mTxtIsbaLivro = (TextView) findViewById(R.id.detalheLivro_txtIsbn);
		mTxtUsadoLivro = (TextView) findViewById(R.id.detalheLivro_txtUsado);
		mBtnAdicionarCarrinho = (Button) findViewById(R.id.detalheLivro_btnAdicionarCarrinho);
		ImageView im = (ImageView) findViewById(R.id.detalheLivro_imagemLivro);
		
		//Obtem o objeto livro passado
		Intent i = getIntent();
		Resources r = getResources();
		
		livro = (LivroNovo) i.getParcelableExtra(BuscarActivity.KEY_LIVRO);
		
		im.setImageBitmap(GetBookCover.getCover(livro.getIsbn()));
		mTxtTituloLivro.setText(livro.getTitulo());
		mTxtAutorLivro.setText(r.getString(R.string.autor) + ": " + livro.getAutor());
		mTxtEditoraLivro.setText(r.getString(R.string.editora) + ": " + livro.getEditora());
		mTxtPrecoLivro.setText(r.getString(R.string.preco) + ": " + livro.getPreco().toString());
		mTxtIsbaLivro.setText(r.getString(R.string.isbn) + ": " + livro.getIsbn());
		if(!livro.getUsado().toString().equals("nao"))
			mTxtUsadoLivro.setText(r.getString(R.string.status)+": "+ livro.getUsado());
		
		if(livro.getQuantidade() < 1) {
			mBtnAdicionarCarrinho.setText("Indisponível");
			mBtnAdicionarCarrinho.setEnabled(false);
		}
		
		
		mBtnAdicionarCarrinho.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				addLivroAoCarrinho();
				
				Toast.makeText(getApplicationContext(), "Livro adicionado ao carrinho", Toast.LENGTH_SHORT).show();
			}
		});	
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		return super.onOptionsItemSelected(item);
	}
	
	private boolean addLivroAoCarrinho() {
		//Obter livros do carrinho
		SharedPreferences prefs = getSharedPreferences("CARRINHO", MODE_PRIVATE);
		String carrinho = prefs.getString("ITENSCARRINHO", JSONParser.DEFAULT_PRODUTOS);
		//List<LivroNovo> list = JSONParser.LivroFromJSON(carrinho);
		List<ItemPedido> itens = JSONParser.ItemPedidoFromJSON(carrinho);
		
		boolean add = true;
		for(ItemPedido ip : itens){
			if(ip.getProduto().getCodigo() == livro.getCodigo()){
				ip.setQuantidade(ip.getQuantidade() + 1);
				add = false;
			}
		}
		
		if(add){
			itens.add(new ItemPedido(1, new LivroNovo(livro), new Dinheiro(livro.getPreco().toString())));
		}
		
		carrinho = JSONParser.ItemPedidoToJSON(itens);
		SharedPreferences.Editor editor = prefs.edit();;
		editor.putString("ITENSCARRINHO", carrinho);
		
		editor.commit();
		return true;
	}
}
