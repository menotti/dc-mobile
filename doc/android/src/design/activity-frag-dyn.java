public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Como estamos usando o pacote de suporte
		//Precisamos usar o Manager desse pacote
		FragmentManager fm = getSupportFragmentManager();
		//Voce pode obter um fragmento da mesma forma que obtem
		//qualquer outra view usando o FragmentManager
		Fragment fragment = fm.findFragmentById(R.id.fragment_content);
		
		if(fragment == null){
			//Comeca uma transacao de fragmentos
			FragmentTransaction ft = fm.beginTransaction();
			//Adiciona o fragmento 
			ft.add(R.id.fragment_content, new BasicFragment());
			//"Commita" a transacao
			ft.commit();
		}
	}
...