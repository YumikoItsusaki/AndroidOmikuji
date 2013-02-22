package makeApplication.omikuji;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class OmikujiActivity extends Activity {

	private OmikujiParts[] omikujiShelf = new OmikujiParts[20];

	private OmikujiBox omikujibox = new OmikujiBox();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.omikuji);

		SharedPreferences pref = 
				PreferenceManager.getDefaultSharedPreferences(this);
		boolean value = pref.getBoolean("button", false);
		
		Button btn = (Button)findViewById(R.id.button1);
		btn.setVisibility(value ? View.VISIBLE : View.INVISIBLE);
	  
		this.omikujibox.setImageView((ImageView) findViewById(R.id.imageView1));
		for (int i = 0; i < 20; i++) {
			omikujiShelf[i] = new OmikujiParts(R.drawable.result2,
					R.string.contents1);
		}
		omikujiShelf[0].drawID = R.drawable.result1;
		omikujiShelf[0].fortuneID = R.string.contents2;

		omikujiShelf[1].drawID = R.drawable.result3;
		omikujiShelf[1].fortuneID = R.string.contents9;

		omikujiShelf[2].fortuneID = R.string.contents3;
		omikujiShelf[3].fortuneID = R.string.contents4;
		omikujiShelf[4].fortuneID = R.string.contents5;
		omikujiShelf[5].fortuneID = R.string.contents6;

	}

	public void onButtonClick(View v) {

		this.omikujibox.shake();

		/*
		 * TranslateAnimation translate = new TranslateAnimation(0, 0, 0, -100);
		 * translate.setRepeatMode(Animation.REVERSE);
		 * translate.setRepeatCount(5); translate.setDuration(100);
		 * 
		 * ImageView imageView = (ImageView)findViewById(R.id.imageView1);
		 * 
		 * RotateAnimation rotate = new RotateAnimation(0, -36,
		 * imageView.getWidth() / 2, imageView.getHeight() / 2);
		 * rotate.setDuration(200);
		 * 
		 * AnimationSet set = new AnimationSet(true); set.addAnimation(rotate);
		 * set.addAnimation(translate);
		 * 
		 * 
		 * imageView.startAnimation(set);
		 * 
		 * /* ImageView image = new ImageView(this);
		 * image.setImageResource(R.drawable.result1); setContentView(image);
		 */

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			if (0 < this.omikujibox.getNumber()) {
				this.drawResult();
			}
		}
		return super.onTouchEvent(event);
	}

	public void drawResult() {

		OmikujiParts op = omikujiShelf[omikujibox.getNumber()];

		setContentView(R.layout.fortune);

		ImageView imageView = (ImageView) findViewById(R.id.imageView1);
		imageView.setImageResource(op.drawID);

		TextView textView = (TextView) findViewById(R.id.textView1);
		textView.setTextColor(Color.BLACK);
		textView.setText(op.fortuneID);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		/*
		Toast toast = Toast.makeText(this, item.getTitle(), Toast.LENGTH_LONG);
		toast.show();
		*/
		
		if (item.getItemId() == R.id.item1) {
			Intent intent = new Intent(this, OmikujiPreferenceActivity.class);
			startActivity(intent);
		}
		
		return super.onOptionsItemSelected(item);
	}
}
