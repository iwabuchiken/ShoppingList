package shoppinglist.main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import shoppinglist.listeners.ButtonOnClickListener;
import shoppinglist.listeners.ButtonOnTouchListener;
import shoppinglist.main.R;
import shoppinglist.utils.DBManager;
import shoppinglist.utils.Methods;
import sl.adapters.ItemListAdapter;
import sl.items.ShoppingItem;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ItemListActv extends ListActivity {

	//
	public static List<ShoppingItem> list;
	
	//
	public static ItemListAdapter adapter;
	
	//
	public static List<Integer> checkedPositions;
	
	public static List<Integer> toBuys;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		/*----------------------------
		 * Steps
		 * 1. Super
		 * 2. Set content
		 * 3. setList()
		 * 4. Set listener => Buttons
		 * 5. Instantiate vars
		----------------------------*/
		super.onCreate(savedInstanceState);

		//
		setContentView(R.layout.itemlist);

		setTitle(this.getClass().getName());
		
		/*----------------------------
		 * 3. setList()
			----------------------------*/
		setList();
		
		/*----------------------------
		 * 4. Set listener => Buttons
			----------------------------*/
		set_listener();
		
		/*----------------------------
		 * 5. Instantiate vars
			----------------------------*/
		checkedPositions = new ArrayList<Integer>();
		
		toBuys = new ArrayList<Integer>();
		
	}//public void onCreate(Bundle savedInstanceState)

	
	private void set_listener() {
		/*----------------------------
		 * Steps
		 * 1. Button => choose
		 * 2. Button => see_chosen
			----------------------------*/
		Button bt_choose = (Button) findViewById(R.id.itemlist_bt_choose);
		
		bt_choose.setTag(Methods.ButtonTags.itemlist_bt_choose);
		
		bt_choose.setOnTouchListener(new ButtonOnTouchListener(this));
		bt_choose.setOnClickListener(new ButtonOnClickListener(this));
		
		/*----------------------------
		 * 2. Button => see_chosen
			----------------------------*/
		Button bt_see_chosen = (Button) findViewById(R.id.itemlist_bt_see_chosen);
		
		bt_see_chosen.setTag(Methods.ButtonTags.itemlist_bt_see_chosen);
		
		bt_see_chosen.setOnTouchListener(new ButtonOnTouchListener(this));
		bt_see_chosen.setOnClickListener(new ButtonOnClickListener(this));
		
		
	}//private void set_listener()

	private void setList() {
		//
		list = new ArrayList<ShoppingItem>();
		
		//
		DBManager dbm = new DBManager(this);
		
		SQLiteDatabase db = dbm.getReadableDatabase();
		
		Cursor c = db.query(
										DBManager.tableName, 
//										DBManager.columns,
										DBManager.columns_with_index,
										null, null, null, null, null);
		
		//
		c.moveToFirst();
		
		for (int i = 0; i < c.getCount(); i++) {
			// Log
			Log.d("ItemList.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "c.getString(0) => " + c.getString(0));
			Log.d("ItemList.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "c.getString(1) => " + c.getString(1));
			Log.d("ItemList.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "c.getString(4) => " + c.getString(4));
			
			//
			ShoppingItem item = new ShoppingItem(
									c.getString(0),		// store
									c.getString(1),		// name
									c.getInt(2),			// price
									c.getString(3),		// genre
									c.getInt(4)				// id
									);
			
			//
			list.add(item);
			
			//
			c.moveToNext();
			
		}//for (int i = 0; i < c.getCount(); i++)
		
		//
		db.close();
		
		/*----------------------------
		 * 4. Set up adapter
			----------------------------*/
		//
		adapter = new ItemListAdapter(
							this,
							R.layout.adapteritem,
							list
							);
		
		//
		setListAdapter(adapter);
		
	}//private void setList()

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// 
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.itemlist, menu);
		
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO 自動生成されたメソッド・スタブ
		switch (item.getItemId()) {
		case R.id.menu_listitem_filter:

			Methods.dlg_filterList(this);
			
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

	@Override
	protected void onListItemClick(ListView lv, View v, int position, long id) {
		/*----------------------------
		 * Steps
		 * 1. super
		 * 2. checkedPositions
		 * 3. Notify adapter
			----------------------------*/
		
		super.onListItemClick(lv, v, position, id);
		
		ShoppingItem si = (ShoppingItem) lv.getItemAtPosition(position);
		
		// Log
		Log.d("ItemList.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "si.toString() => " + si.toString());
				+ "]", 
				"si.getName() => " + si.getName()+
				"/" + "id=" + si.getId());
		
		/*----------------------------
		 * 2. checkedPositions
			----------------------------*/
		if (toBuys.contains((Integer) position)) {
			
			toBuys.remove((Integer) position);
			
		} else if (checkedPositions.contains((Integer) position)) {

			checkedPositions.remove((Integer) position);
			
			// Log
			Log.d("ItemList.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Position removed => " + position + "(size=" + checkedPositions.size());
			
			
		} else {//if (checkedPositions.contains((Integer) position)

			checkedPositions.add((Integer) position);

			// Log
			Log.d("ItemList.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Position added => " + position + "(size=" + checkedPositions.size());

		}//if (checkedPositions.contains((Integer) position)
		
		/*----------------------------
		 * 3. Notify adapter
			----------------------------*/
		adapter.notifyDataSetChanged();
		
	}//protected void onListItemClick(ListView l, View v, int position, long id)
}
