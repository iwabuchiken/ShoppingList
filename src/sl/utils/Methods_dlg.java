package sl.utils;

import java.util.ArrayList;
import java.util.List;

import sl.listeners.dialog.DialogButtonOnClickListener;
import sl.listeners.dialog.DialogButtonOnTouchListener;
import sl.listeners.dialog.DialogOnItemClickListener;
import sl.main.R;
import sl.utils.Tags;
import sl.utils.Tags.DialogTags;

import android.app.Activity;
import android.app.Dialog;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

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
				actv.getString(R.string.dlg_db_admin_item_set_new_column),
				actv.getString(R.string.dlg_db_admin_item_restore_db)
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

}//public class Methods_dlg
