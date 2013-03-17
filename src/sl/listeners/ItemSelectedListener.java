package sl.listeners;

import sl.main.R;
import sl.utils.Methods;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;

public class ItemSelectedListener implements OnItemSelectedListener {

	Activity actv;
	
	public ItemSelectedListener(Activity actv) {
		// TODO Auto-generated constructor stub
		this.actv = actv;
	}

	public void
	onItemSelected
	(AdapterView<?> parent, View v, int position, long id) {
		// TODO Auto-generated method stub
		String storeName = (String) parent.getItemAtPosition(position);

		Spinner spGenre = (Spinner) actv.findViewById(R.id.itemlist_tab1_sp_genre);
		
		String genreName = (String) spGenre.getSelectedItem();

		/***************************************
		 * Filter
		 ***************************************/
		Methods.filterList3(actv, storeName, genreName);
		
//		// Log
//		Log.d("ItemSelectedListener.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "selected=" + selected);
//		
////		Spinner spGenre = (Spinner) actv.findViewById(R.id.itemlist_tab1_sp_genre);
////		
////		String genre = (String) spGenre.getSelectedItem();
//		
//		// Log
//		Log.d("ItemSelectedListener.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "genre=" + genre);
	}//onItemSelected()

	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
