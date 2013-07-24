package com.zncm.EasyOrderMeal;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageViewAdapter extends BaseAdapter {

	Context context;
	ArrayList<HashMap<String, Object>> listData;

	public ImageViewAdapter(Context context,
			ArrayList<HashMap<String, Object>> listData) {
		this.context = context;
		this.listData = listData;
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

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		LayoutInflater mInflater = LayoutInflater.from(context);
		convertView = mInflater.inflate(R.layout.list_order_data, null);
		ImageView img = (ImageView) convertView
				.findViewById(R.id.list_order_data_iv01);
		img.setBackgroundResource(Integer.parseInt(listData.get(position).get(
				"list_img")
				+ ""));
		TextView name = (TextView) convertView
				.findViewById(R.id.list_order_data_tv01);
		name.setText((String) listData.get(position).get("list_name"));
		TextView price = (TextView) convertView
				.findViewById(R.id.list_order_data_tv02);
		price.setText((String) listData.get(position).get("list_price"));
		TextView restaurant = (TextView) convertView
				.findViewById(R.id.list_order_data_tv03);
		restaurant.setText((String) listData.get(position).get(
				"list_restaurant"));

		TextView score = (TextView) convertView
				.findViewById(R.id.list_order_data_tv04);
		score.setText((String) listData.get(position).get("list_score"));

		ImageView img_add = (ImageView) convertView
				.findViewById(R.id.list_order_data_iv02);
		img_add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				HashMap<String, Object> map = (HashMap<String, Object>) ImageViewAdapter.this
						.getItem(position);
				String list_count = map.get("list_count").toString();
				map.remove("list_count");
				map.put("list_count", (Integer.parseInt(list_count) + 1) + "");
				ImageViewAdapter.this.notifyDataSetChanged();
			}
		});
		EditText et01 = (EditText) convertView
				.findViewById(R.id.list_order_data_et01);
		et01.setText((String) listData.get(position).get("list_count"));
		ImageView img_reduce = (ImageView) convertView
				.findViewById(R.id.list_order_data_iv03);
		img_reduce.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				HashMap<String, Object> map = (HashMap<String, Object>) ImageViewAdapter.this
						.getItem(position);
				String list_count = map.get("list_count").toString();
				if (Integer.parseInt(list_count) > 0) {
					map.remove("list_count");
					map.put("list_count", (Integer.parseInt(list_count) - 1)
							+ "");
					ImageViewAdapter.this.notifyDataSetChanged();
				}

			}
		});
		return convertView;
	}
}