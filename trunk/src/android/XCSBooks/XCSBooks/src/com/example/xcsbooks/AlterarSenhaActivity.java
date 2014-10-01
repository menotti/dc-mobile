package com.example.xcsbooks;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.xcsbooks.control.CadastroControl;

public class AlterarSenhaActivity extends BaseActivity {
	private EditText prevSenhaEdit;
	private EditText newSenhaEdit;
	private Button submit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alterar_senha);
		
		prevSenhaEdit = (EditText) findViewById(R.id.prevSenha);
		newSenhaEdit = (EditText) findViewById(R.id.newSenha);
		submit = (Button) findViewById(R.id.submitNewSenha);
		
		
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String prevSenha = prevSenhaEdit.getText().toString(); 
				String newSenha = newSenhaEdit.getText().toString();
				
				boolean teste = true;
				if(prevSenha.isEmpty()){
					teste = false;
					prevSenhaEdit.setError("Digite sua senha atual");
				}
				if(newSenha.isEmpty()){
					teste = false;
					newSenhaEdit.setError("Digite sua nova senha");
				}
				
				if(teste){
					int r = CadastroControl.alterarSenha(newSenha, prevSenha);
					if(r < 0){
						if(r == -2)
							prevSenhaEdit.setError("Senha incorreta");
						else
							Toast.makeText(AlterarSenhaActivity.this, "Erro ao alterar senha", Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(AlterarSenhaActivity.this, "Senha alterada com sucesso", Toast.LENGTH_SHORT).show();
						finish();
					}
				}
			}
		});
		
	}


}
