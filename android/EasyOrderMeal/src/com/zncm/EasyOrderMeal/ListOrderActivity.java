package com.zncm.EasyOrderMeal;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.zncm.EasyOrderMeal.Json.JsonUtils;

public class ListOrderActivity extends Activity implements
		OnCheckedChangeListener {

	int sendType = 0;
	String sendType_str[] = { "餐馆进餐", "外送" };

	int sendTime = 0;
	String sendTime_str[] = { "早餐", "午餐", "晚餐" };

	RadioButton rb01 = null;
	RadioButton rb02 = null;
	RadioGroup rg01 = null;
	RadioButton rb03 = null;
	RadioButton rb04 = null;
	RadioButton rb05 = null;
	RadioGroup rg02 = null;
	DatePicker dp01 = null;
	String time = "";
	SharedPreferences sp = null;
	boolean Is_user_online_sp = false;
	String user_id_sp = "";
	String user_account_sp = "";
	String user_phone_sp = "";

	Bundle bundle = null;
	String bundle_type = "";
	String bundle_choose_id = "";

	int tb_menu_stars = 0;
	String tb_menu_stars_str = "";

	ImageViewAdapter imageViewAdapter = null;
	ArrayList<HashMap<String, Object>> listData = new ArrayList<HashMap<String, Object>>();
	HashMap<String, Object> map = null;

	TextView tv00 = null;
	ImageView iv01 = null;
	ImageView iv02 = null;
	ImageView iv03 = null;
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
	String list_restaurant_phone = "";
	String list_spend = "";

	ArrayList<String> menu = new ArrayList<String>();
	ArrayList<String> menu_name = new ArrayList<String>();
	ArrayList<String> price = new ArrayList<String>();
	ArrayList<String> count = new ArrayList<String>();

	ArrayList<String> menu_category = new ArrayList<String>();
	GridView gv01 = null;
	SimpleAdapter simpleAdapter = null;
	JSONArray jsonArray = null;
	JSONObject jsonObject = null;
	JsonUtils jsonUtils = new JsonUtils();
	float total = 0f;

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
			bundle_choose_id = bundle.getString("choose_id");
		} else {
			return;
		}
	}

	private void initMain() {
		setContentView(R.layout.list_order);
		LoadData();

		sp = getSharedPreferences("EasyOrderMeal_SP", MODE_PRIVATE);
		Is_user_online_sp = sp.getBoolean("Is_user_online_sp", false);
		user_id_sp = sp.getString("user_id_sp", "0");
		user_account_sp = sp.getString("user_account_sp", "0");

		lv01 = (ListView) findViewById(R.id.list_order_lv01);
		iv01 = (ImageView) findViewById(R.id.list_order_iv01);
		iv02 = (ImageView) findViewById(R.id.list_order_iv02);

		// iv03.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// ArrayList<String> price = new ArrayList<String>();
		// ArrayList<String> count = new ArrayList<String>();
		// for (int j = 0; j < listData.size(); j++) {
		//
		// HashMap<String, Object> map = (HashMap<String, Object>)
		// imageViewAdapter
		// .getItem(j);
		// price.add(map.get("list_price").toString());
		// count.add(map.get("list_count").toString());
		//
		// }
		//
		// }
		// });

		iv01.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ListOrderActivity.this,
						ListMenuActivity.class);
				bundle = new Bundle();
				bundle.putString("init_type", bundle_type);
				intent.putExtras(bundle);
				startActivity(intent);
				finish();

			}
		});
		iv02.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (Is_user_online_sp) {
					ChooseTime();
				} else {
					Dialog dialog = new AlertDialog.Builder(
							ListOrderActivity.this)
							.setIcon(R.drawable.info)
							.setTitle("请登录")
							.setMessage("你还未登录,请登录后点餐!")
							.setPositiveButton("去登录",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog,
												int whichButton) {
											Intent intent = new Intent(
													ListOrderActivity.this,
													LoginActivity.class);
											startActivity(intent);
											finish();
										}
									})
							.setNeutralButton("去注册",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog,
												int whichButton) {
											Intent intent = new Intent(
													ListOrderActivity.this,
													RegisterActivity.class);
											startActivity(intent);
											finish();
										}
									})
							.setNegativeButton("取消",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog,
												int whichButton) {

										}
									})

							.create();
					dialog.show();
				}

			}
		});

		//
		// for (int i = 0; i < 5; i++) {
		//
		// map = new HashMap<String, Object>();
		// map.put("list_id0", (i + 1) + "");
		// map.put("list_id", i);
		// map.put("list_img", String.valueOf(R.drawable.yxrs));
		// map.put("list_name", "鱼香肉丝");
		// map.put("list_desc", "鱼香肉丝_香辣可口非常好吃！！");
		// map.put("list_price", 7.5 + "");
		// map.put("list_restaurant", "川菜馆");
		// map.put("list_category", "川菜");
		// map.put("list_restaurant_phone", "15196615493");
		// map.put("list_restaurant_addr", "学府路一段24号");
		// map.put("list_count", 0 + "");
		// map.put("selected", false);
		//
		// if (i < 5) {
		// tb_menu_stars = i;
		// } else {
		// tb_menu_stars = 3;
		// }
		// switch (tb_menu_stars) {
		// case 0:
		// tb_menu_stars_str = "☆☆☆☆☆";
		// break;
		// case 1:
		// tb_menu_stars_str = "★☆☆☆☆";
		// break;
		// case 2:
		// tb_menu_stars_str = "★★☆☆☆";
		// break;
		// case 3:
		// tb_menu_stars_str = "★★★☆☆";
		// break;
		// case 4:
		// tb_menu_stars_str = "★★★★☆";
		// break;
		// case 5:
		// tb_menu_stars_str = "★★★★★";
		// break;
		// }
		// map.put("list_score", tb_menu_stars_str);
		// this.listData.add(map);
		// }

		// bundle_choose_id

		System.out.println("bundle_choose_id: " + bundle_choose_id);
		try {

			String temp[] = bundle_choose_id.split("-");
			StringBuffer sb = new StringBuffer();
			sb.append("menu/getmenusbyids?");
			for (int i = 0; i < temp.length; i++) {
				sb.append("&id" + i + "=");
				sb.append(temp[i]);
			}
			System.out.println("sb.toString()::  " + sb.toString());
			jsonArray = jsonUtils.GetInfo(sb.toString());
			if (jsonArray.length() == Constants.ERROR_CODE) {
				System.out.println("ERROR");
			}
			System.out.println("jsonArray:　　" + jsonArray);
			System.out.println("lengthlength:　　" + jsonArray.length());
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
				map.put("list_restaurant_phone", jsonArray.getJSONObject(i)
						.get("tb_merchant_telephone"));
				map.put("list_count", 0 + "");
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

		imageViewAdapter = new ImageViewAdapter(ListOrderActivity.this,
				listData);
		lv01.setAdapter(imageViewAdapter);

		lv01.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				HashMap<String, Object> map = (HashMap<String, Object>) imageViewAdapter
						.getItem(position);
				list_img = map.get("list_img").toString();
				list_name = map.get("list_name").toString();
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

	private void ListMenuDetail() {
		LayoutInflater layoutInflater = LayoutInflater
				.from(ListOrderActivity.this);
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

		Dialog dialog = new AlertDialog.Builder(ListOrderActivity.this)
				.setIcon(Integer.parseInt(list_img))
				.setTitle(list_name + " || ￥" + list_price).setView(view)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {

					}
				}).create();
		dialog.show();

	}

	private void ChooseTime() {

		LayoutInflater layoutInflater = LayoutInflater
				.from(ListOrderActivity.this);
		View view = layoutInflater.inflate(R.layout.choose_time_dp, null);
		dp01 = (DatePicker) view.findViewById(R.id.choose_time_dp_dp01);

		Dialog dialog = new AlertDialog.Builder(ListOrderActivity.this)

				.setTitle("选择订餐日期")
				.setView(view)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {

						if (((dp01.getMonth() + 1) < 10)
								&& (dp01.getDayOfMonth() < 10)) {
							time = dp01.getYear() + "-0"
									+ (dp01.getMonth() + 1) + "-0"
									+ dp01.getDayOfMonth();
						}

						if (((dp01.getMonth() + 1) >= 10)
								&& (dp01.getDayOfMonth() >= 10)) {
							time = dp01.getYear() + "-" + (dp01.getMonth() + 1)
									+ "-" + dp01.getDayOfMonth();
						}

						if (((dp01.getMonth() + 1) < 10)
								&& (dp01.getDayOfMonth() >= 10)) {
							time = dp01.getYear() + "-0"
									+ (dp01.getMonth() + 1) + "-"
									+ dp01.getDayOfMonth();
						}
						if (((dp01.getMonth() + 1) >= 10)
								&& (dp01.getDayOfMonth() < 10)) {
							time = dp01.getYear() + "-" + (dp01.getMonth() + 1)
									+ "-0" + dp01.getDayOfMonth();
						}

						ChooseSendType();

					}

				})
				.setNeutralButton("取消", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {

					}
				}).create();
		dialog.show();

	}

	private void ChooseSendType() {
		// TODO Auto-generated method stub

		LayoutInflater layoutInflater = LayoutInflater
				.from(ListOrderActivity.this);
		View view = layoutInflater.inflate(R.layout.send_type, null);

		rb01 = (RadioButton) view.findViewById(R.id.send_type_rb01);
		rb02 = (RadioButton) view.findViewById(R.id.send_type_rb02);
		rg01 = (RadioGroup) view.findViewById(R.id.send_type_rg01);
		rb03 = (RadioButton) view.findViewById(R.id.send_type_rb03);
		rb04 = (RadioButton) view.findViewById(R.id.send_type_rb04);
		rb05 = (RadioButton) view.findViewById(R.id.send_type_rb05);
		rg02 = (RadioGroup) view.findViewById(R.id.send_type_rg01);

		rg01.setOnCheckedChangeListener(this);
		rg02.setOnCheckedChangeListener(this);
		Dialog dialog = new AlertDialog.Builder(ListOrderActivity.this)

				.setTitle("选择就餐方式")
				.setView(view)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {

						SureToCheck();

					}

				})
				.setNeutralButton("取消", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {

					}
				}).create();
		dialog.show();

	}

	private void SureToCheck() {

		StringBuffer sb00 = new StringBuffer();

		for (int j = 0; j < listData.size(); j++) {

			HashMap<String, Object> map = (HashMap<String, Object>) imageViewAdapter
					.getItem(j);
			menu.add(map.get("list_id").toString());
			menu_name.add(map.get("list_name").toString());
			price.add(map.get("list_price").toString());
			count.add(map.get("list_count").toString());
			sb00.append(menu_name.get(j) + ":");
			sb00.append("￥" + price.get(j) + "*");
			sb00.append(count.get(j) + "=" + Float.parseFloat(price.get(j))
					* Float.parseFloat(count.get(j)) + "-");

		}

		for (int j = 0; j < price.size(); j++) {
			total += Float.parseFloat(price.get(j))
					* Float.parseFloat(count.get(j));
		}

		Dialog dialog = new AlertDialog.Builder(ListOrderActivity.this)
				.setTitle(user_account_sp + " 的订餐单")
				.setMessage(
						"订餐时间: " + time + "_" + sendTime_str[sendTime] + "-"
								+ "就餐方式:" + sendType_str[sendType] + "-"
								+ sb00.toString() + "总价格: ￥" + total + "")
				.setPositiveButton("订餐", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						System.out.println(":user_id_sp---------->"
								+ user_id_sp);
						try {
							for (int i = 0; i < menu.size(); i++) {
								StringBuffer sb = new StringBuffer();
								sb.append("order/doorder?");
								sb.append("userid=" + user_id_sp);
								sb.append("&menuid=" + menu.get(i));
								sb.append("&count=" + count.get(i));
								jsonObject = jsonUtils.GetInfo2(sb.toString());
								if (jsonObject.getInt("status") == 1) {
									System.out.println(":OKOK---------->");

									Intent intent = new Intent(
											ListOrderActivity.this,
											PersonInfoActivity.class);
									startActivity(intent);
									finish();

								} else {
									System.out.println(":XXXXX---------->");
								}
							}

						} catch (Exception e) {
							// TODO Auto-generated catch
							// block
							e.printStackTrace();
						}
					}
				})
				.setNegativeButton("再选选",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								total = 0f;
								count.clear();
								price.clear();
							}
						})

				.create();
		dialog.show();

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent(ListOrderActivity.this,
					InitMainActivity.class);
			startActivity(intent);
			finish();
		}
		return false;
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub

		if (group == rg01) {
			if (rb01.isChecked()) {
				sendType = 0;
			} else if (rb02.isChecked()) {
				sendType = 1;
			}
		} else if (group == rg02) {
			if (rb03.isChecked()) {
				sendTime = 0;
			} else if (rb04.isChecked()) {
				sendTime = 1;
			} else if (rb05.isChecked()) {
				sendTime = 2;
			}
		}
		// rg01.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		//
		// @Override
		// public void onCheckedChanged(RadioGroup group, int checkedId) {
		// // TODO Auto-generated method stub
		// if (rb01.isChecked()) {
		// sendType = 0;
		// } else if (rb02.isChecked()) {
		// sendType = 1;
		// }
		// }
		// });
		//
		// rg02.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		//
		// @Override
		// public void onCheckedChanged(RadioGroup group, int checkedId) {
		// // TODO Auto-generated method stub
		// if (rb03.isChecked()) {
		// sendTime = 0;
		// } else if (rb04.isChecked()) {
		// sendTime = 1;
		// } else if (rb05.isChecked()) {
		// sendTime = 2;
		// }
		// }
		// });

	}

}