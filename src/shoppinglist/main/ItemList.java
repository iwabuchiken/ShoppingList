package shoppinglist.main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ItemList extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		/*----------------------------
		 * Steps
		 * 1. Super
		 * 2. Set content
		 * 3. Prepare data => List<ShoppingItem>
		----------------------------*/
		super.onCreate(savedInstanceState);

		//
		setContentView(R.layout.itemlist);

		// debug
		Toast.makeText(ItemList.this, "ItemList => Started", 2000).show();
		
		/*----------------------------
		 * 3. Prepare data => List<ShoppingItem>
			----------------------------*/
		//
		List<ShoppingItem> list = new ArrayList<ShoppingItem>();
		
		//
		DBManager dbm = new DBManager(this);
		
		SQLiteDatabase db = dbm.getReadableDatabase();
		
		Cursor c = db.query(
										DBManager.tableName, 
										DBManager.columns, 
										null, null, null, null, null);
		
		//
		c.moveToFirst();
		
		for (int i = 0; i < c.getCount(); i++) {
			// Log
			Log.d("ItemList.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "message" + c.getShort(0));
			
		}//for (int i = 0; i < c.getCount(); i++)
		
		//
		db.close();
		
	}//public void onCreate(Bundle savedInstanceState)

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPause() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onResume();
	}

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
}
