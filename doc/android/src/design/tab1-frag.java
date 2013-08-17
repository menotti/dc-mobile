public class Tab1Fragment extends Fragment {
	public View onCreateView(LayoutInflater inflater,
	ViewGroup container, Bundle savedInstanceState){
		
		if(container == null){
			return null;
		}
		
		return (LinearLayout) inflater.
			inflate(R.layout.tab_fragment1, container, false);
	}
}