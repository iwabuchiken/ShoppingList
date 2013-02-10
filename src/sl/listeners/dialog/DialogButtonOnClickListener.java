package sl.listeners.dialog;

import sl.main.R;
import sl.utils.Methods;
import android.app.Activity;
import android.app.Dialog;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class DialogButtonOnClickListener implements OnClickListener {
	/*----------------------------
	 * Fields
		----------------------------*/
	//
	Activity actv;
	Dialog dlg;
	Dialog dlg2;		//=> Used in dlg_input_empty_btn_XXX

	//
	Vibrator vib;
	
	public DialogButtonOnClickListener(Activity actv, Dialog dlg) {
		//
		this.actv = actv;
		this.dlg = dlg;
		
		//
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
	}

	public DialogButtonOnClickListener(Activity actv, Dialog dlg1,
			Dialog dlg2) {
		//
		this.actv = actv;
		this.dlg = dlg1;
		this.dlg2 = dlg2;
		
		//
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
	}

//	@Override
	public void onClick(View v) {
		//
		Methods.DialogTags tag_name = (Methods.DialogTags) v.getTag();

		//
		switch (tag_name) {
		case dlg_register_store_ok://------------------------------------------
			/*----------------------------
			 * Validate if the edit view has some input.
			 * If no input => Show another dialog
				----------------------------*/
			//
			vib.vibrate(40);
			
			//
			EditText et = (EditText) dlg.findViewById(R.id.dlg_register_store_et);
			
			if (et.getText().toString().equals("")) {
				
				Methods.dlg_input_empty(actv, dlg);
				
			} else {//if (et.getText().toString().equals(""))
				
				Methods.insertStoreName(actv, dlg, "stores", et.getText().toString());
				
			}//if (et.getText().toString().equals(""))
			
			break;
			
		case dlg_register_store_cancel://------------------------------------------
			//
			vib.vibrate(40);
			
			//
			dlg.dismiss();
			
			break;
			
		case dlg_input_empty_btn_reenter://------------------------------------------
			//
			vib.vibrate(40);
			
			//
			dlg2.dismiss();
			
			break;
			
		case dlg_input_empty_btn_cancel://------------------------------------------
			//
			vib.vibrate(40);
			
			//
			dlg2.dismiss();
			dlg.dismiss();
			
			break;
			
		case dlg_reconfirm_store_name_btn_yes://------------------------------------------
			//
			vib.vibrate(40);
			
//			//
//			et object = (et) findViewById(arguement);
//			
			Methods.insertStoreName_final(actv, dlg, dlg2, "stores");
			
			break;
			
		case dlg_reconfirm_store_name_btn_cancel://------------------------------------------
			//
			vib.vibrate(40);
			
			dlg2.dismiss();
			break;

		case dlg_register_genre_cancel://------------------------------------------
			//
			vib.vibrate(40);
			
			dlg.dismiss();
			break;
			
		case dlg_register_genre_register://------------------------------------------
			/*----------------------------
			 * Validate if the edit view has some input.
			 * If no input => Show another dialog
				----------------------------*/
			//
			vib.vibrate(40);
			
			//
			et = (EditText) dlg.findViewById(R.id.dlg_register_genre_et);
			
			if (et.getText().toString().equals("")) {
				
				Methods.dlg_input_empty(actv, dlg);
				
			} else {//if (et.getText().toString().equals(""))
				
				Methods.dlg_reconfirm_genre_name(actv, dlg, "genres", et.getText().toString());
				
//				// debug
//				Toast.makeText(actv, "Start => registerGenre()",
//						2000).show();
				
//				Methods.insertStoreName(actv, dlg, "stores", et.getText().toString());
				
			}//if (et.getText().toString().equals(""))
			
			break;
			
		// dlg_reconfirm_genre_name.xml
		case dlg_reconfirm_genre_name_btn_register://------------------------------------------
			//
			vib.vibrate(40);
			
			Methods.registerGenreName_final(actv, dlg, dlg2, "genres");
			
//			// debug
//			Toast.makeText(actv, "Register", 2000).show();
			
			break;
			
		case dlg_reconfirm_genre_name_btn_cancel://------------------------------------------
			//
			vib.vibrate(40);
			
			dlg2.dismiss();
			
			break;
			
		case dlg_create_table_create://------------------------------------------
			//
			vib.vibrate(40);
			
			//
			Methods.dlg_createTable_isInputEmpty(actv, dlg);
			
//			Methods.createTable_FromDialog(actv, dlg);
			
			break;
			
		case dlg_create_table_cancel://------------------------------------------
			//
			vib.vibrate(40);
			
			dlg.dismiss();
			
			break;

		case dlg_drop_table_btn_cancel://------------------------------------------
			//
			vib.vibrate(40);
			
			dlg.dismiss();
			break;
			
		case dlg_confirm_drop_table_btn_cancel://------------------------------------------
			//
			vib.vibrate(40);
			
			dlg2.dismiss();
			dlg.dismiss();
			
			break;
			
		case dlg_confirm_drop_table_btn_ok://------------------------------------------
			//
			vib.vibrate(40);
			
			// Dismiss the first dialog
			dlg.dismiss();
			
			// Call the method; Pass the second dialog
			Methods.dropTable(actv, dlg2);
			break;
			
		case dlg_filter_list_cancel://------------------------------------------
			
			dlg.dismiss();
			
			break;

		case dlg_filter_list_ok://------------------------------------------
			
			Methods.filterList(actv, dlg);
			
			break;
			
		case dlg_generic_cancel://------------------------------------------
			
			dlg.dismiss();
			
			break;
		
			
		default:
			break;
		}//switch (tag_name)
	}

}
