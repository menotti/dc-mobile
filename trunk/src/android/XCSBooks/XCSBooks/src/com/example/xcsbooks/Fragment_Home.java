package com.example.xcsbooks;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Fragment_Home extends Fragment {

	public View onCreateView(LayoutInflater inflater, final ViewGroup container,
			   Bundle savedInstanceState) {
		View myFragmentView = inflater.inflate(R.layout.fragment_home, container, false);
		
		Button mBtnCadastar = (Button)myFragmentView.findViewById(R.id.frag_btnCadastrar);
		Button mBtnLogar = (Button)myFragmentView.findViewById(R.id.frag_btnLogar);
		
		mBtnCadastar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(container.getContext(), CadastrarClienteActivity.class);
				startActivity(intent);
			}
		});
		
		mBtnLogar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(container.getContext(), LogarActivity.class);
				startActivity(intent);
			}
		});
		
		return myFragmentView;
	}
}
