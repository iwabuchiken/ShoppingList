package sl.tasks;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import sl.items.Furi;
import sl.items.ShoppingItem;
import sl.items.Word;
import sl.libs.json.YahooFurigana;
import sl.libs.xml.XmlHandler;
import sl.libs.xml.domsample.DomSample;
import sl.utils.CONS;
import sl.utils.DBUtils;
import sl.utils.Methods;
import sl.utils.Methods_sl;
import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

public class Task_PostData extends AsyncTask<String, Integer, Integer> {

	static Activity actv;
	
	Dialog dlg;

	public static Vibrator vib;
	
	public Task_PostData(Activity actv) {
		// TODO Auto-generated constructor stub
		this.actv = actv;
		
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
		
	}

	public Task_PostData(Activity actv, Dialog dlg) {
		// TODO Auto-generated constructor stub
		this.actv = actv;
		
		this.dlg = dlg;
		
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
		
	}

	/*********************************
	 * doInBackground(String... params)
	 * 
	 * @return PostSI_NoSIList	=> -1<br>
	 *  
	 *********************************/
	@Override
	protected Integer doInBackground(String... params) {
		
		/*********************************
		 * Build: JSONObject
		 *********************************/
		List<ShoppingItem> si_list = Methods_sl.getSIList(actv);
		
		if (si_list == null) {
			
			return CONS.ReturnValues.FAILED;
		}
		
		int num = si_list.size();
		
//		if (si_list.size() < 10) {
//			
//			num = si_list.size();
//			
//		} else {//if (si_list.size() < 10)
//			
//			num = 10;
//			
//		}//if (si_list.size() < 10)
		
		int count = 0;
		int result;
		
		for (int i = 0; i < num; i++) {
			
			result = _exec_post(si_list.get(i));
			
			if (result == CONS.ReturnValues.OK) {
				
				count += 1;
				
			}
			
		}
		
		return count;
		
	}//doInBackground(String... params)

	private int _exec_post(ShoppingItem si) {
		// TODO Auto-generated method stub
		JSONObject joBody =
				_doInBackground__1_getJSONBody(si);
	
		if (joBody == null) {
			
			// Log
			Log.d("["
					+ "Task_PostData.java : "
					+ +Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + " : "
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "joBody => null");
			
			return CONS.ReturnValues.BuildJOBodyFailed;
			
		}
		
		// Log
		Log.d("[" + "Task_PostData.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]",
				"joBody => " + "[" + joBody.toString() + "]");
		
	//	
		//REF post json: http://stackoverflow.com/questions/6218143/android-post-json-using-http answered Jun 2 '11 at 18:16
		
		/*********************************
		 * Build: HTTP object
		 *********************************/
	//	DefaultHttpClient httpclient = new DefaultHttpClient();
		
		String url = CONS.HTTPData.UrlPostSI;
		
	    //url with the post data
	//	HttpPost httpPost = new HttpPost(url);
		HttpPost httpPost = _doInBackground__2_getHttpPost(url, joBody);
		
		if (httpPost == null) {
			
			// Log
			Log.d("["
					+ "Task_PostData.java : "
					+ +Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + " : "
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "httpPost => null");
			
			return CONS.ReturnValues.BuildHttpPostFailed;
			
		}
		
		// Log
		Log.d("[" + "Task_PostData.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]",
				"httpPost => " + httpPost.toString()
				+ "(" + httpPost.getURI().toString() + ")"
				);
		
	    /***************************************
		 * Post
		 ***************************************/
	    DefaultHttpClient dhc = new DefaultHttpClient();
	    
		HttpResponse hr = null;
		
		try {
			
			hr = dhc.execute(httpPost);
			
		} catch (ClientProtocolException e) {
			// Log
			Log.d("TaskHTTP.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", e.toString());
			
			return CONS.ReturnValues.HttpPostFailed;
			
		} catch (IOException e) {
			
			// Log
			Log.d("TaskHTTP.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", e.toString());
			
			return CONS.ReturnValues.HttpPostFailed;
			
		}
		
		if (hr == null) {
		
			// Log
			Log.d("TaskHTTP.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "hr => null");
			
			return CONS.ReturnValues.HttpPostFailed;
			
		}//if (hr == null)
	
		// Log
		Log.d("[" + "Task_PostData.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "hr => " + hr.getStatusLine().getStatusCode());
		
		return CONS.ReturnValues.OK;
//		return CONS.ReturnValues.NOP;

	}//private int _exec_post()

	private HttpPost
	_doInBackground__2_getHttpPost(String url, JSONObject joBody) {
		// TODO Auto-generated method stub
		StringEntity se;
		
		HttpPost httpPost = new HttpPost(url);
		
		try {
			
			//REF encoging: http://stackoverflow.com/questions/5084462/how-to-send-unicode-characters-in-an-httppost-on-android answered Feb 22 '11 at 22:38
			se = new StringEntity(joBody.toString(), "UTF-8");
			
			// Log
			Log.d("TaskHTTP.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "joBody => Set \"UTF-8\"");
			
		} catch (UnsupportedEncodingException e) {
			
			// Log
			Log.d("TaskHTTP.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return null;
			
		}
		
		httpPost.setEntity(se);
		
		httpPost.setHeader("Accept", "application/json");
	    httpPost.setHeader("Content-type", "application/json");

		return httpPost;
	}//_doInBackground__2_getHttpPost(String url, JSONObject joBody)
	

	@SuppressWarnings("unused")
	private JSONObject
	_doInBackground__1_getJSONBody(ShoppingItem si) {
		
//		/*********************************
//		 * Get: SI list
//		 *********************************/
//		List<ShoppingItem> si_list = Methods_sl.getSIList(actv);
//		
//		if (si_list == null) {
//			
//			// Log
//			Log.d("["
//					+ "Task_PostData.java : "
//					+ +Thread.currentThread().getStackTrace()[2]
//							.getLineNumber() + " : "
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "No SI list");
//			
//			return null;
////			return CONS.ReturnValues.PostSI_NoSIList;
//		}
		
		/*********************************
		 * Prep: values
		 *********************************/
		int store_id = 
				Methods_sl.get_StoreId_from_StoreName(actv,
						si.getStore());
//		si_list.get(0).getStore());
		
		if (store_id == CONS.ReturnValues.NoStoreData) {
			
			// Log
			Log.d("["
					+ "Task_PostData.java : "
					+ +Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + " : "
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "No store data");
			
			return null;
			
		}
		
		int genre_id = 
				Methods_sl.get_GenreId_from_GenreName(actv,
						si.getGenre());
		
		if (genre_id == CONS.ReturnValues.NoGenreData) {
			
			// Log
			Log.d("["
					+ "Task_PostData.java : "
					+ +Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + " : "
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "No genre data");
			
			return null;
			
		}
		
		/***************************************
		 * Build: JOBody
		 *
		 ***************************************/
//		ShoppingItem si = si_list.get(0);
		
		Object[] values = new Object[]{
				
				store_id,		si.getName(),
				si.getPrice(),	genre_id,
				si.getYomi(),	si.getId()
				
		};
//		"item[store_id]",	"item[name]",		1 2
//		"item[price]",		"item[genre_id]",	3 4
//		"item[yomi]",		"item[mobile_id]",	5 6
		
		String[] keys = CONS.HTTPData.siKeys;
		
		//REF json object: http://stackoverflow.com/questions/8706046/create-json-in-android answered Jan 2 '12 at 22:42
//		JSONObject joBody = new JSONObject();
		
		// Log
		Log.d("[" + "Task_PostData.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Start => get JSON body");
		
		JSONObject joBody = Methods_sl.get_json_body_SI(keys, values);
		
		// Add password parameter
		try {
			
//			joBody.put("passwd[sl]", "abc");
//			joBody.put("pass_sl", "abc");
			joBody.put("passwd_sl", "abc");
//			joBody.put("password_sl", "abc");
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			
			// Log
			Log.d("["
					+ "Task_PostData.java : "
					+ +Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + " : "
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]",
					
					"add password param => Failed"
					+ "(" + e.getMessage() + ")");
		
			return null;
		}
		
		if (joBody == null) {
			
			// Log
			Log.d("["
					+ "Task_PostData.java : "
					+ +Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + " : "
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "joBody => null");
			
			return null;
			
		}
		
		return joBody;
		
	}//_doInBackground__1_getJSONBody()

	@Override
	protected void onCancelled() {
		// TODO Auto-generated method stub
		super.onCancelled();
	}

	@Override
	protected void onPostExecute(Integer res) {

		vib.vibrate(150);
		
		String message;
		
		if (res.intValue() == CONS.ReturnValues.FAILED) {
			
			message = "Posting => Failed";
			
		} else {//if (res.intValue() == CONS.ReturnValues.FAILED)
			
			message = "Posting => Done(" 
					+ String.valueOf(res.intValue()) + " items)";
			
		}//if (res.intValue() == CONS.ReturnValues.FAILED)
		
		// debug
		Toast.makeText(actv,
				message,
				Toast.LENGTH_SHORT).show();
		
		
	}//protected void onPostExecute(Integer res)

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		
		// debug
		Toast.makeText(actv, "Posting data ...", Toast.LENGTH_SHORT).show();
	}

}//public class Task_GetYomi extends AsyncTask<String, Integer, Integer>
