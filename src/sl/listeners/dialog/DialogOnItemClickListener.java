package sl.listeners.dialog;

import sl.main.MainActv;
import sl.main.R;
import sl.main.RegisterItemActv;
import sl.utils.CONS;
import sl.utils.DBUtils;
import sl.utils.Methods;
import sl.utils.Methods_sl;
import sl.utils.Tags;
import sl.utils.Tags.DialogTags;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class DialogOnItemClickListener implements OnItemClickListener {

	//
	Activity actv;
	Dialog dlg;
	
	//
	Vibrator vib;
	
	//
	Tags.DialogTags dlgTag = null;
	
	public DialogOnItemClickListener(Activity actv, Dialog dlg) {
		// 
		this.actv = actv;
		this.dlg = dlg;
		
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
		
	}//public DialogOnItemClickListener(Activity actv, Dialog dlg)

	public DialogOnItemClickListener(Activity actv, Dialog dlg, Tags.DialogTags dlgTag) {
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
		
		/*********************************
		 * Called from: Methods_dlg.dlg_db_activity()
		 *********************************/
		Tags.DialogTags tag = (Tags.DialogTags) parent.getTag();
		
		if (tag != null) {
			
			switch (tag) {
			
			case dlg_db_admin_lv:
				
				String choice = (String) parent.getItemAtPosition(position);
				
				dlg_db_admin_lv(choice);
				
				break;
				
			}//switch (tag)
			
		}//if (tag != null)
		
		/*----------------------------
		 * 2. Call a method
			----------------------------*/
//		//debug
//		Tags.DialogTags tag = (Tags.DialogTags) v.getTag();
//		
//		// Log
//		Log.d("DialogOnItemClickListener.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "tag=" + tag);
//		// Log
//		Log.d("DialogOnItemClickListener.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "v.getClass().getName()=" + v.getClass().getName());
//		
//		Tags.DialogTags tag2 = (Tags.DialogTags) parent.getTag();
//		
//		// Log
//		Log.d("DialogOnItemClickListener.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "tag2=" + tag2);
//		
//		// Log
//		Log.d("DialogOnItemClickListener.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "parent.getClass().getName()=" + parent.getClass().getName());
//		
		//////////////////////////////////////////////////////////////debug
		
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
				i.setClass(actv, RegisterItemActv.class);
				
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

		}//if (dlgName != null && dlgName == "confirm_table_drop")
		
	}//public void onItemClick(AdapterView<?> parent, View v, int position, long id)

	private void dlg_db_admin_lv(String choice) {
		// TODO Auto-generated method stub
		if (choice.equals(actv.getString(
						R.string.dlg_db_admin_item_backup_db))) {
			
			dlg_db_admin_lv_backupDb();
			
			return;
			
		} else if (choice.equals(actv.getString(
				R.string.dlg_db_admin_item_refatcor_db))) {

			dlg_db_admin_lv_refactorDb();
			
			return;
			
		} else {
			
		}//if
		
	}//private void dlg_db_admin_lv(String choice)

	private void dlg_db_admin_lv_refactorDb() {
		
//		Methods_sl.refactorDb_colPrice(actv);
		Methods_sl.refactorDb_colGenre(actv);

		/*********************************
		 * Close dialog
		 *********************************/
		dlg.dismiss();
		
		return;

		
//		/*********************************
//		 * Setup DB
//		 *********************************/
//		DBUtils dbu = new DBUtils(actv, CONS.dbName);
//		
//		SQLiteDatabase wdb = dbu.getWritableDatabase();
//		
//		/*********************************
//		 * Query
//		 *********************************/
//		String tableName = "shopping_item";
//		
//		String sql = "SELECT * FROM " + tableName;
//
//		Cursor c = null;
//
//		try {
//			
//			c = wdb.rawQuery(sql, null);
//			
//			/*********************************
//			 * Cursor => null?
//			 *********************************/
//			if (null == c) {
//				
//				// Log
//				Log.d("DialogOnItemClickListener.java"
//						+ "["
//						+ Thread.currentThread().getStackTrace()[2]
//								.getLineNumber()
//						+ ":"
//						+ Thread.currentThread().getStackTrace()[2]
//								.getMethodName() + "]", "Cursor => null");
//				
//				wdb.close();
//				
//				return;
//				
//			}//if (null == c)
//			
//			/*********************************
//			 * Num of entries in the cursor => Less than 1?
//			 *********************************/
//			if (c.getCount() < 1) {
//				
//				// Log
//				Log.d("DialogOnItemClickListener.java"
//						+ "["
//						+ Thread.currentThread().getStackTrace()[2]
//								.getLineNumber()
//						+ ":"
//						+ Thread.currentThread().getStackTrace()[2]
//								.getMethodName() + "]", "Cursor => No entry");
//				
//				wdb.close();
//				
//				return;
//				
//			}//if (null == c)
//			
//			/*********************************
//			 * Start refactoring data
//			 *********************************/
//			// Log
//			Log.d("DialogOnItemClickListener.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "Refactoring starts...");
//			
//			boolean res = Methods_sl.refactorData(
//										actv, wdb, dbu, tableName, c);
//			
//			if (res == true) {
//			
//				// Log
//				Log.d("DialogOnItemClickListener.java"
//						+ "["
//						+ Thread.currentThread().getStackTrace()[2]
//								.getLineNumber()
//						+ ":"
//						+ Thread.currentThread().getStackTrace()[2]
//								.getMethodName() + "]", "Data refactored");
//				
//			} else {//if (res == true)
//
//				// Log
//				Log.d("DialogOnItemClickListener.java"
//						+ "["
//						+ Thread.currentThread().getStackTrace()[2]
//								.getLineNumber()
//						+ ":"
//						+ Thread.currentThread().getStackTrace()[2]
//								.getMethodName() + "]",
//						"Data refactoring => Failed");
//				
//			}//if (res == true)
//			
//			/*********************************
//			 * Closing operations
//			 *********************************/
//			wdb.close();
//			
//			dlg.dismiss();
//			
//			return;
//			
//		} catch (Exception e) {
//
//			// Log
//			Log.d("DialogOnItemClickListener.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "Exception => " + e.toString());
//		}//try
//
//		
//		wdb.close();
		

	}//private void dlg_db_admin_lv_refactorDb()

	private void dlg_db_admin_lv_backupDb() {
		// TODO Auto-generated method stub
		int res = Methods.backupDb(
				actv, CONS.dbName, CONS.dirPath_db_backup);

		if (res == CONS.DB_DOESNT_EXIST) {
			
			// Log
			Log.d("DialogOnItemClickListener.java"
					+ "["
					+ Thread.currentThread().getStackTrace()[2]
							.getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2]
							.getMethodName() + "]", "DB file doesn't exist: " + res);
			
		} else if (res == CONS.DB_FILE_COPY_EXCEPTION) {//if (res == CONS.DB_DOESNT_EXIST)
		
			// Log
			Log.d("DialogOnItemClickListener.java"
					+ "["
					+ Thread.currentThread().getStackTrace()[2]
							.getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2]
							.getMethodName() + "]",
					"Copying file => Failed: " + res);
		
		} else if (res == CONS.DB_CANT_CREATE_FOLDER) {//if (res == CONS.DB_DOESNT_EXIST)
		
			// Log
			Log.d("DialogOnItemClickListener.java"
					+ "["
					+ Thread.currentThread().getStackTrace()[2]
							.getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2]
							.getMethodName() + "]",
					"Can't create a backup folder: " + res);
		
		} else if (res == CONS.DB_BACKUP_SUCCESSFUL) {//if (res == CONS.DB_DOESNT_EXIST)
		
			// Log
			Log.d("DialogOnItemClickListener.java"
					+ "["
					+ Thread.currentThread().getStackTrace()[2]
							.getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2]
							.getMethodName() + "]",
					"Backup successful: " + res);
		
			// debug
			Toast.makeText(actv,
					"DB backup => Done",
					Toast.LENGTH_LONG).show();
			
			/*********************************
			 * If successful, dismiss the dialog
			 *********************************/
			dlg.dismiss();
		
		}//if (res == CONS.DB_DOESNT_EXIST)
		
	}//private void dlg_db_admin_lv_backupDb()

}//public class DialogOnItemClickListener implements OnItemClickListener
