package com.example.grade;

import android.annotation.TargetApi;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	private Context mContext;
	
	public Integer[] thumbIds = {
		R.drawable.sample_0, R.drawable.sample_1,
		R.drawable.sample_2, R.drawable.sample_3,
		R.drawable.sample_4, R.drawable.sample_5,
		R.drawable.sample_6, R.drawable.sample_7
	};

	//Construtor
	public ImageAdapter(Context c){
		mContext = c;
	}
	@Override
	//Método que deve retornar a contagem dos elementos do adaptador
	public int getCount() {
		return thumbIds.length;
	}	

	@Override
	/* Retorna um dos elementos do adaptador, no caso
	 * queremos retornar o elemento do array
	 * de imagens.
	 */
	public Object getItem(int position) {
		return thumbIds[position];
	}

	@Override
	//Não será usado
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView = new ImageView(mContext);
		imageView.setImageResource(thumbIds[position]);
		imageView.setLayoutParams(new GridView.LayoutParams(200,200));
		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		return imageView;
	}

}
