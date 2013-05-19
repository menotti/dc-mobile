public class MainActivity extends Activity {
	private ListView lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Obtem a lista 
		ListView lv = (ListView) findViewById(R.id.listView1);
		
		//Cria uma lista de maps(key->value) dos views de cada item do ListView
		List<Map> list = new ArrayList<Map>();
		Map map = new HashMap();
		map.put("userIcon", R.drawable.miku);
		map.put("userName", "Hatsune Miku");
		map.put("userText", "Texto exemplo para o adaptador");
		list.add(map);
		map = new HashMap();	
		map.put("userIcon", R.drawable.luka);
		map.put("userName", "Megurine Luka");
		map.put("userText", "Texto exemplo para o adaptador");
		list.add(map);
		
		//Cria um adaptador pro layout customizado
		SimpleAdapter adapter = new SimpleAdapter(this, 
			(List<? extends Map<String, ?>>) list, R.layout.item,
			new String[] {"userIcon", "userName", "userText"},
			new int[] {R.id.userIcon, R.id.username, R.id.usertext});
		
		lv.setAdapter(adapter);	 	
	}