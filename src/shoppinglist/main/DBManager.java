package shoppinglist.main;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBManager extends SQLiteOpenHelper {
	// Name
	static final String name = "shopping_list.db";
	
	// Version
	static final int version = 1;
	
	// Factory
	static final CursorFactory factory = null;

	//
	Context context;
	
	//
	static String tableName = "shopping_item";

	static String[] columns = {"store", "name", "price", "genre"};
	static String[] columns_with_index = {"store", "name", "price", "genre", android.provider.BaseColumns._ID};
	
	public DBManager(Context context) {
		super(context, name, factory, version);
		// 
		this.context = context;
		
		// Log
		Log.d("DBManager.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "DBManager => Instance");
		
	}//public DBManager(Context context)

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
			
			return true;
			
		} catch (Exception e) {
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception => " + e.toString());
			
			return false;
		}//try
		
	}//public boolean storeData(SQLiteDatabase db, String tableName, String[] cols, String[] values)
}
