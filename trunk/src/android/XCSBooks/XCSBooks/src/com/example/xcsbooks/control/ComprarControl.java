package com.example.xcsbooks.control;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.util.Log;

public class ComprarControl {
	public static String COMPRAR_URI = "http://diskexplosivo.com/xcsbooks/insert_order.php";
	
	public static int comprar(List<HashMap<String, Object>> pedido){
		AsyncTask<URI, Integer, String> task;
		String resposta = null;
		
		String username = LoginControl.getClienteLogado().getUsername();
		List<NameValuePair> comprarData = new ArrayList<NameValuePair>();
		comprarData.add(new BasicNameValuePair("username", username));
		
		String pedidoStr = "";
		for(HashMap<String, Object> m : pedido){
			pedidoStr += m.get("itemCarrinho_codLivro");
			pedidoStr += "x";
			pedidoStr += m.get("itemCarrinho_quantidadeItem");
			pedidoStr += "/";
		}
		pedidoStr = pedidoStr.substring(0, pedidoStr.length()-1);
		
		Log.d("COMPRA_POST" , username + '\n' + pedidoStr);
		
		comprarData.add(new BasicNameValuePair("pedido", pedidoStr));
		
		try {
			//Faz um request para LOGIN_URI com os dados digitados
			task = new RequestTask(comprarData, COMPRAR_URI, RequestTask.REQUEST_POST).execute();
			//Obtém a resposta do back-end
			resposta = task.get();
		} catch (Exception e){
			Log.e("SEARCH_REQUEST", "Error on GET REQUEST to URL");
		}
		
		if(resposta != null){
			int test = JSONParser.parseResposta(resposta);
			Log.d("COMPRAR", "Resposta: " + test);
			return test;
		}
		return -99;
	}
}
