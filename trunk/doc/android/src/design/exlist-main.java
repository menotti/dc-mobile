public class MainActivity extends Activity {
	private ExpandableListView mExpandableList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mExpandableList = (ExpandableListView) 
			findViewById(R.id.listaExpandivel);
		
		ArrayList<Parent> arrayParents = new ArrayList<Parent>();
		ArrayList<String> arrayChildren;
		
		//Array de fabricantes no arquivo de strings
		String parentsNames[] = getResources().
						getStringArray(R.array.Fabricantes);
		
		for(int i = 0; i < parentsNames.length; i++){
			/*Para cada pai "i" criar um novo objeto
			Parent para setar o nome e os filhos */
			Parent parent = new Parent();
			parent.setTitle(parentsNames[i]);
			
			arrayChildren = new	ArrayList<String>();
			/* Obtem os carros daquele fabricante
			 * primeiro obtendo o resource id (passando o nome do fabricante)
			 * depois usando esse resource id para obter o array de strings
			 */
			int resId = getResources().
				getIdentifier(parentsNames[i], "array", getPackageName());
			String childrenNames[] = getResources().getStringArray(resId);
			
			for(int j = 0; j < childrenNames.length; j++){
				arrayChildren.add(childrenNames[j]);
			}
			
			parent.setmArrayChildren(arrayChildren);
			arrayParents.add(parent);
		}
		
		mExpandableList.setAdapter(
			new CustomAdapter(MainActivity.this, arrayParents));
	}
	...
}