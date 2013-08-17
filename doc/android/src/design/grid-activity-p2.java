 @Override
public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	GridView gridView = (GridView) findViewById(R.id.gridview);
	gridView.setAdapter(new ImageAdapter(this));
	
	//Cria um listener para o evento de clique em um elemento da grade
	gridView.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, 
		 int pos, long id) {		
			//Envia o id da imagem para o FullImageActivity
			Intent intent = new Intent(getApplicationContext(),
			FullImageActivity.class);
			intent.putExtra("id", pos);
			startActivity(intent);
		}
	});
}