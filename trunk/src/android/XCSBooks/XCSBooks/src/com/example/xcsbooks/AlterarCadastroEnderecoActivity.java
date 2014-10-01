package com.example.xcsbooks;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.example.xcsbooks.control.CadastroControl;
import com.example.xcsbooks.control.LoginControl;
import com.example.xcsbooks.model.Cliente;

import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AlterarCadastroEnderecoActivity extends BaseActivity {

	private Cliente cliente;
	private Button mBtnAtualizar;
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
		setContentView(R.layout.activity_alterar_cadastro_endereco);
		
		cliente = LoginControl.getClienteLogado();
		
		mBtnAtualizar = (Button) findViewById(R.id.alterarEndereco_btnAtualizar);
		mEditLogr = (EditText) findViewById(R.id.alterarEndereco_txtLogradouro);
		mEditNum = (EditText) findViewById(R.id.alterarEndereco_txtNumero);
		mEditCompl = (EditText) findViewById(R.id.alterarEndereco_txtComplemento);
		mEditBairro = (EditText) findViewById(R.id.alterarEndereco_txtBairro);
		mEditCidade = (EditText) findViewById(R.id.alterarEndereco_txtCidade);
		mEditUF = (EditText) findViewById(R.id.alterarEndereco_txtEstado);
		mEditCEP = (EditText) findViewById(R.id.alterarEndereco_txtCep);
		
		// pegar dados
		if(cliente.getEndereco()!=null){
			mEditLogr.setText(cliente.getEndereco().getLogradouro());
			mEditNum.setText(String.valueOf(cliente.getEndereco().getNumero()));
			mEditCompl.setText(cliente.getEndereco().getComplemento());
			mEditBairro.setText(cliente.getEndereco().getBairro());
			mEditCidade.setText(cliente.getEndereco().getCidade());
			mEditUF.setText(cliente.getEndereco().getUf());
			mEditCEP.setText(cliente.getEndereco().getCep());
		}
		
		mBtnAtualizar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// Envia dados para o controller! Muitos dados!!!
				Intent intent = getIntent();
				List<NameValuePair> list = new ArrayList<NameValuePair>();
				list.add(new BasicNameValuePair("nome", intent.getStringExtra(CadastrarClienteActivity.KEY_NOME)));
				list.add(new BasicNameValuePair("email", intent.getStringExtra(CadastrarClienteActivity.KEY_EMAIL)));
				list.add(new BasicNameValuePair("tel1", intent.getStringExtra(CadastrarClienteActivity.KEY_TEL1)));
				list.add(new BasicNameValuePair("tel2", intent.getStringExtra(CadastrarClienteActivity.KEY_TEL2)));

				list.add(new BasicNameValuePair("logradouro", mEditLogr.getText().toString()));
				list.add(new BasicNameValuePair("numero", mEditNum.getText().toString()));
				list.add(new BasicNameValuePair("complemento", mEditCompl.getText().toString()));
				list.add(new BasicNameValuePair("bairro", mEditBairro.getText().toString()));
				list.add(new BasicNameValuePair("cidade", mEditCidade.getText().toString()));
				list.add(new BasicNameValuePair("estado", mEditUF.getText().toString()));
				list.add(new BasicNameValuePair("cep", mEditCEP.getText().toString()));
				//---
				

				Cliente cli = CadastroControl.alterar(list);
				
				if(cli != null){
					// atualizar dados do objeto cliente
					cliente.setNome(intent.getStringExtra(CadastrarClienteActivity.KEY_NOME));
					cliente.setEmail(intent.getStringExtra(CadastrarClienteActivity.KEY_EMAIL));
					cliente.setTelefone1(intent.getStringExtra(CadastrarClienteActivity.KEY_TEL1));
					cliente.setTelefone2(intent.getStringExtra(CadastrarClienteActivity.KEY_TEL2));
					
					cliente.getEndereco().setLogradouro(mEditLogr.getText().toString());
					cliente.getEndereco().setNumero(Integer.valueOf(mEditNum.getText().toString()));
					cliente.getEndereco().setComplemento(mEditCompl.getText().toString());
					cliente.getEndereco().setBairro(mEditBairro.getText().toString());
					cliente.getEndereco().setCep(mEditCidade.getText().toString());
					cliente.getEndereco().setUf(mEditUF.getText().toString());
					cliente.getEndereco().setCep(mEditCEP.getText().toString());
					
					Toast.makeText(getApplicationContext(), "Alteração realizada com sucesso", Toast.LENGTH_LONG).show();
					intent 	= new Intent(AlterarCadastroEnderecoActivity.this, HomeActivity.class);
					startActivity(intent);
				}
				
			}
		});
	}
}
