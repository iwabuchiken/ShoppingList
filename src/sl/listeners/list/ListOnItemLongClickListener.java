package sl.listeners.list;

import sl.items.ShoppingItem;
import sl.utils.Methods_dlg;
import sl.utils.Tags;
import android.app.Activity;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;

public class ListOnItemLongClickListener implements OnItemLongClickListener {

	Activity actv;
	Vibrator vib;
	
	public ListOnItemLongClickListener(Activity actv) {
		//
		this.actv = actv;
		//
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);

	}

	public boolean onItemLongClick(AdapterView<?> parent, View v, int position,
			long id) {
		// TODO Auto-generated method stub
		Tags.ListTags tag = (Tags.ListTags) parent.getTag();
		
		switch (tag) {

		case tab_toBuyList:

			tab_toBuyList(parent, position);
			
			break;// case tab_toBuyList

		default:
			break;
		
		}//switch (item)

		return true;
	}

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

}
