public class AccelActivity extends Activity implements
SensorEventListener {
	private TextView xAxis;
	private TextView yAxis;
	private TextView zAxis;
	
	private SensorManager mSensorMan;
	private Sensor mSensor;
	
	private double gravity[] = {1, 1, 1};
	private double acceleration[] = {1, 1, 1};
	