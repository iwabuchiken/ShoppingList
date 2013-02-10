package sl.listeners.dialog;

import sl.items.RegisterItem;
import sl.main.SLActv;
import sl.utils.CONS;
import sl.utils.Methods;
import sl.utils.Methods.DialogTags;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class DialogOnItemClickListener implements OnItemClickListener {

	//
	Activity actv;
	Dialog dlg;
	
	//
	Vibrator vib;
	
	//
	Methods.DialogTags dlgTag = null;
	
	public DialogOnItemClickListener(Activity actv, Dialog dlg) {
		// 
		this.actv = actv;
		this.dlg = dlg;
	}//public DialogOnItemClickListener(Activity actv, Dialog dlg)

	public DialogOnItemClickListener(Activity actv, Dialog dlg, Methods.DialogTags dlgTag) {
		// 
		this.actv = actv;
		this.dlg = dlg;
		this.dlgTag = dlgTag;
		
		//
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
		
	}//public DialogOnItemClickListener(Activity actv, Dialog dlg)

//	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		/*----------------------------
		 * Steps
		 * 0. Vibrate
		 * 1. Get table name
		 * 2. Call method
			----------------------------*/
		/*----------------------------
		 * 0. Vibrate
			----------------------------*/
		vib.vibrate(40);
		
//		String tableName = (String) parent.getItemAtPosition(position);
		
//		// Log
//		Log.d("DialogOnItemClickListener.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "tableName => " + tableName);
//		
//		// Log
//		Log.d("DialogOnItemClickListener.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "position => " + position + " / " + "id => " + id);
		
		/*----------------------------
		 * 2. Call a method
			----------------------------*/
		//
		if (dlgTag != null && dlgTag == DialogTags.dlg_drop_table) {
			
			String tableName = (String) parent.getItemAtPosition(position);
			
			Methods.dlg_confirmTableDrop(actv, dlg, tableName);
			
		} else if (dlgTag != null && dlgTag == DialogTags.dlg_register_main) {
			
			CONS.registerChoice item = 
							(CONS.registerChoice) parent.getItemAtPosition(position);
			
			switch (item) {
			case items:
				
				//
				Intent i = new Intent();
				
				//
				i.setClass(actv, RegisterItem.class);
				
				//
				actv.startActivity(i);
				
				break;
				
			case stores:
				
				Methods.register_store(actv);
				
				break;
				
			case genres:
				
				Methods.registerGenre(actv);
				
				break;
			
			}//switch (item)
			
			
			
//			// Log
//			Log.d("DialogOnItemClickListener.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "item => " + item);
			
			
//			if (condition) {
//				line1
//			} else {//if (condition)
//				line2
//			}//if (condition)
			
			
		}//if (dlgName != null && dlgName == "confirm_table_drop")
			
		
		
	}//public void onItemClick(AdapterView<?> parent, View v, int position, long id)

}
