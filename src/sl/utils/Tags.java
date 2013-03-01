package sl.utils;

public class Tags {

	public static enum DialogTags {
		// dlg_generic
		dlg_generic_cancel, dlg_generic_dismiss,
		
		// dlg_register_store.xml
		dlg_register_store_ok, dlg_register_store_cancel,

		// dlg_input_empty.xml
		dlg_input_empty_btn_reenter, dlg_input_empty_btn_cancel,
		
		// dlg_reconfirm_store_name.xml
		dlg_reconfirm_store_name_btn_yes, dlg_reconfirm_store_name_btn_cancel,
		
		// dlg_register_genre.xml
		dlg_register_genre_register, dlg_register_genre_cancel,
		
		// dlg_reconfirm_genre_name.xml
		dlg_reconfirm_genre_name_btn_register, dlg_reconfirm_genre_name_btn_cancel,
		
		// dlg_create_table.xml
		dlg_create_table_create, dlg_create_table_cancel,

		// dlg_drop_table.xml
		dlg_drop_table_btn_cancel, dlg_drop_table,
		
		// dlg_confirm_drop_table.xml
		dlg_confirm_drop_table_btn_ok, dlg_confirm_drop_table_btn_cancel, dlg_confirm_drop_table,

		// dlg_filter_list.xml
		dlg_filter_list_ok, dlg_filter_list_cancel,
		dlg_filter_list_ok2, dlg_filter_list_cancel2,
		
		// dlg_register_main.xml
		dlg_register_main,
		
		// dlg_db_admin.xml
		dlg_db_admin_lv,
		
		// tabactv_tab2_lv
		dlg_tabactv_tab2_lv,
		
	}//public static enum DialogTags
	
	public static enum ButtonTags {
		// DBAdminActivity.java
		db_manager_activity_create_table, db_manager_activity_drop_table,

		// ShoppingList.java
		sl_main_bt_item_list, sl_main_bt_register, sl_main_bt_db,
		
		// itemlist.xml
		itemlist_bt_choose, itemlist_bt_see_chosen,
		
		// itemlist_tabs.xml
		itemlist_tabs_bt_choose,
		
	}//public static enum ButtonTags

	public static enum ViewNames {
		TV, BT,
	}
	
	public static enum ListTags {
		// TabActv.java
		tab_itemList, tab_toBuyList,
	}
	
}//public class Tags
