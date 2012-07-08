package shoppinglist.lib;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
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

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		Methods.ButtonTags tag_name = (Methods.ButtonTags) v.getTag();
		
		switch (event.getActionMasked()) {
		case MotionEvent.ACTION_DOWN:
			switch (tag_name) {
				//
			case db_manager_activity_create_table:
			case db_manager_activity_drop_table:
				//
				v.setBackgroundColor(Color.GRAY);
				
				break;
				
			}//switch (tag_name)
			
			break;//case MotionEvent.ACTION_DOWN:
			
		case MotionEvent.ACTION_UP:
			switch (tag_name) {
				//
			case db_manager_activity_create_table:
			case db_manager_activity_drop_table:
					//
					v.setBackgroundColor(Color.WHITE);
					
					break;
					
				}//switch (tag_name)
			
			break;//case MotionEvent.ACTION_UP:
		}//switch (event.getActionMasked())
		return false;
	}

}
