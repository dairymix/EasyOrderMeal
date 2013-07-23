package com.zncm.EasyOrderMeal;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

import com.zncm.EasyOrderMeal.Json.JsonUtils;

public class GetInfoActivity extends Activity {
	TextView tv01 = null;
	JSONArray jsonArray = null;
	JSONObject jsonObject = null;
	JsonUtils jsonUtils = new JsonUtils();
	String u_name = "xxxziheng";
	String u_pw = "rjgc2009X";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		InitMain();
	}

	private void InitMain() {
		setContentView(R.layout.get_info);
		tv01 = (TextView) findViewById(R.id.get_info_tv01);

		try {

			// jsonArray = jsonUtils.GetInfo("login/dologin?user=" + u_name
			// + "&pw=" + u_pw);

			jsonArray = jsonUtils.GetInfo("recommendation/getrec");

			if (jsonArray.length() == 0) {
				System.out.println("ERROR");
			}

			// jsonObject = jsonUtils.GetInfo2("login/dologin?user=" + u_name
			// + "&pw=" + u_pw);
			System.out.println("jsonArray:　　" + jsonArray);
			System.out.println("lengthlength:　　" + jsonArray.length());

			System.out.println("jsonArraytb_user_name:　　"
					+ jsonArray.getJSONObject(0).get("tb_menu_name"));

			// System.out.println("jsonObject:　　" + jsonObject);

			// jsonObject = jsonUtils.GetInfo("1");
			// System.out.println("?? -- " + jsonObject);
			// String name = jsonObject.getString("name");
			// String nick = jsonObject.getString("nick");
			// JSONObject contact = (JSONObject) jsonObject.get("contact");
			// String contact_email = contact.getString("email");
			// String contact_website = contact.getString("website");
			// tv01.setText(name + "\n" + name + "\n" + nick + "\n" + contact
			// + "\n" + contact_email + "\n" + contact_website + "");
			// System.out.println("?? " + name + "\n" + name + "\n" + nick +
			// "\n"
			// + contact + "\n" + contact_email + "\n" + contact_website);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent(GetInfoActivity.this,
					InitMainActivity.class);
			startActivity(intent);
			finish();
		}
		return false;
	}

}