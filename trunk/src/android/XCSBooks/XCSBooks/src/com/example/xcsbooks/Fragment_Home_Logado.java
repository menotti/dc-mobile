package com.example.xcsbooks;

import com.example.xcsbooks.control.LoginControl;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment_Home_Logado extends Fragment{
	private TextView tv;
	
	public View onCreateView(LayoutInflater inflater, final ViewGroup container,
			   Bundle savedInstanceState) {
		
		View myFragmentView = inflater.inflate(R.layout.fragment_home_logado, container, false);
		
		tv = (TextView) myFragmentView.findViewById(R.id.fragLogado_txtMsgNomeUsuario);
		tv.setText(tv.getText().toString() + " " + LoginControl.getClienteLogado().getNome());
		
		return myFragmentView;
	}
}
