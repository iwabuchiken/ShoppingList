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
import android.util.Log;
import android.widget.Toast;

public class Task_PostData extends AsyncTask<String, Integer, Integer> {

	static Activity actv;
	
	Dialog dlg;
	
	public Task_PostData(Activity actv) {
		// TODO Auto-generated constructor stub
		this.actv = actv;
	}

	public Task_PostData(Activity actv, Dialog dlg) {
		// TODO Auto-generated constructor stub
		this.actv = actv;
		
		this.dlg = dlg;
		
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
		 * Get: SI list
		 *********************************/
		List<ShoppingItem> si_list = Methods_sl.getSIList(actv);
		
		if (si_list == null) {
			
			
			return CONS.ReturnValues.PostSI_NoSIList;
		}
		
		/*********************************
		 * Prep: values
		 *********************************/
		int store_id = 
				Methods_sl.get_StoreId_from_StoreName(actv,
						si_list.get(0).getStore());
		
		if (store_id == CONS.ReturnValues.NoStoreData) {
			
			return CONS.ReturnValues.NoStoreData;
			
		}
		
		int genre_id = 
				Methods_sl.get_GenreId_from_GenreName(actv,
						si_list.get(0).getGenre());
		
		if (genre_id == CONS.ReturnValues.NoGenreData) {
			
			return CONS.ReturnValues.NoGenreData;
			
		}
		
		ShoppingItem si = si_list.get(0);
		
		Object[] values = new Object[]{
				
				store_id,		si.getName(),
				si.getPrice(),	genre_id,
				si.getYomi(),	si.getId()
				
		};
//		"item[store_id]",	"item[name]",		1 2
//		"item[price]",		"item[genre_id]",	3 4
//		"item[yomi]",		"item[mobile_id]",	5 6
		
		String[] keys = CONS.HTTPData.siKeys;
		
		//REF post json: http://stackoverflow.com/questions/6218143/android-post-json-using-http answered Jun 2 '11 at 18:16
		
		
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String url = CONS.HTTPData.UrlPostSI;
		
	    //url with the post data
		HttpPost httpPost = new HttpPost(url);

		/***************************************
		 * Json object
		 *
		 ***************************************/
		//REF json object: http://stackoverflow.com/questions/8706046/create-json-in-android answered Jan 2 '12 at 22:42
//		JSONObject joBody = new JSONObject();
		
		// Log
		Log.d("[" + "Task_PostData.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Start => get JSON body");
		
		JSONObject joBody = Methods_sl.get_json_body_SI(keys, values);
		
		// Log
		Log.d("[" + "Task_PostData.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]",
				"joBody.length => " + String.valueOf(joBody.length()));
		
		return CONS.ReturnValues.NOP;
		
//		try {
//			
//			joBody.put("file_name", ti.getFile_name());
//			joBody.put("file_path", ti.getFile_path());
//			joBody.put("file_id", ti.getFileId());
//			
//			joBody.put("table_name", ti.getTable_name());
//			
//			joBody.put("memos", ti.getMemo());
//			joBody.put("tags", ti.getTags());
//			
//			
//		} catch (JSONException e) {
//			
//			// Log
//			Log.d("TaskHTTP.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", e.toString());
//			
//			return null;
//			
//		}

//
//		
//		StringEntity se;
//		try {
//			
////			se = new StringEntity(joRoot.toString());
////			se = new StringEntity(joBody.toString());
//			
//			//REF encoging: http://stackoverflow.com/questions/5084462/how-to-send-unicode-characters-in-an-httppost-on-android answered Feb 22 '11 at 22:38
//			se = new StringEntity(joBody.toString(), "UTF-8");
//			
//			// Log
//			Log.d("TaskHTTP.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "joBody => Set \"UTF-8\"");
//			// Log
//			Log.d("TaskHTTP.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "joBody.toString()=" + joBody.toString());
//			
//		} catch (UnsupportedEncodingException e) {
//			
//			// Log
//			Log.d("TaskHTTP.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", e.toString());
//			
//			return null;
//			
//		}
//		
//		httpPost.setEntity(se);
//		
//		httpPost.setHeader("Accept", "application/json");
//	    httpPost.setHeader("Content-type", "application/json");
//		
//	    /***************************************
//		 * Post
//		 ***************************************/
//	    DefaultHttpClient dhc = new DefaultHttpClient();
//	    
//		HttpResponse hr = null;
//		
//		try {
//			
//			hr = dhc.execute(httpPost);
//			
//		} catch (ClientProtocolException e) {
//			// Log
//			Log.d("TaskHTTP.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", e.toString());
//			
//		} catch (IOException e) {
//			
//			// Log
//			Log.d("TaskHTTP.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", e.toString());
//			
//		}
//		
//		/***************************************
//		 * Validate: Return
//		 ***************************************/
//		if (hr == null) {
//			
////			// debug
////			Toast.makeText(actv, "hr == null", 2000).show();
//			
//			// Log
//			Log.d("TaskHTTP.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "hr == null");
//			
////			return CONS.Task_GetTexts.EXECUTE_POST_NULL;
//			return null;
//			
//		} else {//if (hr == null)
//			
//			// Log
//			Log.d("Task_GetTexts.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "Http response => Obtained");
//
//			
////			return null;
//			
//		}//if (hr == null)
//
//		/***************************************
//		 * Status code
//		 ***************************************/
//		/*********************************
//		 * Status code
//		 *********************************/
//		int status = hr.getStatusLine().getStatusCode();
//		
//		if (status == CONS.HTTP_Response.CREATED
//				|| status == CONS.HTTP_Response.OK) {
//
//			// Log
//			Log.d("Task_GetYomi.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "status=" + status);
//
////			return CONS.HTTP_Response.CREATED;
//			
//		} else {//if (status == CONS.HTTP_Response.CREATED)
//			
//			// Log
//			Log.d("Task_GetTexts.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "status=" + status);
//			
//			return CONS.HTTP_Response.NOT_CREATED;
//			
//		}//if (status == CONS.HTTP_Response.CREATED)
//		
//		return null;

		
	}//doInBackground(String... params)

	@Override
	protected void onCancelled() {
		// TODO Auto-generated method stub
		super.onCancelled();
	}

	@Override
	protected void onPostExecute(Integer res) {
		
		// debug
		Toast.makeText(actv, "Posting => Done", Toast.LENGTH_SHORT).show();
		
		
	}//protected void onPostExecute(Integer res)

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		
		// debug
		Toast.makeText(actv, "Posting data ...", Toast.LENGTH_SHORT).show();
	}

}//public class Task_GetYomi extends AsyncTask<String, Integer, Integer>
