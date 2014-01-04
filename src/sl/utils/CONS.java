package sl.utils;

import java.util.List;

import sl.adapters.ItemListAdapter2;
import sl.adapters.ToBuyListAdapter;
import sl.items.ShoppingItem;

import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class CONS {

	/*********************************
	 * From: SLActv.java
	 *********************************/
	/*********************************
	 * Paths
	 *********************************/
	public static String dirName_ExternalStorage = "/mnt/sdcard-ext";

//	public static String dirPath_db = "/data/data/shoppinglist.main/databases";
	public static String dirPath_db = "/data/data/sl.main/databases";
	
	public static String fileName_db_backup_trunk = "shoppinglist_backup";

	public static String fileName_db_backup_ext = ".bk";

	public static String dirPath_db_backup = 
					dirName_ExternalStorage + "/ShoppingList_backup";
	
//	public static String[] registerItems = {"�i��", "�X��", "�W������"};
	
//	public static enum registerChoice = {
	public static enum registerChoice {
		items, stores, genres,
	};
			
	/*********************************
	 * List tags
	 *********************************/
	public static enum ListViewTags {
		tab1_long_click,
	};
	
	/*********************************
	 * Dialog button tags
	 *********************************/
	public static enum DialogButtonTags {
		tab1_delete_item_ok, generic_cancel_second_dialog,
	};
	
	
	/*********************************
	 * From: DBManager.java
	 *********************************/
	public static String tableName = "shopping_item";

//	public static String[] columns = {"store", "name", "price", "genre"};
	public static String[] columns =
					{"store", "name", "price", "genre", "yomi"};
	
	public static String[] columns_with_index = 
					{"store", "name", "price", "genre", android.provider.BaseColumns._ID};

	public static String[]
	columns_with_index2 = 
		{android.provider.BaseColumns._ID, "name", "yomi", "genre", "store", "price"};

	public static String[] columns_for_table_stores = 
					{"store_name", "memo"};
	
	public static String[] columns_for_table_stores_with_index = 
		{android.provider.BaseColumns._ID, "store_name", "memo"};

	public static String[] column_types_for_table_stores = 
												{"TEXT", "TEXT"};
	
	public static String[] columns_for_table_genres = 
										{"genre_name", "memo"};

	
	public static String[] columns_for_table_genres_with_index = 
		{android.provider.BaseColumns._ID, "genre_name", "memo"};

	public static String[] column_types_for_table_genres = 
												{"TEXT", "TEXT"};
		
	/*********************************
	 * DB
	 *********************************/
	public static String dbName = "sl";
	
	/*********************************
	 * Created at: 20130222_095606<br>
	 * 1. Number of columns automatically added 
	 * 		when the table gets created<br/>
	 * 2. For example, if you coded in such a way that
	 * 		the table id is automatically inserted, then
	 * 		you set this value at 1<br>
	 * 3. The name was formulated from "Number of <b>col</b>umns to<br>
	 * 		<b>add</b> <b>up</b> when extracting values from the cursor"
	 *********************************/
	public static int colAddUp = 1;
	
	/*********************************
	 * From: DBUtils.java (formerly, DBManager.java)
	 *********************************/
	// Name
//	public static final String name = "shopping_list.db";
	
	// Version
	static final int version = 1;
	
	// Factory
	public static final CursorFactory factory = null;

	/*********************************
	 * Constant values
	 *********************************/
	// Generic
	public static final int UNKNOWN_ERROR = -9;
	public static final int EXCEPTION = -10;

	// Generi: Exception
	public static final int EXCEPTION_SQL = -1;
	
	public static final int DB_DOESNT_EXIST = -1;
	public static final int DB_CANT_CREATE_FOLDER = -2;
	public static final int DB_BACKUP_SUCCESSFUL = 1;
	public static final int DB_FILE_COPY_EXCEPTION = -3;
	
	public static final int DB_UPDATE_SUCCESSFUL = 2;
	
	// Methods_sl.refactorDb_colPrice()
	public static final int CURSOR_NULL = -1;
	public static final int CURSOR_NO_ENTRY = -2;
	public static final int DATA_REFACTORING_FAILED = -3;
	public static final int DATA_REFACTORED = 1;

	// Get yomi
	public static final int GETYOMI_SUCCESSFUL = 1;
	public static final int GETYOMI_NO_ENTRY = 2;
	public static final int GETYOMI_FAILED = -1;

	// TabActv.java
	public static final int PREP_LIST_SUCCESSFUL = 1;
	public static final int PREP_LIST_FAILED = -1;

	/***************************************
	 * TabActv.java
	 ***************************************/
	/***************************************
	 * List: Ids
	 ***************************************/
//	public static List<Integer> tab_checkedPositions;
	public static List<Integer> tab_checkedItemIds;
	public static List<Integer> tab_toBuyItemIds;
	public static List<Integer> tab_boughtItemIds;
	
	/***************************************
	 * Adapters
	 ***************************************/
	public static ItemListAdapter2 adpItems;
	public static ToBuyListAdapter adpToBuys;
	
	/***************************************
	 * List: ShoppingItem
	 ***************************************/
	public static List<ShoppingItem> toBuyList;
	public static List<ShoppingItem> itemList;
	
	public static boolean bgm;
	
//	public static class VarTabActv {
//		
//		public static TabHost tabHost;
//		
//	}
	
	public static class DBAdmin {
		
		/*********************************
		 * Table names
		 *********************************/
		public static final String tname_stores	= "stores";
		
		public static final String tname_genres	= "genres";
		
		/*********************************
		 * 
		 *********************************/
//		created_at INTEGER, modified_at INTEGER,
		public static final
		String[] timeStamps = {"created_at", "modified_at"};
		
		public static final
		String tname_purchaseSchedule = "purchase_schedule";

		public static
		String[] col_purchaseSchedule =
				//	0				1			2		3		4
				{"store_name", "due_date", "amount", "memo", "items"};

		public static
		String[] col_purchaseSchedule_full =
				//	0				1			2		3		4
				{android.provider.BaseColumns._ID,	// 0
				"created_at", "modified_at",		// 1
				//	2			3			4		5		6
				"store_name", "due_date", "amount", "memo", "items"};

		public static
		String[] colTypes_purchaseSchedule =
				{"TEXT",		"INTEGER", "INTEGER", "TEXT", "TEXT"};

		/***************************************
		 * Query-related constant values
		 ***************************************/
		final static int DB_QUERY_FAILED = -1;
		final static int DB_QUERY_NO_ENTRY = -2;
		final static int DB_DATA_UPDATE_SUCCESSFUL = 1;
		
		final static int DB_DATA_UPDATE_FAILED = -3;
		
	}
	
	public static class MagicConstants {
		
//		public static int MODIFY_TAB2_LV_HEIGHT = 150;
		public static int MODIFY_TAB2_LV_HEIGHT = 300;
		
	}
	
	public static class TabActv {
		
		public static TabHost tabHost;
		public static TabSpec firstTab;
		public static TabSpec secondTab;
		
	}
	
	public static class HTTPData {
		
		public static String UrlPostSI
//				= "http://cosmos-jqm-1.herokuapp.com/items/new";
		= "http://cosmos-jqm-1.herokuapp.com/sl/items/new";
//		= "http://cosmos-jqm-1.herokuapp.com/sl/items/new_data_from_device";
//		= "http://cosmos-jqm-1.herokuapp.com/sl/items/new_data_from_device";
//		= "http://cosmos-jqm-1.herokuapp.com/sl/items/new";
//		= "http://cosmos-jqm-1.herokuapp.com/items/new";
//		= "http://cosmos-jqm-1.herokuapp.com/items";
		
		// http://cosmos-jqm-1.herokuapp.com/items/new
		
		public static String[] siKeys = {
						"item_store_id",	"item_name",
						"item_price",		"item_genre_id",
						"item_yomi",		"item_mobile_id",
//						"item[store_id]",	"item[name]",
//						"item[price]",		"item[genre_id]",
//						"item[yomi]",		"item[mobile_id]",
						};
		
		/*********************************
		 * Posting data => Types
		 *********************************/
		public static enum registerChoice {
			single_item,
		};
		
	}
	
	public static class ReturnValues {
		/*********************************
		 * No data: -1 ~
		 *********************************/
		public static int PostSI_NoSIList	= -1;
		
		public static int NoStoreData		= -2;
		
		public static int NoGenreData		= -3;
		
		/*********************************
		 * Operation failed: -10 ~
		 *********************************/
		public static int QueryFailed		= -10;
		
		public static int BuildJOBodyFailed	= -11;
		
		public static int BuildEntityFailed	= -12;
		
		public static int BuildHttpPostFailed	= -13;
		
		public static int HttpPostFailed	= -14;
		
		/*********************************
		 * Others: > 0, <= -90
		 *********************************/
		public static int OK				= 1;
		
		public static int NOP				= -90;
		
		public static int FAILED			= -91;
		
	}
	
}//public class CONS
