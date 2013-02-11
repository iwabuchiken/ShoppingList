package sl.utils;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DBUtils extends SQLiteOpenHelper {
//	// Name
//	public static final String name = "shopping_list.db";
//	
//	// Version
//	static final int version = 1;
//	
//	// Factory
//	static final CursorFactory factory = null;

	//
	Context context;

	Activity actv;
	
	String dbName;
	//
//	public static String tableName = "shopping_item";
//
//	public static String[] columns = {"store", "name", "price", "genre"};
//	public static String[] columns_with_index = 
//					{"store", "name", "price", "genre", android.provider.BaseColumns._ID};
//
//	public static String[] columns_for_table_stores = 
//					{"store_name", "memo"};
//	
//	public static String[] columns_for_table_stores_with_index = 
//		{android.provider.BaseColumns._ID, "store_name", "memo"};
//
//	public static String[] column_types_for_table_stores = 
//																	{"TEXT", "TEXT"};
//	
//	public static String[] columns_for_table_genres = 
//					{"genre_name", "memo"};
//
//	
//	public static String[] columns_for_table_genres_with_index = 
//		{android.provider.BaseColumns._ID, "genre_name", "memo"};
//
//	public static String[] column_types_for_table_genres = 
//																	{"TEXT", "TEXT"};
//	

	// DB name => Use default: CONS.dbName
	public DBUtils(Context context) {
		super(context, CONS.dbName, CONS.factory, CONS.version);
		// 
		this.context = context;
		
		// Log
		Log.d("DBManager.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "DBManager => Instance");
		
	}//public DBManager(Context context)

	// DB name => From parameter
	public DBUtils(Context context, String dbName) {
		// TODO Auto-generated constructor stub
		super(context, dbName, CONS.factory, CONS.version);
		
		this.context = context;;
		
		this.dbName = dbName;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO 自動生成されたメソッド・スタブ
		// Log
		Log.d("DBManager.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "onCreate()");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public boolean tableExists(SQLiteDatabase db, String tableName) {
		// The table exists?
		Cursor cursor = db.rawQuery(
									"SELECT * FROM sqlite_master WHERE tbl_name = '" + 
									tableName + "'", null);
		
		((Activity) context).startManagingCursor(cursor);
		
		// Judge
		if (cursor.getCount() > 0) {
			return true;
		} else {//if (cursor.getCount() > 0)
			return false;
		}//if (cursor.getCount() > 0)
	}//public boolean tableExists(String tableName)

	public boolean createTable(SQLiteDatabase db, String tableName) {
		//
//		if (!tableExists(db, tableName)) {
		if (tableExists(db, tableName)) {
			// Log
			Log.d("DBManager.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table exists => " + tableName);
			
			return false;
		}//if (!tableExists(SQLiteDatabase db, String tableName))
		
		//
		String sql = "CREATE TABLE " + tableName + " ("
				+ android.provider.BaseColumns._ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"store TEXT, name TEXT, price INTEGER, genre TEXT);";
		
		//
		try {
			db.execSQL(sql);
			
			// Log
			Log.d("DBManager.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table created => " + tableName);
			
			
			return true;
		} catch (SQLException e) {
			// Log
			Log.d("MemoDBHelper.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception => " + e.toString());
			
			return false;
		}//try
		
	}//public boolean createTable(SQLiteDatabase db, String tableName)

	/****************************************
	 * createTable_generic()
	 * 
	 * <Caller> 
	 * 1. 
	 * 
	 * <Desc> 1. <Params> 1.
	 * 
	 * <Return> 1.
	 * 
	 * <Steps> 1.
	 ****************************************/
	public boolean createTable_generic(
					SQLiteDatabase db, String tableName, String[] columns, String[] types) {
		/*----------------------------
		 * Steps
		 * 1. Table exists?
		 * 2. Build sql
		 * 3. Exec sql
			----------------------------*/
		
		//
//		if (!tableExists(db, tableName)) {
		if (tableExists(db, tableName)) {
			// Log
			Log.d("DBManager.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table exists => " + tableName);
			
			return false;
		}//if (!tableExists(SQLiteDatabase db, String tableName))
		
		/*----------------------------
		 * 2. Build sql
			----------------------------*/
		//
		StringBuilder sb = new StringBuilder();
		
		sb.append("CREATE TABLE " + tableName + " (");
		sb.append(android.provider.BaseColumns._ID +
							" INTEGER PRIMARY KEY AUTOINCREMENT, ");
		
		int i = 0;
		for (i = 0; i < columns.length - 1; i++) {
			sb.append(columns[i] + " " + types[i] + ", ");
		}//for (int i = 0; i < columns.length - 1; i++)
		
		sb.append(columns[i] + " " + types[i]);
		
		sb.append(");");
		
		// Log
		Log.d("DBManager.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "sql => " + sb.toString());
		
//		return false;
		
//		String sql = "CREATE TABLE " + tableName + " ("
//				+ android.provider.BaseColumns._ID
//				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//				"store TEXT, name TEXT, price INTEGER, genre TEXT);";
//		
		/*----------------------------
		 * 3. Exec sql
			----------------------------*/
		//
		try {
//			db.execSQL(sql);
			db.execSQL(sb.toString());
			
			// Log
			Log.d("DBManager.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table created => " + tableName);
			
			
			return true;
		} catch (SQLException e) {
			// Log
			Log.d("MemoDBHelper.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception => " + e.toString());
			
			return false;
		}//try
		
	}//public boolean createTable_generic(SQLiteDatabase db, String tableName)

	public boolean storeData(SQLiteDatabase db, String tableName, String[] cols, String[] values) {
		try {
			//
			db.beginTransaction();
			
			//
			ContentValues cv = new ContentValues();
			
			// Put values
			for (int i = 0; i < cols.length; i++) {
				cv.put(cols[i], values[i]);
			}//for (int i = 0; i < columnNames.length; i++)

			// Insert data
			db.insert(tableName, null, cv);
			
			// Set as successful
			db.setTransactionSuccessful();

			// End transaction
			db.endTransaction();
			
			// Log
			StringBuilder sb = new StringBuilder();
			
			for (int i = 0; i < cols.length; i++) {
				//
				sb.append(cols[i] + " => " + values[i] + "/");
				
			}//for (int i = 0; i < cols.length; i++)
			
			Log.d("DBManager.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Stored => " + sb.toString());
			
			return true;
			
		} catch (Exception e) {
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception => " + e.toString());
			
			return false;
		}//try
		
	}//public boolean storeData(SQLiteDatabase db, String tableName, String[] cols, String[] values)

	public Cursor getAllData(
					SQLiteDatabase db, String tableName, String[] cols) {
		//
		Cursor cursor = db.query(tableName, cols, null, null, null, null, null);
		
		return cursor;
	}

	public boolean dropTable(Activity actv, SQLiteDatabase db, String tableName) {
		/*------------------------------
		 * The table exists?
		 *------------------------------*/
		// The table exists?
		boolean tempBool = tableExists(db, tableName);
		
		if (tempBool == true) {
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table exists: " + tableName);
			
		} else {//if (tempBool == true)
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table doesn't exist: " + tableName);
			
			return false;
		}

		/*------------------------------
		 * Drop the table
		 *------------------------------*/
		// Define the sql
        String sql 
             = "DROP TABLE " + tableName;
        
        // Execute
        try {
			db.execSQL(sql);
			
			// Vacuum
			db.execSQL("VACUUM");
			
			// Log
			Log.d("DBManager.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "The table dropped => " + tableName);
			
			// Return
			return true;
			
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			// Log
			Log.e("DBManager.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "DROP TABLE => failed (table=" + tableName + "): " + e.toString());
			
			// debug
			Toast.makeText(actv, 
						"DROP TABLE => failed(table=" + tableName, 
						3000).show();
			
			// Return
			return false;
		}//try

	}//public boolean dropTable(String tableName) 

}

