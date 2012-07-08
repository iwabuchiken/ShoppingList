package shoppinglist.lib;

import shoppinglist.main.R;
import android.app.Activity;
import android.app.Dialog;
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

	public DialogButtonOnClickListener(Activity actv, Dialog dlg) {
		//
		this.actv = actv;
		this.dlg = dlg;
	}

	public DialogButtonOnClickListener(Activity actv, Dialog dlg1,
			Dialog dlg2) {
		//
		this.actv = actv;
		this.dlg = dlg1;
		this.dlg2 = dlg2;
	}

	@Override
	public void onClick(View v) {
		//
		Methods.DialogTags tag_name = (Methods.DialogTags) v.getTag();

		//
		switch (tag_name) {
		case dlg_register_store_ok:
			/*----------------------------
			 * Validate if the edit view has some input.
			 * If no input => Show another dialog
				----------------------------*/
			//
			EditText et = (EditText) dlg.findViewById(R.id.dlg_register_store_et);
			
			if (et.getText().toString().equals("")) {
				
				Methods.dlg_input_empty(actv, dlg);
				
			} else {//if (et.getText().toString().equals(""))
				
				Methods.insertStoreName(actv, dlg, "stores", et.getText().toString());
				
			}//if (et.getText().toString().equals(""))
			
			break;
			
		case dlg_register_store_cancel:
			//
			dlg.dismiss();
			
			break;
			
		case dlg_input_empty_btn_reenter:
			//
			dlg2.dismiss();
			
			break;
			
		case dlg_input_empty_btn_cancel:
			//
			dlg2.dismiss();
			dlg.dismiss();
			
			break;
			
		case dlg_reconfirm_store_name_btn_yes:
//			//
//			et object = (et) findViewById(arguement);
//			
			Methods.insertStoreName_final(actv, dlg, dlg2, "stores");
			
			break;
			
		case dlg_reconfirm_store_name_btn_cancel:
			dlg2.dismiss();
			break;

		case dlg_register_genre_cancel:
			dlg.dismiss();
			break;
			
		case dlg_register_genre_register:
			/*----------------------------
			 * Validate if the edit view has some input.
			 * If no input => Show another dialog
				----------------------------*/
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
		case dlg_reconfirm_genre_name_btn_register:
			
			Methods.registerGenreName_final(actv, dlg, dlg2, "genres");
			
//			// debug
//			Toast.makeText(actv, "Register", 2000).show();
			
			break;
			
		case dlg_reconfirm_genre_name_btn_cancel:
			dlg2.dismiss();
			
			break;
			
		case dlg_create_table_create:
			//
			Methods.dlg_createTable_isInputEmpty(actv, dlg);
			
//			Methods.createTable_FromDialog(actv, dlg);
			
			break;
			
		case dlg_create_table_cancel:
			dlg.dismiss();
			
			break;

		case dlg_drop_table_btn_cancel:
			dlg.dismiss();
			break;
		}//switch (tag_name)
	}

}
