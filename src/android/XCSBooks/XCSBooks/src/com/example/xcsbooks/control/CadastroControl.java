package com.example.xcsbooks.control;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.xcsbooks.HomeActivity;
import com.example.xcsbooks.model.Cliente;
import com.example.xcsbooks.model.Endereco;

public class CadastroControl {
	public static String CADASTRO_URI = "http://diskexplosivo.com/xcsbooks/register.php";
	public static String ALTERA_CADASTRO_URI = "http://diskexplosivo.com/xcsbooks/updateUser.php";
	public static String ALTERA_SENHA_URI = "http://diskexplosivo.com/xcsbooks/updatePassword.php";
	
	public static Cliente cadastrar(List<NameValuePair> list) {
		AsyncTask<URI, Integer, String> task;
		String resposta = null;
	
		try {
			//Faz um request para LOGIN_URI com os dados digitados
			task = new RequestTask(list, CADASTRO_URI, RequestTask.REQUEST_POST).execute();
			//Obtem a resposta do back-end
			resposta = task.get();
		} catch (Exception e){
			Log.e("CADASTRO_REQUEST", "Error on POST REQUEST to URL");
		}
		
		if(resposta != null){
			

			int test = JSONParser.parseResposta(resposta);
			if(test < 0){
				switch(test){
				case -20:
					makeToast("Preencha o nome de usuário");
					break;
				case -21:
					makeToast("Preencha a senha");
					break;
				case -22:
					makeToast("Preencha seus dados");
					break;
				case -23:
					makeToast("Preencha seu e-mail");
					break;
				case -30:
					makeToast("Preencha seu endereço");
					break;
				case -11:
					makeToast("Erro ao enviar informações do cliente");
					break;
				case -10:
					makeToast("Erro ao enviar endereço do cliente");
					break;
				}
				
				return null;
			}
				
			
			//Cliente(String * 7, Endereco(S	tring * 7))
			Cliente cli = 
				new Cliente(
					list.get(0).getValue(),
					list.get(1).getValue(),
					list.get(2).getValue(),
					list.get(3).getValue(),
					list.get(4).getValue(),
					list.get(5).getValue(),
					list.get(6).getValue(),
					new Endereco(
							list.get(7).getValue(),
							Integer.parseInt(list.get(8).getValue()),
							list.get(9).getValue(),
							list.get(10).getValue(),
							list.get(11).getValue(),
							list.get(12).getValue(),
							list.get(13).getValue()));
			return cli;
			
		}			
		return null;
	}

	public static Cliente alterar(List<NameValuePair> list) {
		AsyncTask<URI, Integer, String> task;
		String resposta = null;
	
		try {
			//Faz um request para LOGIN_URI com os dados digitados
			task = new RequestTask(list, ALTERA_CADASTRO_URI, RequestTask.REQUEST_POST).execute();
			//Obtem a resposta do back-end
			resposta = task.get();
		} catch (Exception e){
			Log.e("CADASTRO_REQUEST", "Error on POST REQUEST to URL");
		}
		
		if(resposta != null){
			

			int test = JSONParser.parseResposta(resposta);
			if(test < 0)
				return null;
			
			SharedPreferences prefs = HomeActivity.getInstance().getSharedPreferences("LOGIN_CREDENTIALS", HomeActivity.getInstance().MODE_PRIVATE);
			
			//Cliente(String * 7, Endereco(String * 7))
			Cliente cli = 
				new Cliente(
					prefs.getString("username", "null"),
					prefs.getString("senha", "null"),
					list.get(0).getValue(),
					prefs.getString("cpf", "null"),
					list.get(1).getValue(),
					list.get(2).getValue(),
					list.get(3).getValue(),
					new Endereco(
							list.get(4).getValue(),
							Integer.parseInt(list.get(5).getValue()),
							list.get(6).getValue(),
							list.get(7).getValue(),
							list.get(8).getValue(),
							list.get(9).getValue(),
							list.get(10).getValue()));
			
			SharedPreferences.Editor editor = prefs.edit();
			editor.putString("nome", cli.getNome());
			editor.putString("cpf", cli.getCpf());
			editor.putString("email", cli.getEmail());
			editor.putString("telefone1", cli.getTelefone1());
			editor.putString("telefone2", cli.getTelefone2());
			editor.putString("logradouro", cli.getEndereco().getLogradouro());
			editor.putString("numero", String.valueOf(cli.getEndereco().getNumero()));
			editor.putString("complemento", cli.getEndereco().getComplemento());
			editor.putString("bairro", cli.getEndereco().getBairro());
			editor.putString("cidade", cli.getEndereco().getCidade());
			editor.putString("uf", cli.getEndereco().getUf());
			editor.putString("cep", cli.getEndereco().getCep());
			editor.commit();		
			return cli;
			
		}			
		return null;
	}
	
	
	public static int alterarSenha(String novaSenha, String velhaSenha){
		AsyncTask<URI, Integer, String> task;
		String resposta = null;
	
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("prevsenha", velhaSenha));
		list.add(new BasicNameValuePair("senha", novaSenha));
		Log.d("ALTERA_SENHA", list.get(0).getName() + list.get(0).getValue());
		
		try {
			//Faz um request para LOGIN_URI com os dados digitados
			task = new RequestTask(list, ALTERA_SENHA_URI, RequestTask.REQUEST_POST).execute();
			//Obtem a resposta do back-end
			resposta = task.get();
		} catch (Exception e){
			Log.e("CADASTRO_REQUEST", "Error on POST REQUEST to URL");
		}
		
		if(resposta != null){
			int test = JSONParser.parseResposta(resposta);
			return test;
		}
		return -99;
	}
	
	private static void makeToast(String text){
		Toast.makeText(MyApplication.getInstance(), text, Toast.LENGTH_LONG).show();
	}
}
