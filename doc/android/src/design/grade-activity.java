public class MainActivity extends Activity {

	 @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
        GridView gridView = (GridView) findViewById(R.id.gridview);
 
        // Instance of ImageAdapter Class
        gridView.setAdapter(new ImageAdapter(this));
    }
...
}