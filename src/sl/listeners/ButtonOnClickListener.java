package sl.listeners;

import java.util.ArrayList;
import java.util.List;

import sl.adapters.ItemListAdapter;
import sl.items.ShoppingItem;
import sl.main.DBActv;
import sl.main.ItemListActv;
import sl.main.R;
import sl.utils.Methods;
import sl.utils.Methods_sl;
import sl.utils.Tags;
import android.app.Activity;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class ButtonOnClickListener implements OnClickListener {
	/*----------------------------
	 * Fields
		----------------------------*/
	//
	Activity actv;

	//
	Vibrator vib;
	
	public ButtonOnClickListener(Activity actv) {
		//
		this.actv = actv;
		
		//
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
	}

//	@Override
	public void onClick(View v) {
		//
		Tags.ButtonTags tag_name = (Tags.ButtonTags) v.getTag();

		//
		switch (tag_name) {
		
		case db_manager_activity_create_table://-----------------------------------
			
			// REF => http://www.adakoda.com/android/000124.html
			//
			vib.vibrate(40);
			
			//
			Methods.dlg_createTable(actv);
			
			break;
			
		case db_manager_activity_drop_table://-----------------------------------
			//
//			vib.vibrate(10);
//			vib.vibrate(100);
//			vib.vibrate(50);
			vib.vibrate(40);
			
			//
			Methods.dlg_dropTable(actv);
			
			break;

		case sl_main_bt_item_list://-----------------------------------

			vib.vibrate(Methods.vibLength);
			
			Intent i = new Intent();
			i.setClass(actv, ItemListActv.class);
			actv.startActivity(i);
			
//			// debug
//			Toast.makeText(actv, "sl_main_bt_item_list", 2000)
//					.show();
			
			break;
			
		case sl_main_bt_register://-----------------------------------
			
			vib.vibrate(Methods.vibLength);
			
			Methods.dlg_register_main(actv);
			
//			// debug
//			Toast.makeText(actv, "sl_main_bt_register", 2000)
//					.show();
			
			break;
			
//		case sl_main_bt_db://-----------------------------------
//			
//			vib.vibrate(Methods.vibLength);
//			
//			//
//			i = new Intent();
//			i.setClass(actv, DBAdminActv.class);
//			actv.startActivity(i);
//
//			break;
//			
		case itemlist_bt_choose://-----------------------------------
			
			if (ItemListActv.toBuys == null) {
				
				// Log
				Log.d("ButtonOnClickListener.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]", "ItemListActv.toBuys => null");
				
			} else {//if (ItemListActv.toBuys == null)

				// Log
				Log.d("ButtonOnClickListener.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]", "ItemListActv.toBuys => Not null");

			}//if (ItemListActv.toBuys == null)
			
			
			ItemListActv.toBuys.addAll(ItemListActv.checkedPositions);
			
			List<ShoppingItem> chosen_items_list = new ArrayList<ShoppingItem>();
			
			for (Integer num : ItemListActv.toBuys) {
				
				chosen_items_list.add(ItemListActv.list.get(num));
				
			}

			if (chosen_items_list.size() <= 0) {
				
				// debug
				Toast.makeText(actv, "No item chosen", Toast.LENGTH_SHORT).show();
				
				return;
				
			}//if (chosen_items_list.size() == 0)

			/***************************************
			 * Sort list
			 ***************************************/
			// Log
			Log.d("ButtonOnClickListener.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "chosen_items_list.size()=" + chosen_items_list.size());
			
			// Log
			ShoppingItem si = chosen_items_list.get(0);
			
			Log.d("ButtonOnClickListener.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]",
					"si.getName()=" + si.getName()
					+ "/"
					+ "si.getYomi()=" + si.getYomi());
			
			Methods_sl.sortItemList(chosen_items_list);
			
			/***************************************
			 * Adapter
			 ***************************************/
			ItemListActv.adapter.clear();
			
			ItemListActv.adapter = new ItemListAdapter(
					actv,
					R.layout.adapteritem,
//					ItemList.list
					chosen_items_list
					);

			((ListActivity)actv).setListAdapter(ItemListActv.adapter);
			
			/***************************************
			 * Clear the positions
			 ***************************************/
			ItemListActv.checkedPositions.clear();
			
			ItemListActv.toBuys.clear();
			
			break;
			
//			ItemListActv.toBuys.addAll(ItemListActv.checkedPositions);
//			
//			ItemListActv.adapter.notifyDataSetChanged();
//			
//			break;
			
		default:
			break;
		}//switch (tag_name)
	}

}
