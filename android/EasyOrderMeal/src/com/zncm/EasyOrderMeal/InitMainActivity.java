package com.zncm.EasyOrderMeal;

import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class InitMainActivity extends Activity implements OnItemClickListener {
	int[] image = { R.drawable.jrtj, R.drawable.jxcg, R.drawable.fldc,
			R.drawable.wddd };
	SharedPreferences sp = null;
	boolean Is_user_online_sp = false;

	GridView gridView = null;
	ImageView iv01 = null;
	ImageView iv02 = null;
	ImageAdapter imageAdapter = new ImageAdapter(InitMainActivity.this);

	Bundle bundle = null;
	String init_type = "";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initMain();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.init_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.init_main_item01:

			Intent intent = new Intent(InitMainActivity.this,
					AboutActivity.class);
			startActivity(intent);
			finish();
			break;
		case R.id.init_main_item02:
			sp.edit().putBoolean("Is_user_online_sp", false).commit();
			break;
		case R.id.init_main_item03:
			finish();
			break;

		default:
			break;
		}

		return true;
	}

	private void initMain() {
		setContentView(R.layout.init_main);
		sp = getSharedPreferences("EasyOrderMeal_SP", MODE_PRIVATE);
		Is_user_online_sp = sp.getBoolean("Is_user_online_sp", false);
		iv01 = (ImageView) findViewById(R.id.init_main_iv01);
		iv02 = (ImageView) findViewById(R.id.init_main_iv02);
		iv01.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(InitMainActivity.this,
						RegisterActivity.class);
				startActivity(intent);
				finish();

			}
		});
		iv02.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(InitMainActivity.this,
						LoginActivity.class);
				startActivity(intent);
				finish();

			}
		});

		gridView = (GridView) findViewById(R.id.init_main_gv01);
		gridView.setOnItemClickListener(this);
		gridView.setAdapter(imageAdapter);

	}

	class ImageAdapter extends BaseAdapter {
		Activity activity = null;

		public ImageAdapter(Activity activity) {
			this.activity = activity;
			// TODO Auto-generated constructor stub
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return image.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return image[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView = new ImageView(activity);
			imageView.setImageResource(image[position]);
			return imageView;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		switch ((int) imageAdapter.getItemId(arg2)) {
		case 0:
			Intent intent0 = new Intent(InitMainActivity.this,
					ListMenuActivity.class);
			bundle = new Bundle();
			bundle.putString("init_type", "1");
			intent0.putExtras(bundle);
			startActivity(intent0);
			finish();
			break;
		case 1:
			Intent intent1 = new Intent(InitMainActivity.this,
					RestaurantCategoryActivity.class);
			startActivity(intent1);
			finish();
			break;
		case 2:
			Intent intent2 = new Intent(InitMainActivity.this,
					ListMenuActivity.class);
			bundle = new Bundle();
			bundle.putString("init_type", "2");
			intent2.putExtras(bundle);
			startActivity(intent2);
			finish();
			break;

		case 3:
			Intent intent3 = new Intent(InitMainActivity.this,
					PersonInfoActivity.class);
			startActivity(intent3);
			finish();
			break;

		default:
			break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == event.KEYCODE_BACK) {
			exitDialog();
		}
		return false;
	}

	private void exitDialog() {
		Dialog dialog = new AlertDialog.Builder(InitMainActivity.this)
				.setTitle("退出").setIcon(R.drawable.info).setMessage("真的需要退出么?")
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

					}
				})
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						InitMainActivity.this.finish();
					}
				}).create();

		dialog.show();

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

	}

}