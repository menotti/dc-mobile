public class ImageAdapter extends BaseAdapter {
	private Context mContext;
	
	//Mantendo todos os ids num array
	public Integer[] thumbIds = {
			R.drawable.foto_1, R.drawable.foto_2,
			R.drawable.foto_3, R.drawable.foto_4
	};
	
	//Construtor
	public ImageAdapter(Context c){
		mContext = c;
	}
	
	@Override
	//Retorna o tamanho do array
	public int getCount() {
		return thumbIds.length;
	}

	@Override
	//Retorna um elemento do array
	public Object getItem(int position) {
		return thumbIds[position];
	}

	@Override
	//Nao sera usado
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView = new ImageView(mContext);
		imageView.setImageResource(thumbIds[position]);
		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		imageView.setLayoutParams(new GridView.LayoutParams(70,70));
		return imageView;
	}
}