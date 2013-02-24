package sl.main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import sl.items.ShoppingItem;
import sl.utils.CONS;
import sl.utils.DBUtils;

import android.app.Activity;
import android.app.TabActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class TabActv extends TabActivity implements TabHost.TabContentFactory {
	
	TabHost tabHost;
	TabSpec firstTab;
	TabSpec secondTab;

	List<ShoppingItem> itemList;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemlist_tabs);
        
        setupTabs();
        
        setupListView();
        
    }//public void onCreate(Bundle savedInstanceState)

    private void setupTabs() {
		// TODO Auto-generated method stub
        /***************************************
		 * Tab host
		 ***************************************/
        //TabHostクラスのインスタンス生成
//        TabHost tabHost = getTabHost();
        tabHost = getTabHost();
        
        /***************************************
		 * First tab
		 ***************************************/
        // TabHostからTabSpecの生成
//        TabSpec firstTab = tabHost.newTabSpec("First");
        firstTab = tabHost.newTabSpec("First");
        // タブ部分に表示するテキストおよびアイコンのセット
//        firstTab.setIndicator("firstTab", getResources().getDrawable(android.R.drawable.ic_menu_agenda));
        firstTab.setIndicator(
        		"",
        		getResources().getDrawable(R.drawable.sl_tab_itemlist));
        
        // タブ選択時に表示したいViewのセット
        firstTab.setContent(R.id.first_content);
        // タブをTabHostに追加
        tabHost.addTab(firstTab);
        
        /***************************************
		 * Second tab
		 ***************************************/
//        TabSpec secondTab = tabHost.newTabSpec("Second");
        secondTab = tabHost.newTabSpec("Second");
//        secondTab.setIndicator("secondTab", getResources().getDrawable(android.R.drawable.ic_media_next));
        
        secondTab.setIndicator(
        		"",
        		getResources().getDrawable(R.drawable.sl_basket));

        secondTab.setContent(R.id.second_content);
        
        tabHost.addTab(secondTab);
        
//        //３つ目のタブを生成
//        TabSpec thirdTab = tabHost.newTabSpec("Third");
//        thirdTab.setIndicator("thirdTab", getResources().getDrawable(android.R.drawable.ic_menu_add));
//        thirdTab.setContent(this);
//        tabHost.addTab(thirdTab);

        //最初にカーソルを当てたいタブを指定
        tabHost.setCurrentTabByTag("First");
        
	}//private void setupTabs()

	private void setupListView() {
		
		int numOfEntries = 30;
		
		/***************************************
		 * Prepare list
		 ***************************************/
		int res = prepareItemList();

		
		/***************************************
		 * List in the tab 1
		 ***************************************/
		if (res == CONS.PREP_LIST_SUCCESSFUL) {

			ListView lvTab1 = (ListView) findViewById(R.id.itemlist_tab1_lv);
			
			List<String> listTab1 = new ArrayList<String>();

			
			
			for (int i = 1; i < numOfEntries; i++) {
				
				listTab1.add("Number: " + i);
				
			}//for (int i = 1; i < 11; i++)
			
			/***************************************
			 * Adapter
			 ***************************************/
			ArrayAdapter<String> adpTab1 = new ArrayAdapter<String>(
					this,
					android.R.layout.simple_list_item_1,
					listTab1
					);
			
			lvTab1.setAdapter(adpTab1);

			// Log
			Log.d("TabActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "itemList.size()=" + itemList.size());
			
		} else {//if (res == CONS.PREP_LIST_SUCCESSFUL)
			
			// Log
			Log.d("TabActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Prep list => Failed");
			
		}//if (res == CONS.PREP_LIST_SUCCESSFUL)
//		ListView lvTab1 = (ListView) findViewById(R.id.itemlist_tab1_lv);
//		
//		List<String> listTab1 = new ArrayList<String>();
//
//		
//		
//		for (int i = 1; i < numOfEntries; i++) {
//			
//			listTab1.add("Number: " + i);
//			
//		}//for (int i = 1; i < 11; i++)
//		
//		ArrayAdapter<String> adpTab1 = new ArrayAdapter<String>(
//				this,
//				android.R.layout.simple_list_item_1,
//				listTab1
//				);
//		
//		lvTab1.setAdapter(adpTab1);

		/***************************************
		 * List in the tab 2
		 ***************************************/
		ListView lvTab2 = (ListView) findViewById(R.id.itemlist_tab2_lv);
		
		List<String> listTab2 = new ArrayList<String>();
		
		for (int i = 1; i < numOfEntries; i++) {
			
			listTab2.add("番号: " + i);
			
		}//for (int i = 1; i < 11; i++)
		
		ArrayAdapter<String> adpTab2 = new ArrayAdapter<String>(
				this,
				android.R.layout.simple_list_item_1,
				listTab2
				);
		
		lvTab2.setAdapter(adpTab2);

	}//private void setupListView()

	private int prepareItemList() {
		// TODO Auto-generated method stub
		itemList = new ArrayList<ShoppingItem>();
		
		//
		DBUtils dbm = new DBUtils(this);
		
		SQLiteDatabase rdb = dbm.getReadableDatabase();
		
		Cursor c = null;
		
		try {
			c = rdb.query(
					CONS.tableName, 
//										DBManager.columns,
//				CONS.columns_with_index,
					CONS.columns_with_index2,
											null, null, null, null, null);
		} catch (Exception e) {
			
			// Log
			Log.e("TabActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			rdb.close();
			
			return CONS.PREP_LIST_FAILED;
			
		}//try
		
		//
		c.moveToFirst();
		
		for (int i = 0; i < c.getCount(); i++) {

//			0									1		2		3		4			5
//			{android.provider.BaseColumns._ID, "name", "yomi", "genre", "store", "price"}
			ShoppingItem item = new ShoppingItem(
					c.getInt(0),		// id store
					c.getString(1),		// name
					c.getString(2),		// yomi
					c.getString(3),		// genre
					c.getString(4),		//	store
					c.getInt(5)			// price
					);
			
			//
			itemList.add(item);
			
			//
			c.moveToNext();
			
		}//for (int i = 0; i < c.getCount(); i++)
		
		//
		rdb.close();

		/***************************************
		 * Return
		 ***************************************/
		return CONS.PREP_LIST_SUCCESSFUL;
		
	}//private void prepareItemList()

	private void setupListView_B22_v_1_1_b() {
		// TODO Auto-generated method stub
		ListView lv = (ListView) findViewById(R.id.itemlist_tab2_lv);
		
		List<String> list = new ArrayList<String>();
		
		for (int i = 1; i < 11; i++) {
			
			list.add("Number: " + i);
			
		}//for (int i = 1; i < 11; i++)
		
		ArrayAdapter<String> adp = new ArrayAdapter<String>(
				this,
				android.R.layout.simple_list_item_1,
				list
				);
		
		lv.setAdapter(adp);
		
	}//private void setupListView_B22_v_1_1_b()

		//		@Override
	public View createTabContent(String tag) {
			Time time = new Time("Asia/Tokyo");
			time.setToNow();
			String date = time.year + "年" + (time.month+1) + "月" + time.monthDay + "日" + time.hour + "時" + time.minute + "分" + time.second + "秒";					
			TextView textView = new TextView(this);
			textView.setText(date);			
			return textView;
		}

}//public class TabActv extends TabActivity implements TabHost.TabContentFactory
