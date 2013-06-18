public class BasicFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater,
		ViewGroup container, Bundle savedInstanceState){
		
		//Obtem o layout do fragmento em uma view
		View view = inflater.inflate(R.layout.fragment, container, false);
		
		//Obtem o botao da view
		Button button = (Button) view.findViewById(R.id.fragment_button);
		
		//Um listener simples para o botao
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Activity activity = getActivity();
				
				if(activity != null){
					Toast.makeText(activity,
					"A toast to a fragment", Toast.LENGTH_SHORT).show();
				}				
			}
		});
		return view;		
	}
}