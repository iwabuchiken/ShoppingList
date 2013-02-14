package sl.tasks;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.xml.sax.SAXException;

import sl.libs.xml.XmlHandler;
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
//		Task_GetYomi.doInBackground__2();
		
//		int res = Methods_sl.getYomi(actv, dlg);
		
		/*********************************
		 * XmlHandler
		 *********************************/
		Task_GetYomi.doInBackground__2__1__XmlHandler();
		
		
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
		/*********************************
		 * API-related processes
		 *********************************/
		String name = "洗濯網（中）";

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


	private static void doInBackground__2__1__XmlHandler() {
		// TODO Auto-generated method stub
		String kw = "洗濯網（中）";
		
		String url = "http://jlp.yahooapis.jp/FuriganaService/V1/furigana" +
				"?appid=dj0zaiZpPTZjQWNRNExhd0thayZkPVlXazlhR2gwTTJGUE56SW1jR285TUEtLSZzPWNvbnN1bWVyc2VjcmV0Jng9Mjc-" +
				"&grade=1" +
				"&sentence=" + kw;

		HttpEntity entity = Methods.getYomi_getHttpEntity(url);
		
		if (entity == null) {
			
			// Log
			Log.d("Methods_sl.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "entity == null");
			
			return;
			
		}//if (entity == null)

		/*********************************
		 * Get: XMLPullParser
		 *********************************/
		String xmlString =
					Methods.convert_HttpEntity2XmlString(entity);
		
		if (xmlString == null) {
			
			// Log
			Log.d("Methods_sl.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "xmlString == null");
			
			return;
			
		}//if (entity == null)

		/*********************************
		 * XmlHandler
		 *********************************/
		XmlHandler xh = new XmlHandler();
		
		try {
			
//			xh.showXml(xmlString);
			xh.showXml(url);
			
		} catch (SAXException e) {

			// Log
			Log.e("Task_GetYomi.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
		} catch (IOException e) {

			// Log
			Log.e("Task_GetYomi.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
		} catch (ParserConfigurationException e) {

			// Log
			Log.e("Task_GetYomi.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
		}//try

	}//private static void doInBackground__2__1__XmlHandler()
	

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

}//public class Task_GetYomi extends AsyncTask<String, Integer, Integer>
