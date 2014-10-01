package com.example.xcsbooks.control;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.util.Log;

import com.example.xcsbooks.model.Dinheiro;
import com.example.xcsbooks.model.ItemPedido;
import com.example.xcsbooks.model.LivroNovo;
import com.example.xcsbooks.model.Pedido;

public class BuscaControl {
	public static String BUSCA_LIVRO_URI = "http://diskexplosivo.com/xcsbooks/search.php";
	public static String BUSCA_PEDIDO_URI = "http://diskexplosivo.com/xcsbooks/pedido_cliente.php";
	public static String BUSCA_ISBN_URI = "http://diskexplosivo.com/xcsbooks/bookDetails.php";
	
	 public static List<LivroNovo> buscarLivro(String termo){
         AsyncTask<URI, Integer, String> task;
         String resposta = null;
         
         List<NameValuePair> searchData = new ArrayList<NameValuePair>();
         searchData.add(new BasicNameValuePair("s", termo));
         
         try {
             //Faz um request para BUSCA_LIVRO_URI com os dados digitados
             task = new RequestTask(searchData, BUSCA_LIVRO_URI, RequestTask.REQUEST_GET).execute();
             //Obtém a resposta do back-end
             resposta = task.get();
         } catch (Exception e){
             Log.e("SEARCH_REQUEST", "Error on GET REQUEST to URL");
         }
         
         if(resposta != null){
         
        	 int test = JSONParser.parseResposta(resposta);
             if(test < 0){
            	 Log.d("SEARCH_F", "Resposta: " + test);
                 return new ArrayList<LivroNovo>();
             }
             //Obtém resposta JSON parseada
             List <? extends Map<String, ?>> u = JSONParser.parseBuscaLivro(resposta);
     
             Map t = null;
             List<LivroNovo> list = new ArrayList<LivroNovo>();
             for(int i = 0; i < u.size(); i++){
                     t = new HashMap();
                     t = u.get(i);
                     LivroNovo l = new LivroNovo(
                                     Integer.parseInt((String) t.get("codigo")),
                                     Integer.parseInt((String) t.get("quantidade")),
                                     new Dinheiro((String) t.get("preco")),
                                     (String)t.get("isbn"),
                                     (String)t.get("titulo"),
                                     (String)t.get("autor"),
                                     (String)t.get("editora"),
                                     (String)t.get("usado"));
                     list.add(l);
                     Log.d("LIVRO_I", "Nome: " + l.getTitulo());
             }
             
             //Lê a lista, e cria uma segunda lista, mas com os dados do livro em forma de model
             return list;
         }
   
         return new ArrayList<LivroNovo>();
 }
	

	public static List<Pedido> buscarPedido(String cliente) {
		List<Pedido> listaPedidos = new ArrayList<Pedido>();
		AsyncTask<URI, Integer, String> task;
		String resposta = null;
		
		List<NameValuePair> searchData = new ArrayList<NameValuePair>();
		searchData.add(new BasicNameValuePair("cliente", cliente));
		
		try {
			//Faz um request para BUSCA_PEDIDO_URI com os dados digitados
			task = new RequestTask(searchData, BUSCA_PEDIDO_URI, RequestTask.REQUEST_GET).execute();
			//Obtém a resposta do back-end
			resposta = task.get();
		} catch (Exception e){
			Log.e("SEARCH_REQUEST", "Error on GET REQUEST to URL");
		}
		
		if(resposta != null){
			
			int test = JSONParser.parseResposta(resposta);
			if(test < 0){
				Log.e("SEARCH_PEDIDO", "Resposta: " + test);
				return listaPedidos;
			}
			
			//Obtém resposta JSON parseada
			List <? extends Map<String, ?>> u = JSONParser.parseBuscaPedido(resposta);
		
			Map t = null;
			List<Pedido> listPedido = new ArrayList<Pedido>();
			List<ItemPedido> itensPedido = null;
			for(int i = 0; i < u.size(); i++){
				t = new HashMap();
				t = u.get(i);
				//Mapa do pedido contém 5 itens: id, datahora, estado, total e lista de produtos
				Pedido p = new Pedido(
						Integer.parseInt(t.get("id").toString()),
						(String) t.get("datahora"),
						(String) t.get("estado"),
						new Dinheiro((t.get("total").toString()))
						);
				
				List<? extends Map<String, Object>> lp = (List<? extends Map<String, Object>>) t.get("produtos");
				itensPedido = new ArrayList<ItemPedido>();
				Map mp = null;
				for(int j = 0; j < lp.size(); j++){
					mp = lp.get(j);
					ItemPedido ip = new ItemPedido(Integer.valueOf(mp.get("quantidade").toString()),
							new LivroNovo(
								0,
								0,
								new Dinheiro(mp.get("preco").toString()),
								(String)mp.get("isbn"),
								(String)mp.get("titulo"),
								(String)mp.get("autor"),
								(String)mp.get("editora")));
					ip.setTotalItem(new Dinheiro(mp.get("preco").toString()));
					ip.setTotalItem(new Dinheiro(ip.getTotalItem().mult(ip.getQuantidade())));
					itensPedido.add(ip);	
				}
				p.setItens(itensPedido);
				listPedido.add(p);
			}
			
			//Lê a lista, e cria uma segunda lista, mas com os dados do livro em forma de model
			return listPedido;
		}

		return listaPedidos;
	}
	
	public static LivroNovo buscaISBN(String isbn){
	    AsyncTask<URI, Integer, String> task;
        String resposta = null;
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		
		List<NameValuePair> searchData = new ArrayList<NameValuePair>();
		searchData.add(new BasicNameValuePair("isbn", isbn));
		
		try {
			//Faz um request para BUSCA_PEDIDO_URI com os dados digitados
			task = new RequestTask(searchData, BUSCA_ISBN_URI, RequestTask.REQUEST_GET).execute();
			//Obtém a resposta do back-end
			resposta = task.get();
		} catch (Exception e){
			Log.e("SEARCH_REQUEST", "Error on GET REQUEST to URL");
		}
		
		if(resposta != null){
			
			int test = JSONParser.parseResposta(resposta);
			if(test < 0){
				LivroNovo livro = JSONParser.parseBuscaISBN(resposta);
				return livro;
			} else {
				return null;
			}
		
		}
		return null;
	}
	
}