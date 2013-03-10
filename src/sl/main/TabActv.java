package sl.main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import sl.adapters.ItemListAdapter;
import sl.adapters.ItemListAdapter2;
import sl.adapters.ToBuyListAdapter;
import sl.items.ShoppingItem;
import sl.listeners.ButtonOnClickListener;
import sl.listeners.ButtonOnTouchListener;
import sl.listeners.list.ListOnItemClickListener;
import sl.listeners.list.ListOnItemLongClickListener;
import sl.utils.CONS;
import sl.utils.DBUtils;
import sl.utils.Methods;
import sl.utils.Methods_dlg;
import sl.utils.Methods_sl;
import sl.utils.Tags;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextPaint;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class TabActv extends TabActivity implements TabHost.TabContentFactory {
	
	TabHost tabHost;
	TabSpec firstTab;
	TabSpec secondTab;

	ListView lvTab1;
	ListView lvTab2;
	
//	ArrayAdapter<String> adpTab1;
//	ArrayAdapter<String> adpTab2;

//	ItemListAdapter2 adpItems;
	
//	List<ShoppingItem> itemList;
	
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemlist_tabs);
        
        setTitle(this.getClass().getName());
        
        initVars();
        
        setupTabs();
        
        setupItemListView();
        
        setupToBuyListView();
        
        setupListeners();
        
        //debug
        int[] size = Methods.getDisplaySize(this);
        
        // Log
		Log.d("TabActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]",
				"window size: width=" + size[0]
				+ "/"
				+ "window size: height=" + size[1]);
         
    }//public void onCreate(Bundle savedInstanceState)

    private void setupListeners() {
		// TODO Auto-generated method stub
		/***************************************
		 * Set listener: lvTab1
		 ***************************************/
		lvTab1.setTag(Tags.ListTags.tab_itemList);
		
		lvTab1.setOnItemClickListener(new ListOnItemClickListener(this));

		/***************************************
		 * Set listener: ImageButton
		 ***************************************/
		ImageButton ib_tab1Choose = (ImageButton) findViewById(R.id.itemlist_tab1_ib);
		
		ib_tab1Choose.setTag(Tags.ButtonTags.itemlist_tabs_bt_choose);
		
		ib_tab1Choose.setOnClickListener(new ButtonOnClickListener(this));
		
		ib_tab1Choose.setOnTouchListener(new ButtonOnTouchListener(this));
		
		/***************************************
		 * Set listener: lvTab2
		 ***************************************/
		/***************************************
		 * Listener: Item click
		 ***************************************/
		lvTab2.setTag(Tags.ListTags.tab_toBuyList);
		
		lvTab2.setOnItemClickListener(new ListOnItemClickListener(this));
		
		/***************************************
		 * Listener: Item long click
		 ***************************************/
		lvTab2.setOnItemLongClickListener(new ListOnItemLongClickListener(this));
		
	}//private void setupListeners()

	private void initVars() {
		// TODO Auto-generated method stub
		CONS.tab_checkedItemIds = new ArrayList<Integer>();
		CONS.tab_toBuyItemIds = new ArrayList<Integer>();
		CONS.tab_boughtItemIds = new ArrayList<Integer>();
		
		/***************************************
		 * Get preference value: bgm
		 ***************************************/
		SharedPreferences prefs = this
				.getSharedPreferences(
					this.getString(R.string.shared_preferences_name),
					Context.MODE_PRIVATE);

//		boolean bgm = prefs.getBoolean(actv.getString(R.string.prefs_key_bgm), false);
		CONS.bgm = prefs.getBoolean(this.getString(R.string.prefs_key_bgm), false);
		
		// Log
		Log.d("MainActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "bgm=" + CONS.bgm);

	}//private void initVars()

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
//        firstTab = tabHost.newTabSpec("First");
        firstTab = tabHost.newTabSpec(this.getString(R.string.tabactv_tabtags_first));
        
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
//        secondTab = tabHost.newTabSpec("Second");
//        secondTab.setIndicator("secondTab", getResources().getDrawable(android.R.drawable.ic_media_next));
        
        secondTab = tabHost.newTabSpec(this.getString(R.string.tabactv_tabtags_second));
        
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

        /***************************************
		 * Set size to views: Tab2
		 ***************************************/
        ListView lvTab2ToBuyList = (ListView) this.findViewById(R.id.itemlist_tab2_lv);
        TextView tvTab2DueDate = (TextView) this.findViewById(R.id.itemlist_tab2_tv_due_date);
        
        // Window height
//        int windowHeight = Methods.getDisplaySize(this)[0];
        int windowHeight = Methods.getDisplaySize(this)[1];
        
        // TextView height
        int tvTab2DueDate_Height = tvTab2DueDate.getHeight();
//        
//        lvTab2ToBuyList.setLayoutParams(new LinearLayout.LayoutParams(
//				LayoutParams.MATCH_PARENT,	// Width
////				300));
//				windowHeight - tvTab2DueDate_Height));		// Height
//        
//        // Log
//		Log.d("TabActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]",
//				"windowHeight=" + windowHeight
//				+ "/"
//				+ "tvTab2DueDate_Height=" + tvTab2DueDate_Height);

//		// Log
//		Log.d("TabActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]",
//				"tvTab2DueDate=" + tvTab2DueDate.getLineCount());
		
		/***************************************
		 * Get text view height
		 ***************************************/
		// REF=> http://stackoverflow.com/questions/4912687/android-get-the-height-of-the-textview	(answered Feb 6 '11 at 14:03)
		// REF=> http://stackoverflow.com/questions/3630086/how-to-get-string-width-on-android	(answered Sep 2 '10 at 19:05)
		Rect bounds = new Rect();
//		String s = "abcde";
		String s = tvTab2DueDate.getText().toString();
		TextPaint p = tvTab2DueDate.getPaint();
		p.getTextBounds(s, 0, s.length(), bounds);
//		
//		// Log
//		Log.d("TabActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "bounds.height()=" + bounds.height());
//		
		int textHeight = bounds.height();

//		lvTab2ToBuyList.setLayoutParams(new LinearLayout.LayoutParams(
//					LayoutParams.MATCH_PARENT,	// Width
//		//				300));
//					windowHeight - (textHeight + 300)));		// Height

//      // Log
//		Log.d("TabActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]",
//				"windowHeight=" + windowHeight
//				+ "/"
//				+ "windowHeight - (textHeight + 10)="
//				+ (windowHeight - (textHeight + 300)));
		
//		// Log
//		Log.d("TabActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]",
//				"tabHost.getTabWidget().getMeasuredHeight()="
//				+ tabHost.getTabWidget().getMeasuredHeight()
//				+ "/"
//				+ "tabHost.getTabWidget().getHeight()="
//				+ tabHost.getTabWidget().getHeight()
//				+ "/"
//				+ "tabHost.getTabWidget().getChildAt(1).getBottom()="
//				+ tabHost.getTabWidget().getChildAt(1).getBottom()
//				//REF=> http://stackoverflow.com/questions/2502800/tabwidget-height	(answered Jun 15 '11 at 15:16)
//				+ "tabHost.getTabWidget().getChildAt(1).getLayoutParams().height="
//				+ tabHost.getTabWidget().getChildAt(1).getLayoutParams().height);
		
		// Log
		int widgetHeight = tabHost.getTabWidget().getChildAt(1).getLayoutParams().height;

//		Log.d("TabActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]",
//				"(windowHeight - (textHeight + widgetHeight))="
//				+ (windowHeight - (textHeight + widgetHeight)));

		lvTab2ToBuyList.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT,	// Width
	//				300));
//				(windowHeight - (textHeight + widgetHeight + 150))));		// Height
				(windowHeight - (textHeight + widgetHeight
									+ CONS.MagicConstants.MODIFY_TAB2_LV_HEIGHT))));		// Height

		
//		// Log
//		Log.d("TabActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "tabHost.getTabContentView().getHeight()="
//				+ tabHost.getTabContentView().getHeight());
//		
//		// Log
//		Log.d("TabActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "tabHost.getTabContentView().getLayoutParams().height="
//				+ tabHost.getTabContentView().getLayoutParams().height);
		
		/***************************************
		 * Set default due date
		 ***************************************/
		tvTab2DueDate.setText(Methods.getTimeLabel_Japanese(Methods.getMillSeconds_now()));

        /***************************************
		 * Set current tab
		 ***************************************/
        //最初にカーソルを当てたいタブを指定
        tabHost.setCurrentTabByTag("First");
        
        
        
	}//private void setupTabs()

	private void setupItemListView() {
		
		int numOfEntries = 30;
		
		/***************************************
		 * Prepare list
		 ***************************************/
		int res = prepareItemList();
		
		/***************************************
		 * List in the tab 1
		 ***************************************/
		if (res == CONS.PREP_LIST_SUCCESSFUL) {

			lvTab1 = (ListView) findViewById(R.id.itemlist_tab1_lv);
			
			List<String> listTab1 = new ArrayList<String>();

			
			
			for (int i = 1; i < numOfEntries; i++) {
				
				listTab1.add("Number: " + i);
				
			}//for (int i = 1; i < 11; i++)
			
			/***************************************
			 * Adapter
			 ***************************************/

			CONS.adpItems = new ItemListAdapter2(
					this,
					R.layout.adapteritem,
					CONS.itemList
					);
			
//			lvTab1.setAdapter(adpTab1);
			lvTab1.setAdapter(CONS.adpItems);

			// Log
			Log.d("TabActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "itemList.size()=" + CONS.itemList.size());
	
		} else {//if (res == CONS.PREP_LIST_SUCCESSFUL)
			
			// Log
			Log.d("TabActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Prep item list => Failed");
			
		}//if (res == CONS.PREP_LIST_SUCCESSFUL)

	}//private void setupListView()

	private void setupToBuyListView() {
		
		/***************************************
		 * List in the tab 2
		 ***************************************/
		int res = prepareToBuyList();
		
		// Log
		Log.d("TabActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "res=" + res);
		
		if (res == CONS.PREP_LIST_SUCCESSFUL) {
			lvTab2 = (ListView) findViewById(R.id.itemlist_tab2_lv);
			
//			List<String> listTab2 = new ArrayList<String>();
			
//			for (int i = 1; i < numOfEntries; i++) {
//				
//				listTab2.add("番号: " + i);
//				
//			}//for (int i = 1; i < 11; i++)
			
	//		ArrayAdapter<String> adpTab2 = new ArrayAdapter<String>(
			
//			CONS.adpToBuys = new ItemListAdapter2(
			CONS.adpToBuys = new ToBuyListAdapter(
					this,
	//				android.R.layout.simple_list_item_1,
					R.layout.adapteritem,
					CONS.toBuyList
					);
			
		} else {//if (res == CONS.PREP_LIST_SUCCESSFUL)
			
			// Log
			Log.d("TabActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Prep toBuy list => Failed");
			
		}//if (res == CONS.PREP_LIST_SUCCESSFUL)

//		lvTab2.setAdapter(adpTab2);
		lvTab2.setAdapter(CONS.adpToBuys);

		// Log
		Log.d("TabActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "CONS.adpToBuys => Set");
		
	}//private void setupToBuyListView()

	private int prepareItemList() {
		/***************************************
		 * itemList
		 ***************************************/
		CONS.itemList = new ArrayList<ShoppingItem>();
		
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
			CONS.itemList.add(item);
			
			//
			c.moveToNext();
			
		}//for (int i = 0; i < c.getCount(); i++)
		
		//
		rdb.close();

		/***************************************
		 * Sort list
		 ***************************************/
		Methods_sl.sortItemList(CONS.itemList);
		
		/***************************************
		 * Return
		 ***************************************/
		return CONS.PREP_LIST_SUCCESSFUL;
		
	}//private void prepareItemList()

	private int prepareToBuyList() {
		/***************************************
		 * itemList
		 ***************************************/
		CONS.toBuyList = new ArrayList<ShoppingItem>();
		
		/***************************************
		 * Setup db
		 ***************************************/
		DBUtils dbm = new DBUtils(this);
		
		SQLiteDatabase rdb = dbm.getReadableDatabase();
		
		Cursor c = null;
		
		for (Integer itemId : CONS.tab_toBuyItemIds) {
			
			try {
				
				c = rdb.query(
						CONS.tableName, 
	//										DBManager.columns,
	//				CONS.columns_with_index,
						CONS.columns_with_index2,
						String.valueOf(CONS.columns_with_index2[0]),
						new String[]{String.valueOf(itemId.intValue())},
						null, null, null);
				
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

			/***************************************
			 * If the cursor is null, then move on to
			 * 	the next id
			 ***************************************/
			if (c == null) {
				
				// Log
				Log.d("TabActv.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]",
						"c==null => id=" + itemId.intValue());
				
				continue;
				
			}//if (c == null)
			
			/***************************************
			 * If no result, then also, move on to
			 * 	the next
			 ***************************************/
			if (c.getCount() < 1) {
				
				// Log
				Log.d("TabActv.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]",
						"c.getCount() < 1 => id=" + itemId.intValue());
				
				continue;
				
			}//if (c.getCount() < 1)
			
			
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
				CONS.toBuyList.add(item);
				
				//
				c.moveToNext();
				
			}//for (int i = 0; i < c.getCount(); i++)

		}//for (Integer itemId : CONS.tab_toBuyItemIds)

		//
		rdb.close();

		/***************************************
		 * Sort list
		 ***************************************/
//		Methods_sl.sortItemList(itemList);
		Methods_sl.sortItemList(CONS.toBuyList);
		
		//debug
		//debug
		StringBuilder sb = new StringBuilder();
		
		for (ShoppingItem item : CONS.toBuyList) {
			
			sb.append(item.getId());
			
			sb.append(",");
			
		}
		
		// Log
		Log.d("ButtonOnClickListener.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "CONS.toBuyList=" + sb.toString());
		
		/***************************************
		 * Return
		 ***************************************/
		return CONS.PREP_LIST_SUCCESSFUL;
		
	}//private int prepareToBuyList()

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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		String tabTag = tabHost.getCurrentTabTag();

//		/***************************************
//		 * Switch the menu file using tab tag
//		 ***************************************/
//		if (tabTag.equals(this.getString(R.string.tabactv_tabtags_first))) {
//			
//			MenuInflater mi = getMenuInflater();
//			mi.inflate(R.menu.itemlist, menu);
//			
//		} else {//if (tabTag.equals(this.getString(R.string.tabactv_tabtags_first)))
//			
//		}//if (tabTag.equals(this.getString(R.string.tabactv_tabtags_first)))

		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.itemlist, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.menu_listitem_filter:

			String tabTag = tabHost.getCurrentTabTag();
			
			if (tabTag.equals(this.getString(R.string.tabactv_tabtags_first))) {
				
				Methods.dlg_filterList2(this);
				
			} else {//if (tabTag.equals(this.getString(R.string.tabactv_tabtags_first)))
				
			}//if (tabTag.equals(this.getString(R.string.tabactv_tabtags_first)))
			
			
//			// Log
//			Log.d("TabActv.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "tabHost.getCurrentTabTag()=" + tabHost.getCurrentTabTag());
			
//			Methods.dlg_filterList2(this);
			
			break;

		case R.id.menu_listitem_tabToBuy_clear_selections:

			Methods_dlg.dlg_tabActv_clearSelections(this);
			
//			CONS.toBuyList.clear();
//			CONS.tab_toBuyItemIds.clear();
//			
//			CONS.adpItems.notifyDataSetChanged();
//			CONS.adpToBuys.notifyDataSetChanged();
			
			break;
			
		case R.id.menu_listitem_tabToBuy_admin_db:
			
			Methods_dlg.dlg_tabActv_adminDb(this);
			
			break;// case R.id.menu_listitem_tabToBuy_admin_db
			
		default:
			break;
		}//switch (item.getItemId())

		return super.onOptionsItemSelected(item);
	}

}//public class TabActv extends TabActivity implements TabHost.TabContentFactory
