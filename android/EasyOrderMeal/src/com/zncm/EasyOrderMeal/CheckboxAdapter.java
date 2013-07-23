package com.zncm.EasyOrderMeal;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SimpleAdapter;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

public class CheckboxAdapter extends BaseAdapter {

	Context context;
	ArrayList<HashMap<String, Object>> listData;
	HashMap<Integer, Boolean> state = new HashMap<Integer, Boolean>();

	// 构造函数
	public CheckboxAdapter(Context context,
			ArrayList<HashMap<String, Object>> listData) {
		this.context = context;
		this.listData = listData;

		System.out.println("DDDDDDDDDDDDDDDDDDDDDDD");
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	// 重写View
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		LayoutInflater mInflater = LayoutInflater.from(context);
		convertView = mInflater.inflate(R.layout.list_menu_data, null);
		ImageView img = (ImageView) convertView
				.findViewById(R.id.list_menu_data_iv01);
		img.setBackgroundResource(Integer.parseInt(listData.get(position).get(
				"list_img")
				+ ""));
		TextView name = (TextView) convertView
				.findViewById(R.id.list_menu_data_tv01);
		name.setText((String) listData.get(position).get("list_name"));
		TextView price = (TextView) convertView
				.findViewById(R.id.list_menu_data_tv02);
		price.setText((String) listData.get(position).get("list_price"));
		TextView restaurant = (TextView) convertView
				.findViewById(R.id.list_menu_data_tv03);
		restaurant.setText((String) listData.get(position).get(
				"list_restaurant"));
		TextView restaurant_addr = (TextView) convertView
				.findViewById(R.id.list_menu_data_tv04);
		restaurant_addr.setText((String) listData.get(position).get(
				"list_restaurant_addr"));

		CheckBox check = (CheckBox) convertView
				.findViewById(R.id.list_menu_data_cb01);
		check.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					state.put(position, isChecked);
				} else {
					state.remove(position);
				}
			}
		});
		check.setChecked((state.get(position) == null ? false : true));
		return convertView;
	}
}