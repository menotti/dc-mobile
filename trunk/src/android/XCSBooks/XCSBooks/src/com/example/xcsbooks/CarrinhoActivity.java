package com.example.xcsbooks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

import com.example.xcsbooks.control.GetBookCover;
import com.example.xcsbooks.control.JSONParser;
import com.example.xcsbooks.control.LoginControl;
import com.example.xcsbooks.model.Dinheiro;
import com.example.xcsbooks.model.ItemPedido;
import com.example.xcsbooks.model.LivroNovo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class CarrinhoActivity extends BaseActivity {
	private SharedPreferences prefs;
	private ListView mLv; 
	private List<ItemPedido> itens;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_carrinho);
		
		mLv = (ListView) findViewById(R.id.carrinho_listaItensView);
		
		buildShoppingCart();
		View v = getLayoutInflater().inflate(R.layout.carrinho_footer, null);
		Button footerComprar = (Button) v.findViewById(R.id.carrinho_btnComprar);
		mLv.addFooterView(v);
		
		if(itens.isEmpty()){
			footerComprar.setEnabled(false);
		}
		
		footerComprar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent;
				if(LoginControl.getClienteLogado() != null) {
					intent = new Intent(CarrinhoActivity.this, ComprarActivity.class);
				} else {
					intent = new Intent(CarrinhoActivity.this, LogarActivity.class);
					intent.putExtra("WHERE_TO_GO", "ComprarActivity");
				}
				startActivity(intent);
			}
		});
		
	}
	
	private void buildShoppingCart(){
		prefs = getSharedPreferences("CARRINHO", MODE_PRIVATE);
		String stritenspedido = prefs.getString("ITENSCARRINHO", JSONParser.DEFAULT_PRODUTOS);
		itens = JSONParser.ItemPedidoFromJSON(stritenspedido);
		
		
		List<HashMap<String,Object>> list = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = null;
		
		for(ItemPedido ip : itens) {
			//int quant = prefs.getInt("LIVROS" + livros.get(i).getCodigo(), 1);
			ip.setTotalItem(new Dinheiro( ((LivroNovo)ip.getProduto()).getPreco().mult(ip.getQuantidade()) ));
			
			map = new HashMap<String, Object>();
			map.put("itemCarrinho_quantidadeItem", ip.getQuantidade());
			map.put("itemCarrinho_codLivro", ip.getProduto().getCodigo());
			map.put("itemCarrinho_thumbLivro", GetBookCover.getCover(((LivroNovo)ip.getProduto()).getIsbn()));
			map.put("itemCarrinho_tituloLivro", ((LivroNovo)ip.getProduto()).getTitulo().toString());
			map.put("itemCarrinho_autorLivro", ((LivroNovo)ip.getProduto()).getAutor().toString());
			map.put("itemCarrinho_editoraLivro", ((LivroNovo)ip.getProduto()).getEditora());
			map.put("itemCarrinho_totalItem", ip.getTotalItem().toString());
			list.add(map);
		}
		
		//Adapter
		final CarrinhoListAdapter adapter = new CarrinhoListAdapter(this, (List<HashMap<String, Object>>) list, 
			R.layout.item_carrinho, 
			new String[] {
				"itemCarrinho_thumbLivro",
				"itemCarrinho_tituloLivro",
				"itemCarrinho_autorLivro",
				"itemCarrinho_editoraLivro",
				"itemCarrinho_quantidadeItem",
				"itemCarrinho_totalItem"},
			new int[] {
				R.id.itemCarrinho_thumbLivro,
				R.id.itemCarrinho_txtTituloLivro,
				R.id.itemCarrinho_txtAutorLivro,
				R.id.itemCarrinho_txtEditoraLivro,
				R.id.itemCarrinho_txtQuantidadeLivro,
				R.id.itemCarrinho_txtPrecoLivro});
		
		mLv.setAdapter(adapter);
	}
	
	@Override
	protected void onResume(){
		super.onRestart();
		buildShoppingCart();
	}
	
	@Override
	protected void onPause(){
		super.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		return super.onOptionsItemSelected(item);
	}

}
