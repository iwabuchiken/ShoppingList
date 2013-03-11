package sl.listeners.dialog;

import java.util.Calendar;

import sl.items.PS;
import sl.main.R;
import sl.utils.CONS;
import sl.utils.DBUtils;
import sl.utils.Methods;
import sl.utils.Methods_sl;
import sl.utils.Tags;
import android.app.Activity;
import android.app.Dialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class
DialogButtonOnClickListener implements OnClickListener {
	/*----------------------------
	 * Fields
		----------------------------*/
	//
	Activity actv;
	Dialog dlg1;
	Dialog dlg2;		//=> Used in dlg_input_empty_btn_XXX

	//
	Vibrator vib;
	
	public DialogButtonOnClickListener(Activity actv, Dialog dlg) {
		//
		this.actv = actv;
		this.dlg1 = dlg;
		
		//
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
	}

	public DialogButtonOnClickListener(Activity actv, Dialog dlg1,
			Dialog dlg2) {
		//
		this.actv = actv;
		this.dlg1 = dlg1;
		this.dlg2 = dlg2;
		
		//
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
	}

//	@Override
	public void onClick(View v) {
		//
		Tags.DialogTags tag_name = (Tags.DialogTags) v.getTag();

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
			EditText et = (EditText) dlg1.findViewById(R.id.dlg_register_store_et);
			
			if (et.getText().toString().equals("")) {
				
				Methods.dlg_input_empty(actv, dlg1);
				
			} else {//if (et.getText().toString().equals(""))
				
				Methods.insertStoreName(actv, dlg1, "stores", et.getText().toString());
				
			}//if (et.getText().toString().equals(""))
			
			break;
			
		case dlg_register_store_cancel://------------------------------------------
			//
			vib.vibrate(40);
			
			//
			dlg1.dismiss();
			
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
			dlg1.dismiss();
			
			break;
			
		case dlg_reconfirm_store_name_btn_yes://------------------------------------------
			//
			vib.vibrate(40);
			
//			//
//			et object = (et) findViewById(arguement);
//			
			Methods.insertStoreName_final(actv, dlg1, dlg2, "stores");
			
			break;
			
		case dlg_reconfirm_store_name_btn_cancel://------------------------------------------
			//
			vib.vibrate(40);
			
			dlg2.dismiss();
			break;

		case dlg_register_genre_cancel://------------------------------------------
			//
			vib.vibrate(40);
			
			dlg1.dismiss();
			break;
			
		case dlg_register_genre_register://------------------------------------------
			/*----------------------------
			 * Validate if the edit view has some input.
			 * If no input => Show another dialog
				----------------------------*/
			//
			vib.vibrate(40);
			
			//
			et = (EditText) dlg1.findViewById(R.id.dlg_register_genre_et);
			
			if (et.getText().toString().equals("")) {
				
				Methods.dlg_input_empty(actv, dlg1);
				
			} else {//if (et.getText().toString().equals(""))
				
				Methods.dlg_reconfirm_genre_name(actv, dlg1, "genres", et.getText().toString());
				
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
			
			Methods.registerGenreName_final(actv, dlg1, dlg2, "genres");
			
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
			Methods.dlg_createTable_isInputEmpty(actv, dlg1);
			
//			Methods.createTable_FromDialog(actv, dlg);
			
			break;
			
		case dlg_create_table_cancel://------------------------------------------
			//
			vib.vibrate(40);
			
			dlg1.dismiss();
			
			break;

		case dlg_drop_table_btn_cancel://------------------------------------------
			//
			vib.vibrate(40);
			
			dlg1.dismiss();
			break;
			
		case dlg_confirm_drop_table_btn_cancel://------------------------------------------
			//
			vib.vibrate(40);
			
			dlg2.dismiss();
			dlg1.dismiss();
			
			break;
			
		case dlg_confirm_drop_table_btn_ok://------------------------------------------
			//
			vib.vibrate(40);
			
			// Dismiss the first dialog
			dlg1.dismiss();
			
			// Call the method; Pass the second dialog
			Methods.dropTable(actv, dlg2);
			break;
			
		case dlg_filter_list_cancel://------------------------------------------
			
			dlg1.dismiss();
			
			break;

		case dlg_filter_list_ok://------------------------------------------
			
			Methods.filterList(actv, dlg1);
			
			break;
			
		case dlg_filter_list_ok2://------------------------------------------
			
			Methods.filterList2(actv, dlg1);
			
			break;

		case dlg_generic_cancel://------------------------------------------
			
			dlg1.dismiss();
			
			break;
		
		case dlg_generic_dismiss://------------------------------------------
			
			dlg1.dismiss();
			
			break;

		case dlg_generic_dismiss_second_dialog://------------------------------------------
			
			dlg2.dismiss();
			
			break;

		case dlg_save_tobuy_list_bt_ok:
			
			case_dlg_save_tobuy_list_bt_ok();
			
			break;// case dlg_save_tobuy_list_bt_ok
			
		default:
			break;
		}//switch (tag_name)
	}

	private void case_dlg_save_tobuy_list_bt_ok() {
		// TODO Auto-generated method stub
		/***************************************
		 * Get data: Date
		 ***************************************/
		DatePicker dp = (DatePicker) dlg2.findViewById(R.id.dlg_save_tobuy_list_dp);
		
		int year = dp.getYear();
		int month = dp.getMonth();
//		int month = dp.getMonth() + 1;
		int day = dp.getDayOfMonth();
		// Log
		Log.d("DialogButtonOnClickListener.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]",
				"year=" + year
				+ "/"
				+ "month=" + month
				+ "/"
				+ "day=" + day);

		Calendar cal = Calendar.getInstance();
		
		cal.set(year, month, day);
		
		long dueDate = cal.getTimeInMillis();
		
		/***************************************
		 * Get data: The rest
		 ***************************************/
		/***************************************
		 * Store name
		 ***************************************/
		Spinner spStoreNames = (Spinner) dlg2.findViewById(R.id.dlg_save_tobuy_list_sp_store_name);
		
		String storeName = spStoreNames.getSelectedItem().toString();
		
		/***************************************
		 * Is the schedule already in db?
		 ***************************************/
		boolean res = Methods_sl.isInDb_PS(actv, storeName, cal);
		
		// Log
		Log.d("DialogButtonOnClickListener.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "res=" + res);
		
		//debug
		
		
		/***************************************
		 * Amount
		 ***************************************/
		EditText etAmount = (EditText) dlg2.findViewById(R.id.dlg_save_tobuy_list_et_amount);
		
		/***************************************
		 * Memo
		 ***************************************/
		EditText etMemo = (EditText) dlg2.findViewById(R.id.dlg_save_tobuy_list_et_memo);
		
		/***************************************
		 * Items
		 ***************************************/
		// Log
		Log.d("DialogButtonOnClickListener.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Calling => case_dlg_save_tobuy_list_bt_ok__convertToBuyList2String()");
		
		String items = case_dlg_save_tobuy_list_bt_ok__getItemIdsString();
		
		/***************************************
		 * Store data
		 * 1. Get dbId: Get the number of data already stored
		 * 		=> Add 1 to the number
		 * 2. Construct a PS instance
		 * 3. Store date => DBUtils.
		 ***************************************/
		/***************************************
		 * Construct: A PS instance
		 ***************************************/
		PS ps = new PS();
		
//		ps.setDbId(dbId);
		ps.setStoreName(spStoreNames.getSelectedItem().toString());
		ps.setAmount(Integer.parseInt(etAmount.getText().toString()));
		ps.setMemo(etMemo.getText().toString());
		ps.setItems(items);
		ps.setDueDate(dueDate);
		
//		/***************************************
//		 * Store the PS instance to database
//		 ***************************************/
//		DBUtils dbu = new DBUtils(actv, CONS.dbName);
////		
////		SQLiteDatabase wdb = dbu.getWritableDatabase();
//
//		res = dbu.storeData_PS(
//								CONS.dbName,
//								CONS.DBAdmin.tname_purchaseSchedule,
//								ps);
//		
//		
		/***************************************
		 * Dismiss dialog
		 ***************************************/
		dlg1.dismiss();
		dlg2.dismiss();
		
	}//private void case_dlg_save_tobuy_list_bt_ok()

	private String case_dlg_save_tobuy_list_bt_ok__getItemIdsString() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		
		for (Integer id : CONS.tab_toBuyItemIds) {
			
			sb.append(String.valueOf(id.intValue()));
			sb.append(" ");
			
		}//for (Integer id : CONS.tab_toBuyItemIds)

		return sb.toString().trim();
		
//		String s = sb.toString();
//
//		// Log
//		Log.d("DialogButtonOnClickListener.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "s=" + s + "(" + s.length() + ")");
//
//		s = s.trim();
//		
//		// Log
//		Log.d("DialogButtonOnClickListener.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "s=" + s + "(" + s.length() + ")");
//		
//		return null;
	}//private void case_dlg_save_tobuy_list_bt_ok()

}//DialogButtonOnClickListener implements OnClickListener
