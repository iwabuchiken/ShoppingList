package sl.listeners.list;

import java.io.File;

import org.apache.commons.lang.StringUtils;

import sl.items.ShoppingItem;
import sl.main.MainActv;
import sl.main.R;
import sl.main.RegisterItemActv;
import sl.tasks.TaskAudioTrack;
import sl.tasks.Task_GetYomi;
import sl.utils.CONS;
import sl.utils.DBUtils;
import sl.utils.Methods;
import sl.utils.Methods_dlg;
import sl.utils.Methods_sl;
import sl.utils.Tags;
import sl.utils.Tags.DialogTags;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ListOnItemClickListener implements OnItemClickListener {

	//
	Activity actv;

	//
	Vibrator vib;
	
//	boolean bgm;
	
	//
	Tags.DialogTags dlgTag = null;

	public ListOnItemClickListener(Activity actv) {
		//
		this.actv = actv;
		//
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);

//		/***************************************
//		 * Get preference value: bgm
//		 ***************************************/
//		SharedPreferences prefs = actv
//				.getSharedPreferences(
//					actv.getString(R.string.shared_preferences_name),
//					Context.MODE_PRIVATE);
//
////		boolean bgm = prefs.getBoolean(actv.getString(R.string.prefs_key_bgm), false);
//		CONS.bgm = prefs.getBoolean(actv.getString(R.string.prefs_key_bgm), false);
//		
//		// Log
//		Log.d("MainActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "bgm=" + CONS.bgm);

	}//public ListOnItemClickListener(Activity actv)

	
	//	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

//		vib.vibrate(40);
		
		/*********************************
		 * Called from: Methods_dlg.dlg_db_activity()
		 *********************************/
//		Tags.DialogTags tag = (Tags.DialogTags) parent.getTag();
		Tags.ListTags tag = (Tags.ListTags) parent.getTag();
		
		// Log
		Log.d("ListOnItemClickListener.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "tag.name()=" + tag.name());
		
		
		switch (tag) {
		case tab_itemList:

			tab_itemList(parent, position);
			
			break;

		case tab_toBuyList:

			tab_toBuyList(parent, position);
			
			break;// case tab_toBuyList

		default:
			break;
		
		}//switch (item)

	}//public void onItemClick(AdapterView<?> parent, View v, int position, long id)


	private void tab_toBuyList(AdapterView<?> parent, int position) {
		// TODO Auto-generated method stub
		/***************************************
		 * Get item
		 ***************************************/
		ShoppingItem si = (ShoppingItem) parent.getItemAtPosition(position);
		
		// Log
		Log.d("ListOnItemClickListener.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "si.getName()=" + si.getName());
		
		/***************************************
		 * Show dialog
		 ***************************************/
		Methods_dlg.dlg_tabActv_tab2Lv(actv, si);
		
	}//private void tab_toBuyList(AdapterView<?> parent, int position)


	private void tab_itemList(AdapterView<?> parent, int position) {
		// TODO Auto-generated method stub
		/***************************************
		 * Get item
		 ***************************************/
		ShoppingItem si = (ShoppingItem) parent.getItemAtPosition(position);
		
		// Log
		Log.d("ListOnItemClickListener.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "si.getName()=" + si.getName());
		
		/***************************************
		 * checkedItemPositions
		 ***************************************/
		int itemId = si.getId();
		
		if (CONS.tab_checkedItemIds.contains(itemId)) {
			
//			CONS.tab_checkedPositions.remove(itemId);
			CONS.tab_checkedItemIds.remove(new Integer(itemId));

			/***************************************
			 * BGM
			 ***************************************/
			if (CONS.bgm == true) {
				
				int bgmResourceId = R.raw.bgm_3_uncheck_item;
				
				TaskAudioTrack task = new TaskAudioTrack(actv);
				
//				task.execute("Start");
				task.execute(bgmResourceId);
				
			}//if (bgm == true)

		} else if (!CONS.tab_checkedItemIds.contains(itemId)) {//if (CONS.tab_checkedPositions.contains(itemId))
			
			CONS.tab_checkedItemIds.add(itemId);
			
			// Log
			Log.d("ListOnItemClickListener.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Added: Item id=" + itemId);

			/***************************************
			 * BGM
			 ***************************************/
			if (CONS.bgm == true) {
				
				int bgmResourceId = R.raw.bgm_2_koto_t150_1second;
				
				TaskAudioTrack task = new TaskAudioTrack(actv);
				
//				task.execute("Start");
				task.execute(bgmResourceId);
				
			}//if (bgm == true)

		}//if (CONS.tab_checkedPositions.contains(itemId))
		
		//debug
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < CONS.tab_checkedItemIds.size(); i++) {
		
			sb.append(CONS.tab_checkedItemIds.get(i));
			sb.append(",");
//			// Log
//			Log.d("ListOnItemClickListener.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "item" + (i+1) + "=" + CONS.tab_checkedPositions.get(i));
//			
		}//for (int i = 0; i < CONS.tab_checkedPositions.size(); i++)
		
		// Log
		Log.d("ListOnItemClickListener.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Positions=" + sb.toString());
		
//		/***************************************
//		 * BGM
//		 ***************************************/
//
//		/***************************************
//		 * Play sound
//		 ***************************************/
//		if (bgm == true) {
//			
//			int bgmResourceId = R.raw.bgm_2_koto_t150_1second;
//			
////			bgm(actv, bgmResourceId);
//			
////			Methods_sl.playSound(this);
////			TaskAudioTrack task = new TaskAudioTrack(actv, bgmResourceId);
//			TaskAudioTrack task = new TaskAudioTrack(actv);
//			
////			task.execute("Start");
//			task.execute(bgmResourceId);
//			
//		}//if (bgm == true)
		
		/***************************************
		 * Notify adapter
		 ***************************************/
		CONS.adpItems.notifyDataSetChanged();
		
	}//private void tab_itemList(AdapterView<?> parent, int position)

}//public class DialogOnItemClickListener implements OnItemClickListener
