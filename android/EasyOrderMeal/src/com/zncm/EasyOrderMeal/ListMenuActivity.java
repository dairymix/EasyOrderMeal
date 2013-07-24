package com.zncm.EasyOrderMeal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.zncm.EasyOrderMeal.Json.JsonUtils;

public class ListMenuActivity extends Activity implements OnClickListener {
	int show_icon = 0;
	Bundle bundle = null;
	String bundle_type = "";
	String bundle_merchant_id = "";
	String bundle_merchant_name = "";
	CheckboxAdapter checkboxAdapter = null;
	HashMap<String, Object> map = null;
	HashMap<String, String> map2 = null;
	ArrayList<HashMap<String, Object>> listData = new ArrayList<HashMap<String, Object>>();
	List<Map<String, String>> list01 = new ArrayList<Map<String, String>>();
	ImageView iv01 = null;
	ImageView iv02 = null;
	ImageView iv03 = null;
	ImageView iv04 = null;
	TextView tv01 = null;
	TextView tv02 = null;
	LinearLayout ll01 = null;

	ListView lv01 = null;

	String list_id = "";
	String list_img = "";
	String list_name = "";
	String list_desc = "";
	String list_price = "";
	String list_score = "";
	String list_category = "";
	String list_restaurant = "";
	String list_restaurant_addr = "";
	String list_merchant_telephone = "";
	String list_menu_category = "";

	StringBuffer sb_list_id = new StringBuffer();;

	int tb_menu_stars = 0;
	String tb_menu_stars_str = "";
	ArrayList<String> menu_category = new ArrayList<String>();
	ArrayList<String> menu_category_id = new ArrayList<String>();

	GridView gv01 = null;
	SimpleAdapter simpleAdapter = null;
	JSONArray jsonArray = null;
	JSONArray jsonArray2 = null;
	JSONObject jsonObject = null;
	JsonUtils jsonUtils = new JsonUtils();

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.main_menu_item01:

			HashMap<Integer, Boolean> state = checkboxAdapter.state;
			String options = "选择的项是:";
			for (int j = 0; j < checkboxAdapter.getCount(); j++) {
				System.out.println("state.get(" + j + ")==" + state.get(j));
				if (state.get(j) != null) {
					HashMap<String, Object> map = (HashMap<String, Object>) checkboxAdapter
							.getItem(j);
					list_id = map.get("list_id").toString();
					sb_list_id.append(list_id).append("-");
				}

			}

			Intent intent = new Intent(ListMenuActivity.this,
					ListOrderActivity.class);
			bundle = new Bundle();
			bundle.putString("choose_id", sb_list_id.toString());
			bundle.putString("init_type", bundle_type);
			intent.putExtras(bundle);
			startActivity(intent);
			finish();
			break;

		default:
			break;
		}

		return true;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initMain();
	}

	private void LoadData() {
		Intent intent = this.getIntent();
		bundle = intent.getExtras();
		if (bundle != null) {
			bundle_type = bundle.getString("init_type");
			bundle_merchant_id = bundle.getString("merchant_id");
			bundle_merchant_name = bundle.getString("merchant_name");
		} else {
			return;
		}
	}

	private void InitCategory(String list_menu_category2) {

		System.out.println("XXXXX------------>" + list_menu_category2);
		// TODO Auto-generated method stub
		listData.clear();
		try {
			jsonArray = jsonUtils.GetInfo("menu/getmenubycls?id="
					+ list_menu_category2);

			jsonArray2 = jsonUtils.GetInfo("menu/getpagebycls?id="
					+ list_menu_category2);

			System.out.println("page_num:　　"
					+ jsonArray2.getJSONObject(0).getString("cnt"));

			Constants.CURRENT_PAGE = 1;
			Constants.TOTAL_PAGES = Integer.parseInt(jsonArray2
					.getJSONObject(0).getString("cnt"));
			tv02.setText(Constants.CURRENT_PAGE + "/" + Constants.TOTAL_PAGES);

			if (jsonArray.length() == Constants.ERROR_CODE) {
				System.out.println("ERROR");
			}

			for (int i = 0; i < jsonArray.length(); i++) {
				map = new HashMap<String, Object>();
				map.put("list_id0", (i + 1) + "");
				map.put("list_id", jsonArray.getJSONObject(i).get("tb_menu_id"));
				switch (i) {
				case 0:
					show_icon = R.drawable.icon_01;
					break;
				case 1:
					show_icon = R.drawable.icon_02;
					break;
				case 2:
					show_icon = R.drawable.icon_03;
					break;
				case 3:
					show_icon = R.drawable.icon_04;
					break;
				case 4:
					show_icon = R.drawable.icon_05;
					break;
				case 5:
					show_icon = R.drawable.icon_06;
					break;
				case 6:
					show_icon = R.drawable.icon_07;
					break;
				case 7:
					show_icon = R.drawable.icon_08;
					break;
				case 8:
					show_icon = R.drawable.icon_09;
					break;
				case 9:
					show_icon = R.drawable.icon_10;
					break;
				case 10:
					show_icon = R.drawable.icon_11_;
					break;

				}

				map.put("list_img", String.valueOf(show_icon));
				map.put("list_name",
						jsonArray.getJSONObject(i).get("tb_menu_name"));
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
				map.put("list_merchant_telephone", jsonArray.getJSONObject(i)
						.get("tb_merchant_telephone"));
				map.put("selected", false);

				this.listData.add(map);
				checkboxAdapter.notifyDataSetChanged();

			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void initMain() {
		setContentView(R.layout.list_menu);
		LoadData();
		lv01 = (ListView) findViewById(R.id.list_menu_lv01);
		iv01 = (ImageView) findViewById(R.id.list_menu_iv01);
		iv02 = (ImageView) findViewById(R.id.list_menu_iv02);
		iv03 = (ImageView) findViewById(R.id.list_menu_iv03);
		iv04 = (ImageView) findViewById(R.id.list_menu_iv04);
		ll01 = (LinearLayout) findViewById(R.id.list_menu_ll01);

		iv03.setOnClickListener(this);
		iv04.setOnClickListener(this);

		gv01 = (GridView) findViewById(R.id.list_menu_gv01);

		tv01 = (TextView) findViewById(R.id.list_menu_tv01);
		tv02 = (TextView) findViewById(R.id.list_menu_tv02);

		if (bundle_type.equals("1")) {
			tv01.setText("今日推荐");
		} else if (bundle_type.equals("2")) {
			tv01.setText("分类点餐");
		} else if (bundle_type.equals("3")) {
			tv01.setText(bundle_merchant_name + "");
		}

		iv01.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ListMenuActivity.this,
						InitMainActivity.class);
				startActivity(intent);
				finish();

			}
		});
		iv02.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				HashMap<Integer, Boolean> state = checkboxAdapter.state;
				String options = "选择的项是:";
				for (int j = 0; j < checkboxAdapter.getCount(); j++) {
					if (state.get(j) != null) {
						HashMap<String, Object> map = (HashMap<String, Object>) checkboxAdapter
								.getItem(j);
						list_id = map.get("list_id").toString();
						sb_list_id.append(list_id).append("-");

					}

				}
				Intent intent = new Intent(ListMenuActivity.this,
						ListOrderActivity.class);

				bundle = new Bundle();
				bundle.putString("choose_id", sb_list_id.toString());
				bundle.putString("init_type", bundle_type);
				intent.putExtras(bundle);

				startActivity(intent);
				finish();
			}
		});

		if (bundle_type.equals("0")) {
			// // 推荐列表
			// try {
			// jsonArray = jsonUtils.GetInfo("recommendation/getrec");
			// if (jsonArray.length() == Constants.ERROR_CODE) {
			// System.out.println("ERROR");
			// }
			// System.out.println("jsonArray:　　" + jsonArray);
			// System.out.println("lengthlength:　　" + jsonArray.length());
			//
			// System.out.println("jsonArraytb_user_name:　　"
			// + jsonArray.getJSONObject(0).get("tb_menu_name"));
			// for (int i = 0; i < jsonArray.length(); i++) {
			// map = new HashMap<String, Object>();
			// map.put("list_id0", (i + 1) + "");
			// map.put("list_id",
			// jsonArray.getJSONObject(i).get("tb_menu_id"));
			// map.put("list_img", String.valueOf(R.drawable.yxrs));
			// map.put("list_name",
			// jsonArray.getJSONObject(i).get("tb_menu_name"));
			// map.put("list_desc",
			// jsonArray.getJSONObject(i).get(
			// "tb_menu_description"));
			// map.put("list_price",
			// jsonArray.getJSONObject(i).get("tb_menu_price"));
			// map.put("list_score",
			// jsonArray.getJSONObject(i).get("tb_menu_stars"));
			// map.put("list_restaurant",
			// jsonArray.getJSONObject(i).get("tb_merchant_name"));
			// map.put("list_restaurant_addr", jsonArray.getJSONObject(i)
			// .get("tb_merchant_address"));
			// map.put("list_restaurant_desc", jsonArray.getJSONObject(i)
			// .get("tb_merchant_telephone"));
			// map.put("selected", false);
			//
			// this.listData.add(map);
			//
			// }
			//
			// } catch (JSONException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// } catch (Exception e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }

		} else if (bundle_type.equals("1")) {

			try {
				jsonArray = jsonUtils.GetInfo("recommendation/getrec");
				if (jsonArray.length() == Constants.ERROR_CODE) {
					System.out.println("ERROR");
				}
				System.out.println("jsonArray:　　" + jsonArray);
				System.out.println("lengthlength:　　" + jsonArray.length());

				System.out.println("jsonArraytb_user_name:　　"
						+ jsonArray.getJSONObject(0).get("tb_menu_name"));
				for (int i = 0; i < jsonArray.length(); i++) {
					map = new HashMap<String, Object>();
					map.put("list_id0", (i + 1) + "");
					map.put("list_id",
							jsonArray.getJSONObject(i).get("tb_menu_id"));
					switch (i) {
					case 0:
						show_icon = R.drawable.icon_01;
						break;
					case 1:
						show_icon = R.drawable.icon_02;
						break;
					case 2:
						show_icon = R.drawable.icon_03;
						break;
					case 3:
						show_icon = R.drawable.icon_04;
						break;
					case 4:
						show_icon = R.drawable.icon_05;
						break;
					case 5:
						show_icon = R.drawable.icon_06;
						break;
					case 6:
						show_icon = R.drawable.icon_07;
						break;
					case 7:
						show_icon = R.drawable.icon_08;
						break;
					case 8:
						show_icon = R.drawable.icon_09;
						break;
					case 9:
						show_icon = R.drawable.icon_10;
						break;
					case 10:
						show_icon = R.drawable.icon_11_;
						break;

					}

					map.put("list_img", String.valueOf(show_icon));
					map.put("list_name",
							jsonArray.getJSONObject(i).get("tb_menu_name"));
					map.put("list_desc",
							jsonArray.getJSONObject(i).get(
									"tb_menu_description"));
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
							jsonArray.getJSONObject(i).get(
									"tb_menuclassify_name"));
					map.put("list_restaurant_addr", jsonArray.getJSONObject(i)
							.get("tb_merchant_address"));
					map.put("list_merchant_telephone",
							jsonArray.getJSONObject(i).get(
									"tb_merchant_telephone"));
					map.put("selected", false);

					this.listData.add(map);

				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (bundle_type.equals("2")) {

			ll01.setVisibility(View.VISIBLE);

			try {
				jsonArray = jsonUtils.GetInfo("menu/getcls");
				if (jsonArray.length() == Constants.ERROR_CODE) {
					System.out.println("ERROR");
				}

				gv01.setVisibility(View.VISIBLE);

				for (int i = 0; i < jsonArray.length(); i++) {
					menu_category.add(jsonArray.getJSONObject(i)
							.get("tb_menuclassify_name").toString());
					menu_category_id.add(jsonArray.getJSONObject(i)
							.get("tb_menuclassify_id").toString());
				}

				for (int i = 0; i < menu_category.size(); i++) {
					map2 = new HashMap<String, String>();
					System.out.println("cls------------->"
							+ menu_category_id.get(i).toString());
					map2.put("list_name", menu_category.get(i).toString());
					map2.put("list_menu_category_id", menu_category_id.get(i)
							.toString());
					this.list01.add(map2);

				}

				this.simpleAdapter = new SimpleAdapter(ListMenuActivity.this,
						list01, R.layout.gv_list_menu_data,
						new String[] { "list_name" },
						new int[] { R.id.gv_list_menu_data_tv01 });
				gv01.setAdapter(simpleAdapter);
				gv01.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int position, long arg3) {
						Map<String, String> map = (Map<String, String>) ListMenuActivity.this.simpleAdapter
								.getItem(position);
						list_menu_category = map.get("list_menu_category_id");
						InitCategory(list_menu_category);

					}
				});

				jsonArray = jsonUtils.GetInfo("menu/getmenubycls?id=" + 1);

				jsonArray2 = jsonUtils.GetInfo("menu/getpagebycls?id=" + 1);

				System.out.println("page_num:　　"
						+ jsonArray2.getJSONObject(0).getString("cnt"));

				Constants.CURRENT_PAGE = 1;
				Constants.TOTAL_PAGES = Integer.parseInt(jsonArray2
						.getJSONObject(0).getString("cnt"));
				tv02.setText(Constants.CURRENT_PAGE + "/"
						+ Constants.TOTAL_PAGES);
				if (jsonArray.length() == Constants.ERROR_CODE) {
					System.out.println("ERROR");
				}

				System.out.println("tb_merchant_telephone:　　"
						+ jsonArray.getJSONObject(0).get(
								"tb_merchant_telephone"));

				for (int i = 0; i < jsonArray.length(); i++) {
					map = new HashMap<String, Object>();
					map.put("list_id0", (i + 1) + "");
					map.put("list_id",
							jsonArray.getJSONObject(i).get("tb_menu_id"));
					switch (i) {
					case 0:
						show_icon = R.drawable.icon_01;
						break;
					case 1:
						show_icon = R.drawable.icon_02;
						break;
					case 2:
						show_icon = R.drawable.icon_03;
						break;
					case 3:
						show_icon = R.drawable.icon_04;
						break;
					case 4:
						show_icon = R.drawable.icon_05;
						break;
					case 5:
						show_icon = R.drawable.icon_06;
						break;
					case 6:
						show_icon = R.drawable.icon_07;
						break;
					case 7:
						show_icon = R.drawable.icon_08;
						break;
					case 8:
						show_icon = R.drawable.icon_09;
						break;
					case 9:
						show_icon = R.drawable.icon_10;
						break;
					case 10:
						show_icon = R.drawable.icon_11_;
						break;

					}

					map.put("list_img", String.valueOf(show_icon));
					map.put("list_name",
							jsonArray.getJSONObject(i).get("tb_menu_name"));
					map.put("list_desc",
							jsonArray.getJSONObject(i).get(
									"tb_menu_description"));
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
							jsonArray.getJSONObject(i).get(
									"tb_menuclassify_name"));
					map.put("list_restaurant_addr", jsonArray.getJSONObject(i)
							.get("tb_merchant_address"));
					map.put("list_merchant_telephone",
							jsonArray.getJSONObject(i).get(
									"tb_merchant_telephone"));
					map.put("selected", false);

					this.listData.add(map);

				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// gv01.setVisibility(View.VISIBLE);
			//
			// menu_category.add("凉菜");
			// menu_category.add("热菜");
			// menu_category.add("饮料");
			// menu_category.add("面食");
			// menu_category.add("凉菜1");
			// menu_category.add("热菜1");
			// menu_category.add("饮料1");
			// menu_category.add("面食1");
			//
			// for (int i = 0; i < menu_category.size(); i++) {
			// map2 = new HashMap<String, String>();
			// map2.put("list_name", menu_category.get(i).toString());
			// this.list01.add(map2);
			//
			// }
			//
			// this.simpleAdapter = new SimpleAdapter(ListMenuActivity.this,
			// list01, R.layout.gv_list_menu_data,
			// new String[] { "list_name" },
			// new int[] { R.id.gv_list_menu_data_tv01 });
			// gv01.setAdapter(simpleAdapter);

		} else if (bundle_type.equals("3")) {

			ll01.setVisibility(View.VISIBLE);

			try {
				jsonArray = jsonUtils.GetInfo("menu/getcls");
				if (jsonArray.length() == Constants.ERROR_CODE) {
					System.out.println("ERROR");
				}

				gv01.setVisibility(View.VISIBLE);

				for (int i = 0; i < jsonArray.length(); i++) {
					menu_category.add(jsonArray.getJSONObject(i)
							.get("tb_menuclassify_name").toString());
					menu_category_id.add(jsonArray.getJSONObject(i)
							.get("tb_menuclassify_id").toString());
				}

				for (int i = 0; i < menu_category.size(); i++) {
					map2 = new HashMap<String, String>();
					System.out.println("cls------------->"
							+ menu_category_id.get(i).toString());
					map2.put("list_name", menu_category.get(i).toString());
					map2.put("list_menu_category_id", menu_category_id.get(i)
							.toString());
					this.list01.add(map2);

				}

				this.simpleAdapter = new SimpleAdapter(ListMenuActivity.this,
						list01, R.layout.gv_list_menu_data,
						new String[] { "list_name" },
						new int[] { R.id.gv_list_menu_data_tv01 });
				gv01.setAdapter(simpleAdapter);
				gv01.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int position, long arg3) {
						Map<String, String> map = (Map<String, String>) ListMenuActivity.this.simpleAdapter
								.getItem(position);
						list_menu_category = map.get("list_menu_category_id");
						InitCategory(list_menu_category);

					}
				});

				jsonArray = jsonUtils.GetInfo("getpagebymer?id=" + 1);

				jsonArray2 = jsonUtils.GetInfo("menu/getpagebymer?id=" + 1);

				System.out.println("page_num:　　"
						+ jsonArray2.getJSONObject(0).getString("cnt"));

				Constants.CURRENT_PAGE = 1;
				Constants.TOTAL_PAGES = Integer.parseInt(jsonArray2
						.getJSONObject(0).getString("cnt"));
				tv02.setText(Constants.CURRENT_PAGE + "/"
						+ Constants.TOTAL_PAGES);
				if (jsonArray.length() == Constants.ERROR_CODE) {
					System.out.println("ERROR");
				}

				System.out.println("tb_merchant_telephone:　　"
						+ jsonArray.getJSONObject(0).get(
								"tb_merchant_telephone"));

				for (int i = 0; i < jsonArray.length(); i++) {
					map = new HashMap<String, Object>();
					map.put("list_id0", (i + 1) + "");
					map.put("list_id",
							jsonArray.getJSONObject(i).get("tb_menu_id"));
					switch (i) {
					case 0:
						show_icon = R.drawable.icon_01;
						break;
					case 1:
						show_icon = R.drawable.icon_02;
						break;
					case 2:
						show_icon = R.drawable.icon_03;
						break;
					case 3:
						show_icon = R.drawable.icon_04;
						break;
					case 4:
						show_icon = R.drawable.icon_05;
						break;
					case 5:
						show_icon = R.drawable.icon_06;
						break;
					case 6:
						show_icon = R.drawable.icon_07;
						break;
					case 7:
						show_icon = R.drawable.icon_08;
						break;
					case 8:
						show_icon = R.drawable.icon_09;
						break;
					case 9:
						show_icon = R.drawable.icon_10;
						break;
					case 10:
						show_icon = R.drawable.icon_11_;
						break;

					}

					map.put("list_img", String.valueOf(show_icon));
					map.put("list_name",
							jsonArray.getJSONObject(i).get("tb_menu_name"));
					map.put("list_desc",
							jsonArray.getJSONObject(i).get(
									"tb_menu_description"));
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
							jsonArray.getJSONObject(i).get(
									"tb_menuclassify_name"));
					map.put("list_restaurant_addr", jsonArray.getJSONObject(i)
							.get("tb_merchant_address"));
					map.put("list_merchant_telephone",
							jsonArray.getJSONObject(i).get(
									"tb_merchant_telephone"));
					map.put("selected", false);

					this.listData.add(map);

				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		checkboxAdapter = new CheckboxAdapter(ListMenuActivity.this, listData);
		lv01.setAdapter(checkboxAdapter);
		lv01.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				HashMap<String, Object> map = (HashMap<String, Object>) checkboxAdapter
						.getItem(position);
				list_img = map.get("list_img").toString();
				list_name = map.get("list_name").toString();
				list_desc = map.get("list_desc").toString();
				list_price = map.get("list_price").toString();
				list_score = map.get("list_score").toString();
				list_restaurant = map.get("list_restaurant").toString();
				list_merchant_telephone = map.get("list_merchant_telephone")
						.toString();
				list_category = map.get("list_category").toString();
				list_restaurant_addr = map.get("list_restaurant_addr")
						.toString();
				ListMenuDetail();

			}

		});

	}

	private void InitDatas(String list_menu_category2) {
		try {

			listData.clear();

			System.out.println("xxx:　" + Constants.CURRENT_PAGE);

			jsonArray = jsonUtils.GetInfo("menu/getmenubycls?id="
					+ list_menu_category2 + "&page=" + Constants.CURRENT_PAGE);

			if (jsonArray.length() == Constants.ERROR_CODE) {
				System.out.println("ERROR");
			}

			for (int i = 0; i < jsonArray.length(); i++) {
				map = new HashMap<String, Object>();
				map.put("list_id0", (i + 1) + "");
				map.put("list_id", jsonArray.getJSONObject(i).get("tb_menu_id"));
				map.put("list_img", String.valueOf(R.drawable.yxrs));
				map.put("list_name",
						jsonArray.getJSONObject(i).get("tb_menu_name"));
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
				map.put("list_merchant_telephone", jsonArray.getJSONObject(i)
						.get("tb_merchant_telephone"));
				map.put("selected", false);

				this.listData.add(map);

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		checkboxAdapter.notifyDataSetChanged();

	}

	private void ListMenuDetail() {
		LayoutInflater layoutInflater = LayoutInflater
				.from(ListMenuActivity.this);
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
		tv04.setText(list_merchant_telephone);
		tv05.setText(list_category);

		Dialog dialog = new AlertDialog.Builder(ListMenuActivity.this)
				.setIcon(Integer.parseInt(list_img))
				.setTitle(list_name + " || ￥" + list_price).setView(view)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {

					}
				}).create();
		dialog.show();

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent(ListMenuActivity.this,
					InitMainActivity.class);
			startActivity(intent);
			finish();
		}
		return false;
	}

	@Override
	public void onClick(View view) {

		if (view.getId() == R.id.list_menu_iv03) {

			tv02.setText(Constants.CURRENT_PAGE + "/" + Constants.TOTAL_PAGES);
			if (Constants.CURRENT_PAGE > 1) {
				Constants.CURRENT_PAGE--;
				InitDatas(list_menu_category);
				tv02.setText(Constants.CURRENT_PAGE + "/"
						+ Constants.TOTAL_PAGES);
			} else {
				Toast.makeText(ListMenuActivity.this, "已是第一页",
						Toast.LENGTH_SHORT).show();
			}

		} else if (view.getId() == R.id.list_menu_iv04) {

			if (Constants.CURRENT_PAGE < Constants.TOTAL_PAGES) {

				Constants.CURRENT_PAGE++;
				InitDatas(list_menu_category);
				tv02.setText(Constants.CURRENT_PAGE + "/"
						+ Constants.TOTAL_PAGES);

			} else {
				Toast.makeText(ListMenuActivity.this, "已是最后一页",
						Toast.LENGTH_SHORT).show();
			}

		}

	}
}