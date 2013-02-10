package shoppinglist.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import shoppinglist.listeners.ButtonOnTouchListener;
import shoppinglist.listeners.DialogButtonOnClickListener;
import shoppinglist.listeners.DialogButtonOnTouchListener;
import shoppinglist.listeners.DialogOnItemClickListener;
import shoppinglist.main.ItemList;
import shoppinglist.main.R;
import shoppinglist.main.RegisterItem;
import shoppinglist.main.ShoppingItem;
import shoppinglist.main.SLActv;
import android.app.Activity;
import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Vibrator;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
//import android.view.WindowManager.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Methods {

	//
	static ArrayAdapter<String> adapter;		//=> Used in: public static void dlg_dropTable(Activity actv)

	public static int vibLength = 35;
	
	/*----------------------------
	 * Variables
		----------------------------*/
	//
	public static enum DialogTags {
		// dlg_generic
		dlg_generic_cancel,
		
		// dlg_register_store.xml
		dlg_register_store_ok, dlg_register_store_cancel,

		// dlg_input_empty.xml
		dlg_input_empty_btn_reenter, dlg_input_empty_btn_cancel,
		
		// dlg_reconfirm_store_name.xml
		dlg_reconfirm_store_name_btn_yes, dlg_reconfirm_store_name_btn_cancel,
		
		// dlg_register_genre.xml
		dlg_register_genre_register, dlg_register_genre_cancel,
		
		// dlg_reconfirm_genre_name.xml
		dlg_reconfirm_genre_name_btn_register, dlg_reconfirm_genre_name_btn_cancel,
		
		// dlg_create_table.xml
		dlg_create_table_create, dlg_create_table_cancel,

		// dlg_drop_table.xml
		dlg_drop_table_btn_cancel, dlg_drop_table,
		
		// dlg_confirm_drop_table.xml
		dlg_confirm_drop_table_btn_ok, dlg_confirm_drop_table_btn_cancel, dlg_confirm_drop_table,

		// dlg_filter_list.xml
		dlg_filter_list_ok, dlg_filter_list_cancel,
		
		// dlg_register_main.xml
		dlg_register_main,
		
	}//public static enum DialogTags
	
	public static enum ButtonTags {
		// DBAdminActivity.java
		db_manager_activity_create_table, db_manager_activity_drop_table,

		// ShoppingList.java
		sl_main_bt_item_list, sl_main_bt_register, sl_main_bt_db,
		
		// itemlist.xml
		itemlist_bt_choose, itemlist_bt_see_chosen,
		
	}//public static enum ButtonTags

	public static enum ViewNames {
		TV, BT,
	}
	
	public static void register_store(Activity actv) {
		/*----------------------------
		 * Steps
		 * 1. Set up
		 * 2. Add listeners => OnTouch
		 * 3. Add listeners => OnClick
			----------------------------*/
		
		// 
		Dialog dlg = new Dialog(actv);
		
		//
		dlg.setContentView(R.layout.dlg_register_store);
		
		// Title
		dlg.setTitle(R.string.dlg_register_store_title);
		
		//
		
		
		/*----------------------------
		 * 2. Add listeners => OnTouch
			----------------------------*/
		//
		Button btn_ok = (Button) dlg.findViewById(R.id.dlg_register_store_btn_ok);
		Button btn_cancel = (Button) dlg.findViewById(R.id.dlg_register_store_btn_cancel);
		
		//
		btn_ok.setTag(DialogTags.dlg_register_store_ok);
		btn_cancel.setTag(DialogTags.dlg_register_store_cancel);
		
		//
		btn_ok.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg));
		btn_cancel.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg));
		
		/*----------------------------
		 * 3. Add listeners => OnClick
			----------------------------*/
		//
		btn_ok.setOnClickListener(new DialogButtonOnClickListener(actv, dlg));
		btn_cancel.setOnClickListener(new DialogButtonOnClickListener(actv, dlg));
		
		
		//
		dlg.show();
		
		
		
		
	}//public static void register_store(Activity actv)


	public static void dlg_input_empty(Activity actv, Dialog dlg) {
		/*----------------------------
		 * Steps
		 * 1. Set up
		 * 2. Add listeners => OnTouch
		 * 3. Add listeners => OnClick
			----------------------------*/
		
		// 
		Dialog dlg_new = new Dialog(actv);
		
		//
		dlg_new.setContentView(R.layout.dlg_input_empty);
		
		// Title
		dlg_new.setTitle(R.string.dlg_input_empty_title);
		
		/*----------------------------
		 * 2. Add listeners => OnTouch
			----------------------------*/
		//
		Button btn_ok = (Button) dlg_new.findViewById(R.id.dlg_input_empty_btn_reenter);
		Button btn_cancel = (Button) dlg_new.findViewById(R.id.dlg_input_empty_btn_cancel);
		
		//
		btn_ok.setTag(DialogTags.dlg_input_empty_btn_reenter);
		btn_cancel.setTag(DialogTags.dlg_input_empty_btn_cancel);
		
		//
		btn_ok.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg_new));
		btn_cancel.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg_new));
		
		/*----------------------------
		 * 3. Add listeners => OnClick
			----------------------------*/
		//
		btn_ok.setOnClickListener(new DialogButtonOnClickListener(actv, dlg, dlg_new));
		btn_cancel.setOnClickListener(new DialogButtonOnClickListener(actv, dlg, dlg_new));
		
		//
		dlg_new.show();
		
		
	}//public static void dlg_input_empty(Activity actv, Dialog dlg)


	public static void insertStoreName(
					Activity actv, Dialog dlg, String tableName, String storeName) {
		/*----------------------------
		 * Steps
		 * 1. Table exists?
		 * 1-2. If not => Create table
		 * 2. Reconfirm store name
			----------------------------*/
		
		// 
		DBManager dbm = new DBManager(actv);
		
		SQLiteDatabase db = dbm.getWritableDatabase();
		
		//
		if (!dbm.tableExists(db, tableName)) {
			//
//			dbm.createTable(db, tableName);
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table does not exist => " + tableName);

			/*----------------------------
			 * 1-2. If not => Create table
				----------------------------*/
			//
			String[] columns = DBManager.columns_for_table_stores;
			
			String[] types = DBManager.column_types_for_table_stores;
			
			dbm.createTable_generic(db, tableName, columns, types);
			
		} else {//if (!dbm.tableExists(db, tableName))
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table exists => " + tableName);
			
		}//if (!dbm.tableExists(db, tableName))
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "Store name => " + storeName);
		
		//
		db.close();
		
		/*----------------------------
		 * 2. Reconfirm store name
			----------------------------*/
		dlg_reconfirm_store_name(actv, dlg, tableName, storeName);
		
		
	}//public static void insertStoreName(Activity actv, String tableName, String storeName)

	/****************************************
	 * dlg_reconfirm_store_name()
	 * 
	 * <Caller> 
	 * 1. insertStoreName()
	 * 
	 * <Desc> 1. <Params> 1.
	 * 
	 * <Return> 1.
	 * 
	 * <Steps> 1.
	 ****************************************/
	private static void dlg_reconfirm_store_name(
										Activity actv, Dialog dlg,
										String tableName, String storeName) {
		/*----------------------------
		 * Steps
		 * 1. Set up
		 * 2. Add listeners => OnTouch
		 * 3. Add listeners => OnClick
		 * 4. Set store name
			----------------------------*/
		
		// 
		Dialog dlg_new = new Dialog(actv);
		
		//
		dlg_new.setContentView(R.layout.dlg_reconfirm_store_name);
		
		// Title
		dlg_new.setTitle(R.string.dlg_reconfirm_store_name_title);
		
		/*----------------------------
		 * 2. Add listeners => OnTouch
			----------------------------*/
		//
		Button btn_ok = (Button) dlg_new.findViewById(R.id.dlg_reconfirm_store_name_btn_yes);
		Button btn_cancel = (Button) dlg_new.findViewById(R.id.dlg_reconfirm_store_name_btn_cancel);
		
		//
		btn_ok.setTag(DialogTags.dlg_reconfirm_store_name_btn_yes);
		btn_cancel.setTag(DialogTags.dlg_reconfirm_store_name_btn_cancel);
		
		//
		btn_ok.setOnTouchListener(new DialogButtonOnTouchListener(actv));
		btn_cancel.setOnTouchListener(new DialogButtonOnTouchListener(actv));
		
		/*----------------------------
		 * 3. Add listeners => OnClick
			----------------------------*/
		//
		btn_ok.setOnClickListener(new DialogButtonOnClickListener(actv, dlg, dlg_new));
		btn_cancel.setOnClickListener(new DialogButtonOnClickListener(actv, dlg, dlg_new));
		
		/*----------------------------
		 * 4. Set store name
			----------------------------*/
		//
//		TextView tv_store_name = (TextView) dlg_new.findViewById(R.id.dlg_reconfirm_store_name_tv_message_store_name);
		TextView tv_store_name = (TextView) dlg_new.findViewById(R.id.dlg_reconfirm_store_name_tv_store_name);
		
		tv_store_name.setText(storeName);
		tv_store_name.setTextColor(Color.YELLOW);
		
		//
		dlg_new.show();
		
	}//private static void dlg_reconfirm_store_name


	public static void insertStoreName_final(
							Activity actv, Dialog dlg, Dialog dlg2, String tableName) {
		/*----------------------------
		 * Steps
		 * 1. Set up
		 * 2. Insert data
		 * 3. Close db
		 * 4. Close dialogues
			----------------------------*/
		
		//
		TextView tv_store_name = 
					(TextView) dlg2.findViewById(
//							R.id.dlg_reconfirm_store_name_tv_message_store_name);
							R.id.dlg_reconfirm_store_name_tv_store_name);
		
		String storeName = tv_store_name.getText().toString();
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "Store name => " + storeName);
		
		
		// 
		DBManager dbm = new DBManager(actv);
		
		SQLiteDatabase db = dbm.getWritableDatabase();

		/*----------------------------
		 * 2. Insert data
			----------------------------*/
		//
		boolean result = dbm.storeData(
										db, 
										tableName, 
										DBManager.columns_for_table_stores, 
										new String[]{storeName, ""});
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "dbm.storeData => " + result);
			
		/*----------------------------
		 * 3. Close db
			----------------------------*/
		db.close();

		
		/*----------------------------
		 * 4. Close dialogues
			----------------------------*/
		//
		if (result == true) {
			// debug
			Toast.makeText(actv, "“X•Ü–¼‚ª“o˜^‚³‚ê‚Ü‚µ‚½@=>@" + storeName, 3000).show();
			
			//
			dlg2.dismiss();
			dlg.dismiss();
			
		} else {//if (result == true)
			// debug
			Toast.makeText(actv, "“X•Ü–¼“o˜^@=>@‚Å‚«‚Ü‚¹‚ñ‚Å‚µ‚½", 3000).show();
			
			//
			dlg2.dismiss();
			
		}//if (result == true)
		
		
		
//		//debug
//		String q = "DROP TABLE stores;";
//		
//		db.execSQL(q);
//		
//		// Log
//		Log.d("Methods.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "db.execSQL(q) => Done");
		
		
//		Cursor c = db.rawQuery(q, null);

//		// Log
//		Log.d("Methods.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "c => " + c.toString());
		
		
//		//
//		db.close();
		
//		// Log
//		Log.d("Methods.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "Store name => " + tv_store_name.getText().toString());
		
		
		
	}//public static void insertStoreName_final()

	public static void registerGenre(Activity actv) {
		/*----------------------------
		 * Steps
		 * 1. Set up
		 * 2. Add listeners => OnTouch
		 * 3. Add listeners => OnClick
			----------------------------*/
		
		// 
		Dialog dlg = new Dialog(actv);
		
		//
		dlg.setContentView(R.layout.dlg_register_genre);
		
		// Title
		dlg.setTitle(R.string.dlg_register_genre_title);
		
		/*----------------------------
		 * 2. Add listeners => OnTouch
			----------------------------*/
		//
		Button btn_ok = (Button) dlg.findViewById(R.id.dlg_register_genre_btn_register);
		Button btn_cancel = (Button) dlg.findViewById(R.id.dlg_register_genre_btn_cancel);
		
		//
		btn_ok.setTag(DialogTags.dlg_register_genre_register);
		btn_cancel.setTag(DialogTags.dlg_register_genre_cancel);
		
		//
		btn_ok.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg));
		btn_cancel.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg));
		
		/*----------------------------
		 * 3. Add listeners => OnClick
			----------------------------*/
		//
		btn_ok.setOnClickListener(new DialogButtonOnClickListener(actv, dlg));
		btn_cancel.setOnClickListener(new DialogButtonOnClickListener(actv, dlg));
		
		
		//
		dlg.show();
		
		
		
		
	}//public static void registerGenre(Activity actv)

	public static void dlg_reconfirm_genre_name(
								Activity actv, Dialog dlg, String tableName, String genreName) {
		/*----------------------------
		 * Steps
		 * 1. Set up
		 * 2. Add listeners => OnTouch
		 * 3. Add listeners => OnClick
		 * 4. Set store name
			----------------------------*/
		
		// 
		Dialog dlg_new = new Dialog(actv);
		
		//
		dlg_new.setContentView(R.layout.dlg_reconfirm_genre_name);
		
		// Title
		dlg_new.setTitle(R.string.generic_title_reconfirm);
		
		/*----------------------------
		 * 2. Add listeners => OnTouch
			----------------------------*/
		//
		Button btn_ok = (Button) dlg_new.findViewById(R.id.dlg_reconfirm_genre_name_btn_yes);
		Button btn_cancel = (Button) dlg_new.findViewById(R.id.dlg_reconfirm_genre_name_btn_cancel);
		
		//
		btn_ok.setTag(DialogTags.dlg_reconfirm_genre_name_btn_register);
		btn_cancel.setTag(DialogTags.dlg_reconfirm_genre_name_btn_cancel);
		
		//
		btn_ok.setOnTouchListener(new DialogButtonOnTouchListener(actv));
		btn_cancel.setOnTouchListener(new DialogButtonOnTouchListener(actv));
		
		/*----------------------------
		 * 3. Add listeners => OnClick
			----------------------------*/
		//
		btn_ok.setOnClickListener(new DialogButtonOnClickListener(actv, dlg, dlg_new));
		btn_cancel.setOnClickListener(new DialogButtonOnClickListener(actv, dlg, dlg_new));
		
		/*----------------------------
		 * 4. Set store name
			----------------------------*/
		//
//		TextView tv_store_name = (TextView) dlg_new.findViewById(R.id.dlg_reconfirm_store_name_tv_message_store_name);
		TextView tv_genre_name = 
					(TextView) dlg_new.findViewById(R.id.dlg_reconfirm_genre_name_tv_genre_name);
		
		tv_genre_name.setText(genreName);
		tv_genre_name.setTextColor(Color.YELLOW);
		
		//
		dlg_new.show();
		
	}//public static void dlg_reconfirm_genre_name
	
	public static void registerGenre_final(Activity actv, Dialog dlg1, Dialog dlg2) {
		/*----------------------------
		 * Steps
			----------------------------*/
		
	}//public static void registerGenre_final(Activity actv, Dialog dlg1, Dialog dlg2)


	
	public static void registerGenreName_final(Activity actv, Dialog dlg,
			Dialog dlg2, String tableName) {
		/*----------------------------
		 * Steps
		 * 1. Set up
		 * 1-2. Table exists?
		 * 2. Insert data
		 * 3. Close db
		 * 4. Close dialogues
			----------------------------*/
		
		//
		TextView tv_genre_name = 
					(TextView) dlg2.findViewById(
//							R.id.dlg_reconfirm_store_name_tv_message_store_name);
							R.id.dlg_reconfirm_genre_name_tv_genre_name);
		
//		// debug
//		Toast.makeText(actv, tv_genre_name.getText(), 2000).show();
		
		String genreName = tv_genre_name.getText().toString();
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "Genre name => " + genreName);
		
		
		// 
		DBManager dbm = new DBManager(actv);
		
		SQLiteDatabase db = dbm.getWritableDatabase();
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "DB => Opened");
		
		/*----------------------------
		 * 1-2. Table exists?
			----------------------------*/
		//
		boolean result = dbm.tableExists(db, tableName);
		
		/*----------------------------
		 * If the table doesn't exist, create a new one
			----------------------------*/
		if (result == false) {
			result = dbm.createTable_generic(
					db, 
					tableName, 
					DBManager.columns_for_table_genres, 
					DBManager.column_types_for_table_genres);
			
			if (result == false) {
				/*----------------------------
				 * If "create table" failed, dismiss the reconfirm dialog
				 * 		=> Close db
				 * 		=> Back to "Enter genre name" dialog
					----------------------------*/
				
				// Log
				Log.d("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "Create table => Failed: " + tableName);
				
				// debug
				Toast.makeText(actv, "Create table => Failed: " + tableName, 3000).show();
				
				db.close();
				
				dlg2.dismiss();
				
				return;
				
			}//if (result == false)
		}//if (result == false)
		
		/*----------------------------
		 * At this point, the table exists
			----------------------------*/
		

		/*----------------------------
		 * 2. Insert data
			----------------------------*/
		//
		result = dbm.storeData(
										db, 
										tableName, 
										DBManager.columns_for_table_genres, 
										new String[]{genreName, ""});

//		/*----------------------------
//		 * If "storeData" failed, dismiss the reconfirm dialog
//		 * 		=> Close db
//		 * 		=> Back to "Enter genre name" dialog
//			----------------------------*/		
//		if (result == false) {
//			// Log
//			Log.d("Methods.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "dbm.storeData => Failed");
//			
//			// debug
//			Toast.makeText(actv, "Store data => Failed", 3000).show();
//			
//			db.close();
//			
//			dlg2.dismiss();
//			
//			return;
//			
//		}//if (result == false)
//		
//		// Log
//		Log.d("Methods.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "Data stored");
			
		/*----------------------------
		 * 3. Close db
			----------------------------*/
		db.close();
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "DB => Closed");
		
		/*----------------------------
		 * 4. Close dialogues
			----------------------------*/
		//debug
//		boolean result = true;
		
		//
		if (result == true) {
			// debug
			Toast.makeText(actv, "ƒWƒƒƒ“ƒ‹–¼‚ª“o˜^‚³‚ê‚Ü‚µ‚½@=>@" + genreName, 3000).show();
			
			//
			dlg2.dismiss();
			dlg.dismiss();
			
		} else {//if (result == true)
			/*----------------------------
			 * If "storeData" failed, dismiss the reconfirm dialog
			 * 		=> Close db
			 * 		=> Back to "Enter genre name" dialog
				----------------------------*/		

			// debug
			Toast.makeText(actv, "ƒWƒƒƒ“ƒ‹–¼“o˜^@=>@‚Å‚«‚Ü‚¹‚ñ‚Å‚µ‚½", 3000).show();
			
			//
			dlg2.dismiss();
			
		}//if (result == true)
		
		
//		
////		//debug
////		String q = "DROP TABLE stores;";
////		
////		db.execSQL(q);
////		
////		// Log
////		Log.d("Methods.java" + "["
////				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
////				+ "]", "db.execSQL(q) => Done");
//		
//		
////		Cursor c = db.rawQuery(q, null);
//
////		// Log
////		Log.d("Methods.java" + "["
////				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
////				+ "]", "c => " + c.toString());
//		
//		
////		//
////		db.close();
//		
////		// Log
////		Log.d("Methods.java" + "["
////				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
////				+ "]", "Store name => " + tv_store_name.getText().toString());
//		
//		
		
	}//public static void registerGenreName_final


	public static void dlg_createTable(Activity actv) {
		Dialog dlg = new Dialog(actv);
		
		//
		dlg.setContentView(R.layout.dlg_create_table);
		
		// Title
		dlg.setTitle(R.string.dlg_create_table_title);
		
		//
		
		
		/*----------------------------
		 * 2. Add listeners => OnTouch
			----------------------------*/
		//
		Button btn_ok = (Button) dlg.findViewById(R.id.dlg_create_table_btn_create);
		Button btn_cancel = (Button) dlg.findViewById(R.id.dlg_create_table_btn_cancel);
		
		//
		btn_ok.setTag(DialogTags.dlg_create_table_create);
		btn_cancel.setTag(DialogTags.dlg_create_table_cancel);
		
		//
		btn_ok.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg));
		btn_cancel.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg));
		
		/*----------------------------
		 * 3. Add listeners => OnClick
			----------------------------*/
		//
		btn_ok.setOnClickListener(new DialogButtonOnClickListener(actv, dlg));
		btn_cancel.setOnClickListener(new DialogButtonOnClickListener(actv, dlg));
		
		
		//
		dlg.show();
		
	}//public static void dlg_createTable(Activity actv)


	public static void createTable_FromDialog(Activity actv, Dialog dlg, String tableName, String[] columns, String[] types) {
		/*----------------------------
		 * Steps
		 * 1. DBManager
		 * 2. Table exists?
		 * 3. Create table
			----------------------------*/
		// 
		DBManager dbm = new DBManager(actv);
		
		SQLiteDatabase db = dbm.getWritableDatabase();
		
		/*----------------------------
		 * 2. Table exists?
			----------------------------*/
		if (dbm.tableExists(db, tableName)) {
			//
//			dbm.createTable(db, tableName);
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table exists => " + tableName);

			return;
		}//if (dbm.tableExists(db, tableName))
		
		/*----------------------------
		 * 3. Create table
			----------------------------*/
		boolean result = dbm.createTable_generic(db, tableName, columns, types);
		
		if (result == true) {
			// debug
			Toast.makeText(actv, "Table created => " + tableName, 3000).show();
			
			//
			dlg.dismiss();
			
		} else {//if (result == true)
			// debug
			Toast.makeText(actv, "Create table => failed: " + tableName, 3000).show();
		}//if (result == true)
		
		
		
	}//public static void createTable_FromDialog(Activity actv, Dialog dlg)


	public static void dlg_createTable_isInputEmpty(Activity actv, Dialog dlg) {
		/*----------------------------
		 * Steps
		 * 1. Get views
		 * 2. Prepare data
		 * 3. Data valid?
		 * 4. Send data to other method
			----------------------------*/
		
		// Get views
		EditText et_table_name = (EditText) dlg.findViewById(R.id.dlg_create_table_et_table_name);
		EditText et_column_name = (EditText) dlg.findViewById(R.id.dlg_create_table_et_column_name);
		EditText et_data_type = (EditText) dlg.findViewById(R.id.dlg_create_table_et_data_type);
		
		if (et_table_name.getText().length() == 0 ||
			et_column_name.getText().length() == 0 ||
			et_data_type.getText().length() == 0
				) {
			// debug
			Toast.makeText(actv, "Empty box", 3000).show();
			
			return;
		} else {//if (et_column_name.getText().length() == 0)
			// debug
			Toast.makeText(actv, "Input complete", 3000).show();
			
//			dlg.dismiss();
		}//if (et_column_name.getText().length() == 0)
		
		/*----------------------------
		 * 2. Prepare data
			----------------------------*/
		//
		String[] columns = et_column_name.getText().toString().split(" ");
		String[] types = et_data_type.getText().toString().split(" ");
		
		/*----------------------------
		 * 3. Data valid?
			----------------------------*/
		if (columns.length != types.length) {
			// debug
			Toast.makeText(actv, "Columns and data types don't match", 3000).show();
			
			return;
		}//if (columns.length != types.length)
		
		/*----------------------------
		 * 4. Send data to other method
			----------------------------*/
		createTable_FromDialog(actv, dlg, et_table_name.getText().toString(), columns, types);
		
		
	}//public static void dlg_createTable_isInputEmpty(Activity actv, Dialog dlg)


	public static void dlg_dropTable(Activity actv) {
		/*----------------------------
		 * Steps
		 * 1. Dialog
		 * 		1.1. Set up
		 * 		1.2. OnTouch
		 * 		1.3. OnClick
		 * 2. Adapter
		 * 		2.1. Prepare data
		 * 		2.2. Create adapter
		 * 3. ListView
		 * 		3.1 Get view
		 * 		3.2. Set adapter to list view
		 * 		3.3. Set listener to list view
		 * 		1.5. OnListItem
		 * 4. Set listener to list view
		 * 5. Show dialog
			----------------------------*/
		// 
		Dialog dlg = new Dialog(actv);
		
		//
		dlg.setContentView(R.layout.dlg_drop_table);
		
		// Title
		dlg.setTitle(R.string.dlg_drop_table_title);
		
		
		
		// Width
//		android.view.WindowManager.LayoutParams params 
//												= dlg.getWindow().getAttributes();
//		
//		params.width = 350;
//
//		dlg.getWindow().setAttributes(params);
		/*----------------------------
		 * 1.2. Add listeners => OnTouch
			----------------------------*/
		//
		Button btn_cancel = (Button) dlg.findViewById(R.id.dlg_drop_table_btn_cancel);
		
		//
		btn_cancel.setTag(DialogTags.dlg_drop_table_btn_cancel);
		
		//
		btn_cancel.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg));
		
		/*----------------------------
		 * 1.3. Add listeners => OnClick
			----------------------------*/
		//
		btn_cancel.setOnClickListener(new DialogButtonOnClickListener(actv, dlg));
		
		
		/*----------------------------
		 * 2. Adapter
			----------------------------*/
		// 
		DBManager dbm = new DBManager(actv);
		
		SQLiteDatabase db = dbm.getWritableDatabase();

		//=> source: http://stackoverflow.com/questions/4681744/android-get-list-of-tables : "Just had to do the same. This seems to work:"
		String q = "SELECT name FROM " + "sqlite_master"+
						" WHERE type = 'table' ORDER BY name";
		
		Cursor c = null;
		try {
			c = db.rawQuery(q, null);
		} catch (Exception e) {
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception => " + e.toString());
		}
		
		// Table names list
		List<String> tableList = new ArrayList<String>();
		
		// Log
		if (c != null) {
			c.moveToFirst();
			
			for (int i = 0; i < c.getCount(); i++) {
				// Log
				Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "c.getString(0) => " + c.getString(0));
				
				//
				tableList.add(c.getString(0));
				
				// Next
				c.moveToNext();
				
			}//for (int i = 0; i < c.getCount(); i++)

		} else {//if (c != null)
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "c => null");
		}//if (c != null)
		
		
		db.close();
		
		// Adapter
		adapter = new ArrayAdapter<String>(
						actv,
						android.R.layout.simple_list_item_1, 
						tableList
				);
		
		/*----------------------------
		 * 3. ListView
			----------------------------*/
		ListView lv = (ListView) dlg.findViewById(R.id.dlg_drop_lv_table_list);
		
		// Set adapter
		lv.setAdapter(adapter);
		
		
//		LayoutParams params = lv.getLayoutParams();
//		
//		params.width = 100;
//		
//		lv.setLayoutParams(params);
		
		/*----------------------------
		 * 4. Set listener to list view
			----------------------------*/
//		lv.setOnItemClickListener(new DialogOnItemClickListener(actv, dlg));
		lv.setOnItemClickListener(new DialogOnItemClickListener(actv, dlg, DialogTags.dlg_drop_table));
		
		
		
		/*----------------------------
		 * 5. Show dialog
			----------------------------*/
		dlg.show();
		
	}//public static void dlg_dropTable(Activity actv)


	public static void dlg_confirmTableDrop(Activity actv, Dialog dlg, String tableName) {
		/*----------------------------
		 * Steps
		 * 1. Set up dialog
		 * 2. Set table name to view
		 * 3. Set listener => onTouch
		 * 4. Set listener => Cancel
		 * 5. Set listener => Drop
		 * 6. Show dialog
			----------------------------*/
		
		// 
		Dialog dlg2 = new Dialog(actv);
		
		//
		dlg2.setContentView(R.layout.dlg_confirm_drop_table);
		
		// Title
		dlg2.setTitle(R.string.generic_title_reconfirm);
		
		/*----------------------------
		 * 2. Set table name to view
			----------------------------*/
		//
//		TextView tv_table_name = (TextView) actv.findViewById(R.id.dlg_confirm_drop_table_tv_table_name);
		TextView tv_table_name = (TextView) dlg2.findViewById(R.id.dlg_confirm_drop_table_tv_table_name);
		
		tv_table_name.setText(tableName);

		/*----------------------------
		 * 3. Set listener => onTouch
			----------------------------*/
		// Buttons
		Button btn_ok = (Button) dlg2.findViewById(R.id.dlg_confirm_drop_table_btn_ok);
		Button btn_cancel = (Button) dlg2.findViewById(R.id.dlg_confirm_drop_table_btn_cancel);
		
		// Tags
		btn_ok.setTag(DialogTags.dlg_confirm_drop_table_btn_ok);
		btn_cancel.setTag(DialogTags.dlg_confirm_drop_table_btn_cancel);
		
		// Set
		btn_ok.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg));
		btn_cancel.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg));
		
		/*----------------------------
		 * 4. Set listener => Cancel
			----------------------------*/
		btn_cancel.setOnClickListener(new DialogButtonOnClickListener(actv, dlg, dlg2));
		
		/*----------------------------
		 * 5. Set listener => Drop
			----------------------------*/
		btn_ok.setOnClickListener(new DialogButtonOnClickListener(actv, dlg, dlg2));
		
		/*----------------------------
		 * 6. Show dialog
			----------------------------*/
		dlg2.show();
		
	}//public static void dlg_confirmTableDrop


	public static void dropTable(Activity actv, Dialog dlg) {
		/*----------------------------
		 * Steps
		 * 1. Get table name
		 * 2. Open db
		 * 3. Drop table
		 * 4. Dismiss dialog
		 * 5. Close db
			----------------------------*/
		
		// 
		TextView tv_table_name = (TextView) dlg.findViewById(R.id.dlg_confirm_drop_table_tv_table_name);
		
		String tableName = tv_table_name.getText().toString();
		
		// 
		DBManager dbm = new DBManager(actv);
		
		SQLiteDatabase db = dbm.getWritableDatabase();

		boolean result = dbm.dropTable(actv, db, tv_table_name.getText().toString());
		
		if (result == true) {
			// debug
			Toast.makeText(actv, "Table dropped => " + tableName, 3000).show();
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table dropped => " + tableName);
			
		} else {//if (result == true)
			// debug
			Toast.makeText(actv, "Drop table => Failed: " + tableName, 3000).show();
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Drop table => Failed: " + tableName);
			
		}//if (result == true)
		
		/*----------------------------
		 * 4. Dismiss dialog
			----------------------------*/
		dlg.dismiss();
		
		db.close();
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "db => Closed");
		
		
	}//public static void dropTable(Activity actv, Dialog dlg)


	public static void dlg_filterList(Activity actv) {
		/*----------------------------
		 * Steps
		 * 1. Set up
		 * 2. Prepare data for spinners
		 * 		2.1. Stores
		 * 		2.2. Genres
		 * 		2.3. Close db
		 * 3. Set data to adapter
		 * 4. Adapter to spinner
		 * 5. Set listeners
		 * 		5.1. Touch
		 * 		5.2. Click
		 * 9. Show dialog
			----------------------------*/
		
		// 
		Dialog dlg = new Dialog(actv);
		
		//
		dlg.setContentView(R.layout.dlg_filter_list);
		
		// Title
		dlg.setTitle(R.string.dlg_filter_list_tv_title);
		
		/*----------------------------
		 * 2. Prepare data for spinners
			----------------------------*/
		/*----------------------------
		 * 2.1. Stores
			----------------------------*/
		List<String> storeList = new ArrayList<String>();
		
		DBManager dbm = new DBManager(actv);
		
		SQLiteDatabase db = dbm.getReadableDatabase();
		
		Cursor c = dbm.getAllData(db, "stores", DBManager.columns_for_table_stores_with_index);
		
		// All
		storeList.add(actv.getString(R.string.generic_label_all));
		
		//
		c.moveToFirst();
		
		for (int i = 0; i < c.getCount(); i++) {
			//
			storeList.add(c.getString(1));
			
			//
			c.moveToNext();
		}//for (int i = 0; i < c.getCount(); i++)
		
		/*----------------------------
		 * 2.2. Genres
			----------------------------*/
		List<String> genreList = new ArrayList<String>();
		
		c = dbm.getAllData(db, "genres", DBManager.columns_for_table_genres_with_index);
		
		// All
		genreList.add(actv.getString(R.string.generic_label_all));
		
		//
		c.moveToFirst();
		
		for (int i = 0; i < c.getCount(); i++) {
			//
			genreList.add(c.getString(1));
			
			//
			c.moveToNext();
		}//for (int i = 0; i < c.getCount(); i++)
		
		/*----------------------------
		 * 2.3. Close db
			----------------------------*/
		db.close();
		
		/*----------------------------
		 * 3. Set data to adapter
			----------------------------*/
		// Stores
		ArrayAdapter<String> adapterStore = new ArrayAdapter<String>(
	              actv, android.R.layout.simple_spinner_item, storeList);
		
		// Stores
		ArrayAdapter<String> adapterGenre = new ArrayAdapter<String>(
	              actv, android.R.layout.simple_spinner_item, genreList);
		
		// Drop down view
		adapterStore.setDropDownViewResource(
				android.R.layout.simple_spinner_dropdown_item);
		
		adapterGenre.setDropDownViewResource(
				android.R.layout.simple_spinner_dropdown_item);
		
		/*----------------------------
		 * 4. Adapter to spinner
			----------------------------*/
		//
		Spinner spStore = (Spinner) dlg.findViewById(R.id.dlg_filter_list_sp_store);
		Spinner spGenre = (Spinner) dlg.findViewById(R.id.dlg_filter_list_sp_genre);
		
		spStore.setAdapter(adapterStore);
		spGenre.setAdapter(adapterGenre);
		
		/*----------------------------
		 * 5. Set listeners
			----------------------------*/
		/*----------------------------
		 * 5.1. Touch
			----------------------------*/
		// View
		Button btn_ok = (Button) dlg.findViewById(R.id.dlg_filter_list_bt_ok);
		Button btn_cancel = (Button) dlg.findViewById(R.id.dlg_filter_list_bt_cancel);
		
		// Tags
		btn_ok.setTag(DialogTags.dlg_filter_list_ok);
		btn_cancel.setTag(DialogTags.dlg_filter_list_cancel);
		
		// Set
		btn_ok.setOnTouchListener(new DialogButtonOnTouchListener(actv));
		btn_cancel.setOnTouchListener(new DialogButtonOnTouchListener(actv));
		
		/*----------------------------
		 * 5.2. Click
			----------------------------*/
		// 
		btn_ok.setOnClickListener(new DialogButtonOnClickListener(actv, dlg));
		btn_cancel.setOnClickListener(new DialogButtonOnClickListener(actv, dlg));
		
		/*----------------------------
		 * 9. Show dialog
			----------------------------*/
		dlg.show();
		
	}//public static void dlg_filterList(Activity actv)


	public static void filterList(Activity actv, Dialog dlg) {
		/*----------------------------
		 * Steps
		 * 1. Get db
		 * 2. Get store name and genre name
		 * 2-2. Dismiss dlg
		 * 3. Build query
		 * 4. Exec query
		 * 5. Update list
		 * 6. Close db
		 * 7. Notify adapter
		 * 8. Sort adapter
			----------------------------*/
		// 
		DBManager dbm = new DBManager(actv);
		
		SQLiteDatabase db = dbm.getReadableDatabase();

		/*----------------------------
		 * 2. Get store name and genre name
			----------------------------*/
		Spinner spStore = (Spinner)dlg.findViewById(R.id.dlg_filter_list_sp_store);
		Spinner spGenre = (Spinner)dlg.findViewById(R.id.dlg_filter_list_sp_genre);
		
		String storeName = (String) spStore.getSelectedItem();
		String genreName = (String) spGenre.getSelectedItem();

		/*----------------------------
		 * 2-2. Dismiss dlg
			----------------------------*/
		dlg.dismiss();

		/*----------------------------
		 * 3. Build query
			----------------------------*/
		//
		String query;
		
		// Both are "All"
		if (storeName.equals(actv.getString(R.string.generic_label_all)) &&
				genreName.equals(actv.getString(R.string.generic_label_all))) {
			query = "SELECT * FROM " + DBManager.tableName;

		// Store => All, Genre => Specific
		} else if (storeName.equals(actv.getString(R.string.generic_label_all)) &&
						!genreName.equals(actv.getString(R.string.generic_label_all))) {
			
			query = "SELECT * FROM " + DBManager.tableName + 
							" WHERE genre is '" + genreName + "'";
					
		// Store => Specific, Genre => All
		} else if (!storeName.equals(actv.getString(R.string.generic_label_all)) &&
						genreName.equals(actv.getString(R.string.generic_label_all))) {
			
			query = "SELECT * FROM " + DBManager.tableName + 
					" WHERE store is '" + storeName + "'";

		// Store => Specific, Genre => Specific
		} else {
			
			query = "SELECT * FROM " + DBManager.tableName + 
					" WHERE store is '" + storeName + "'" + " AND " +
					"genre is '" + genreName + "'";
			
		}//if (storeName.equals(actv.getString(R.string.generic_label_all)))
		
		/*----------------------------
		 * 4. Exec query
			----------------------------*/
		Cursor c = db.rawQuery(query, null);
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "c.getCount() => " + c.getCount());
		
		/*----------------------------
		 * 5. Update list
			----------------------------*/
		//
		c.moveToFirst();
		
		ItemList.list.clear();
		
		//
		for (int i = 0; i < c.getCount(); i++) {
			//
			ShoppingItem item = new ShoppingItem(
									c.getString(1),		// store
									c.getString(2),		// name
									c.getInt(3),			// price
									c.getString(4),		// genre
									c.getInt(0)				// id
									);
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "c.getString(0) => " + c.getString(0));
			
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "c.getString(1) => " + c.getString(1));
			
			//
			ItemList.list.add(item);
			
			//
			c.moveToNext();

		}//for (int i = 0; i < c.getCount(); i++)
		
		// Sort list
//		Collections.sort(ItemList.list);
		
		
		/*----------------------------
		 * 6. Close db
			----------------------------*/
		db.close();
		
		/*----------------------------
		 * 7. Notify adapter
			----------------------------*/
		ItemList.adapter.notifyDataSetChanged();
		
		/*----------------------------
		 * 8. Sort adapter
			----------------------------*/
		Comparator<Object> cmp = new Comparator<Object>(){

//			@Override
			public int compare(Object obj1, Object obj2) {
				// 
				String itemName1 = ((ShoppingItem) obj1).getName();
				String itemName2 = ((ShoppingItem) obj2).getName();
				
				return itemName1.compareToIgnoreCase(itemName2);
			}//public int compare(Object obj1, Object obj2)
			
		};//Comparator<Object> cmp = new Comparator<Object>()
		
		// Sort
		ItemList.adapter.sort(cmp);
		
	}//public static void filterList(Activity actv, Dialog dlg)
	
	/****************************************
	 *
	 * 
	 * <Caller> 
	 * 1. ShoppingList.add_listeners()
	 * 
	 *  <Desc> 
	 *  1. REF=> ChineseReader2\src\cr2\main\Utils.java
	 *  
	 *  <Params> 1.
	 * 
	 * <Return> 1.
	 * 
	 * <Steps> 1.
	 ****************************************/
	public static void setOnTouchListener_button(Activity actv, Methods.ViewNames viewName, 
				Methods.ButtonTags tagName, int resourceId) {
		//
	//		if (viewName.equals("tv")) {
		/*----------------------------
		 * memo: Second param needs not to be enum. To avoid mistyping,
		 * 				I decieded to use enum instead of raw string :20120721_182940
			----------------------------*/
		
		if (viewName == Methods.ViewNames.TV) {
			// Get the view
			TextView tv = (TextView) actv.findViewById(resourceId);
			  
			// Set a tag
			tv.setTag(tagName);
			  
			// Set a listener
			tv.setOnTouchListener(new ButtonOnTouchListener(actv));
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Listener set => " + tv.toString());
			
			
//		} else if (viewName.equals("bt")) {//if (viewName.equals("textview"))
		} else if (viewName == Methods.ViewNames.BT) {//if (viewName.equals("textview"))
			// Get the view
			Button bt = (Button) actv.findViewById(resourceId);
			  
			// Set a tag
			bt.setTag(tagName);
			  
			// Set a listener
			bt.setOnTouchListener(new ButtonOnTouchListener(actv));

			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Listener set => " + bt.toString());
		
		}//if (viewName.equals("textview"))
		
	}//public void setOnTouchListener_button()
	
	
	
	public static void dlg_register_main(Activity actv) {
		/*----------------------------
		 * Steps
		 * 1. Get a dialog
		 * 2. List view
		 * 3. Set listener => list
		 * 9. Show dialog
			----------------------------*/
		 
		Dialog dlg = dlg_template_cancel(actv, 
				R.layout.dlg_register_main, R.string.generic_register,
				R.id.dlg_register_main_btn_cancel, Methods.DialogTags.dlg_generic_cancel);
		
		/*----------------------------
		 * 2. List view
		 * 		1. Get view
		 * 		2. Prepare list data
		 * 		3. Prepare adapter
		 * 		4. Set adapter
			----------------------------*/
		ListView lv = (ListView) dlg.findViewById(R.id.dlg_register_main_lv_list);
		
		/*----------------------------
		 * 2.2. Prepare list data
			----------------------------*/
//		List<String> registerItems = new ArrayList<String>();
		List<SLActv.registerChoice> registerItems = 
					new ArrayList<SLActv.registerChoice>();
		
		for (SLActv.registerChoice item : SLActv.registerChoice.values()) {
			
			registerItems.add(item);
			
		}//for (String string : ShoppingListActivity.registerItems)
		
//		ArrayAdapter<String> adp = new ArrayAdapter<String>(
		ArrayAdapter<SLActv.registerChoice> adp = 
				new ArrayAdapter<SLActv.registerChoice>(
		
				actv,
				android.R.layout.simple_list_item_1,
				registerItems
		);
		
		/*----------------------------
		 * 2.4. Set adapter
			----------------------------*/
		lv.setAdapter(adp);
		
		/*----------------------------
		 * 3. Set listener => list
			----------------------------*/
		lv.setOnItemClickListener(
						new DialogOnItemClickListener(
								actv, 
								dlg, 
								Methods.DialogTags.dlg_register_main));
		
		/*----------------------------
		 * 9. Show dialog
			----------------------------*/
		dlg.show();
		
	}//public static void dlg_register_main(Activity actv)
	
	public static Dialog dlg_template_cancel(Activity actv, 
			int layoutId, int titleStringId,
			int cancelButtonId, DialogTags cancelTag) {
		/*----------------------------
		* Steps
		* 1. Set up
		* 2. Add listeners => OnTouch
		* 3. Add listeners => OnClick
		----------------------------*/
		
		// 
		Dialog dlg = new Dialog(actv);
		
		//
		dlg.setContentView(layoutId);
		
		// Title
		dlg.setTitle(titleStringId);
		
		/*----------------------------
		* 2. Add listeners => OnTouch
		----------------------------*/
		//
		Button btn_cancel = (Button) dlg.findViewById(cancelButtonId);
		
		//
		btn_cancel.setTag(cancelTag);
		
		//
		btn_cancel.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg));
		
		/*----------------------------
		* 3. Add listeners => OnClick
		----------------------------*/
		//
		btn_cancel.setOnClickListener(new DialogButtonOnClickListener(actv, dlg));
		
		//
		//dlg.show();
		
		return dlg;
	
	}//public static Dialog dlg_template_okCancel()

	public static void db_backup(Activity actv) {
		/*----------------------------
		 * 1. Prep => File names
		 * 2. Prep => Files
		 * 2-2. Folder exists?
		 * 3. Copy
			----------------------------*/
		String dirPath_db = "/data/data/shoppinglist.main/databases";
		
		String fileName_db = "shopping_list.db";
		
		String dirName_ExternalStorage = "/mnt/sdcard-ext";
		
		String dirPath_db_backup = dirName_ExternalStorage + "/ShoppingList_backup";
		
		String fileName_db_backup_trunk = "ShopplingList_backup";
		
		String fileName_db_backup_ext = ".bk";

		String time_label = Methods.get_TimeLabel(Methods.getMillSeconds_now());
		
		String db_src = StringUtils.join(new String[]{dirPath_db, fileName_db}, File.separator);
		
		String db_dst = StringUtils.join(new String[]{dirPath_db_backup, fileName_db_backup_trunk}, File.separator);
		db_dst = db_dst + "_" + time_label + fileName_db_backup_ext;
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "db_src: " + db_src + " * " + "db_dst: " + db_dst);
		
		/*----------------------------
		 * 2. Prep => Files
			----------------------------*/
		File src = new File(db_src);
		File dst = new File(db_dst);
		
		/*----------------------------
		 * 2-2. Folder exists?
			----------------------------*/
		File db_backup = new File(dirPath_db_backup);
		
		if (!db_backup.exists()) {
			
			try {
				db_backup.mkdir();
				
				// Log
				Log.d("Methods.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ "]", "Folder created: " + db_backup.getAbsolutePath());
			} catch (Exception e) {
				
				// Log
				Log.d("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "Create folder => Failed");
				
				return;
				
			}
			
		} else {//if (!db_backup.exists())
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Folder exists: ");
			
		}//if (!db_backup.exists())
		
		/*----------------------------
		 * 3. Copy
			----------------------------*/
		try {
			FileChannel iChannel = new FileInputStream(src).getChannel();
			FileChannel oChannel = new FileOutputStream(dst).getChannel();
			iChannel.transferTo(0, iChannel.size(), oChannel);
			iChannel.close();
			oChannel.close();
			
			// Log
			Log.d("ThumbnailActivity.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "File copied");
			
			// debug
			Toast.makeText(actv, "DB backup => Done", 3000).show();
			
		} catch (FileNotFoundException e) {
			// Log
			Log.d("MainActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception: " + e.toString());
			
		} catch (IOException e) {
			// Log
			Log.d("MainActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception: " + e.toString());
		}//try

		
	}//public static void db_backup(Activity actv, Dialog dlg, String item)

	public static long getMillSeconds_now() {
		
		Calendar cal = Calendar.getInstance();
		
		return cal.getTime().getTime();
		
	}//private long getMillSeconds_now(int year, int month, int date)

	public static String get_TimeLabel(long millSec) {
		
		 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd_HHmmss");
		 
		return sdf1.format(new Date(millSec));
		
	}//public static String get_TimeLabel(long millSec)

	public static boolean restore_db(Activity actv, String dbName,
			String src, String dst) {
		/*********************************
		 * 1. Setup db
		 * 2. Setup: File paths
		 * 3. Setup: File objects
		 * 4. Copy file
		 * 
		 *********************************/
		// Setup db
		DBManager dbu = new DBManager(actv, dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();
	
		wdb.close();
	
		
		/*********************************
		 * 2. Setup: File paths
		 *********************************/
	//	String src = 
	//			"/mnt/sdcard-ext/ShoppingList_backup/shoppinglist_backup_20120906_201402.bk";
	//			"/mnt/sdcard-ext/CR4_backup/cr4_backup_20120907_184555.bk";
	
		
	//	String dst =
	////			"/data/data/test.main/databases/shoppinglist.db";
	//			"/data/data/cr4.main/databases/cr4.db";
	
		/*********************************
		 * 3. Setup: File objects
		 *********************************/
		File f_src = new File(src);
		File f_dst = new File(dst);
	
		/*********************************
		 * 4. Copy file
		 *********************************/
		try {
			FileChannel iChannel = new FileInputStream(src).getChannel();
			FileChannel oChannel = new FileOutputStream(dst).getChannel();
			iChannel.transferTo(0, iChannel.size(), oChannel);
			iChannel.close();
			oChannel.close();
			
			// Log
			Log.d("ThumbnailActivity.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "File copied: " + src);
			
			// debug
			Toast.makeText(actv, "DB restoration => Done", 3000).show();
			
			return true;
	
		} catch (FileNotFoundException e) {
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception: " + e.toString());
			
			return false;
			
		} catch (IOException e) {
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception: " + e.toString());
			
			return false;
			
		}//try
		
}//private boolean restore_db()

}
