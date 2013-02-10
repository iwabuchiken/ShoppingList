package sl.listeners;

import sl.utils.Methods;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class ButtonOnTouchListener implements OnTouchListener {

	/*----------------------------
	 * Fields
		----------------------------*/
	//
	Activity actv;

	public ButtonOnTouchListener(Activity actv) {
		//
		this.actv = actv;
	}

//	@Override
	
	public boolean onTouch(View v, MotionEvent event) {
		// TODO 自動生成されたメソッド・スタブ
		Methods.ButtonTags tag_name = (Methods.ButtonTags) v.getTag();
		
		// Log
		Log.d("ButtonOnTouchListener.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "tag_name => " + tag_name.name());
		
		
		switch (event.getActionMasked()) {
		case MotionEvent.ACTION_DOWN:
			// Log
			Log.d("ButtonOnTouchListener.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "ACTION_DOWN");

			switch (tag_name) {
				//
			case db_manager_activity_create_table:
			case db_manager_activity_drop_table:

			case sl_main_bt_item_list:
			case sl_main_bt_register:
			case sl_main_bt_db:
				
			case itemlist_bt_choose:
			case itemlist_bt_see_chosen:
				//
				v.setBackgroundColor(Color.GRAY);
				
				break;
				
			}//switch (tag_name)
			
			break;//case MotionEvent.ACTION_DOWN:
			
		case MotionEvent.ACTION_UP:
			
			// Log
			Log.d("ButtonOnTouchListener.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "ACTION_UP");
			
			
			switch (tag_name) {
				//
			case db_manager_activity_create_table:
			case db_manager_activity_drop_table:

			case sl_main_bt_item_list:
			case sl_main_bt_register:
			case sl_main_bt_db:
				
			case itemlist_bt_choose:
			case itemlist_bt_see_chosen:

					//
					v.setBackgroundColor(Color.WHITE);
					
					break;
					
				}//switch (tag_name)
			
			break;//case MotionEvent.ACTION_UP:
		}//switch (event.getActionMasked())
		return false;
	}

}
