package com.zncm.EasyOrderMeal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.zncm.EasyOrderMeal.Json.JsonUtils;

public class RegisterActivity extends Activity {

	EditText et01 = null;
	EditText et02 = null;
	EditText et03 = null;
	EditText et04 = null;
	RadioButton rb01 = null;
	RadioButton rb02 = null;
	RadioGroup rg01 = null;

	ImageView iv01 = null;
	ImageView iv02 = null;
	ImageView iv03 = null;
	ImageView iv04 = null;
	ImageView iv05 = null;

	int sex_type = 0;
	String user = "";
	String pw = "";
	String pw1 = "";
	String telephone = "";
	// 0女1男

	JSONArray jsonArray = null;
	JSONObject jsonObject = null;
	JsonUtils jsonUtils = new JsonUtils();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initMain();

	}

	private void initMain() {
		setContentView(R.layout.register);
		et01 = (EditText) findViewById(R.id.register_et01);
		et02 = (EditText) findViewById(R.id.register_et02);
		et03 = (EditText) findViewById(R.id.register_et03);
		et04 = (EditText) findViewById(R.id.register_et04);
		rb01 = (RadioButton) findViewById(R.id.register_rb01);
		rb02 = (RadioButton) findViewById(R.id.register_rb02);
		rg01 = (RadioGroup) findViewById(R.id.register_rg01);
		iv01 = (ImageView) findViewById(R.id.register_iv01);
		iv02 = (ImageView) findViewById(R.id.register_iv02);
		iv03 = (ImageView) findViewById(R.id.register_iv03);
		iv04 = (ImageView) findViewById(R.id.register_iv04);

		iv01.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(RegisterActivity.this,
						InitMainActivity.class);
				startActivity(intent);
				finish();
			}
		});

		rg01.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if (rb01.isChecked()) {
					sex_type = 0;
				} else if (rb02.isChecked()) {
					sex_type = 1;
				}
			}
		});

		System.out.println("xxx: " + user + "--" + pw + "--" + pw1 + "--"
				+ telephone + "--");

		iv03.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				user = et01.getText().toString();
				pw = et02.getText().toString();
				pw1 = et03.getText().toString();
				telephone = et04.getText().toString();

				if (!pw.equals(pw1)) {
					Toast.makeText(RegisterActivity.this, "两次输入密码不一致!",
							Toast.LENGTH_SHORT).show();
					return;
				} else if (user.trim().equals("")
						|| telephone.trim().equals("") || pw.trim().equals("")) {
					Toast.makeText(RegisterActivity.this, "用户名密码或者电话号码不能为空!",
							Toast.LENGTH_SHORT).show();
					return;
				} else {

					try {
						jsonObject = jsonUtils.GetInfo2("user/doreg?user="
								+ user + "&pw=" + pw + "&sex=" + sex_type
								+ "&telephone=" + telephone + "&name="
								+ "undefined");
						if (jsonObject.getInt("status") == 0) {
							Toast.makeText(RegisterActivity.this,
									"恭喜您注册成功,转入登录页面!", Toast.LENGTH_SHORT)
									.show();
							Intent intent = new Intent(RegisterActivity.this,
									LoginActivity.class);
							startActivity(intent);
							finish();
						} else if (jsonObject.getInt("status") == 1) {
							Toast.makeText(RegisterActivity.this, "该用户已存在!",
									Toast.LENGTH_SHORT).show();
							et01.setText(user);
							et02.setText(pw);
							et03.setText(pw1);
							et04.setText(telephone);

						} else {
							Toast.makeText(RegisterActivity.this, "未知错误0x0001",
									Toast.LENGTH_SHORT).show();
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
				// TODO Auto-generated method stub
				Intent intent = new Intent(RegisterActivity.this,
						InitMainActivity.class);
				startActivity(intent);
				finish();
			}

		});

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent(RegisterActivity.this,
					InitMainActivity.class);
			startActivity(intent);
			finish();
		}
		return false;
	}

}