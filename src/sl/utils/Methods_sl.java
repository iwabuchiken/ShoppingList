package sl.utils;

import java.util.Iterator;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Methods_sl {

	public static boolean refactorData(Activity actv,
							SQLiteDatabase wdb, DBUtils dbu, String tableName, Cursor c) {
		// TODO Auto-generated method stub
	
		// Log
		Log.d("Methods_sl.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Starting...");
		
		c.moveToFirst();
		
		// Log
		Log.d("Methods_sl.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Cursor moved to the first");
		
		/*********************************
		 * Column "price" => If not numeric, then move the data
		 * 	to the column "yomi"
		 *********************************/
		for (int i = 0; i < c.getCount(); i++) {
			/*********************************
			 * Get data in the "price" column
			 *********************************/
//			int colNumPrice = Methods.getArrayIndex(
//									CONS.columns,
//									"price");
//			
//			// Log
//			Log.d("Methods_sl.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "colNumPrice=" + colNumPrice);
			
			String data =
					c.getString(1 + Methods.getArrayIndex(
									CONS.columns,
									"price"));
			
			// Log
			Log.d("Methods_sl.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "data=" + data);
			
//			if (Methods.is_numeric(data)) {
			if (Methods.is_numeric(data) || data.equals("")) {
				
				// Log
				Log.d("Methods_sl.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]",
						"Data type => OK: "
							+ c.getString(1 + Methods.getArrayIndex(
												CONS.columns,
												"name")));
				
			} else {//if (Methods.is_numeric(data))

				// Log
				Log.d("Methods_sl.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]",
						"Data type => Not OK: "
							+ c.getString(1 + Methods.getArrayIndex(
											CONS.columns,
											"name")));

				long dbId = c.getLong(0);
				
				dbu.updateData(actv,
						wdb, tableName,
						dbId,
						CONS.columns[
						     Methods.getArrayIndex(CONS.columns, "yomi")],
						data);

				dbu.updateData(actv,
						wdb, tableName,
						dbId,
						CONS.columns[
				             Methods.getArrayIndex(CONS.columns, "price")],
						"");
				
			}//if (Methods.is_numeric(data))
			
			/*********************************
			 * Next data
			 *********************************/
			c.moveToNext();
			
		}//for (int i = 0; i < c.getCount(); i++)
		
		return false;
		
	}//public static boolean refactorData()

	public static int refactorDb_colPrice(Activity actv) {
		// TODO Auto-generated method stub
		/*********************************
		 * Setup DB
		 *********************************/
		DBUtils dbu = new DBUtils(actv, CONS.dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();
		
		/*********************************
		 * Query
		 *********************************/
		String tableName = "shopping_item";
		
		String sql = "SELECT * FROM " + tableName;

		Cursor c = null;

		try {
			
			c = wdb.rawQuery(sql, null);
			
			/*********************************
			 * Cursor => null?
			 *********************************/
			if (null == c) {
				
				// Log
				Log.d("DialogOnItemClickListener.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]", "Cursor => null");
				
				wdb.close();
				
				return CONS.CURSOR_NULL;
				
			}//if (null == c)
			
			/*********************************
			 * Num of entries in the cursor => Less than 1?
			 *********************************/
			if (c.getCount() < 1) {
				
				// Log
				Log.d("DialogOnItemClickListener.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]", "Cursor => No entry");
				
				wdb.close();
				
				return CONS.CURSOR_NO_ENTRY;
				
			}//if (null == c)
			
			/*********************************
			 * Start refactoring data
			 *********************************/
			// Log
			Log.d("DialogOnItemClickListener.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Refactoring starts...");
			
			boolean res = Methods_sl.refactorData(
										actv, wdb, dbu, tableName, c);
			
			if (res == true) {
			
				// Log
				Log.d("DialogOnItemClickListener.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]", "Data refactored");
				
				wdb.close();
				
				return CONS.DATA_REFACTORED;
				
				
			} else {//if (res == true)

				// Log
				Log.d("DialogOnItemClickListener.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]",
						"Data refactoring => Failed");
				
				wdb.close();
				
				return CONS.DATA_REFACTORING_FAILED;
				
			}//if (res == true)
			
		} catch (Exception e) {

			// Log
			Log.d("DialogOnItemClickListener.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Exception => " + e.toString());
			
			wdb.close();
			
			return CONS.EXCEPTION;
			
		}//try

	}//private void refactorDb()

	public static int refactorDb_colGenre(Activity actv) {
		// TODO Auto-generated method stub
		/*********************************
		 * Setup DB
		 *********************************/
		DBUtils dbu = new DBUtils(actv, CONS.dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();
		
		/*********************************
		 * Query
		 *********************************/
		String tableName = "shopping_item";
		
		String sql = "SELECT * FROM " + tableName;

		Cursor c = null;
		
		try {
			
			c = wdb.rawQuery(sql, null);
			
			/*********************************
			 * Cursor => null?
			 *********************************/
			if (null == c) {
				
				// Log
				Log.d("DialogOnItemClickListener.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]", "Cursor => null");
				
				wdb.close();
				
				return CONS.CURSOR_NULL;
				
			}//if (null == c)
			
			/*********************************
			 * Num of entries in the cursor => Less than 1?
			 *********************************/
			if (c.getCount() < 1) {
				
				// Log
				Log.d("DialogOnItemClickListener.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]", "Cursor => No entry");
				
				wdb.close();
				
				return CONS.CURSOR_NO_ENTRY;
				
			}//if (null == c)
			
			/*********************************
			 * Start refactoring data
			 *********************************/
			// Log
			Log.d("DialogOnItemClickListener.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Refactoring starts...");
			
			c.moveToFirst();
			
			/*********************************
			 * Column "genre" => If numeric, then move the data
			 * 	to the column "price"
			 *********************************/
			for (int i = 0; i < c.getCount(); i++) {
				/*********************************
				 * Get data in the "price" column
				 *********************************/
				String data =
						c.getString(1 + Methods.getArrayIndex(
										CONS.columns,
										"genre"));
				
				// Log
				Log.d("Methods_sl.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2].getMethodName()
						+ "]", "data=" + data);
				
//				if (Methods.is_numeric(data)) {
				if (Methods.is_numeric(data)) {

					// Log
					Log.d("Methods_sl.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber()
							+ ":"
							+ Thread.currentThread().getStackTrace()[2]
									.getMethodName() + "]",
							"Data is numeric: " + data);

					long dbId = c.getLong(0);
					
					dbu.updateData(actv,
							wdb, tableName,
							dbId,
							CONS.columns[
							     Methods.getArrayIndex(CONS.columns, "price")],
							data);

					dbu.updateData(actv,
							wdb, tableName,
							dbId,
							CONS.columns[
					             Methods.getArrayIndex(CONS.columns, "genre")],
							"");
					
				} else {//if (Methods.is_numeric(data))

					// Log
					Log.d("Methods_sl.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber()
							+ ":"
							+ Thread.currentThread().getStackTrace()[2]
									.getMethodName() + "]",
							"Data is not numeric: " + data);

				}//if (Methods.is_numeric(data))
				
				/*********************************
				 * Next data
				 *********************************/
				c.moveToNext();
				
			}//for (int i = 0; i < c.getCount(); i++)
			
			wdb.close();
			
			return CONS.DATA_REFACTORED;
			
		} catch (Exception e) {

			// Log
			Log.d("DialogOnItemClickListener.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Exception => " + e.toString());
			
			wdb.close();
			
			return CONS.EXCEPTION;
			
		}//try

	}//public static int refactorDb_colGenre(Activity actv)

	public static int refactorDb_colPrice_CanDo(Activity actv) {
		// TODO Auto-generated method stub
		/*********************************
		 * Setup DB
		 *********************************/
		DBUtils dbu = new DBUtils(actv, CONS.dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();
		
		/*********************************
		 * Query
		 *********************************/
		String tableName = "shopping_item";
		
		String sql = "SELECT * FROM " + tableName;

		Cursor c = null;

		try {
			
			c = wdb.rawQuery(sql, null);
			
			/*********************************
			 * Cursor => null?
			 *********************************/
			if (null == c) {
				
				// Log
				Log.d("DialogOnItemClickListener.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]", "Cursor => null");
				
				wdb.close();
				
				return CONS.CURSOR_NULL;
				
			}//if (null == c)
			
			/*********************************
			 * Num of entries in the cursor => Less than 1?
			 *********************************/
			if (c.getCount() < 1) {
				
				// Log
				Log.d("DialogOnItemClickListener.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]", "Cursor => No entry");
				
				wdb.close();
				
				return CONS.CURSOR_NO_ENTRY;
				
			}//if (null == c)
			
			/*********************************
			 * Start refactoring data
			 *********************************/
			// Log
			Log.d("DialogOnItemClickListener.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Refactoring starts...");
			
			boolean res = Methods_sl.refactorData_colPrice_CanDo(
										actv, wdb, dbu, tableName, c);
			
			if (res == true) {
			
				// Log
				Log.d("DialogOnItemClickListener.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]", "Data refactored");
				
				wdb.close();
				
				return CONS.DATA_REFACTORED;
				
				
			} else {//if (res == true)

				// Log
				Log.d("DialogOnItemClickListener.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]",
						"Data refactoring => Failed");
				
				wdb.close();
				
				return CONS.DATA_REFACTORING_FAILED;
				
			}//if (res == true)
			
		} catch (Exception e) {

			// Log
			Log.d("DialogOnItemClickListener.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Exception => " + e.toString());
			
			wdb.close();
			
			return CONS.EXCEPTION;
			
		}//try

	}//public static int refactorDb_colPrice_CanDo(Activity actv)

	private static boolean refactorData_colPrice_CanDo(Activity actv,
			SQLiteDatabase wdb, DBUtils dbu, String tableName, Cursor c) {
		// Log
		Log.d("Methods_sl.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Starting...");
		
		c.moveToFirst();
		
		// Log
		Log.d("Methods_sl.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Cursor moved to the first");
		
		/*********************************
		 * Among records which data in the "store" column is 
		 * 	"キャン・ドウ", if the value of the column "price" is
		 * 	blank(i.e. ""), then insert the value 100
		 *********************************/
		boolean res = true;
		
		for (int i = 0; i < c.getCount(); i++) {
			/*********************************
			 * Get data in the "price" column
			 *********************************/
			String storeName = c.getString(1 + Methods.getArrayIndex(
									CONS.columns,
									"store"));
			
			String priceData =
					c.getString(1 + Methods.getArrayIndex(
									CONS.columns,
									"price"));

			// Log
			Log.d("Methods_sl.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]",
					"store=" + storeName + "/"
					+ "price=" + priceData);
//			
//			
//			// Log
//			Log.d("Methods_sl.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "priceData.getClass().getName()=" + priceData.getClass().getName());
			
//			if (storeName.equals("キャン・ドウ")) {
//				
//				// Log
//				Log.d("Methods_sl.java"
//						+ "["
//						+ Thread.currentThread().getStackTrace()[2]
//								.getLineNumber()
//						+ ":"
//						+ Thread.currentThread().getStackTrace()[2]
//								.getMethodName() + "]",
//						"storeName => キャン・ドウ: " + storeName);
//				
//			} else {//if (storeName.equals("キャン・ドウ"))
//				
//				// Log
//				Log.d("Methods_sl.java"
//						+ "["
//						+ Thread.currentThread().getStackTrace()[2]
//								.getLineNumber()
//						+ ":"
//						+ Thread.currentThread().getStackTrace()[2]
//								.getMethodName() + "]",
//						"storeName => Not キャン・ドウ: " + storeName);
//				
//			}//if (storeName.equals("キャン・ドウ"))
			
			
//			if (storeName.equals("キャン・ドウ") &&
			if (storeName.equals("キャン・ドゥ") &&
					(priceData.equals("") || priceData == null)) {
			
				long dbId = c.getLong(0);
				
				// Log
				Log.d("Methods_sl.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]",
						"Refactoring data: " + dbId);
				
				String targetColName = "price";
				
				res = dbu.updateData(actv,
						wdb, tableName,
						dbId,
						CONS.columns[
						     Methods.getArrayIndex(
						    		 	CONS.columns,
						    		 	targetColName)],
						100);
				
				if (res == false) {
					
					return res;
					
				}//if (res == false)
				
			} else {//if (storeName.equals("キャン・ドウ"))
				
				// Log
				Log.d("Methods_sl.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]",
						"No processing: data=" + c.getLong(0));
				
//				continue;
				
			}//if (storeName.equals("キャン・ドウ"))
			
			/*********************************
			 * Next data
			 *********************************/
			c.moveToNext();
			
		}//for (int i = 0; i < c.getCount(); i++)
		
		return res;
		
	}//private static boolean refactorData_colPrice_CanDo(

}//public class Methods_sl
