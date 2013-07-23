package com.zncm.EasyOrderMeal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;

public class AboutActivity extends Activity {

	WebView wv01 = null;
	String webUrl = "file:///android_asset/about.html";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		initMain();
	}

	private void initMain() {

		wv01 = (WebView) findViewById(R.id.about_wv01);
		try {
			wv01.loadUrl(webUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent(AboutActivity.this,
					InitMainActivity.class);
			startActivity(intent);
			finish();
		}
		return false;
	}
}
