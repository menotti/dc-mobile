public class ImageAdapter extends BaseAdapter {
	private Context mContext;
	
	//Mantendo todos os ids num array
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
	public View getView(int position, View convertView,
	ViewGroup parent) {
		ImageView imageView = new ImageView(mContext);
		imageView.setImageResource(thumbIds[position]);
		imageView.setLayoutParams(new GridView.LayoutParams(200,200));
		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		return imageView;
	}
}