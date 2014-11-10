package com.example.tabhostrelativelayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class Tab2Fragment extends Fragment {
	public View onCreateView(LayoutInflater inflater, 
			ViewGroup container, Bundle savedInstanceState){
		
		if(container == null){
			return null;
		}
		
		return (LinearLayout) inflater.inflate(R.layout.activity_tab2_fragment, 
			container, false);
	}
}
