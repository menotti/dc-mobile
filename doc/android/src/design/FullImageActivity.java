public class FullImageActivity extends Activity {

	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.full_image);
		
		//Obtem os dados do intent
		Intent intent = getIntent();
		
		//Seleciona o id da imagem
		int id = intent.getExtras().getInt("id");
		ImageAdapter imageAdapter = new ImageAdapter(this);
		
		//Configura o ImageView para mostrar a imagem correspondente
		ImageView imageView = (ImageView) findViewById(R.id.full_image_view);
		imageView.setImageResource(imageAdapter.thumbIds[id]);
		
		//Configura o TextView para mostrar uma descricao da imagem
		TextView textView = (TextView) findViewById(R.id.myImageViewText);
		textView.setText("Image id: " + id);
	}
}