package sl.adapters;

import java.util.List;

import sl.items.ShoppingItem;
import sl.main.ItemListActv;
import sl.main.R;
import sl.main.R.id;
import sl.utils.CONS;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ItemListAdapter2 extends ArrayAdapter<ShoppingItem> {

	//
	private int resourceId; 
	
	public ItemListAdapter2(Context context, int textViewResourceId,
												List<ShoppingItem> list) {
		super(context, textViewResourceId, list);
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
		
		this.resourceId = textViewResourceId;
		
		
	}//public ItemListAdapter()

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		/*----------------------------
		 * Steps
		 * 1. Inflate
		 * 2. Get views
		 * 3. Get item
		 * 4. Set values
		 * 
		 * 5. Set background
			----------------------------*/
		
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resourceId, null);
        }

        ShoppingItem si = (ShoppingItem) getItem(position);

        getView__1_setupTextView(convertView, si);

//		/*----------------------------
//		 * 5. Set background
//			----------------------------*/
        
        getView__2_setupBackground(convertView, si, position);
        
//		if (ItemListActv.toBuys.contains((Integer) position)) {
//			
//			convertView.setBackgroundColor(Color.GREEN);
//			
//		} else if (ItemListActv.checkedPositions.contains((Integer) position)) {
//			
//			convertView.setBackgroundColor(Color.BLUE);
//			
//		} else {//if (ItemList.checkedPositions.contains((Integer) position))
//			
//			convertView.setBackgroundColor(Color.BLACK);
//			
//		}//if (ItemList.checkedPositions.contains((Integer) position))
		
		
		
		
		return convertView;
//		return super.getView(position, convertView, parent);
	}//public View getView(int position, View convertView, ViewGroup parent)

	private void
	getView__2_setupBackground(View convertView, ShoppingItem si, int position) {

		if (CONS.tab_checkedPositions.contains(new Integer(si.getId()))) {
			
			convertView.setBackgroundColor(Color.BLUE);
			
		} else {//if (CONS.)
			
			convertView.setBackgroundColor(Color.BLACK);
			
		}//if (CONS.)
		
		
//		if (ItemListActv.toBuys.contains((Integer) position)) {
//		
//			convertView.setBackgroundColor(Color.GREEN);
//			
//		} else if (ItemListActv.checkedPositions.contains((Integer) position)) {
//			
//			convertView.setBackgroundColor(Color.BLUE);
//			
//		} else {//if (ItemList.checkedPositions.contains((Integer) position))
//			
//			convertView.setBackgroundColor(Color.BLACK);
//			
//		}//if (ItemList.checkedPositions.contains((Integer) position))

	}//private void getView__2_setupBackground(View convertView, int position)

	private void getView__1_setupTextView(View convertView, ShoppingItem si) {
		// TODO Auto-generated method stub
        //
        TextView tv_item_name = 
        				(TextView) convertView.findViewById(R.id.adapteritem_tv_item_name);
        TextView tv_store = 
				(TextView) convertView.findViewById(R.id.adapteritem_tv_store);
        TextView tv_price = 
				(TextView) convertView.findViewById(R.id.adapteritem_tv_price);
        TextView tv_genre = 
				(TextView) convertView.findViewById(R.id.adapteritem_tv_genre);

		/*----------------------------
		 * 4. Set values
			----------------------------*/
		//
		tv_item_name.setText(si.getName());
		tv_store.setText(si.getStore());
//		tv_price.setText(si.getPrice());
		tv_price.setText(String.valueOf(si.getPrice()));
		
		tv_genre.setText("(" + si.getGenre() + ")");

	}//private void getView__1_setupTextView(View convertView)

}//public class ItemListAdapter extends ArrayAdapter<ShoppingItem>
