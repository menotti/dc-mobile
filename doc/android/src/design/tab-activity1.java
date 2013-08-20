public class TabLayoutActivity extends Activity
						implements TabHost.OnTabChangeListener {
	private TabHost mTabHost;
	private HashMap<String, TabInfo> mapTabInfo =
									new HashMap<String, TabInfo>();
	private TabInfo mLastTab = null;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Estabelece o layout da activity
		setContentView(R.layout.activity_tab_layout);
		
		//Metodo para inicializar as abas
		initialiseTabHost(savedInstanceState);
		if(savedInstanceState != null){
			//Determina a aba que esta selecionada
			mTabHost.setCurrentTabByTag
				(savedInstanceState.getString("tab"));
		}
	}