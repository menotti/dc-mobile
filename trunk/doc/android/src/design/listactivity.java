public class MainActivity extends Activity {
	private ListView lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Obtem o array de strings para popular a lista
		String listStr[] = getResources().getStringArray(R.array.listString);
		
		//Obtem a lista 
		ListView lv = (ListView) findViewById(R.id.listView1);
		
		//Adaptador das strings para a lista
		lv.setAdapter(new ArrayAdapter<String>
			(this, android.R.layout.simple_list_item_1, listStr));
			
		/* Acao para quando clica num elemento da lista
		 * precisa criar um listener e programa-lo para
		 * realizar uma acao. */
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent,
			View view, int position, long id) {
				//Quando clicado, mostra um Toast
				Toast.makeText(getApplicationContext(),
					((TextView) view).getText(), Toast.LENGTH_SHORT).show();
			}
		});
	}
...