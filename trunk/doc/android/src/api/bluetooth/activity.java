public class DeviceListActivity extends Activity
 implements OnItemClickListener {
	private ListView deviceList;
	
	private BluetoothAdapter mBluetoothAdapter;
	private ArrayAdapter<String> mArrayAdapter;
