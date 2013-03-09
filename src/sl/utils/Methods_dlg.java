package sl.utils;

import java.util.ArrayList;
import java.util.List;

import sl.adapters.PSListAdapter;
import sl.items.PS;
import sl.items.ShoppingItem;
import sl.listeners.dialog.DialogButtonOnClickListener;
import sl.listeners.dialog.DialogButtonOnTouchListener;
import sl.listeners.dialog.DialogOnItemClickListener;
import sl.main.R;
import sl.main.RegisterItemActv;
import sl.utils.Tags;
import sl.utils.Tags.DialogTags;

import android.app.Activity;
import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class Methods_dlg {

	public static void dlg_db_activity(Activity actv) {
		/*----------------------------
		 * 1. Dialog
		 * 2. Prep => List
		 * 3. Adapter
		 * 4. Set adapter
		 * 
		 * 5. Set listener to list
		 * 6. Show dialog
			----------------------------*/
		Dialog dlg = Methods_dlg.dlg_template_cancel(
									actv,
									R.layout.dlg_db_admin, 
									R.string.dlg_db_admin_title, 
									R.id.dlg_db_admin_bt_cancel, 
									Tags.DialogTags.dlg_generic_dismiss);
		
		/*----------------------------
		 * 2. Prep => List
			----------------------------*/
		String[] choices = {
				actv.getString(R.string.dlg_db_admin_item_backup_db),
				actv.getString(R.string.dlg_db_admin_item_refresh_db),
				actv.getString(R.string.dlg_db_admin_item_refatcor_db),
				actv.getString(R.string.dlg_db_admin_item_restore_db),
				actv.getString(R.string.dlg_db_admin_item_get_yomi)
		};
		
		List<String> list = new ArrayList<String>();
		
		for (String item : choices) {
			
			list.add(item);
			
		}
		
		/*----------------------------
		 * 3. Adapter
			----------------------------*/
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				actv,
//				R.layout.dlg_db_admin,
				android.R.layout.simple_list_item_1,
				list
				);

		/*----------------------------
		 * 4. Set adapter
			----------------------------*/
		ListView lv = (ListView) dlg.findViewById(R.id.dlg_db_admin_lv);
		
		lv.setAdapter(adapter);
		
		/*----------------------------
		 * 5. Set listener to list
			----------------------------*/
		lv.setTag(Tags.DialogTags.dlg_db_admin_lv);
		
		lv.setOnItemClickListener(new DialogOnItemClickListener(actv, dlg));
		
		/*----------------------------
		 * 6. Show dialog
			----------------------------*/
		dlg.show();
		
		
	}//public static void dlg_db_activity(Activity actv)

	public static void dlg_tabActv_adminDb(Activity actv) {
		/*----------------------------
		 * 1. Dialog
		 * 2. Prep => List
		 * 3. Adapter
		 * 4. Set adapter
		 * 
		 * 5. Set listener to list
		 * 6. Show dialog
			----------------------------*/
		Dialog dlg = Methods_dlg.dlg_template_cancel(
									actv,
									R.layout.dlg_db_admin, 
									R.string.menu_listitem_tabToBuy_admin_db, 
									R.id.dlg_db_admin_bt_cancel, 
									Tags.DialogTags.dlg_generic_dismiss);
		
		/*----------------------------
		 * 2. Prep => List
			----------------------------*/
		String[] choices = {
				actv.getString(R.string.menu_listitem_tabToBuy_admin_db_save_tobuy_list),
				actv.getString(R.string.menu_listitem_tabToBuy_admin_db_load_tobuy_list),
				
		};
		
		List<String> list = new ArrayList<String>();
		
		for (String item : choices) {
			
			list.add(item);
			
		}
		
		/*----------------------------
		 * 3. Adapter
			----------------------------*/
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				actv,
//				R.layout.dlg_db_admin,
				android.R.layout.simple_list_item_1,
				list
				);

		/*----------------------------
		 * 4. Set adapter
			----------------------------*/
		ListView lv = (ListView) dlg.findViewById(R.id.dlg_db_admin_lv);
		
		lv.setAdapter(adapter);
		
		/*----------------------------
		 * 5. Set listener to list
			----------------------------*/
//		lv.setTag(Tags.DialogTags.dlg_db_admin_lv);
		lv.setTag(Tags.DialogTags.dlg_tabActv_adminDb);
		
		lv.setOnItemClickListener(new DialogOnItemClickListener(actv, dlg));
		
		/*----------------------------
		 * 6. Show dialog
			----------------------------*/
		dlg.show();
		
		
	}//public static void dlg_tabActv_adminDb(Activity actv)

	public static Dialog dlg_template_cancel(Activity actv, int layoutId, int titleStringId,
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

	
	public static
	Dialog dlg_template_cancel_2Dialogues
	(Activity actv, int layoutId, int titleStringId,
			int cancelButtonId, DialogTags cancelTag, Dialog dlg1) {
		/*----------------------------
		* Steps
		* 1. Set up
		* 2. Add listeners => OnTouch
		* 3. Add listeners => OnClick
		----------------------------*/
		
		// 
		Dialog dlg2 = new Dialog(actv);
		
		//
		dlg2.setContentView(layoutId);
		
		// Title
		dlg2.setTitle(titleStringId);
		
		/*----------------------------
		* 2. Add listeners => OnTouch
		----------------------------*/
		//
		Button btn_cancel = (Button) dlg2.findViewById(cancelButtonId);
		
		//
		btn_cancel.setTag(cancelTag);
		
		//
		btn_cancel.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg2));
		
		/*----------------------------
		* 3. Add listeners => OnClick
		----------------------------*/
		//
		btn_cancel.setOnClickListener(new DialogButtonOnClickListener(actv, dlg1, dlg2));
		
		//
		//dlg2.show();
		
		return dlg2;
	
	}//public static Dialog dlg_template_cancel_2Dialogues


	public static Dialog dlg_template_cancel(Activity actv, int layoutId, String title,
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
//		dlg.setTitle(titleStringId);
		dlg.setTitle(title);
		
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

	public static void dlg_tabActv_tab2Lv(Activity actv, ShoppingItem si) {
		/***************************************
		 * 1. Dialog
		 ***************************************/
		Dialog dlg = Methods_dlg.dlg_template_cancel(
				actv,
				R.layout.dlg_db_admin, 
				si.getName(), 
				R.id.dlg_db_admin_bt_cancel, 
				Tags.DialogTags.dlg_generic_dismiss);

//		dlg.setTitle(si.getName());
		
		/***************************************
		 * 2. Prep => List
		 ***************************************/
		String[] choices = {
				actv.getString(R.string.tabactv_tab2_lv_delete_from_list),
		};

		List<String> list = new ArrayList<String>();
		
		for (String item : choices) {
			
			list.add(item);
			
		}
		
		/***************************************
		 * 3. Adapter
		 ***************************************/
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				actv,
//				R.layout.dlg_db_admin,
				android.R.layout.simple_list_item_1,
				list
				);

		/***************************************
		 * 4. Set adapter
		 ***************************************/
		ListView lv = (ListView) dlg.findViewById(R.id.dlg_db_admin_lv);
		
		lv.setAdapter(adapter);
		
		/***************************************
		 * 5. Set listener to list
		 ***************************************/
		lv.setTag(Tags.DialogTags.dlg_tabactv_tab2_lv);
		
		lv.setOnItemClickListener(new DialogOnItemClickListener(actv, dlg, si));
		
		/***************************************
		 * 6. Show dialog
		 ***************************************/
		dlg.show();
		
	}//public static void dlg_tabActv_tab2Lv(Activity actv, ShoppingItem si)

	public static void dlg_saveToBuyList(Activity actv, Dialog dlg1) {
		// TODO Auto-generated method stub
		/***************************************
		 * Setup
		 ***************************************/
		Dialog dlg2 = Methods_dlg.dlg_template_okCancel_2Dialogues(
				actv,
				R.layout.dlg_save_tobuy_list, 
				R.string.menu_listitem_tabToBuy_admin_db_save_tobuy_list,
				
				R.id.dlg_save_tobuy_list_bt_ok,
				R.id.dlg_save_tobuy_list_bt_cancel,
				
				Tags.DialogTags.dlg_save_tobuy_list_bt_ok,
//				Tags.DialogTags.dlg_generic_dismiss,
				Tags.DialogTags.dlg_generic_dismiss_second_dialog,
				
				dlg1);
		
		/***************************************
		 * Spinner
		 ***************************************/
		Spinner spStoreNames = (Spinner) dlg2.findViewById(R.id.dlg_save_tobuy_list_sp_store_name);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
	              actv, android.R.layout.simple_spinner_item);

		
		/*----------------------------
		 * 2. Get store names from db
			----------------------------*/
		DBUtils dbm = new DBUtils(actv);
		
		SQLiteDatabase db = dbm.getReadableDatabase();
		
		Cursor c = dbm.getAllData(db, "stores", CONS.columns_for_table_stores_with_index);
		
		// Log
		Log.d("RegisterItem.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "c.getCount()" + c.getCount());
		
		c.moveToFirst();
		
		// Log
		for (int i = 0; i < c.getCount(); i++) {
//			Log.d("RegisterItem.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "c.getString(1) => " + c.getString(1));

			/*----------------------------
			 * 3. Set store data to adapter
				----------------------------*/
//			adapter.add("abc");
			adapter.add(c.getString(1));

			c.moveToNext();
		}//for (int i = 0; i < c.getCount(); i++)
		
		
		/*----------------------------
		 * 3-1. setDropDownViewResource
			----------------------------*/
		adapter.setDropDownViewResource(
						android.R.layout.simple_spinner_dropdown_item);
		
		/*----------------------------
		 * 3-2. Close db
			----------------------------*/
		db.close();
		
		/*----------------------------
		 * 4. Set adapter to spinner
			----------------------------*/
		spStoreNames.setAdapter(adapter);
		
		/***************************************
		 * Set spinner default value
		 * 1. Get the first item from CONS.toBuyList
		 * 2. Get the store name from the item
		 * 3. Use this store name as the default
		 ***************************************/
		ShoppingItem item = CONS.toBuyList.get(0);
		
		if (item != null) {
			
			String defaultStoreName = item.getStore();

			if (defaultStoreName != null) {

				int position = adapter.getPosition(defaultStoreName);
				
				spStoreNames.setSelection(position);

			}//if (defaultStoreName != null)
			
		}//if (item != null)
//		String defaultStoreName = item.getStore();
//		
//		int position = adapter.getPosition(defaultStoreName);
//		
//		spStoreNames.setSelection(position);
		
		/***************************************
		 * Amount(Sum of items in price)
		 ***************************************/
		int amount = 0;
		
		for (ShoppingItem i : CONS.toBuyList) {
			
			amount += i.getPrice();
			
		}//for (ShoppingItem i : CONS.toBuyList)
		
		EditText etAmount = (EditText) dlg2.findViewById(R.id.dlg_save_tobuy_list_et_amount);
		
		etAmount.setText(String.valueOf(amount));
		
		/***************************************
		 * Show dialog
		 ***************************************/
		dlg2.show();

	}//public static void dlg_saveToBuyList(Activity actv, Dialog dlg1)

	public static Dialog dlg_template_okCancel(Activity actv, int layoutId, int titleStringId,
			int okButtonId, int cancelButtonId, DialogTags okTag, DialogTags cancelTag) {
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
		Button btn_ok = (Button) dlg.findViewById(okButtonId);
		Button btn_cancel = (Button) dlg.findViewById(cancelButtonId);
		
		//
		btn_ok.setTag(okTag);
		btn_cancel.setTag(cancelTag);
		
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
		//dlg.show();
		
		return dlg;
	
	}//public static Dialog dlg_template_okCancel()

	public static
	Dialog dlg_template_okCancel_2Dialogues
	(Activity actv,
			int layoutId, int titleStringId,
			int okButtonId, int cancelButtonId,
			DialogTags okTag, DialogTags cancelTag,
			Dialog dlg1) {
		/*----------------------------
		* Steps
		* 1. Set up
		* 2. Add listeners => OnTouch
		* 3. Add listeners => OnClick
		----------------------------*/
		
		// 
		Dialog dlg2 = new Dialog(actv);
		
		//
		dlg2.setContentView(layoutId);
		
		// Title
		dlg2.setTitle(titleStringId);
		
		/*----------------------------
		* 2. Add listeners => OnTouch
		----------------------------*/
		//
		Button btn_ok = (Button) dlg2.findViewById(okButtonId);
		Button btn_cancel = (Button) dlg2.findViewById(cancelButtonId);
		
		//
		btn_ok.setTag(okTag);
		btn_cancel.setTag(cancelTag);
		
		//
		btn_ok.setOnTouchListener(
				new DialogButtonOnTouchListener(actv, dlg2));
		btn_cancel.setOnTouchListener(
				new DialogButtonOnTouchListener(actv, dlg2));
		
		/*----------------------------
		* 3. Add listeners => OnClick
		----------------------------*/
		//
		btn_ok.setOnClickListener(
				new DialogButtonOnClickListener(actv, dlg1, dlg2));
		btn_cancel.setOnClickListener(
				new DialogButtonOnClickListener(actv, dlg1, dlg2));
		
		//
		//dlg.show();
		
		return dlg2;
	
	}//public static Dialog dlg_template_okCancel()

	
	public static void
	dlg_LoadToBuyList(Activity actv, Dialog dlg1) {
		// TODO Auto-generated method stub
		/***************************************
		 * 1. Get cursor
		 * 2. Build a PS list
		 * 3. Show the list in the dialog
		 ***************************************/
//		DBUtils dbu = new DBUtils(actv, CONS.dbName);
		
		List<PS> psList = Methods_sl.getPSList(actv);

		/***************************************
		 * Sort list
		 ***************************************/
		Methods_sl.sortPSList(psList, Tags.SortTags.pslist_due_date);
		
//		for (PS item : psList) {
//			
//			// Log
//			Log.d("Methods_dlg.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "item.getDueDate()=" + item.getDueDate());
//			
//		}//for (PS item : psList)
//		// Log
//		Log.d("Methods_dlg.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "psList.size()=" + psList.size());
		
		/***************************************
		 * 3. Show the list in the dialog
		 ***************************************/
//		(Activity actv, int layoutId, int titleStringId,
//				int cancelButtonId, DialogTags cancelTag, Dialog dlg1)
		Dialog dlg2 = Methods_dlg.dlg_template_cancel_2Dialogues(
				actv,
				R.layout.dlg_db_admin, 
//				R.string.menu_listitem_tabToBuy_admin_db_save_tobuy_list,
				R.string.menu_listitem_tabToBuy_admin_db_load_tobuy_list,
				
				R.id.dlg_db_admin_bt_cancel,
//				dlg_generic_dismiss
//				Tags.DialogTags.dlg_generic_dismiss,
				Tags.DialogTags.dlg_generic_dismiss_second_dialog,
				
				dlg1);

		/***************************************
		 * Set list
		 ***************************************/
		PSListAdapter adp = new PSListAdapter(
				actv,
				R.layout.listrow_load_tobuy_list,
				psList
				);
		
		ListView lv = (ListView) dlg2.findViewById(R.id.dlg_db_admin_lv);
		
		int lvHeight = Methods.getSmallerNumber(350, 75 * psList.size());
		
		lv.setLayoutParams(new LinearLayout.LayoutParams(
										LayoutParams.WRAP_CONTENT,	// Width
//										300));
										lvHeight));					// Height
		
		
		
		lv.setAdapter(adp);
		
		/***************************************
		 * Show dialog
		 ***************************************/
		dlg2.show();
		
		
	}//dlg_LoadToBuyList(Activity actv, Dialog dlg)

}//public class Methods_dlg
