package sl.tasks;

import sl.utils.Methods_sl;
import android.app.Activity;
import android.os.AsyncTask;

public class TaskAudioTrack extends AsyncTask<String, Integer, Integer> {

	Activity actv;
	
	public TaskAudioTrack(Activity actv) {
		// TODO Auto-generated constructor stub
		this.actv = actv;
	}

	@Override
	protected Integer doInBackground(String... messages) {
		
		Methods_sl.playSound(actv);
		
		return null;
	}

	@Override
	protected void onCancelled() {
		// TODO Auto-generated method stub
		super.onCancelled();
	}

	@Override
	protected void onPostExecute(Integer result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}

}
