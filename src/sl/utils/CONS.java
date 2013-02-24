package sl.utils;

import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.widget.TabHost;

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
	
	
//	public static class VarTabActv {
//		
//		public static TabHost tabHost;
//		
//	}
}//public class CONS
