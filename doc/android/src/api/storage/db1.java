public class CarOpenHelper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "MyCars";
	private static final String DATABASE_TABLE_NAME = "Cars";
	private static final String DATABASE_TABLE_CREATE = 
			"CREATE TABLE " + DATABASE_TABLE_NAME + " (" +
			"SN INT PRIMARY KEY, " +
			"Manufacturer TEXT, " +
			"Model TEXT, " +
			"Year INT);";

	public CarOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_TABLE_CREATE);
			
		db.execSQL(	"INSERT INTO " + DATABASE_TABLE_NAME + 
					" SELECT '1' AS SN, 'Mazda' AS Manufacturer," +
					" 'MX-5' AS Model, '1991' AS Year" +
					" UNION SELECT '2', 'Mazda', 'RX-8', '2001'" +
					" UNION SELECT '3', 'Mazda', 'Speed3', '2007'" +
					" UNION SELECT '4', 'Subaru', 'Impreza', '2010'" +
					" UNION SELECT '5', 'Fiat', '500', '2012'" +
					" UNION SELECT '6', 'Ford', 'Focus', '2008'" + 
					" UNION SELECT '7', 'Fiat', 'Punto', '2012'" +
					" UNION SELECT '8', 'Ford', 'Fiesta', '2006'" +
					" UNION SELECT '9', 'Honda', 'Civic', '2013'" +
					" UNION SELECT '10', 'Honda', 'Fit', '2010'" +
					" UNION SELECT '11', 'Toyota', 'Corolla', '2010'" +
					" UNION SELECT '12', 'Chevrolet', 'Celta', '2009'" +
					" UNION SELECT '13', 'Chevrolet', 'Cruze', '2012'");
		
	}
}