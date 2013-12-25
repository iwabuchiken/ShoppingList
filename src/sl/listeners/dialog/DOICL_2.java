package sl.listeners.dialog;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;

import org.apache.commons.lang.StringUtils;

import sl.items.ShoppingItem;
import sl.main.MainActv;
import sl.main.R;
import sl.main.RegisterItemActv;
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
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class DOICL_2 implements OnItemClickListener {

	//
	Activity actv;
	
	Dialog dlg;
	Dialog dlg1;
	Dialog dlg2;
	
	ShoppingItem si;
	//
	Vibrator vib;
	
	//
	Tags.DialogTags dlgTag = null;
	
	public DOICL_2(Activity actv, Dialog dlg, Tags.DialogTags dlgTag) {
		// 
		this.actv = actv;
		this.dlg = dlg;
		this.dlgTag = dlgTag;
		
		//
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
		
	}//public DialogOnItemClickListener(Activity actv, Dialog dlg)
	
	public DOICL_2(Activity actv, Dialog dlg1,
			DialogTags dlgItemListLongClick, ShoppingItem si) {
		// 
		this.actv = actv;
		this.dlg = dlg;
		this.dlgTag = dlgTag;
		
		//
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
		
	}

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
		
		// Log
		Log.d("[" + "DOICL_2.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "tag => " + tag);
		
	}//public void onItemClick(AdapterView<?> parent, View v, int position, long id)


}//public class DialogOnItemClickListener implements OnItemClickListener
