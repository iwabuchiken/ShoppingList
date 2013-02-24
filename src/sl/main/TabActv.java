package sl.main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.TabActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class TabActv extends TabActivity implements TabHost.TabContentFactory {	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemlist_tabs);
        
        /***************************************
		 * Tab host
		 ***************************************/
        //TabHostクラスのインスタンス生成
        TabHost tabHost = getTabHost();
        
        /***************************************
		 * First tab
		 ***************************************/
        // TabHostからTabSpecの生成
        TabSpec firstTab = tabHost.newTabSpec("First");
        // タブ部分に表示するテキストおよびアイコンのセット
        firstTab.setIndicator("firstTab", getResources().getDrawable(android.R.drawable.ic_menu_agenda));
        // タブ選択時に表示したいViewのセット
        firstTab.setContent(R.id.first_content);
        // タブをTabHostに追加
        tabHost.addTab(firstTab);
        
        /***************************************
		 * Second tab
		 ***************************************/
        TabSpec secondTab = tabHost.newTabSpec("Second");
        secondTab.setIndicator("secondTab", getResources().getDrawable(android.R.drawable.ic_media_next));
        secondTab.setContent(R.id.second_content);
        tabHost.addTab(secondTab);
        
//        //３つ目のタブを生成
//        TabSpec thirdTab = tabHost.newTabSpec("Third");
//        thirdTab.setIndicator("thirdTab", getResources().getDrawable(android.R.drawable.ic_menu_add));
//        thirdTab.setContent(this);
//        tabHost.addTab(thirdTab);
        
        setupListView_B22_v_1_1_b();
        
        //最初にカーソルを当てたいタブを指定
        tabHost.setCurrentTabByTag("First");
        
    }//public void onCreate(Bundle savedInstanceState)

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
