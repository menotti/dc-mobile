public class TabLayoutActivity extends Activity {
	private TabHost mTabHost;
	private HashMap mapTabInfo = new HashMap();
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