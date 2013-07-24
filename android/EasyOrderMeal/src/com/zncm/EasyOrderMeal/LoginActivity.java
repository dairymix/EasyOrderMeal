package com.zncm.EasyOrderMeal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.zncm.EasyOrderMeal.Json.JsonUtils;

public class LoginActivity extends Activity {
	SharedPreferences sp = null;
	boolean Is_user_online_sp = false;
	String user_id_sp = "";
	String user_account_sp = "";
	String user_pwd_sp = "";
	String user_phone_sp = "";
	boolean Is_login_choose_sp = false;

	EditText et01 = null;
	EditText et02 = null;
	ImageView iv01 = null;
	ImageView iv02 = null;
	ImageView iv03 = null;
	ImageView iv04 = null;
	CheckBox cb01 = null;

	String user_id = "";
	String user = "";
	String pw = "";
	String user_phone = "";

	JSONArray jsonArray = null;
	JSONObject jsonObject = null;
	JsonUtils jsonUtils = new JsonUtils();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initMain();

	}

	private void initMain() {
		setContentView(R.layout.login);

		sp = getSharedPreferences("EasyOrderMeal_SP", MODE_PRIVATE);
		Is_user_online_sp = sp.getBoolean("Is_user_online_sp", false);
		Is_login_choose_sp = sp.getBoolean("Is_login_choose_sp", false);
		user_id_sp = sp.getString("user_id_sp", "0");
		user_account_sp = sp.getString("user_account_sp", "0");
		user_pwd_sp = sp.getString("user_pwd_sp", "0");

		et01 = (EditText) findViewById(R.id.login_et01);
		et02 = (EditText) findViewById(R.id.login_et02);

		cb01 = (CheckBox) findViewById(R.id.login_cb01);
		if (Is_login_choose_sp) {
			cb01.setChecked(true);
			et01.setText(user_account_sp);
			et02.setText(user_pwd_sp);
		} else {
			et01.setText("");
			et02.setText("");
		}

		cb01.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					Is_login_choose_sp = true;
				} else {
					Is_login_choose_sp = false;
				}
			}
		});

		iv01 = (ImageView) findViewById(R.id.login_iv01);

		iv01.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(LoginActivity.this,
						InitMainActivity.class);
				startActivity(intent);
				finish();
			}
		});

		iv03 = (ImageView) findViewById(R.id.login_iv03);
		iv04 = (ImageView) findViewById(R.id.login_iv04);
		iv03.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				user = et01.getText().toString();
				pw = et02.getText().toString();

				if (et01.getText().toString().trim().equals("")
						|| et02.getText().toString().trim().equals("")) {
					Toast.makeText(LoginActivity.this, "用户名或者密码输入为空!",
							Toast.LENGTH_SHORT).show();
					return;
				} else {

					try {
						jsonArray = jsonUtils.GetInfo("user/dologin?user="
								+ user + "&pw=" + pw);
						if (jsonArray.length() == Constants.ERROR_CODE) {
							Toast.makeText(LoginActivity.this, "登录失败!",
									Toast.LENGTH_SHORT).show();
							Intent intent = new Intent(LoginActivity.this,
									LoginActivity.class);
							startActivity(intent);
							finish();
						} else {

							Toast.makeText(LoginActivity.this, "登录成功!",
									Toast.LENGTH_SHORT).show();

							user_id = (String) jsonArray.getJSONObject(0).get(
									"tb_user_id");
							sp.edit().putBoolean("Is_user_online_sp", true)
									.commit();
							if (Is_login_choose_sp) {
								sp.edit()
										.putBoolean("Is_login_choose_sp", true)
										.commit();
							} else {
								sp.edit()
										.putBoolean("Is_login_choose_sp", false)
										.commit();
							}

							sp.edit().putString("user_id_sp", user_id).commit();
							sp.edit().putString("user_account_sp", user)
									.commit();
							sp.edit().putString("user_pwd_sp", pw).commit();
							sp.edit().putString("user_phone_sp", user_phone)
									.commit();

							Intent intent = new Intent(LoginActivity.this,
									InitMainActivity.class);
							Bundle bundle = new Bundle();
							bundle.putString("login_user_id", user_id);
							bundle.putString("user_account_sp", user);
							intent.putExtras(bundle);
							startActivity(intent);
							finish();

						}

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		});

		iv04.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LoginActivity.this,
						InitMainActivity.class);
				startActivity(intent);
				finish();
			}
		});

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent(LoginActivity.this,
					InitMainActivity.class);
			startActivity(intent);
			finish();
		}
		return false;
	}

}