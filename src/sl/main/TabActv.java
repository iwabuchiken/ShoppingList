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
	
	TabHost tabHost;
	TabSpec firstTab;
	TabSpec secondTab;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemlist_tabs);
        
        setupTabs();
        
        setupListView();
        
    }//public void onCreate(Bundle savedInstanceState)

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
        firstTab = tabHost.newTabSpec("First");
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
        secondTab = tabHost.newTabSpec("Second");
//        secondTab.setIndicator("secondTab", getResources().getDrawable(android.R.drawable.ic_media_next));
        
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

        //最初にカーソルを当てたいタブを指定
        tabHost.setCurrentTabByTag("First");
        
	}//private void setupTabs()

	private void setupListView() {
		
		int numOfEntries = 30;
		
		/***************************************
		 * List in the tab 1
		 ***************************************/
		ListView lvTab1 = (ListView) findViewById(R.id.itemlist_tab1_lv);
		
		List<String> listTab1 = new ArrayList<String>();
		
		for (int i = 1; i < numOfEntries; i++) {
			
			listTab1.add("Number: " + i);
			
		}//for (int i = 1; i < 11; i++)
		
		ArrayAdapter<String> adpTab1 = new ArrayAdapter<String>(
				this,
				android.R.layout.simple_list_item_1,
				listTab1
				);
		
		lvTab1.setAdapter(adpTab1);

		/***************************************
		 * List in the tab 2
		 ***************************************/
		ListView lvTab2 = (ListView) findViewById(R.id.itemlist_tab2_lv);
		
		List<String> listTab2 = new ArrayList<String>();
		
		for (int i = 1; i < numOfEntries; i++) {
			
			listTab2.add("番号: " + i);
			
		}//for (int i = 1; i < 11; i++)
		
		ArrayAdapter<String> adpTab2 = new ArrayAdapter<String>(
				this,
				android.R.layout.simple_list_item_1,
				listTab2
				);
		
		lvTab2.setAdapter(adpTab2);

	}//private void setupListView()

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
