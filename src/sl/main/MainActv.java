package sl.main;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import org.apache.commons.lang.StringUtils;

import sl.listeners.ButtonOnClickListener;
import sl.listeners.ButtonOnTouchListener;
import sl.main.R;
import sl.tasks.TaskAudioTrack;
import sl.utils.CONS;
import sl.utils.DBUtils;
import sl.utils.Methods;
import sl.utils.Methods_dlg;
import sl.utils.Methods_sl;
import sl.utils.Tags;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

//public class ShoppingListActivity extends ListActivity {
public class MainActv extends Activity {
	
	public static Vibrator vib;

//	/*********************************
//	 * Paths
//	 *********************************/
//	String dirName_ExternalStorage = "/mnt/sdcard-ext";
//
//	String dirPath_db = "/data/data/shoppinglist.main/databases";
//
//	String fileName_db_backup_trunk = "shoppinglist_backup";
//
//	String fileName_db_backup_ext = ".bk";
//
//	String dirPath_db_backup = dirName_ExternalStorage + "/ShoppingList_backup";
//	
////	public static String[] registerItems = {"�i��", "�X��", "�W������"};
//	
////	public static enum registerChoice = {
//	public static enum registerChoice {
//		items, stores, genres,
//	};
//			
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	/*----------------------------
		 * Steps
		 * 1. Set up
		 * 		1. super
		 * 		2. Content view
		 * 		3. vib
		 * 2. Add listeners
			----------------------------*/
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actv_main);
        
        setTitle(this.getClass().getName());
        
        vib = (Vibrator) this.getSystemService(this.VIBRATOR_SERVICE);
        
        /*----------------------------
		 * 2. Add listeners
			----------------------------*/
		add_listeners();
        
        //debug
//		backup_db();
//        debug_restore_db();
//		debug_B13_v_1_0();
        
		
    }//public void onCreate(Bundle savedInstanceState)

    
	private void debug_B13_v_1_0() {
		
		File f = new File(CONS.dirPath_db);
		
		String[] fileNames = f.list();

		// Log
		Log.d("SLActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "f.exists() => " + f.exists());
	
		if (fileNames != null) {
		
			// Log
			Log.d("SLActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "fileNames.length=" + fileNames.length);
			
			for (String name : fileNames) {
				
				// Log
				Log.d("SLActv.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]", "name=" + name);
				
			}//for (String name : fileNames)
			
		} else {//if (fileNames != null)
			
			// Log
			Log.d("SLActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "fileNames == null");
			
		}//if (fileNames != null)
		
		/*********************************
		 * Create a db file
		 *********************************/
		DBUtils dbm = new DBUtils(this, CONS.dbName);
		
		SQLiteDatabase db = dbm.getWritableDatabase();
		
		db.close();
		
		// Log
		Log.d("SLActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "db => Done");
		
		// Log
		Log.d("SLActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "f.exists() => " + f.exists());
		

		/*********************************
		 * Restore db
		 *********************************/
		String srcFileName = Methods.getFileNameFromDir_latest(
										this, CONS.dirPath_db_backup);
		
		String src = StringUtils.join(
							new String[]{CONS.dirPath_db_backup,
									srcFileName},
							File.separator);
		
		String dst = StringUtils.join(
							new String[]{
									CONS.dirPath_db,
									CONS.dbName},
							File.separator);
//		String dst = CONS.dirPath_db;
		
		Methods.restore_db(this, CONS.dbName, src, dst);
		
//		for (String name : fileNames) {
//			
//			// Log
//			Log.d("SLActv.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "name=" + name);
//			
//		}//for (String name : fileNames)
		
	}


	private void debug_restore_db() {
		/*********************************
		 * memo
		 *********************************/
		String src = StringUtils.join(
				new String[]{CONS.dirPath_db_backup,
						"shoppinglist_backup_20121108_122426.bk"},
				File.separator);
		
		String dst = StringUtils.join(
				new String[]{CONS.dirPath_db,
						CONS.dbName},
				File.separator);
		
		// Restore db
		Methods.restore_db(this, CONS.dbName, src, dst);
		
		
		File f = new File(CONS.dirPath_db);
		
		String[] f_list = f.list();
		
		for (String fname : f_list) {
			
			// Log
			Log.d("SLActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "fname=" + fname);
			
		}
		
	}//private void debug_restore_db()

	private void backup_db() {
		/*----------------------------
		 * memo
			----------------------------*/
//		String dirName_ExternalStorage = "/mnt/sdcard-ext";
		
//		String dirPath_db_backup = dirName_ExternalStorage + "/ShoppingList_backup";

//		String dirPath_db = "/data/data/shoppinglist.main/databases";
		
//		String fileName_db_backup_trunk = "shoppinglist_backup";
//		String fileName_db_backup_ext = ".bk";

		
		
		String time_label = Methods.get_TimeLabel(Methods.getMillSeconds_now());
		
		String db_src = StringUtils.join(
							new String[]{
								CONS.dirPath_db,
								CONS.dbName},
							File.separator);
		
		String db_dst = StringUtils.join(
							new String[]{
								CONS.dirPath_db_backup,
								CONS.fileName_db_backup_trunk},
							File.separator);
		
		db_dst = db_dst + "_" + time_label + CONS.fileName_db_backup_ext;
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", 
				"db_src: " + db_src + " * " + "db_dst: " + db_dst);
//				"db_dst: " + db_dst);
		
//		/*----------------------------
//		 * 2. Prep => Files
//			----------------------------*/
//		File src = new File(db_src);
//		File dst = new File(db_dst);
//		
//		/*----------------------------
//		 * 2-2. Folder exists?
//			----------------------------*/
//		File db_backup = new File(CONS.dirPath_db_backup);
//		
//		if (!db_backup.exists()) {
//			
//			try {
//				db_backup.mkdir();
//				
//				// Log
//				Log.d("Methods.java" + "["
//						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//						+ "]", "Folder created: " + db_backup.getAbsolutePath());
//			} catch (Exception e) {
//				
//				// Log
//				Log.d("Methods.java"
//						+ "["
//						+ Thread.currentThread().getStackTrace()[2]
//								.getLineNumber() + "]", "Create folder => Failed");
//				
//				return;
//				
//			}
//			
//		} else {//if (!db_backup.exists())
//			
//			// Log
//			Log.d("Methods.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "Folder exists: ");
//			
//		}//if (!db_backup.exists())
//		
//		/*----------------------------
//		 * 3. Copy
//			----------------------------*/
//		try {
//			FileChannel iChannel = new FileInputStream(src).getChannel();
//			FileChannel oChannel = new FileOutputStream(dst).getChannel();
//			iChannel.transferTo(0, iChannel.size(), oChannel);
//			iChannel.close();
//			oChannel.close();
//			
//			// Log
//			Log.d("ThumbnailActivity.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "File copied");
//			
//			// debug
//			Toast.makeText(this, "DB backup => Done", 3000).show();
//
//		} catch (FileNotFoundException e) {
//			// Log
//			Log.d("MainActv.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "Exception: " + e.toString());
//			
//		} catch (IOException e) {
//			// Log
//			Log.d("MainActv.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "Exception: " + e.toString());
//		}//try

		

		
//		Methods.db_backup(this);
//		
//		DBManager dbm = new DBManager(this);
//		
//		SQLiteDatabase db = dbm.getWritableDatabase();
//
//		// Log
//		Log.d("ShoppingListActivity.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "db.getPath(): " + db.getPath());
//		
//		db.close();
		
		
	}//private void backup_db()

	private void add_listeners() {
		/*----------------------------
		 * Steps
		 * 1. tv_list
		 * 2. tv_register
		 * 3. tv_db
			----------------------------*/
		
		// 
		TextView tv_list = (TextView) findViewById(R.id.sl_tv_item_list);
		
		
		
		tv_list.setTag(Tags.ButtonTags.sl_main_bt_item_list);
		
		tv_list.setOnTouchListener(new ButtonOnTouchListener(this));
		tv_list.setOnClickListener(new ButtonOnClickListener(this));
		
		/*----------------------------
		 * 2. tv_register
			----------------------------*/
		TextView tv_register = (TextView) findViewById(R.id.sl_tv_register);
		
		tv_register.setTag(Tags.ButtonTags.sl_main_bt_register);
		
		tv_register.setOnTouchListener(new ButtonOnTouchListener(this));
		tv_register.setOnClickListener(new ButtonOnClickListener(this));
		
//		/*----------------------------
//		 * 3. tv_db
//			----------------------------*/
//		TextView tv_db = (TextView) findViewById(R.id.sl_tv_db);
//		
//		tv_db.setTag(Tags.ButtonTags.sl_main_bt_db);
//		
//		tv_db.setOnTouchListener(new ButtonOnTouchListener(this));
//		tv_db.setOnClickListener(new ButtonOnClickListener(this));
		
//		Methods.setOnTouchListener_button(this, Methods.ViewNames.TV, 
//				Tags.ButtonTags.sl_main_bt_item_list, R.id.sl_tv_item_list);
//		
//		Methods.setOnTouchListener_button(this, Methods.ViewNames.TV, 
//				Tags.ButtonTags.sl_main_bt_register, R.id.sl_tv_register);
//		
//		Methods.setOnTouchListener_button(this, Methods.ViewNames.TV, 
//				Tags.ButtonTags.sl_main_bt_db, R.id.sl_tv_db);
		
		
	}//private void add_listeners()

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// 
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.menu_main, menu);

		
		
		return super.onCreateOptionsMenu(menu);
	}//public boolean onCreateOptionsMenu(Menu menu)

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
//		// Log
//		Log.d("MainActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "Options");
		
		switch (item.getItemId()) {
				
			case R.id.v1_menu_db_manager:
				
				// Log
				Log.d("MainActv.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]", "db_manager");
				
				Methods_dlg.dlg_db_activity(this);
				
				
//				Intent i = new Intent();
//				
//				i = new Intent();
//				
//				i.setClass(this, DBActv.class);
//				
//				i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//				
//				startActivity(i);

				
				break;
				
			case R.id.menu_main_tab_actv:
				
//				// Log
//				Log.d("MainActv.java"
//						+ "["
//						+ Thread.currentThread().getStackTrace()[2]
//								.getLineNumber()
//						+ ":"
//						+ Thread.currentThread().getStackTrace()[2]
//								.getMethodName() + "]", "tab_actv");
				
//				Methods_dlg.dlg_db_activity(this);
//
//				
				Intent i = new Intent();
				
				i = new Intent();
				
				i.setClass(this, TabActv.class);
				
				i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				
				startActivity(i);

				break;

			case R.id.menu_main_pref_actv:
				
//				// Log
//				Log.d("MainActv.java"
//						+ "["
//						+ Thread.currentThread().getStackTrace()[2]
//								.getLineNumber()
//						+ ":"
//						+ Thread.currentThread().getStackTrace()[2]
//								.getMethodName() + "]", "tab_actv");
				
//				Methods_dlg.dlg_db_activity(this);
//
//				
				i = new Intent();
				
				i = new Intent();
				
				i.setClass(this, PrefActv.class);
				
				i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				
				startActivity(i);

				break;// case R.id.menu_main_pref_actv
				
			default:
				break;
					
		}//switch (item.getItemId())
		
		return super.onOptionsItemSelected(item);
	}//public boolean onOptionsItemSelected(MenuItem item)

	@Override
	protected void onPause() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onResume();
		
		// Log
		Log.d("MainActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Resume");
		
		/***************************************
		 * Preference values: BGM
		 ***************************************/
		SharedPreferences prefs = this
				.getSharedPreferences(
					this.getString(R.string.shared_preferences_name),
					Context.MODE_PRIVATE);

		boolean bgm = prefs.getBoolean(this.getString(R.string.prefs_key_bgm), false);
		
		// Log
		Log.d("MainActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "bgm=" + bgm);
		 
		/***************************************
		 * Play sound
		 ***************************************/
		if (bgm == true) {
			
//			Methods_sl.playSound(this);
			TaskAudioTrack task = new TaskAudioTrack(this);
			
			task.execute("Start");
			
		}//if (bgm == true)
		
	}//protected void onResume()

	@Override
	protected void onStart() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onDestroy();
	}
    
    
    
}//public class ShoppingListActivity extends ListActivity
