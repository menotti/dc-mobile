package com.example.xcsbooks;

import java.util.ArrayList;
import java.util.List;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.example.xcsbooks.control.CadastroControl;
import com.example.xcsbooks.control.LoginControl;
import com.example.xcsbooks.model.Cliente;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastrarEnderecoActivity extends BaseActivity {
	private Button mBtnCadastrar;
	private EditText mEditLogr;
	private EditText mEditNum;
	private EditText mEditCompl;
	private EditText mEditBairro;
	private EditText mEditCidade;
	private EditText mEditUF;
	private EditText mEditCEP;
	
	public final static String KEY_LOGR = "com.example.xcsbooks.CADASTRAR_LOGR";
	public final static String KEY_NUM = "com.example.xcsbooks.CADASTRAR_NUM";
	public final static String KEY_COMPL = "com.example.xcsbooks.CADASTRAR_COMPL";
	public final static String KEY_BAIRRO = "com.example.xcsbooks.CADASTRAR_BAIRRO";
	public final static String KEY_CIDADE = "com.example.xcsbooks.CADASTRAR_CIDADE";
	public final static String KEY_UF = "com.example.xcsbooks.CADASTRAR_UF";
	public final static String KEY_CEP = "com.example.xcsbooks.CADASTRAR_CEP";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar_endereco);
		
		mBtnCadastrar = (Button) findViewById(R.id.cadastrarEndereco_btnCadastrar);
		mEditLogr = (EditText) findViewById(R.id.cadastrarEndereco_txtLogradouro);
		mEditNum = (EditText) findViewById(R.id.cadastrarEndereco_txtNumero);
		mEditCompl = (EditText) findViewById(R.id.cadastrarEndereco_txtComplemento);
		mEditBairro = (EditText) findViewById(R.id.cadastrarEndereco_txtBairro);
		mEditCidade = (EditText) findViewById(R.id.cadastrarEndereco_txtCidade);
		mEditUF = (EditText) findViewById(R.id.cadastrarEndereco_txtEstado);
		mEditCEP = (EditText) findViewById(R.id.cadastrarEndereco_txtCep);
			
		mBtnCadastrar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				boolean teste = true;
				String message = "";
				String title = "";
				
				String logra = mEditLogr.getText().toString();
				String num = mEditNum.getText().toString();
				String bairro = mEditBairro.getText().toString();
				String cidade = mEditCidade.getText().toString();
				String uf = mEditUF.getText().toString();
				String cep = mEditCEP.getText().toString();
				
				if (cep.isEmpty()){
					teste = false;
					message = "Digite CEP!";
					mEditCEP.setError(message);
				}
				if (uf.isEmpty()){
					teste = false;
					message = "Digite UF!";
					mEditUF.setError(message);
				}
				if (cidade.isEmpty()){
					teste = false;
					message = "Digite cidade!";
					mEditCidade.setError(message);
				}
				if (bairro.isEmpty()){
					teste = false;
					message = "Digite bairro!";
					mEditBairro.setError(message);
				}				
				if (num.isEmpty()){
					teste = false;
					message = "Digite número!";
					mEditNum.setError(message);
				}
				if (logra.isEmpty()){
					teste = false;
					message = "Digite logradouro!";
					mEditLogr.setError(message);
				}
				
				if(teste){
					// Envia dados para o controller! Muitos dados!!!
					Intent intent = getIntent();
					List<NameValuePair> list = new ArrayList<NameValuePair>();
					list.add(new BasicNameValuePair("nome", intent.getStringExtra(CadastrarClienteActivity.KEY_NOME)));
					list.add(new BasicNameValuePair("cpf", intent.getStringExtra(CadastrarClienteActivity.KEY_CPF)));
					list.add(new BasicNameValuePair("email", intent.getStringExtra(CadastrarClienteActivity.KEY_EMAIL)));
					list.add(new BasicNameValuePair("tel1", intent.getStringExtra(CadastrarClienteActivity.KEY_TEL1)));
					list.add(new BasicNameValuePair("tel2", intent.getStringExtra(CadastrarClienteActivity.KEY_TEL2)));
					list.add(new BasicNameValuePair("username", intent.getStringExtra(CadastrarClienteActivity.KEY_UNAME)));
					list.add(new BasicNameValuePair("senha", intent.getStringExtra(CadastrarClienteActivity.KEY_PWD)));
					list.add(new BasicNameValuePair("logradouro", mEditLogr.getText().toString()));
					list.add(new BasicNameValuePair("numero", mEditNum.getText().toString()));
					list.add(new BasicNameValuePair("complemento", mEditCompl.getText().toString()));
					list.add(new BasicNameValuePair("bairro", mEditBairro.getText().toString()));
					list.add(new BasicNameValuePair("cidade", mEditCidade.getText().toString()));
					list.add(new BasicNameValuePair("estado", mEditUF.getText().toString()));
					list.add(new BasicNameValuePair("cep", mEditCEP.getText().toString()));
	
					Cliente cli = CadastroControl.cadastrar(list);
						
					if(cli != null){
						Toast.makeText(getApplicationContext(), "Cadastro realizado com sucesso", Toast.LENGTH_LONG).show();
						intent 	= new Intent(CadastrarEnderecoActivity.this, HomeActivity.class);
						startActivity(intent);
					}
				} 
			}
		});
		
	}
}
