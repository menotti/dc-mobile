package com.example.xcsbooks.control;

import java.net.URI;
import java.util.List;

import org.apache.http.NameValuePair;

import android.os.AsyncTask;
import android.util.Log;

import com.example.xcsbooks.model.Dinheiro;
import com.example.xcsbooks.model.LivroUsado;

public class CadastrarLivroUsadaControl {
	public static String CADASTROLIVROUSADO_URI = "http://diskexplosivo.com/xcsbooks/insert_usedbook.php";

	public static int cadastrar(List<NameValuePair> list) {
		AsyncTask<URI, Integer, String> task;
		String resposta = null;
	
		try {
			//Faz um request para LOGIN_URI com os dados digitados
			task = new RequestTask(list, CADASTROLIVROUSADO_URI, RequestTask.REQUEST_POST).execute();
			//Obtem a resposta do back-end
			resposta = task.get();
		} catch (Exception e){
			Log.e("CADASTRO_REQUEST", "Error on POST REQUEST to URL");
		}
		
		if(resposta != null){
			return JSONParser.parseResposta(resposta);
		}
		return -99;
	}
}
