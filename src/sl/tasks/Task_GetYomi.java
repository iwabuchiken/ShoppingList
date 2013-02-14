package sl.tasks;

import sl.utils.CONS;
import sl.utils.DBUtils;
import sl.utils.Methods;
import sl.utils.Methods_sl;
import android.app.Activity;
import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class Task_GetYomi extends AsyncTask<String, Integer, Integer> {

	static Activity actv;
	
	Dialog dlg;
	
	public Task_GetYomi(Activity actv) {
		// TODO Auto-generated constructor stub
		this.actv = actv;
	}

	public Task_GetYomi(Activity actv, Dialog dlg) {
		// TODO Auto-generated constructor stub
		this.actv = actv;
		
		this.dlg = dlg;
		
	}

	@Override
	protected Integer doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		
//		int res = Task_GetYomi.doInBackground__1();
//		Task_GetYomi.doInBackground__1();
		Task_GetYomi.doInBackground__2();
		
//		int res = Methods_sl.getYomi(actv, dlg);
		
		
		return CONS.GETYOMI_FAILED;
//		return res;
		
//		return null;
	}

	private static int doInBackground__1() {
		
		DBUtils dbu = new DBUtils(actv, CONS.dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();
		
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
				
				return CONS.GETYOMI_FAILED;
				
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
				
				return CONS.GETYOMI_FAILED;
				
			}//if (null == c)
			
			/*********************************
			 * Start
			 *********************************/
			c.moveToFirst();
			
			for (int i = 0; i < c.getCount(); i++) {

				String yomi =
						c.getString(1 + Methods.getArrayIndex(
											CONS.columns,
											"yomi"));
	
				String name =
						c.getString(1 + Methods.getArrayIndex(
											CONS.columns,
											"name"));
				
				// Log
				Log.d("Task_GetYomi.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2].getMethodName()
						+ "]",
						"name=" + name
						+ "/"
						+ "yomi=" + yomi);
				
				/*********************************
				 * If "yomi" value is null,
				 * 	=> Get yomi using the method: Methods.getYomi_full(String. String)
				 *********************************/
				if (yomi == null || yomi.equals("")) {
					
					yomi = Methods.getYomi_full(name, "utf-8")[2];
					
					// Log
					Log.d("Task_GetYomi.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber()
							+ ":"
							+ Thread.currentThread().getStackTrace()[2]
									.getMethodName() + "]", "yomi=" + yomi);
					
				}//if (yomi == null || yomi.equals(""))
				
				/*********************************
				 * Next
				 *********************************/
				c.moveToNext();

			}//for (int i = 0; i < c.getCount(); i++)
			
		} catch (Exception e) {
			
			// Log
			Log.d("DialogOnItemClickListener.java"
					+ "["
					+ Thread.currentThread().getStackTrace()[2]
							.getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2]
							.getMethodName() + "]", e.toString());
			
			wdb.close();
			
			return CONS.GETYOMI_FAILED;
			
		}//try
		
		wdb.close();
		
		return CONS.GETYOMI_FAILED;
		
	}//private static int doInBackground__1()

	private static int doInBackground__2() {
		
		String name = "ô‘ó–Ôi’†j";

		String yomi = Methods_sl.getYomi_full(name, "utf-8")[2];
		
		if (yomi != null) {
			
			// Log
			Log.d("Task_GetYomi.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "yomi=" + yomi);
			
			return CONS.GETYOMI_SUCCESSFUL;
			
		} else {//if (yomi != null)
		
			// Log
			Log.d("Task_GetYomi.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "yomi == null (" + name + ")");
			
			return CONS.GETYOMI_FAILED;
			
		}//if (yomi != null)
		
		
		
	}//private static int doInBackground__1()


	@Override
	protected void onCancelled() {
		// TODO Auto-generated method stub
		super.onCancelled();
	}

	@Override
	protected void onPostExecute(Integer res) {
		// TODO Auto-generated method stub
		super.onPostExecute(res);
		
		switch (res) {
		case CONS.GETYOMI_SUCCESSFUL:
			
			// debug
			Toast.makeText(actv,
					"Get yomi => Done", Toast.LENGTH_LONG).show();
			
			break;
			
		case CONS.GETYOMI_FAILED:

			// debug
			Toast.makeText(actv,
					"Get yomi => Failed", Toast.LENGTH_LONG).show();

			break;
			
		default:
			break;
		}//switch (res)
		
	}//protected void onPostExecute(Integer res)

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

}
