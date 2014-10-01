package com.example.xcsbooks;

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

public class AlterarCadastroClienteActivity extends BaseActivity {

	private Cliente cliente;
	private Button mBtnContinue;
	private EditText mEdtNome;
	private EditText mEdtEmail;
	private EditText mEditTel1;
	private EditText mEditTel2;
	
	public static final String KEY_NOME = "com.example.xcsbooks.CADASTRAR_NOME";
	public static final String KEY_EMAIL = "com.example.xcsbooks.CADASTRAR_EMAIL";
	public static final String KEY_TEL1 = "com.example.xcsbooks.CADASTRAR_TEL1";
	public static final String KEY_TEL2 = "com.example.xcsbooks.CADASTRAR_TEL2";

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alterar_cadastro_cliente);
		
		cliente = LoginControl.getClienteLogado();
		
		mBtnContinue = (Button) findViewById(R.id.alterarCliente_btnContinue);
		mEdtNome = (EditText) findViewById(R.id.alterarCliente_txtName);
		mEdtEmail = (EditText) findViewById(R.id.alterarCliente_txtEmail);
		mEditTel1 = (EditText) findViewById(R.id.alterarCliente_txtTel1);
		mEditTel2 = (EditText) findViewById(R.id.alterarCliente_txtTel2);
		
		mEdtNome.setText(cliente.getNome());
		mEdtEmail.setText(cliente.getEmail());
		mEditTel1.setText(cliente.getTelefone1());
		mEditTel2.setText(cliente.getTelefone2());
		
		mBtnContinue.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(AlterarCadastroClienteActivity.this, AlterarCadastroEnderecoActivity.class);
				intent.putExtra(KEY_NOME, mEdtNome.getText().toString());
				intent.putExtra(KEY_EMAIL, mEdtEmail.getText().toString());
				intent.putExtra(KEY_TEL1, mEditTel1.getText().toString());
				intent.putExtra(KEY_TEL2, mEditTel2.getText().toString());
				
				startActivity(intent);
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		return super.onOptionsItemSelected(item);
	}

}
