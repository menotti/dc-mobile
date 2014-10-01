package com.example.xcsbooks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.xcsbooks.control.BuscaControl;
import com.example.xcsbooks.control.GetBookCover;
import com.example.xcsbooks.model.LivroNovo;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class BuscarActivity extends BaseActivity {
	private String termo;
	private EditText mEditBusca;
	private List<LivroNovo> livros;
	private List searchList;
	
	public static final String KEY_LIVRO = "com.example.xcsbooks.buscar.LIVRO";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buscar);
	
		termo = getIntent().getStringExtra(BaseActivity.KEY_BUSCA);
		mEditBusca = (EditText) findViewById(R.id.busca_txtBusca);
		mEditBusca.setText(termo);
		mEditBusca.clearFocus();
			
		ListView lv = (ListView) findViewById(R.id.busca_listaResultadosView);
		searchList = new ArrayList();
		performSearch(termo);
		
		String[] t = {"itemLista_thumbLivro","itemLista_tituloLivro",
					"itemLista_autorLivro", "itemLista_precoLivro",
					"itemLista_usadoLivro"};
			
		int[] i = {R.id.itemLista_thumbLivro,
					R.id.itemLista_tituloLivro, R.id.itemLista_autorLivro, R.id.itemLista_precoLivro,
					R.id.itemLista_usadoLivro};
		
		//Adapter
	
		final ExtendedSimpleAdapter adapter = new ExtendedSimpleAdapter(this, (List<HashMap<String, Object>>) searchList, R.layout.item_lista, t , i);
		
		lv.setAdapter(adapter);
	
		lv.setOnItemClickListener(new OnItemClickListener() {
		
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(BuscarActivity.this, DetalhesLivroActivity.class);
				intent.putExtra(KEY_LIVRO, livros.get(position));
				startActivity(intent);
			}
		});
		
		mEditBusca.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				termo = v.getText().toString();
				performSearch(termo);
				adapter.notifyDataSetChanged();
				return true;
			}
		});
	}
	
	private void performSearch(String termo){
		//Obtém livros da busca
		livros = BuscaControl.buscarLivro(termo);
		searchList.clear();
		
		Map map = null;
		for(LivroNovo livro : livros) {
			map = new HashMap();
			map.put("itemLista_thumbLivro", GetBookCover.getCover(livro.getIsbn()));
			map.put("itemLista_tituloLivro", livro.getTitulo());
			map.put("itemLista_autorLivro", livro.getAutor());
			map.put("itemLista_precoLivro", livro.getPreco().toString());
			if(livro.getUsado().toString().equals("nao"))
				map.put("itemLista_usadoLivro", "");
			else
				map.put("itemLista_usadoLivro", getResources().getString(R.string.usado));
			searchList.add(map);
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