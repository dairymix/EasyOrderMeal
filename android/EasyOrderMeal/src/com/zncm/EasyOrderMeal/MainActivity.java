package com.zncm.EasyOrderMeal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		InitMain();
	}

	private void InitMain() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(MainActivity.this, AppLeadActivity.class);
		startActivity(intent);
		finish();
	}
}