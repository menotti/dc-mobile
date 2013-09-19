package com.example.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class Tab3Fragment extends Fragment {
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		
		if(container == null){
			return null;
		}
		
		return (LinearLayout) inflater.inflate(R.layout.tab_fragment3, container, false);
	}
}
