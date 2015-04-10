package com.zncm.EasyOrderMeal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.zncm.EasyOrderMeal.Json.JsonUtils;

public class RestaurantCategoryActivity extends Activity {

	ImageView iv01 = null;
	GridView gv01 = null;
	List<Map<String, Object>> list01 = new ArrayList<Map<String, Object>>();
	HashMap<String, Object> map = null;
	SimpleAdapter simpleAdapter = null;
	int merchant_stars = 0;
	String merchant_stars_str = "";
	int tb_menu_stars = 0;
	String tb_merchant_stars = "";
	Bundle bundle = null;
	String merchant_name = "";

	String list_id = "";

	JSONArray jsonArray = null;
	JSONArray jsonArray2 = null;
	JSONObject jsonObject = null;
	JsonUtils jsonUtils = new JsonUtils();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		initMain();

	}

	private void initMain() {
		setContentView(R.layout.restaurant_category);

		iv01 = (ImageView) findViewById(R.id.restaurant_category_iv01);
		iv01.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(RestaurantCategoryActivity.this,
						InitMainActivity.class);
				startActivity(intent);
				finish();

			}
		});

		gv01 = (GridView) findViewById(R.id.restaurant_category_gv01);

		try {
			jsonArray = jsonUtils.GetInfo("menu/getmer");
			if (jsonArray.length() == Constants.ERROR_CODE) {
				System.out.println("ERROR");
			}
			for (int i = 0; i < jsonArray.length(); i++) {
				map = new HashMap<String, Object>();
				map.put("list_id0", (i + 1) + "");
				map.put("list_id",
						jsonArray.getJSONObject(i).get("tb_merchant_id"));

				switch (Math.round(Float.parseFloat((String) jsonArray
						.getJSONObject(i).get("tb_merchant_vote")))) {
				case 0:
					tb_merchant_stars = "☆☆☆☆☆";
					break;
				case 1:
					tb_merchant_stars = "★☆☆☆☆";
					break;
				case 2:
					tb_merchant_stars = "★★☆☆☆";
					break;
				case 3:
					tb_merchant_stars = "★★★☆☆";
					break;
				case 4:
					tb_merchant_stars = "★★★★☆";
					break;
				case 5:
					tb_merchant_stars = "★★★★★";
					break;
				}
				map.put("tb_merchant_stars", tb_merchant_stars);

				map.put("tb_merchant_name",
						jsonArray.getJSONObject(i).get("tb_merchant_name"));

				System.out.println("name "
						+ jsonArray.getJSONObject(i).get("tb_merchant_name"));

				map.put("selected", false);

				this.list01.add(map);

			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.simpleAdapter = new SimpleAdapter(RestaurantCategoryActivity.this,
				list01, R.layout.restaurant_category_item, new String[] {
						"tb_merchant_name", "tb_merchant_stars" }, new int[] {
						R.id.restaurant_category_item_tv01,
						R.id.restaurant_category_item_tv02 });
		gv01.setAdapter(simpleAdapter);

		gv01.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				Map<String, String> map = (Map<String, String>) RestaurantCategoryActivity.this.simpleAdapter
						.getItem(position);

				list_id = map.get("list_id").toString();
				merchant_name = map.get("tb_merchant_name").toString();

				Intent intent = new Intent(RestaurantCategoryActivity.this,
						ListMenuActivity.class);
				bundle = new Bundle();
				bundle.putString("init_type", "3");
				bundle.putString("merchant_id", list_id);
				bundle.putString("merchant_name", merchant_name);
				intent.putExtras(bundle);
				startActivity(intent);
				finish();

			}
		});

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent(RestaurantCategoryActivity.this,
					InitMainActivity.class);
			startActivity(intent);
			finish();
		}
		return false;
	}

}
