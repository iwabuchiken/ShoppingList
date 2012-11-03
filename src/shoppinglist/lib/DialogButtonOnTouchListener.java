package shoppinglist.lib;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class DialogButtonOnTouchListener implements OnTouchListener {

	/*----------------------------
	 * Fields
		----------------------------*/
	//
	Activity actv;
	Dialog dlg;
	
	public DialogButtonOnTouchListener(Activity actv, Dialog dlg) {
		//
		this.actv = actv;
		this.dlg = dlg;
	}
	
	public DialogButtonOnTouchListener(Activity actv) {
		//
		this.actv = actv;
	}

//	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO 自動生成されたメソッド・スタブ
		Methods.DialogTags tag_name = (Methods.DialogTags) v.getTag();
		
		switch (event.getActionMasked()) {
		case MotionEvent.ACTION_DOWN:
				switch (tag_name) {
					//
				case dlg_register_store_ok:
				case dlg_register_store_cancel:
				case dlg_input_empty_btn_reenter:
				case dlg_input_empty_btn_cancel:
				case dlg_reconfirm_store_name_btn_yes:
				case dlg_reconfirm_store_name_btn_cancel:
				case dlg_register_genre_register:
				case dlg_register_genre_cancel:

				case dlg_reconfirm_genre_name_btn_register:
				case dlg_reconfirm_genre_name_btn_cancel:
				case dlg_create_table_create:
				case dlg_create_table_cancel:
				case dlg_drop_table_btn_cancel:
				case dlg_confirm_drop_table_btn_ok:
				case dlg_confirm_drop_table_btn_cancel:
				case dlg_filter_list_ok:
				case dlg_filter_list_cancel:
				case dlg_generic_cancel:
					//
					v.setBackgroundColor(Color.GRAY);
					
					break;
					
				}//switch (tag_name)
			break;//case MotionEvent.ACTION_DOWN:
			
		case MotionEvent.ACTION_UP:
			switch (tag_name) {
				//
				case dlg_register_store_ok:
				case dlg_register_store_cancel:
				case dlg_input_empty_btn_reenter:
				case dlg_input_empty_btn_cancel:
				case dlg_reconfirm_store_name_btn_yes:
				case dlg_reconfirm_store_name_btn_cancel:
				case dlg_register_genre_register:
				case dlg_register_genre_cancel:

				case dlg_reconfirm_genre_name_btn_register:
				case dlg_reconfirm_genre_name_btn_cancel:	

				case dlg_create_table_create:
				case dlg_create_table_cancel:
				
				case dlg_drop_table_btn_cancel:
				
				case dlg_confirm_drop_table_btn_ok:
				case dlg_confirm_drop_table_btn_cancel:
				
				case dlg_filter_list_ok:
				case dlg_filter_list_cancel:
				
				case dlg_generic_cancel:
					//
					v.setBackgroundColor(Color.WHITE);
					
					break;
					
				}//switch (tag_name)
			break;//case MotionEvent.ACTION_UP:
		}//switch (event.getActionMasked())
		return false;
	}

}
