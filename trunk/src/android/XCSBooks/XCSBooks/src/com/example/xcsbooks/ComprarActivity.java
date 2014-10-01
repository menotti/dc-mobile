package com.example.xcsbooks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xcsbooks.control.ComprarControl;
import com.example.xcsbooks.control.GetBookCover;
import com.example.xcsbooks.control.JSONParser;
import com.example.xcsbooks.control.LoginControl;
import com.example.xcsbooks.model.Dinheiro;
import com.example.xcsbooks.model.ItemPedido;
import com.example.xcsbooks.model.LivroNovo;

public class ComprarActivity extends BaseActivity {
	private TextView tv;
	private SharedPreferences prefs;
	private List<HashMap<String, Object>> pedido;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comprar);

		if(LoginControl.getClienteLogado() == null){
			Toast.makeText(this, "Logue para poder comprar", Toast.LENGTH_LONG).show();
			Intent intent = new Intent(ComprarActivity.this, LogarActivity.class);
			startActivity(intent);
		} else {
			//Obtém dados do carrinho para preencher lista
			prefs = getSharedPreferences("CARRINHO", MODE_PRIVATE);
			String strlivros = prefs.getString("ITENSCARRINHO", JSONParser.DEFAULT_PRODUTOS);
			List<ItemPedido> itens = JSONParser.ItemPedidoFromJSON(strlivros);
			
			//Preenche lista, adiciona header e footer...
			ListView lv = (ListView) findViewById(R.id.compra_listaCarrinho);
			
			pedido = new ArrayList<HashMap<String, Object>>();
			HashMap<String, Object> map = new HashMap<String, Object>();;
			
			Dinheiro precoFinal = new Dinheiro(0);
			
			for(ItemPedido ip : itens) {
				map = new HashMap<String, Object>();
				map.put("itemCarrinho_quantidadeItem", ip.getQuantidade());
				map.put("itemCarrinho_codLivro", ip.getProduto().getCodigo());
				map.put("itemCarrinho_thumbLivro", GetBookCover.getCover(((LivroNovo)ip.getProduto()).getIsbn()));
				map.put("itemCarrinho_tituloLivro", ((LivroNovo)ip.getProduto()).getTitulo().toString());
				map.put("itemCarrinho_autorLivro", ((LivroNovo)ip.getProduto()).getAutor().toString());
				map.put("itemCarrinho_editoraLivro", ((LivroNovo)ip.getProduto()).getEditora());
				ip.setTotalItem(new Dinheiro(ip.getProduto().getPreco().mult(ip.getQuantidade())));
				map.put("itemCarrinho_totalItem",  ip.getTotalItem().toString());
				
				pedido.add(map);
				
				precoFinal.valor = precoFinal.soma(ip.getTotalItem());
			}
			
			//Adapter
			final SimpleAdapter adapter = new SimpleAdapter(this, pedido, 
				R.layout.comprar_item, 
				new String[] {
					"itemCarrinho_tituloLivro",
					"itemCarrinho_quantidadeItem",
					"itemCarrinho_totalItem"},
				new int[] {
					R.id.comprar_nomeLivro,
					R.id.comprar_quantidadeLivro,
					R.id.comprar_precoLivro});
			
			LayoutInflater inflater = getLayoutInflater();
			View header = inflater.inflate(R.layout.comprar_header, null);
			lv.addHeaderView(header);
			
			View footer = inflater.inflate(R.layout.comprar_footer, null);
			TextView precoFinal_txt = (TextView) footer.findViewById(R.id.comprar_precoFinal);
			precoFinal_txt.setText("Total: " + precoFinal.toString());
			
			Button confirma = (Button) footer.findViewById(R.id.comprar_Botao_Confirma);
			confirma.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
	
					if(!pedido.isEmpty()){
						int resposta = ComprarControl.comprar(pedido);
						Log.d("COMPRAR_ACT", "resposta:" + resposta);
						if(resposta >= 0){
							//TODO trocar para string recurso
							Toast.makeText(ComprarActivity.this, "Compra realizada com sucesso" , Toast.LENGTH_SHORT).show();
							
							//Limpar carrinho
							SharedPreferences.Editor editor = prefs.edit();
							editor.clear().commit();
							
							Intent intent = new Intent(ComprarActivity.this, HomeActivity.class);
							intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(intent);
						} else {
							Toast.makeText(ComprarActivity.this, "Erro na realização da compra" , Toast.LENGTH_SHORT).show();
						}
					} else {
						Toast.makeText(ComprarActivity.this, "Seu carrinho está vazio", Toast.LENGTH_SHORT).show();
					}
				}
			});
			
			lv.addFooterView(footer);
			
			lv.setAdapter(adapter);	
		}
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
