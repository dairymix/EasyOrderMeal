package com.zncm.EasyOrderMeal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.ImageView;

public class ErrorPageActivity extends Activity {
	WebView wv01 = null;
	String webUrl = "file:///android_asset/error_page.html";
	ImageView iv01 = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initMain();

	}

	private void initMain() {
		setContentView(R.layout.error_page);
		iv01 = (ImageView) findViewById(R.id.error_page_iv01);
		iv01.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ErrorPageActivity.this,
						InitMainActivity.class);
				startActivity(intent);
				finish();
			}
		});

		wv01 = (WebView) findViewById(R.id.error_page_wv01);
		try {
			wv01.loadUrl(webUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent(ErrorPageActivity.this,
					InitMainActivity.class);
			startActivity(intent);
			finish();
		}
		return false;
	}

}
