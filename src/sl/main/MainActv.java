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
import sl.utils.CONS;
import sl.utils.DBUtils;
import sl.utils.Methods;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
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
////	public static String[] registerItems = {"品物", "店舗", "ジャンル"};
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
        setContentView(R.layout.shoppinglist);
        
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
		
		
		
		tv_list.setTag(Methods.ButtonTags.sl_main_bt_item_list);
		
		tv_list.setOnTouchListener(new ButtonOnTouchListener(this));
		tv_list.setOnClickListener(new ButtonOnClickListener(this));
		
		/*----------------------------
		 * 2. tv_register
			----------------------------*/
		TextView tv_register = (TextView) findViewById(R.id.sl_tv_register);
		
		tv_register.setTag(Methods.ButtonTags.sl_main_bt_register);
		
		tv_register.setOnTouchListener(new ButtonOnTouchListener(this));
		tv_register.setOnClickListener(new ButtonOnClickListener(this));
		
		/*----------------------------
		 * 3. tv_db
			----------------------------*/
		TextView tv_db = (TextView) findViewById(R.id.sl_tv_db);
		
		tv_db.setTag(Methods.ButtonTags.sl_main_bt_db);
		
		tv_db.setOnTouchListener(new ButtonOnTouchListener(this));
		tv_db.setOnClickListener(new ButtonOnClickListener(this));
		
//		Methods.setOnTouchListener_button(this, Methods.ViewNames.TV, 
//				Methods.ButtonTags.sl_main_bt_item_list, R.id.sl_tv_item_list);
//		
//		Methods.setOnTouchListener_button(this, Methods.ViewNames.TV, 
//				Methods.ButtonTags.sl_main_bt_register, R.id.sl_tv_register);
//		
//		Methods.setOnTouchListener_button(this, Methods.ViewNames.TV, 
//				Methods.ButtonTags.sl_main_bt_db, R.id.sl_tv_db);
		
		
	}//private void add_listeners()

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// 
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.listmenu, menu);

		
		
		return super.onCreateOptionsMenu(menu);
	}//public boolean onCreateOptionsMenu(Menu menu)

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO 自動生成されたメソッド・スタブ
		switch (item.getItemId()) {
			case R.id.v1_menu_add_item:
				//
				Intent i = new Intent();
				
				//
				i.setClass(this, RegisterItemActv.class);
				
				//
				startActivity(i);
				
				break;
			
			case R.id.v1_menu_item_list:
				//
				i = new Intent();
				i.setClass(this, ItemListActv.class);
				startActivity(i);
				
				break;
				
			case R.id.v1_menu_register_store:
				//
				Methods.register_store(this);
				
				break;
				
			case R.id.v1_menu_add_genre:
				//
				Methods.registerGenre(this);
				
				break;
				
			case R.id.v1_menu_db_manager:
				//
				i = new Intent();
				i.setClass(this, DBAdminActv.class);
				startActivity(i);
				
				break;
		}//switch (item.getItemId())
		
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPause() {
		// TODO 自動生成されたメソッド・スタブ
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();
	}

	@Override
	protected void onStart() {
		// TODO 自動生成されたメソッド・スタブ
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO 自動生成されたメソッド・スタブ
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO 自動生成されたメソッド・スタブ
		super.onDestroy();
	}
    
    
    
}//public class ShoppingListActivity extends ListActivity
