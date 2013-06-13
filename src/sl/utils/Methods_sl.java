package sl.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
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

import android.app.Activity;
import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.util.Log;
import android.util.Xml;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import sl.items.PS;
import sl.items.ShoppingItem;
import sl.main.MainActv;
import sl.main.R;
import sl.utils.Tags.SortTags;

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
				Log.e("DialogOnItemClickListener.java"
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
			Log.e("DialogOnItemClickListener.java" + "["
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
			Log.e("DialogOnItemClickListener.java" + "["
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
			Log.e("DialogOnItemClickListener.java" + "["
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
		 * 	"�L�����E�h�E", if the value of the column "price" is
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
			
//			if (storeName.equals("�L�����E�h�E") &&
			if (storeName.equals("�L�����E�h�D") &&
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
				
			} else {//if (storeName.equals("�L�����E�h�E"))
				
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
				
			}//if (storeName.equals("�L�����E�h�E"))
			
			/*********************************
			 * Next data
			 *********************************/
			c.moveToNext();
			
		}//for (int i = 0; i < c.getCount(); i++)
		
		return res;
		
	}//private static boolean refactorData_colPrice_CanDo(

	
	public static int getYomi(Activity actv, Dialog dlg) {
		
//		Methods_sl.getYomi_xml(actv, dlg);
		
//		int res = Methods_sl.getYomi_B18_v_1_3(actv, dlg);
		
		String kw = "�X�|���W";
		String enc = "utf-8";
		
//		int res = Methods_sl.getYomi_full(kw, enc);
//		String[] strings = Methods_sl.getYomi_full(kw, enc);
		String[] strings = Methods.getYomi_full(kw, enc);

		if (strings != null) {
			
			for (String str : strings) {
				
				// Log
				Log.d("Methods_sl.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]",
					"str=" + str);
				
			}//for (String str : strings)
			
			return CONS.GETYOMI_SUCCESSFUL;
			
		} else {//if (strings != null)
			
			return CONS.GETYOMI_FAILED;
			
		}//if (strings != null)
		
	}//public static int getYomi(Activity actv, Dialog dlg)

	public static String[] getYomi_full(String kw, String enc) {
		/*********************************
		 * Build a url string
		 *********************************/
		String url = "http://jlp.yahooapis.jp/FuriganaService/V1/furigana" +
				"?appid=dj0zaiZpPTZjQWNRNExhd0thayZkPVlXazlhR2gwTTJGUE56SW1jR285TUEtLSZzPWNvbnN1bWVyc2VjcmV0Jng9Mjc-" +
				"&grade=1" +
				"&sentence=" + kw;
		
		/*********************************
		 * Get: Http entity
		 *********************************/
		HttpEntity entity = Methods.getYomi_getHttpEntity(url);

		if (entity == null) {
			
			// Log
			Log.d("Methods_sl.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "entity == null");
			
			return null;
			
		}//if (entity == null)

		/*********************************
		 * Get: XMLPullParser
		 *********************************/
		String xmlString =
					Methods.convert_HttpEntity2XmlString(entity);
		
		XmlPullParser xmlPullParser =
				Methods.getYomi_getXmlParser(xmlString, enc);
		
		/*********************************
		 * Extract: Furigana
		 *********************************/
		String furigana =
//				Methods.getYomi_getFurigana(xmlPullParser, "Furigana");
//				Methods_sl.getYomi_B18_v_1_3__3_getFurigana(
//				xmlPullParser, "Furigana");
				Methods_sl.getYomi_B18_v_2_0__3_getFurigana(
						xmlPullParser, "Furigana");
		
		/*********************************
		 * Extract: Surface
		 *********************************/
		xmlPullParser =
				Methods.getYomi_getXmlParser(xmlString, "utf-8");
		
		String surface =
				Methods.getYomi_getFurigana(xmlPullParser, "Surface");
//				Methods_sl.getYomi_B18_v_1_3__3_getFurigana(
//										xmlPullParser, "Surface");

		/*********************************
		 * Return
		 *********************************/
//		if (furigana == null) {
		if (furigana == null && surface == null) {

			// Log
			Log.d("Methods_sl.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]",
					"surface=" + surface + "/"
					+ "furigana == null");

			return null;
			
		} else {//if (furigana == null)
			
			// Log
			Log.d("Methods_sl.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]",
					"surface=" + surface + "/"
					+ "furigana = " + furigana);
			
			return new String[]{kw, surface, furigana};
			
		}//if (furigana == null)

	}//private static String[] getYomi_full(String kw, String enc)

	private static String
	getYomi_B18_v_2_0__3_getFurigana
	(XmlPullParser xmlPullParser, String targetTag) {

		/*********************************
		 * Steps
		 * 
		 * Find a tag "Word"
		 * Found a END_TAG?
		 * 	If no
		 * 		Found a Surface tag?
		 * 			If yes
		 * 				Store the text to SURFACE:StringBuilder
		 * 			If no
		 * 	If yes
		 *********************************/
		
		String targetString = null;
		
		try {
			
			for(int e = xmlPullParser.getEventType();
					e != XmlPullParser.END_DOCUMENT;
					e = xmlPullParser.next()) {
				
				if(e == XmlPullParser.START_TAG &&
//						xmlPullParser.getName().equals("Furigana")) {
						xmlPullParser.getName().equals(targetTag)) {
					
					targetString = xmlPullParser.nextText();
					
					// Log
					Log.d("Methods_sl.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber()
							+ ":"
							+ Thread.currentThread().getStackTrace()[2]
									.getMethodName() + "]",
//							"Furigana=" + xmlPullParser.nextText());
							targetTag + "=" + targetString);
					
					return targetString;
					
				} else {//if
					
//					// Log
//					Log.d("Methods_sl.java"
//							+ "["
//							+ Thread.currentThread().getStackTrace()[2]
//									.getLineNumber()
//							+ ":"
//							+ Thread.currentThread().getStackTrace()[2]
//									.getMethodName() + "]",
//							"tag=" + xmlPullParser.getName());
					
				}//if
				
			}//for
			
		} catch (XmlPullParserException e) {

			// Log
			Log.e("Methods_sl.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return null;
			
		} catch (IOException e) {

			// Log
			Log.e("Methods_sl.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return null;

		}//try

		return targetString;
		
	}//private static String getYomi_B18_v_2_0__3_getFurigana(XmlPullParser xmlPullParser)

	private static int getYomi_B18_v_1_3(Activity actv, Dialog dlg) {
		// TODO Auto-generated method stub
		/*********************************
		 * Build a url string
		 *********************************/
//		String sen = "�`�s�[";
		String sen = "�X�|���W";
		
		String url = "http://jlp.yahooapis.jp/FuriganaService/V1/furigana" +
		"?appid=dj0zaiZpPTZjQWNRNExhd0thayZkPVlXazlhR2gwTTJGUE56SW1jR285TUEtLSZzPWNvbnN1bWVyc2VjcmV0Jng9Mjc-" +
		"&grade=1" +
		"&sentence=" + sen;
		
		/*********************************
		 * Get: Http entity
		 *********************************/
		HttpEntity entity =
//				Methods_sl.getYomi_B18_v_1_3__1_getHttpEntity(sen);
				Methods.getYomi_getHttpEntity(url);
		
		if (entity == null) {
			
			// Log
			Log.d("Methods_sl.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "entity == null");
			
			return CONS.GETYOMI_FAILED;
			
		}//if (entity == null)
		
		/*********************************
		 * Get: XMLPullParser
		 *********************************/
		String xmlString =
//					Methods_sl.convert_HttpEntity2XmlString(entity);
					Methods.convert_HttpEntity2XmlString(entity);
		
		XmlPullParser xmlPullParser =
//				Methods_sl.getYomi_B18_v_1_3__2_getXmlParser(entity);
//				Methods_sl.getYomi_B18_v_1_3__2_getXmlParser(xmlString);
				Methods.getYomi_getXmlParser(xmlString, "utf-8");
		
		/*********************************
		 * Parse xml: Extract the target string
		 *********************************/
//		String furigana =
//				Methods_sl.getYomi_B18_v_1_3__3_getFurigana(
//								xmlPullParser);

		String furigana =
//				Methods_sl.getYomi_B18_v_1_3__3_getFurigana(
//								xmlPullParser, "Furigana");
				Methods.getYomi_getFurigana(xmlPullParser, "Furigana");
		
		/*********************************
		 * Extract: Surface
		 *********************************/
		xmlPullParser =
//				Methods_sl.getYomi_B18_v_1_3__2_getXmlParser(xmlString);
				Methods.getYomi_getXmlParser(xmlString, "utf-8");
		
		String targetString =
//				Methods_sl.getYomi_B18_v_1_3__3_getFurigana(
//							xmlPullParser, "Surface");
				Methods.getYomi_getFurigana(xmlPullParser, "Surface");
		
		// Log
		Log.d("Methods_sl.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]",
				"target=" + sen + "/"
				+ "Surface=" + targetString);
		
		/*********************************
		 * Return
		 *********************************/
//		if (furigana == null) {
		if (furigana == null && targetString == null) {

			// Log
			Log.d("Methods_sl.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]",
					"target=" + sen + "/"
					+ "furigana == null");

			return CONS.GETYOMI_FAILED;
			
		} else {//if (furigana == null)
			
			// Log
			Log.d("Methods_sl.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]",
					"target=" + sen + "/"
					+ "furigana = " + furigana);
			
			return CONS.GETYOMI_SUCCESSFUL;
			
		}//if (furigana == null)
		
	}//private static int getYomi_B18_v_1_3(Activity actv, Dialog dlg)

	private static String
	getYomi_B18_v_1_3__3_getFurigana(XmlPullParser xmlPullParser) {

		String furigana = null;
		
		try {
			
			for(int e = xmlPullParser.getEventType();
					e != XmlPullParser.END_DOCUMENT;
					e = xmlPullParser.next()) {
				
				if(e == XmlPullParser.START_TAG &&
						xmlPullParser.getName().equals("Furigana")) {
					
					furigana = xmlPullParser.nextText();
					
					// Log
					Log.d("Methods_sl.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber()
							+ ":"
							+ Thread.currentThread().getStackTrace()[2]
									.getMethodName() + "]",
//							"Furigana=" + xmlPullParser.nextText());
							"Furigana=" + furigana);
					
					return furigana;
					
				} else {//if
					
					// Log
					Log.d("Methods_sl.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber()
							+ ":"
							+ Thread.currentThread().getStackTrace()[2]
									.getMethodName() + "]",
							"tag=" + xmlPullParser.getName());
					
				}//if
				
			}//for
			
		} catch (XmlPullParserException e) {

			// Log
			Log.e("Methods_sl.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return null;
			
		} catch (IOException e) {

			// Log
			Log.e("Methods_sl.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return null;

		}//try

		return furigana;
		
	}//private static String getYomi_B18_v_1_3__3_getFurigana(XmlPullParser xmlPullParser)

	private static String
	getYomi_B18_v_1_3__3_getFurigana
	(XmlPullParser xmlPullParser, String targetTag) {

		String targetString = null;
		
		try {
			
			for(int e = xmlPullParser.getEventType();
					e != XmlPullParser.END_DOCUMENT;
					e = xmlPullParser.next()) {
				
				if(e == XmlPullParser.START_TAG &&
//						xmlPullParser.getName().equals("Furigana")) {
						xmlPullParser.getName().equals(targetTag)) {
					
					targetString = xmlPullParser.nextText();
					
					// Log
					Log.d("Methods_sl.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber()
							+ ":"
							+ Thread.currentThread().getStackTrace()[2]
									.getMethodName() + "]",
//							"Furigana=" + xmlPullParser.nextText());
							targetTag + "=" + targetString);
					
					return targetString;
					
				} else {//if
					
//					// Log
//					Log.d("Methods_sl.java"
//							+ "["
//							+ Thread.currentThread().getStackTrace()[2]
//									.getLineNumber()
//							+ ":"
//							+ Thread.currentThread().getStackTrace()[2]
//									.getMethodName() + "]",
//							"tag=" + xmlPullParser.getName());
					
				}//if
				
			}//for
			
		} catch (XmlPullParserException e) {

			// Log
			Log.e("Methods_sl.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return null;
			
		} catch (IOException e) {

			// Log
			Log.e("Methods_sl.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return null;

		}//try

		return targetString;
		
	}//private static String getYomi_B18_v_1_3__3_getFurigana(XmlPullParser xmlPullParser)

	private static XmlPullParser
	getYomi_B18_v_1_3__2_getXmlParser(HttpEntity entity) {

		/*********************************
		 * Prepare: InputStream object
		 * Ref => http://symfoware.blog68.fc2.com/blog-entry-711.html
		 *********************************/
		String xmlString = null;
		
		try {
			
			xmlString = EntityUtils.toString(entity);
			
			// Log
			Log.d("Methods_sl.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", xmlString);
			
		} catch (ParseException e) {

			// Log
			Log.e("Methods_sl.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return null;
			
		} catch (IOException e) {

			// Log
			Log.e("Methods_sl.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return null;

		}
		
		InputStream is = null;
		
		try {
			
			is = new ByteArrayInputStream(
									xmlString.getBytes("utf-8"));
			
		} catch (UnsupportedEncodingException e) {

			// Log
			Log.e("Methods_sl.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return null;
			
		}
		
		/*********************************
		 * Prepare: XML parser
		 * REF=> http://android.roof-balcony.com/shori/xml/xmlparse/
		 *********************************/
		XmlPullParser xmlPullParser = Xml.newPullParser();
		
		try {
			
			xmlPullParser.setInput(is, "UTF-8");
			
		} catch (XmlPullParserException e) {
			
			// Log
			Log.e("Methods_sl.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return null;
			
		}
		
		return xmlPullParser;
		
	}//private static XmlPullParser getYomi_B18_v_1_3__2_getXmlParser()

	private static XmlPullParser
	getYomi_B18_v_1_3__2_getXmlParser(String xmlString) {

		/*********************************
		 * Prepare: InputStream object
		 * Ref => http://symfoware.blog68.fc2.com/blog-entry-711.html
		 *********************************/
		InputStream is = null;
		
		try {
			
			is = new ByteArrayInputStream(
									xmlString.getBytes("utf-8"));
			
		} catch (UnsupportedEncodingException e) {

			// Log
			Log.e("Methods_sl.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return null;
			
		}
		
		/*********************************
		 * Prepare: XML parser
		 * REF=> http://android.roof-balcony.com/shori/xml/xmlparse/
		 *********************************/
		XmlPullParser xmlPullParser = Xml.newPullParser();
		
		try {
			
			xmlPullParser.setInput(is, "UTF-8");
			
		} catch (XmlPullParserException e) {
			
			// Log
			Log.e("Methods_sl.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return null;
			
		}
		
		return xmlPullParser;
		
	}//private static XmlPullParser getYomi_B18_v_1_3__2_getXmlParser()

	public static String
	convert_HttpEntity2XmlString(HttpEntity entity) {

		/*********************************
		 * Prepare: InputStream object
		 * Ref => http://symfoware.blog68.fc2.com/blog-entry-711.html
		 *********************************/
		String xmlString = null;
		
		try {
			
			xmlString = EntityUtils.toString(entity);
			
			// Log
			Log.d("Methods_sl.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", xmlString);
			
		} catch (ParseException e) {

			// Log
			Log.e("Methods_sl.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return null;
			
		} catch (IOException e) {

			// Log
			Log.e("Methods_sl.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return null;

		}
		
		return xmlString;
		
	}//private static XmlPullParser getYomi_B18_v_1_3__2_getXmlParser()

	private static HttpEntity 
	getYomi_B18_v_1_3__1_getHttpEntity(String sen) {

		String url = "http://jlp.yahooapis.jp/FuriganaService/V1/furigana" +
		"?appid=dj0zaiZpPTZjQWNRNExhd0thayZkPVlXazlhR2gwTTJGUE56SW1jR285TUEtLSZzPWNvbnN1bWVyc2VjcmV0Jng9Mjc-" +
		"&grade=1" +
		"&sentence=" + sen;
//		+ "output=json";
		
		HttpPost httpPost = new HttpPost(url);
		
		httpPost.setHeader("Content-type", "application/json");
		
		/*********************************
		 * memo
		 *********************************/
		HttpUriRequest postRequest = httpPost;
		
		DefaultHttpClient dhc = new DefaultHttpClient();
		
		HttpResponse hr = null;
		
		/*********************************
		 * Execute request
		 *********************************/
		try {
			
			// Log
			Log.d("Methods_sl.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Executing postRequest...");
			
			hr = dhc.execute(postRequest);
			
		} catch (ClientProtocolException e) {

			// Log
			Log.e("Methods_sl.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return null;
			
		} catch (IOException e) {

			// Log
			Log.e("Methods_sl.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return null;
			
		}//try
		
		/*********************************
		 * Process response
		 *********************************/
		if (hr == null) {
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "hr == null");
			
			return null;
			
		}//if (hr == null)

		/*********************************
		 * Get status
		 *********************************/
		int statusCode = hr.getStatusLine().getStatusCode();
		
		// Log
		Log.d("Methods_sl.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "statusCode: " + statusCode);		

//		HttpEntity entity = hr.getEntity();
		return hr.getEntity();
		
	}//private static HttpEntity getYomi_B18_v_1_3__1_getHttpEntity(String sen)

	private static int getYomi_xml(Activity actv, Dialog dlg) {
		// TODO Auto-generated method stub
		String sen = "�`�s�[";
		
		String url = "http://jlp.yahooapis.jp/FuriganaService/V1/furigana" +
		"?appid=dj0zaiZpPTZjQWNRNExhd0thayZkPVlXazlhR2gwTTJGUE56SW1jR285TUEtLSZzPWNvbnN1bWVyc2VjcmV0Jng9Mjc-" +
		"&grade=1" +
		"&sentence=" + sen;
//		+ "output=json";
		
		HttpPost httpPost = new HttpPost(url);
		
		httpPost.setHeader("Content-type", "application/json");
		
		/*********************************
		 * memo
		 *********************************/
		HttpUriRequest postRequest = httpPost;
		
		DefaultHttpClient dhc = new DefaultHttpClient();
		
		HttpResponse hr = null;
		
		/*********************************
		 * Execute request
		 *********************************/
		try {
			
			// Log
			Log.d("Methods_sl.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Executing postRequest...");
			
			hr = dhc.execute(postRequest);
			
		} catch (ClientProtocolException e) {

			// Log
			Log.e("Methods_sl.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return CONS.GETYOMI_FAILED;
			
		} catch (IOException e) {

			// Log
			Log.e("Methods_sl.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return CONS.GETYOMI_FAILED;
			
		}//try
		
		/*********************************
		 * Process response
		 *********************************/
		if (hr == null) {
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "hr == null");
			
			return CONS.GETYOMI_FAILED;
			
		}//if (hr == null)

		/*********************************
		 * Get status
		 *********************************/
		int statusCode = hr.getStatusLine().getStatusCode();
		
		// Log
		Log.d("Methods_sl.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "statusCode: " + statusCode);		

		HttpEntity entity = hr.getEntity();
		
		/*********************************
		 * Prepare: InputStream object
		 * Ref => http://symfoware.blog68.fc2.com/blog-entry-711.html
		 *********************************/
		String xmlString = null;
		
		try {
			
			xmlString = EntityUtils.toString(entity);
			
			// Log
			Log.d("Methods_sl.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", xmlString);
			
		} catch (ParseException e) {

			// Log
			Log.e("Methods_sl.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return CONS.GETYOMI_FAILED;
			
		} catch (IOException e) {

			// Log
			Log.e("Methods_sl.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return CONS.GETYOMI_FAILED;

		}
		
		InputStream is = null;
		
		try {
			
			is = new ByteArrayInputStream(
									xmlString.getBytes("utf-8"));
			
		} catch (UnsupportedEncodingException e) {

			// Log
			Log.e("Methods_sl.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return CONS.GETYOMI_FAILED;
			
		}
		
		/*********************************
		 * Prepare: XML parser
		 * REF=> http://android.roof-balcony.com/shori/xml/xmlparse/
		 *********************************/
		XmlPullParser xmlPullParser = Xml.newPullParser();
		
		try {
			
			xmlPullParser.setInput(is, "UTF-8");
			
			/*********************************
			 * XML => Parsing
			 *********************************/
			for(int e = xmlPullParser.getEventType();
					e != XmlPullParser.END_DOCUMENT;
					e = xmlPullParser.next()) {
				
				if(e == XmlPullParser.START_TAG &&
						xmlPullParser.getName().equals("Furigana")) {
					
					// Log
					Log.d("Methods_sl.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber()
							+ ":"
							+ Thread.currentThread().getStackTrace()[2]
									.getMethodName() + "]",
							"Furigana=" + xmlPullParser.nextText());
					
				} else {//if
					
					// Log
					Log.d("Methods_sl.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber()
							+ ":"
							+ Thread.currentThread().getStackTrace()[2]
									.getMethodName() + "]",
							"tag=" + xmlPullParser.getName());
					
				}//if
				
			}//for

			
		} catch (XmlPullParserException e) {
			
			// Log
			Log.e("Methods_sl.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return CONS.GETYOMI_FAILED;
			
		} catch (IOException e) {

			// Log
			Log.e("Methods_sl.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return CONS.GETYOMI_FAILED;
			
		}
		
		return CONS.GETYOMI_SUCCESSFUL;
		
	}//private static int getYomi_xml(Activity actv, Dialog dlg)

	/***************************************
	 * Sort list => By Yomi
	 ***************************************/
	public static void
	sortItemList(List<ShoppingItem> tiList) {
		
		Collections.sort(tiList, new Comparator<ShoppingItem>(){

//			@Override
			public int compare(ShoppingItem i1, ShoppingItem i2) {
				// TODO �����������ꂽ���\�b�h�E�X�^�u
				
//				return (int) (i1.getDate_added() - i2.getDate_added());
				
//				return (int) (i1.getName().compareToIgnoreCase(i2.getName()));
				
//				// Log
//				Log.d("Methods_sl.java"
//						+ "["
//						+ Thread.currentThread().getStackTrace()[2]
//								.getLineNumber()
//						+ ":"
//						+ Thread.currentThread().getStackTrace()[2]
//								.getMethodName() + "]",
//						"i1.getYomi()=" + i1.getYomi()
//						+ "/"
//						+ "i2.getYomi()=" + i2.getYomi());
				
				return (int) (i1.getYomi().compareToIgnoreCase(i2.getYomi()));
			}
			
		});//Collections.sort()

	}//public static void sort_tiList(List<ThumbnailItem> tiList)

	public static void playSound(Activity actv) {
		int minBufferSize = AudioTrack.getMinBufferSize(
									44100,
									AudioFormat.CHANNEL_CONFIGURATION_MONO, 
									AudioFormat.ENCODING_PCM_16BIT);

		AudioTrack audioTrack = new AudioTrack(
						AudioManager.STREAM_MUSIC, 44100,
						AudioFormat.CHANNEL_CONFIGURATION_MONO, 
						AudioFormat.ENCODING_PCM_16BIT,
						minBufferSize,
						AudioTrack.MODE_STREAM); 
		
		audioTrack.play();
		
	    int i = 0;
	    int bufferSize = 512;
	    byte [] buffer = new byte[bufferSize];
//	    InputStream inputStream = actv.getResources().openRawResource(R.raw.bgm_1);
	    
	    InputStream inputStream = actv.getResources().openRawResource(R.raw.bgm_2_koto_t150_1second);
	    
	    try {
	        while((i = inputStream.read(buffer)) != -1)
	            audioTrack.write(buffer, 0, i);
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    try {
	        inputStream.close();
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    
	}//public static void playSound(Activity actv)

	public static void playSound(Activity actv, int bgmResourceId) {
		// TODO Auto-generated method stub
		int minBufferSize = AudioTrack.getMinBufferSize(
				44100,
				AudioFormat.CHANNEL_CONFIGURATION_MONO, 
				AudioFormat.ENCODING_PCM_16BIT);

		AudioTrack audioTrack = new AudioTrack(
			AudioManager.STREAM_MUSIC, 44100,
			AudioFormat.CHANNEL_CONFIGURATION_MONO, 
			AudioFormat.ENCODING_PCM_16BIT,
			minBufferSize,
			AudioTrack.MODE_STREAM); 
		
		audioTrack.play();
		
		int i = 0;
		int bufferSize = 512;
		byte [] buffer = new byte[bufferSize];
		//InputStream inputStream = actv.getResources().openRawResource(R.raw.bgm_1);
		
//		InputStream inputStream = actv.getResources().openRawResource(R.raw.bgm_2_koto_t150_1second);
		InputStream inputStream = 
						actv.getResources().openRawResource(bgmResourceId);
		
		try {
			
			while((i = inputStream.read(buffer)) != -1)
			audioTrack.write(buffer, 0, i);
			
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			
			inputStream.close();
			
			// Log
			Log.d("Methods_sl.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Stream closed");
			
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}

		audioTrack.stop();
		
		// Log
		Log.d("Methods_sl.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Audio stopped");
		
	}//public static void playSound(Activity actv, int bgmResourceId)

	
	public static void updateListView_ToBuyList(Activity actv) {
		/***************************************
		 * Clear the list "toBuyList : ListView<ShoppingItem>
		 ***************************************/
		CONS.toBuyList.clear();
		
		/***************************************
		 * Setup db
		 ***************************************/
		DBUtils dbm = new DBUtils(actv);
		
		SQLiteDatabase rdb = dbm.getReadableDatabase();
		
		Cursor c = null;
		
		for (Integer itemId : CONS.tab_toBuyItemIds) {
			
			try {
				
				c = rdb.query(
						CONS.tableName, 
	//										DBManager.columns,
	//				CONS.columns_with_index,
						CONS.columns_with_index2,
//						String.valueOf(CONS.columns_with_index2[0]),
						String.valueOf(CONS.columns_with_index2[0]) + "=?",
						new String[]{String.valueOf(itemId.intValue())},
						null, null, null);
				
			} catch (Exception e) {
				
				// Log
				Log.e("Methods_sl.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2].getMethodName()
						+ "]", e.toString());
				
				rdb.close();
				
//				return CONS.PREP_LIST_FAILED;
				return;
				
			}//try

			/***************************************
			 * If the cursor is null, then move on to
			 * 	the next id
			 ***************************************/
			if (c == null) {
				
				// Log
				Log.d("Methods_sl.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]",
						"c==null => id=" + itemId.intValue());
				
				continue;
				
			}//if (c == null)
			
			/***************************************
			 * If no result, then also, move on to
			 * 	the next
			 ***************************************/
			if (c.getCount() < 1) {
				
				// Log
				Log.d("Methods_sl.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]",
						"c.getCount() < 1 => id=" + itemId.intValue());
				
				continue;
				
			}//if (c.getCount() < 1)
			
			/***************************************
			 * If has result, the add the new item
			 * 	to the list
			 ***************************************/
			//
			c.moveToFirst();
			
			for (int i = 0; i < c.getCount(); i++) {
	
	//			0									1		2		3		4			5
	//			{android.provider.BaseColumns._ID, "name", "yomi", "genre", "store", "price"}
				ShoppingItem item = new ShoppingItem(
						c.getInt(0),		// id store
						c.getString(1),		// name
						c.getString(2),		// yomi
						c.getString(3),		// genre
						c.getString(4),		//	store
						c.getInt(5)			// price
						);
				
				//
				CONS.toBuyList.add(item);
				
				// Log
				Log.d("Methods_sl.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]",
						"Item added to toBuyList => " + item.getName());
				
				//
				c.moveToNext();
				
			}//for (int i = 0; i < c.getCount(); i++)

		}//for (Integer itemId : CONS.tab_toBuyItemIds)

		//
		rdb.close();
		
		/***************************************
		 * Sort list
		 ***************************************/
		Methods_sl.sortItemList(CONS.toBuyList);
		
		/***************************************
		 * Update "sum" value
		 ***************************************/
		TextView tvSum = (TextView) actv.findViewById(R.id.itemlist_tab2_tv_sum);
		
		// Get sum
		int sum = 0;
		
		for (int i = 0; i < CONS.toBuyList.size(); i++) {
			
//			ShoppingItem si = CONS.toBuyList.get(0);
			ShoppingItem si = CONS.toBuyList.get(i);
			
			sum += si.getPrice();
			
		}//for (int i = 0; i < CONS.toBuyList.size(); i++)
		
		// Display
		tvSum.setText(String.format("合計 %d 円", sum));
		
	}//public static void updateToBuyList(Activity actv)

	
	public static List<PS> getPSList(Activity actv) {
		
		DBUtils dbu = new DBUtils(actv, CONS.dbName);
		
		List<PS> psList = dbu.getPSList(actv);
		
		dbu.close();
		
//		return dbu.getPSList(actv);
		return psList;
		
//		return null;
	}//public static List<PS> getPSList(Activity actv)

	
	public static void sortPSList(List<PS> psList, SortTags sortOrder) {
		
		switch (sortOrder) {
		
		case pslist_store_name:

			Collections.sort(psList, new Comparator<PS>(){

//				@Override
				public int compare(PS i1, PS i2) {

					
					return (int) (i1.getStoreName().compareToIgnoreCase(i2.getStoreName()));
				}//public int compare(PS i1, PS i2)

			});//Collections.sort()
			
			break;

		case pslist_due_date:

			Collections.sort(psList, new Comparator<PS>(){

//				@Override
				public int compare(PS i1, PS i2) {

//					// Log
//					Log.d("Methods_sl.java"
//							+ "["
//							+ Thread.currentThread().getStackTrace()[2]
//									.getLineNumber()
//							+ ":"
//							+ Thread.currentThread().getStackTrace()[2]
//									.getMethodName() + "]",
//							"i1=" + i1.getStoreName() + "(" + String.valueOf(i1.getDueDate()) +
//									" " + Methods.get_TimeLabel(i1.getDueDate()) + ")"
//							+ "i2=" + i2.getStoreName() + "(" + String.valueOf(i2.getDueDate()) +
//									" " + Methods.get_TimeLabel(i2.getDueDate()) + ")");
//					// Log
//					Log.d("Methods_sl.java"
//							+ "["
//							+ Thread.currentThread().getStackTrace()[2]
//									.getLineNumber()
//							+ ":"
//							+ Thread.currentThread().getStackTrace()[2]
//									.getMethodName() + "]",
//							"i1-i2=" + String.valueOf(i1.getDueDate() - i2.getDueDate()));
//					"i2-i1=" + String.valueOf(i2.getDueDate() - i1.getDueDate()));
//					return (int) (i2.getDueDate() - i1.getDueDate());
//					return (int) (i1.getDueDate() - i2.getDueDate());
					
//					if ((i1.getDueDate() - i2.getDueDate()) > 0) {
//						return 1;
//					} else if ((i1.getDueDate() - i2.getDueDate()) < 0) {//if ((i1.getDueDate() - i2.getDueDate()) > 0)
//						return -1;
//					} else {//if ((i1.getDueDate() - i2.getDueDate()) > 0)
//						return 0;
//					}//if ((i1.getDueDate() - i2.getDueDate()) > 0)
					
					return ((i2.getDueDate() - i1.getDueDate()) > 0) ? 1
							: ((i2.getDueDate() - i1.getDueDate()) < 0) ? -1
							: 0;
					
				}//public int compare(PS i1, PS i2)

			});//Collections.sort()
			
			break;

		default:
			break;
			
		}//switch (sortOrder)
			
	}//public static void sortPSList(List<PS> psList, SortTags sortOrder)

	
	public static
	List<ShoppingItem> getSIListFromItemList
	(Activity actv, String s_ItemList) {
		// TODO Auto-generated method stub
		String[] ary_ItemList = s_ItemList.split(" ");
		
		List<ShoppingItem> siList = new ArrayList<ShoppingItem>();
		
		/***************************************
		 * Setup db
		 ***************************************/
		DBUtils dbu = new DBUtils(actv);
		
//		SQLiteDatabase rdb = dbu.getReadableDatabase();
		
		for (int i = 0; i < ary_ItemList.length; i++) {
			
			ShoppingItem si = dbu.getSIFromDbId(ary_ItemList[i]);
			
			if (si == null) {
				
				// Log
				Log.d("Methods_sl.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]", "si => null");
				
				continue;
				
			}//if (si == null)
			
			// Log
			Log.d("Methods_sl.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "si.getName()=" + si.getName());
			
			siList.add(si);
			
		}//for (int i = 0; i < ary_ItemList.length; i++)


		return siList;
		
	}//public static List<ShoppingItem> getSIListFromItemList

	
	public static boolean isInDb_PS
	(Activity actv, String storeName, Calendar cal) {
		
//		int newYear = cal.YEAR;
		
//		int newMonth = cal.MONTH;
//		
//		int newDay = cal.DATE;

		int newYear = cal.get(Calendar.YEAR);
		int newMonth = cal.get(Calendar.MONTH);
		int newDay = cal.get(Calendar.DATE);

		DBUtils dbu = new DBUtils(actv);
		
		SQLiteDatabase rdb = dbu.getReadableDatabase();

		String sql = "SELECT * FROM " + CONS.DBAdmin.tname_purchaseSchedule;

		Cursor c = null;

		try {
			
			c = rdb.rawQuery(sql, null);
			
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
				
				rdb.close();
				
				return false;
				
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
				
				rdb.close();
				
				return false;
				
			}//if (null == c)
			
			/*********************************
			 * 
			 *********************************/
			c.moveToFirst();
			
			/***************************************
			 * 1. From the cursor, get due date in millseconds (d1
			 * 2. From d1, get integer array (d2)
			 * 3. Compare d2 with the data in the param cal
			 * 
			 ***************************************/
			for (int i = 0; i < c.getCount(); i++) {
				/***************************************
				 * Get target data: 1. Due date 2. Store name
				 ***************************************/
				long targetDueDate = c.getLong(
								c.getColumnIndex(
										CONS.DBAdmin.col_purchaseSchedule[1]));
				
				int[] targetDueDateData = Methods.getDateArrayFromLongData(targetDueDate);
				
				
				String targetStoreName = c.getString(
							c.getColumnIndex(
									CONS.DBAdmin.col_purchaseSchedule[0]));
				
				// Log
				Log.d("Methods_sl.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]",
//						"cal.YEAR=" + cal.YEAR
//						+ "/"
//						+ "cal.MONTH=" + cal.MONTH
//						+ "/"
//						+ "cal.DATE=" + cal.DATE);
						"newYear=" + newYear
						+ "/"
						+ "newMonth=" + newMonth
						+ "/"
						+ "newDay=" + newDay);
				// Log
				Log.d("Methods_sl.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]",
						"targetStoreName=" + targetStoreName
						+ "/"
						+ "targetDueDateData[0]=" + targetDueDateData[0]
						+ "/"
						+ "targetDueDateData[1]=" + targetDueDateData[1]
						+ "/"
						+ "targetDueDateData[2]=" + targetDueDateData[2]
						);
				
				
//				if (cal.YEAR == dueDateData[0]
//						&& cal.MONTH == dueDateData[1]
//						&& cal.DATE == dueDateData[2]) {
				if (targetStoreName.equals(storeName)
					&& newYear == targetDueDateData[0]
					&& newMonth == targetDueDateData[1]
					&& newDay == targetDueDateData[2]) {
					
					rdb.close();
					
					return true;
					
				}//if (cal.YEAR == dueDateData[0])
				
				c.moveToNext();
				
			}//for (int i = 0; i < c.getCount(); i++)
			
		} catch (Exception e) {

			// Log
			Log.e("DialogOnItemClickListener.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Exception => " + e.toString());
			
			rdb.close();
			
			return false;
			
		}//try 
		
		/***************************************
		 * Close db
		 ***************************************/
		rdb.close();
		
		return false;
		
	}//public static boolean isInDb_PS()

	public static void sortItemList_GenreItemName() {
		// TODO Auto-generated method stub
		
	}

	public static void
	sortItemList_GenreItemName(List<ShoppingItem> itemList) {
		// TODO Auto-generated method stub
		/***************************************
		 * Sort: Genre
		 ***************************************/
		Collections.sort(itemList, new Comparator<ShoppingItem>(){

//			@Override
			public int compare(ShoppingItem i1, ShoppingItem i2) {

				
				return (int) (i1.getGenre().compareTo(i2.getGenre()));
				
			}//public int compare(PS i1, PS i2)

		});//Collections.sort()

		/***************************************
		 * Sort: Item name
		 ***************************************/
		Collections.sort(itemList, new Comparator<ShoppingItem>(){

//			@Override
			public int compare(ShoppingItem i1, ShoppingItem i2) {
				
//				if (!i1.getName().equals(i2.getName())) {
				if (i1.getGenre().equals(i2.getGenre())) {
					
					return (i1.getYomi().compareTo(i2.getYomi()));
					
				} else {//if (i1.getName().equals(i2.getName()) == condition)
					
					return 0;
					
				}//if (i1.getName().equals(i2.getName()) == condition)
				
				
//				return (int) (i1.getName().compareTo(i2.getName()));
				
			}//public int compare(PS i1, PS i2)

		});//Collections.sort()

	}//sortItemList_GenreItemName(List<ShoppingItem> itemList)

}//public class Methods_sl
