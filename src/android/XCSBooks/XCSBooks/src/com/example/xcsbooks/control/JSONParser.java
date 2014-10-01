package com.example.xcsbooks.control;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.example.xcsbooks.model.Dinheiro;
import com.example.xcsbooks.model.ItemPedido;
import com.example.xcsbooks.model.LivroNovo;

public class JSONParser {
	public static final String DEFAULT_PRODUTOS = "{ \"itensPedido\": [] }";
	
	public static int parseResposta(String JSONStr){
		int r = -99;
		
		try{
			JSONObject jobj = new JSONObject(JSONStr);
			r = jobj.getInt("resposta");
			Log.d("GENERIC_RESPONSE", "Response: " + r);
		} catch (JSONException e) {
			Log.e("JSON", "Error parsing JSONString: " + JSONStr);
			byte[] b = JSONStr.getBytes();
			if(b[0] == 10){
				Log.d("BYTE", "Byte maldito");
				for(int i = 0; i < b.length - 1; i++){
					b[i] = b[i+1];
				}
				String n = "";
				try {
					n = new String(b, "UTF-8");
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try{
					JSONObject jobj2 = new JSONObject(n);
					r = jobj2.getInt("resposta");
				} catch (JSONException e2){
					Log.e("JSON", "Error parsing JSONString: " + n);
				}
			}
		}

		return r;
	}

	public static Map<String, Object> parseLogin(String JSONStr){
		Map<String, Object> map = new HashMap<String, Object>();
		Log.d("JSON_LOGIN", JSONStr);
		
		try{
			JSONObject jobj = new JSONObject(JSONStr);
			JSONObject login = jobj.getJSONObject("login");
			map.put("session_id", login.getString("sessionID"));
			map.put("nome", login.getString("nome"));
			map.put("cpf", login.getString("cpf"));
			map.put("email", login.getString("email"));
			map.put("telefone1", login.getString("telefone1"));
			map.put("telefone2", login.getString("telefone2"));
			
			JSONObject e = login.getJSONObject("endereco");
				
			map.put("logradouro", e.getString("logradouro"));	
			map.put("numero", e.getInt("numero"));
			map.put("complemento", e.getString("complemento"));
			map.put("bairro", e.getString("bairro"));
			map.put("cidade", e.getString("cidade"));
			map.put("uf", e.getString("uf"));
			map.put("cep", e.getString("cep"));
			
		} catch (JSONException e) {
			Log.e("JSON", "Error parsing JSONString: " + JSONStr);
		}
		
		return map;
	}
	
	public static List<Map<String, String>> parseBuscaLivro(String JSONStr){
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		
		try{
			JSONObject jobj = new JSONObject(JSONStr);
			JSONArray livros = jobj.getJSONArray("livros");

			for(int i = 0; i < livros.length(); i++){
				map = new HashMap<String, String>();
				JSONObject t = livros.getJSONObject(i);
				map.put("codigo", t.getString("codigo"));
				map.put("isbn", t.getString("isbn"));
				map.put("titulo", t.getString("titulo"));
				map.put("autor", t.getString("autor"));
				map.put("editora", t.getString("editora"));
				map.put("quantidade", t.getString("quantidade"));
				map.put("preco", t.getString("preco"));
				map.put("usado", t.getString("usado"));
				list.add(map);
			}
			
		} catch (JSONException e) {
			Log.e("JSON", "Error parsing JSONString Livro: " + JSONStr);
		}
		
		return list;
	}
	
	public static List<Map<String, Object>> parseBuscaPedido(String JSONStr){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> listProdutos;
		Map<String, Object> map = null;
		
		try{
			JSONObject jobj = new JSONObject(JSONStr);
			JSONArray pedidos = jobj.getJSONArray("pedidos");
			Log.d("JSON", "Pedido length = " + pedidos.length());
			
			for(int i = 0; i < pedidos.length(); i++){
				map = new HashMap<String, Object>();
				JSONObject t = pedidos.getJSONObject(i);
				map.put("id", t.getInt("id"));
				map.put("datahora", t.getString("datahora"));
				map.put("estado", t.getString("estado"));
				map.put("total", new Dinheiro(t.getDouble("total")));

				// Produtos
				JSONArray produtos = t.getJSONArray("produtos");
				Map<String, Object> map_prod;
				listProdutos = new ArrayList<Map<String,Object>>();
				for(int j = 0; j < produtos.length(); j++){
					map_prod = new HashMap<String, Object>();
					JSONObject p = produtos.getJSONObject(j);
					map_prod.put("isbn", p.getString("isbn"));
					map_prod.put("titulo", p.getString("titulo"));
					map_prod.put("quantidade", p.getInt("quantidade"));
					map_prod.put("preco", new Dinheiro(p.getDouble("preco")));
					
					listProdutos.add(map_prod);
				}
				
				map.put("produtos", listProdutos);
				list.add(map);
			}
			
		} catch (JSONException e) {
			Log.e("JSON", "Error parsing JSONString Pedido: " + JSONStr);
			Log.e("JSON", "Error message: " + e.getMessage() +", " + e.getCause());
		}
		
		return list;
	}
	
	public static String LivroToJSON(List<LivroNovo> list){
		String json = "{ \"livros\": [";
		
		for(int i = 0; i < list.size(); i++){
			json += "{";
				LivroNovo ln = list.get(i);
				json += "\"codigo\":\"" + ln.getCodigo() + "\",";
				json += "\"isbn\":\"" + ln.getIsbn() + "\",";
				json += "\"titulo\":\"" + ln.getTitulo() + "\",";
				json += "\"autor\":\"" + ln.getAutor() + "\",";
				json += "\"editora\":\"" + ln.getEditora() + "\",";
				json += "\"quantidade\":\"" + ln.getQuantidade() + "\",";
				json += "\"preco\":\"" + ln.getPreco() + "\",";
				json += "\"usado\":\"" + ln.getUsado() + "\"";
				
				if(i < list.size() - 1)
					json += "},";
				else
					json += "}";			
		}

		json += "]}";
		return json;
	}
	
	public static List<LivroNovo> LivroFromJSON(String json){
		List<LivroNovo> list = new ArrayList<LivroNovo>();
		
		try{
			JSONObject jobj = new JSONObject(json);
			JSONArray livros = jobj.getJSONArray("livros");
			LivroNovo ln;
			for(int i = 0; i < livros.length(); i++){
				JSONObject t = livros.getJSONObject(i);
				ln = new LivroNovo(
						Integer.parseInt(t.getString("codigo")),
						Integer.parseInt(t.getString("quantidade")),
						new Dinheiro(t.getString("preco")),
						t.getString("isbn"),
						t.getString("titulo"),
						t.getString("autor"),
						t.getString("editora"),
						t.getString("usado"));
				
				list.add(ln);
			}
			
		} catch (JSONException e) {
			Log.e("JSON", "Error parsing JSONString: " + json);
		}

		return list;
	}
	
	public static List<ItemPedido> ItemPedidoFromJSON(String json){
		List<ItemPedido> list = new ArrayList<ItemPedido>();
		
		try{
			JSONObject jobj = new JSONObject(json);
			JSONArray itensPedido = jobj.getJSONArray("itensPedido");
			
			JSONObject jLivro;

			ItemPedido item;
			for(int i = 0; i < itensPedido.length(); i++){
				JSONObject ip = itensPedido.getJSONObject(i);
				
				jLivro = ip.getJSONObject("produto");
				
				item = new ItemPedido(Integer.parseInt(ip.getString("quantidade")),
						new LivroNovo(
							Integer.parseInt(jLivro.getString("codigo")),
							Integer.parseInt(jLivro.getString("quantidade")),
							new Dinheiro(jLivro.getString("preco")),
							jLivro.getString("isbn"),
							jLivro.getString("titulo"),
							jLivro.getString("autor"),
							jLivro.getString("editora")),
						new Dinheiro(ip.getString("totalItem")));
				
				list.add(item);
			}
			
		} catch (JSONException e) {
			Log.e("JSON", "Error parsing JSONString: " + json);
		}

		return list;
	}
	
	public static String ItemPedidoToJSON(List<ItemPedido> list){
		String json = "{ \"itensPedido\": [";
		int i = 0;
		for(ItemPedido item : list){
			json += "{";
			json += "\"quantidade\":\""+item.getQuantidade()+"\",";
			json += "\"produto\":{";
			
				
				json += "\"codigo\":\"" + item.getProduto().getCodigo() + "\",";
				json += "\"isbn\":\"" + ((LivroNovo)item.getProduto()).getIsbn() + "\",";
				json += "\"titulo\":\"" + ((LivroNovo)item.getProduto()).getTitulo() + "\",";
				json += "\"autor\":\"" + ((LivroNovo)item.getProduto()).getAutor() + "\",";
				json += "\"editora\":\"" + ((LivroNovo)item.getProduto()).getEditora() + "\",";
				json += "\"quantidade\":\"" + ((LivroNovo)item.getProduto()).getQuantidade() + "\",";
				json += "\"preco\":\"" + ((LivroNovo)item.getProduto()).getPreco().toDouble() + "\"";
				
				json += "},";
				
				json += "\"totalItem\":\"" + item.getTotalItem().toDouble() + "\"";
				
				
				if(i < list.size() - 1)
					json += "},";
				else
					json += "}";	
				i++;
		}

		json += "]}";
		return json;
	}
	
	public static String parseImage(String JSONStr){
		String img = "";
		
		try{
			JSONObject jobj = new JSONObject(JSONStr);
			img = jobj.getString("image"); 
		} catch (JSONException e){
			e.printStackTrace();
		}
		
		return img;
	}
	
	public static LivroNovo parseBuscaISBN(String json){
		LivroNovo livro = null;
		try{
			JSONObject jobj = new JSONObject(json);
			livro = new LivroNovo(0, 0, null, jobj.getString("isbn"), jobj.getString("titulo"), jobj.getString("autor"), jobj.getString("editora"));
		} catch(JSONException e){
			Log.e("BUSCA_ISBN_JSON", "Erro ao parsear: " + json);
		}
		
		return livro;
	}
}