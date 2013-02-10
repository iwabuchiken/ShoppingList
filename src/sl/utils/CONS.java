package sl.utils;

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
	
//	public static String[] registerItems = {"•i•¨", "“X•Ü", "ƒWƒƒƒ“ƒ‹"};
	
//	public static enum registerChoice = {
	public static enum registerChoice {
		items, stores, genres,
	};
			
	/*********************************
	 * From: DBManager.java
	 *********************************/
	public static String tableName = "shopping_item";

	public static String[] columns = {"store", "name", "price", "genre"};
	public static String[] columns_with_index = 
					{"store", "name", "price", "genre", android.provider.BaseColumns._ID};

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
		
}//public class CONS
