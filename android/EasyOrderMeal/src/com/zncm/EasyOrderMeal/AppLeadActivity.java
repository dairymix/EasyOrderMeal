package com.zncm.EasyOrderMeal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.ViewFlipper;

public class AppLeadActivity extends Activity implements OnGestureListener {

	ViewFlipper vf01 = null;
	GestureDetector gd01 = null;
	int count = 0;
	Context context = null;
	ChooseImageView currentImage;
	LinearLayout ll01 = null;
	int leadImages[] = new int[] { R.drawable.lead_1, R.drawable.lead_2,
			R.drawable.lead_3, R.drawable.lead_4, R.drawable.lead_5,
			R.drawable.lead_6 };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initMain();

	}

	private void initMain() {
		setContentView(R.layout.app_lead);
		gd01 = new GestureDetector(AppLeadActivity.this);
		vf01 = (ViewFlipper) findViewById(R.id.app_lead_vf01);
		ll01 = (LinearLayout) findViewById(R.id.app_lead_ll01);
		context = getApplicationContext();
		for (int i = 0; i < leadImages.length; i++) {
			ChooseImageView chooseImageView = new ChooseImageView(context);
			LeadImageView leadImageView = new LeadImageView(context,
					leadImages[i]);
			if (i == 0) {
				chooseImageView.Choose();
			}
			ll01.addView(chooseImageView);
			vf01.addView(leadImageView);

		}

	}

	class ChooseImageView extends ImageView {

		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

		public ChooseImageView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			params.setMargins(20, 0, 0, 0);
			setLayoutParams(params);
			UnChoose();

		}

		private void UnChoose() {
			// TODO Auto-generated method stub
			setBackgroundResource(R.drawable.unchoose);
		}

		private void Choose() {
			// TODO Auto-generated method stub
			setBackgroundResource(R.drawable.choose);
		}

	}

	class LeadImageView extends ImageView {
		public LeadImageView(Context context, Integer id) {
			// TODO Auto-generated constructor stub
			super(context);
			setImageResource(id);
			setScaleType(ScaleType.FIT_XY);

		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return this.gd01.onTouchEvent(event);
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		if (e1.getX() - e2.getX() > 80) {
			vf01.setInAnimation(AnimationUtils.loadAnimation(this,
					R.anim.push_left_in));
			vf01.setOutAnimation(AnimationUtils.loadAnimation(this,
					R.anim.push_left_out));
			if (count < leadImages.length - 1) {
				vf01.showNext();
				count++;
				SetImageChoose();
			} else {
				startActivity(new Intent(getApplication(),
						InitMainActivity.class));
				this.finish();
			}
			return true;
		} else if (e1.getX() - e2.getX() < -80) {
			vf01.setInAnimation(AnimationUtils.loadAnimation(this,
					R.anim.push_right_in));
			vf01.setOutAnimation(AnimationUtils.loadAnimation(this,
					R.anim.push_right_out));
			if (count > 0) {
				vf01.showPrevious();
				count--;
			}
			SetImageChoose();
			return true;
		}
		return false;
	}

	private void SetImageChoose() {
		for (int i = 0; i < ll01.getChildCount(); i++) {
			currentImage = (ChooseImageView) ll01.getChildAt(i);
			currentImage.UnChoose();
		}
		currentImage = (ChooseImageView) ll01.getChildAt(count);
		currentImage.Choose();

	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

}