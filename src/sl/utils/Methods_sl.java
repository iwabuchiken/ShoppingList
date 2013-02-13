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
	
}
