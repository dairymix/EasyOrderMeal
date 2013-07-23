package com.zncm.EasyOrderMeal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.zncm.EasyOrderMeal.Json.JsonUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class PersonInfoActivity extends Activity {
	ImageView iv01 = null;
	ImageView iv02 = null;

	SharedPreferences sp = null;
	String user_pwd_sp = "";
	String pwd = "";
	String pwd1 = "";

	String user_id_sp = "";
	String user_account_sp = "";

	ListView lv01 = null;
	List<Map<String, Object>> list01 = new ArrayList<Map<String, Object>>();
	HashMap<String, Object> map = null;

	SimpleAdapter simpleAdapter = null;

	JSONArray jsonArray = null;
	JSONArray jsonArray2 = null;
	JSONObject jsonObject = null;
	JsonUtils jsonUtils = new JsonUtils();

	String list_id = "";
	String list_menu = "";
	String list_desc = "";
	String list_price = "";
	String list_score = "";
	String list_category = "";
	String list_restaurant = "";
	String list_restaurant_addr = "";
	String list_restaurant_phone = "";
	String list_spend = "";
	int tb_menu_stars = 0;
	String tb_menu_stars_str = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initMain();

	}

	private void initMain() {
		sp = getSharedPreferences("EasyOrderMeal_SP", MODE_PRIVATE);
		user_id_sp = sp.getString("user_id_sp", "0");
		user_account_sp = sp.getString("user_account_sp", "0");
		System.out.println("user_id_sp: " + user_id_sp);
		System.out.println("user_account_sp: " + user_account_sp);
		setContentView(R.layout.person_info);
		iv01 = (ImageView) findViewById(R.id.person_info_iv01);
		iv02 = (ImageView) findViewById(R.id.person_info_iv02);

		lv01 = (ListView) findViewById(R.id.person_info_lv01);

		sp = getSharedPreferences("EasyOrderMeal_SP", MODE_PRIVATE);

		iv01.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(PersonInfoActivity.this,
						InitMainActivity.class);
				startActivity(intent);
				finish();
			}
		});

		iv02.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				UpdatePWD();
			}

		});

		try {
			jsonArray = jsonUtils.GetInfo("order/getorderbyuser?id="
					+ user_id_sp + "&page=" + 1);

			jsonArray2 = jsonUtils.GetInfo("order/getpagebyuser?id="
					+ user_id_sp);
			if (jsonArray.length() == Constants.ERROR_CODE) {
				System.out.println("ERROR");
			}

			for (int i = 0; i < jsonArray.length(); i++) {
				map = new HashMap<String, Object>();
				map.put("list_menu",
						jsonArray.getJSONObject(i).get("tb_menu_name"));
				map.put("list_count",
						jsonArray.getJSONObject(i).get("tb_order_count"));
				map.put("list_price",
						jsonArray.getJSONObject(i).get("tb_menu_price"));
				map.put("list_time",
						jsonArray.getJSONObject(i).get("tb_order_time")
								.toString().substring(0, 10));

				map.put("list_desc",
						jsonArray.getJSONObject(i).get("tb_menu_description"));
				map.put("list_price",
						jsonArray.getJSONObject(i).get("tb_menu_price"));

				switch (Math.round(Float.parseFloat((String) jsonArray
						.getJSONObject(i).get("tb_menu_stars")))) {
				case 0:
					tb_menu_stars_str = "☆☆☆☆☆";
					break;
				case 1:
					tb_menu_stars_str = "★☆☆☆☆";
					break;
				case 2:
					tb_menu_stars_str = "★★☆☆☆";
					break;
				case 3:
					tb_menu_stars_str = "★★★☆☆";
					break;
				case 4:
					tb_menu_stars_str = "★★★★☆";
					break;
				case 5:
					tb_menu_stars_str = "★★★★★";
					break;
				}
				map.put("list_score", tb_menu_stars_str);

				map.put("list_restaurant",
						jsonArray.getJSONObject(i).get("tb_merchant_name"));
				map.put("list_category",
						jsonArray.getJSONObject(i).get("tb_menuclassify_name"));
				map.put("list_restaurant_addr",
						jsonArray.getJSONObject(i).get("tb_merchant_address"));
				map.put("list_restaurant_phone", jsonArray.getJSONObject(i)
						.get("tb_merchant_telephone"));

				float total = 0f;
				map.put("list_total",
						"￥"
								+ Float.parseFloat((String) jsonArray
										.getJSONObject(i).get("tb_menu_price"))
								* Float.parseFloat((String) jsonArray
										.getJSONObject(i).get("tb_order_count")));
				this.list01.add(map);
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.simpleAdapter = new SimpleAdapter(PersonInfoActivity.this, list01,
				R.layout.person_info_data, new String[] { "list_menu",
						"list_count", "list_total", "list_time" },
				new int[] { R.id.person_info_data_tv01,
						R.id.person_info_data_tv02, R.id.person_info_data_tv03,
						R.id.person_info_data_tv04 });
		lv01.setAdapter(simpleAdapter);
		lv01.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				HashMap<String, Object> map = (HashMap<String, Object>) simpleAdapter
						.getItem(position);

				list_menu = map.get("list_menu").toString();
				list_desc = map.get("list_desc").toString();
				list_price = map.get("list_price").toString();
				list_score = map.get("list_score").toString();
				list_restaurant = map.get("list_restaurant").toString();
				list_restaurant_phone = map.get("list_restaurant_phone")
						.toString();
				list_category = map.get("list_category").toString();
				list_restaurant_addr = map.get("list_restaurant_addr")
						.toString();
				ListMenuDetail();

			}

		});
	}

	private void UpdatePWD() {

		LayoutInflater layoutInflater = LayoutInflater
				.from(PersonInfoActivity.this);
		View view = layoutInflater.inflate(R.layout.update_pwd, null);
		final EditText et01 = (EditText) view
				.findViewById(R.id.update_pwd_et01);
		final EditText et02 = (EditText) view
				.findViewById(R.id.update_pwd_et02);
		final EditText et03 = (EditText) view
				.findViewById(R.id.update_pwd_et03);

		Dialog dialog = new AlertDialog.Builder(PersonInfoActivity.this)
				.setIcon(R.drawable.info)
				.setTitle("修改密码")
				.setView(view)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {

						if (user_pwd_sp.equals(et01.getText().toString())) {
							pwd = et02.getText().toString();
							pwd1 = et03.getText().toString();
							if (!pwd.equals(pwd1)) {
								Toast.makeText(PersonInfoActivity.this,
										"两次输入密码不一致!", Toast.LENGTH_SHORT)
										.show();
								return;
							} else {

							}
						} else {
							Toast.makeText(PersonInfoActivity.this, "旧密码输入错误!",
									Toast.LENGTH_SHORT).show();
							return;
						}

					}
				})
				.setNeutralButton("取消", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {

					}
				}).create();
		dialog.show();

	}

	private void ListMenuDetail() {
		LayoutInflater layoutInflater = LayoutInflater
				.from(PersonInfoActivity.this);
		View view = layoutInflater.inflate(R.layout.list_menu_detail, null);
		TextView tv00 = (TextView) view.findViewById(R.id.list_menu_deail_tv00);
		TextView tv01 = (TextView) view.findViewById(R.id.list_menu_deail_tv01);
		TextView tv02 = (TextView) view.findViewById(R.id.list_menu_deail_tv02);
		TextView tv03 = (TextView) view.findViewById(R.id.list_menu_deail_tv03);
		TextView tv04 = (TextView) view.findViewById(R.id.list_menu_deail_tv04);
		TextView tv05 = (TextView) view.findViewById(R.id.list_menu_deail_tv05);
		tv00.setText(list_score);
		tv01.setText(list_desc);
		tv02.setText(list_restaurant);
		tv03.setText(list_restaurant_addr);
		tv04.setText(list_restaurant_phone);
		tv05.setText(list_category);

		Dialog dialog = new AlertDialog.Builder(PersonInfoActivity.this)
				.setTitle(list_menu + " || ￥" + list_price).setView(view)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {

					}
				}).create();
		dialog.show();

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent(PersonInfoActivity.this,
					InitMainActivity.class);
			startActivity(intent);
			finish();
		}
		return false;
	}

}
