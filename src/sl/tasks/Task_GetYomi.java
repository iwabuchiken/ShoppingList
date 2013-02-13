package sl.tasks;

import sl.utils.CONS;
import sl.utils.Methods_sl;
import android.app.Activity;
import android.app.Dialog;
import android.os.AsyncTask;
import android.widget.Toast;

public class Task_GetYomi extends AsyncTask<String, Integer, Integer> {

	Activity actv;
	
	Dialog dlg;
	
	public Task_GetYomi(Activity actv) {
		// TODO Auto-generated constructor stub
		this.actv = actv;
	}

	public Task_GetYomi(Activity actv, Dialog dlg) {
		// TODO Auto-generated constructor stub
		this.actv = actv;
		
		this.dlg = dlg;
		
	}

	@Override
	protected Integer doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		
		int res = Methods_sl.getYomi(actv, dlg);

		return res;
		
//		return null;
	}

	@Override
	protected void onCancelled() {
		// TODO Auto-generated method stub
		super.onCancelled();
	}

	@Override
	protected void onPostExecute(Integer res) {
		// TODO Auto-generated method stub
		super.onPostExecute(res);
		
		switch (res) {
		case CONS.GETYOMI_SUCCESSFUL:
			
			// debug
			Toast.makeText(actv,
					"Get yomi => Done", Toast.LENGTH_LONG).show();
			
			break;
			
		case CONS.GETYOMI_FAILED:

			// debug
			Toast.makeText(actv,
					"Get yomi => Failed", Toast.LENGTH_LONG).show();

			break;
			
		default:
			break;
		}//switch (res)
		
	}//protected void onPostExecute(Integer res)

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

}
