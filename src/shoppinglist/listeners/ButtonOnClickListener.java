package shoppinglist.listeners;

import shoppinglist.main.DBAdminActivity;
import shoppinglist.main.ItemList;
import shoppinglist.main.R;
import shoppinglist.utils.Methods;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Vibrator;
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
		Methods.ButtonTags tag_name = (Methods.ButtonTags) v.getTag();

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
			i.setClass(actv, ItemList.class);
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
			
		case sl_main_bt_db://-----------------------------------
			
			vib.vibrate(Methods.vibLength);
			
			//
			i = new Intent();
			i.setClass(actv, DBAdminActivity.class);
			actv.startActivity(i);

			break;
			
		case itemlist_bt_choose://-----------------------------------
			
			ItemList.toBuys.addAll(ItemList.checkedPositions);
			
			ItemList.adapter.notifyDataSetChanged();
			
			break;
			
		default:
			break;
		}//switch (tag_name)
	}

}
