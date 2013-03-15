package sl.listeners.list;

import sl.items.ShoppingItem;
import sl.main.R;
import sl.main.RegisterItemActv;
import sl.utils.CONS;
import sl.utils.DBUtils;
import sl.utils.Methods_dlg;
import sl.utils.Tags;
import android.app.Activity;
import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemLongClickListener;

public class ListOnItemLongClickListener implements OnItemLongClickListener {

	Activity actv;
	Vibrator vib;
	
	public ListOnItemLongClickListener(Activity actv) {
		//
		this.actv = actv;
		//
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);

	}

	public boolean onItemLongClick(AdapterView<?> parent, View v, int position,
			long id) {
		// TODO Auto-generated method stub
		Tags.ListTags tag = (Tags.ListTags) parent.getTag();
		
		switch (tag) {

		case tab_toBuyList://-------------------------------------------

			case_tab_toBuyList(parent, position);
			
			break;// case tab_toBuyList

		case tab_itemList://-------------------------------------------
			
			case_tab_itemList(parent, position);
			
			break;// case tab_itemList
			
		default:
			break;
		
		}//switch (item)

		return true;
	}

	private void case_tab_itemList(AdapterView<?> parent, int position) {
		// TODO Auto-generated method stub
		Dialog dlg = Methods_dlg.dlg_template_okCancel(
						actv,
						R.layout.dlg_edit_items,
						R.string.dlg_edit_items_title,
						
						R.id.dlg_edit_items_btn_ok,
						R.id.dlg_edit_items_btn_cancel,
						
						Tags.DialogTags.dlg_edit_items_bt_ok,
						Tags.DialogTags.dlg_generic_cancel);
		
		/***************************************
		 * Get item
		 ***************************************/
		ShoppingItem si = (ShoppingItem) parent.getItemAtPosition(position); 
		
		/***************************************
		 * Set store name
		 ***************************************/
		case_tab_itemList__setStoreName(si, dlg);
		
		// Log
		Log.d("ListOnItemLongClickListener.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "setStoreName => Done");
		
		/***************************************
		 * Set: Item name
		 ***************************************/
		case_tab_itemList__setItemNameAndYomi(si, dlg);
		
		// Log
		Log.d("ListOnItemLongClickListener.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Item name and yomi => Done");
		
		/***************************************
		 * Set: Price and genre
		 ***************************************/
		case_tab_itemList__setPrice(si, dlg);

		/***************************************
		 * Set: Genre
		 ***************************************/
		case_tab_itemList__setGenre(si, dlg);
		
		dlg.show();
		
	}//private void case_tab_itemList(AdapterView<?> parent, int position)

	private void
	case_tab_itemList__setGenre(ShoppingItem si, Dialog dlg) {
		// TODO Auto-generated method stub
		// Resource => http://www.java2s.com/Open-Source/Android/Samples/techbooster/org/jpn/techbooster/sample/spinner/SpinnerActivity.java.htm
		Spinner sp_genre_name = (Spinner) dlg.findViewById(R.id.dlg_edit_items_sp_genre);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
	              actv, android.R.layout.simple_spinner_item);
//
		/*----------------------------
		 * 2. Get genre names from db
			----------------------------*/
		DBUtils dbm = new DBUtils(actv);
		
		SQLiteDatabase db = dbm.getReadableDatabase();
		
		Cursor c = dbm.getAllData(db, "genres", CONS.columns_for_table_genres_with_index);
		
		// Log
		Log.d("RegisterItem.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "c.getCount()" + c.getCount());
		
		c.moveToFirst();
		
		// Log
		for (int i = 0; i < c.getCount(); i++) {

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
		sp_genre_name.setAdapter(adapter);
		
		/***************************************
		 * Set initial value
		 ***************************************/
		int num = 0;
		
		for (int i = 0; i < adapter.getCount(); i++) {
			
			String genreName = adapter.getItem(i);
			
			// Log
			Log.d("ListOnItemLongClickListener.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]",
//					"si.getName()=" + si.getName()
					"si.getGenre()=" + si.getGenre()
					+ "/"
					+ "genreName=" + genreName);
			
//			if (si.getName().equals(storeName)) {
			if (si.getGenre().equals(genreName)) {
				
				// Log
				Log.d("ListOnItemLongClickListener.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]",
						"si.getGenre().equals(genreName)");
				
				num = i;
				
				break;
				
			}//if (si.getName() == condition)
			
		}//for (int i = 0; i < adapter.getCount(); i++)
		
		sp_genre_name.setSelection(num);

	
	}//case_tab_itemList__setGenre(ShoppingItem si, Dialog dlg)
	

	private void
	case_tab_itemList__setPrice(ShoppingItem si, Dialog dlg) {
		// TODO Auto-generated method stub

		EditText etPrice = (EditText) dlg.findViewById(R.id.dlg_edit_items_et_price);
		
//		etPrice.setText(si.getPrice());				//=> android.content.res.Resources$NotFoundException: String resource ID #0x64

		etPrice.setText(String.valueOf(si.getPrice()));
		
	}//case_tab_itemList__setPrice(ShoppingItem si, Dialog dlg)

	private void case_tab_itemList__setItemNameAndYomi(ShoppingItem si, Dialog dlg) {
		// TODO Auto-generated method stub
		EditText etItemName = (EditText) dlg.findViewById(R.id.dlg_edit_items_et_name);
		etItemName.setText(si.getName());

		EditText etYomi = (EditText) dlg.findViewById(R.id.dlg_edit_items_et_yomi);
		etYomi.setText(si.getYomi());
		
	}

	private void
	case_tab_itemList__setStoreName(ShoppingItem si, Dialog dlg) {
		// TODO Auto-generated method stub
		// Resource => http://www.java2s.com/Open-Source/Android/Samples/techbooster/org/jpn/techbooster/sample/spinner/SpinnerActivity.java.htm
		Spinner sp_store_name = (Spinner) dlg.findViewById(R.id.dlg_edit_items_sp_store);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
	              actv, android.R.layout.simple_spinner_item);

		/***************************************
		 * Get store names from db
		 ***************************************/
		DBUtils dbm = new DBUtils(actv);
		
		SQLiteDatabase db = dbm.getReadableDatabase();
		
		Cursor c = dbm.getAllData(db, "stores", CONS.columns_for_table_stores_with_index);
		
		// Log
		Log.d("ListOnItemLongClickListener.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "c.getCount()" + c.getCount());
		
		c.moveToFirst();
		
		// Log
		for (int i = 0; i < c.getCount(); i++) {

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
		sp_store_name.setAdapter(adapter);

		/***************************************
		 * Set the initial store name
		 * 1. Get the position number
		 * 2. Set the selection
		 ***************************************/
		/***************************************
		 * 1. Get the position number
		 ***************************************/
		int num = 0;
		
		for (int i = 0; i < adapter.getCount(); i++) {
			
			String storeName = adapter.getItem(i);
			
			// Log
			Log.d("ListOnItemLongClickListener.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]",
//					"si.getName()=" + si.getName()
					"si.getStore()=" + si.getStore()
					+ "/"
					+ "storeName=" + storeName);
			
//			if (si.getName().equals(storeName)) {
			if (si.getStore().equals(storeName)) {
				
				// Log
				Log.d("ListOnItemLongClickListener.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]", "si.getName().equals(storeName)");
				
				num = i;
				
				break;
				
			}//if (si.getName() == condition)
			
		}//for (int i = 0; i < adapter.getCount(); i++)
		
		sp_store_name.setSelection(num);
		
	}//case_tab_itemList__setStoreName(ShoppingItem si, Dialog dlg)

	private void case_tab_toBuyList(AdapterView<?> parent, int position) {
		// TODO Auto-generated method stub
		/***************************************
		 * Get item
		 ***************************************/
		ShoppingItem si = (ShoppingItem) parent.getItemAtPosition(position);
		
		// Log
		Log.d("ListOnItemClickListener.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "si.getName()=" + si.getName());
		
		/***************************************
		 * Show dialog
		 ***************************************/
		Methods_dlg.dlg_tabActv_tab2Lv(actv, si);
		
	}//private void tab_toBuyList(AdapterView<?> parent, int position)

}
