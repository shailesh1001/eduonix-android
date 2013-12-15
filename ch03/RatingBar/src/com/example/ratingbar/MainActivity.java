package com.example.ratingbar;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void voteUp(View view) {
		vote(0.5f);
	}
	
	public void voteDown(View view) {
		vote(-0.5f);
	}
	
	private void vote(float step) {
		RatingBar bar = (RatingBar) findViewById(R.id.ratingBar1);
		bar.setRating(bar.getRating() + step);
		
		final float rating = bar.getRating();
		Button down = (Button) findViewById(R.id.buttonDown);
		Button up = (Button) findViewById(R.id.buttonUp);
		
		down.setEnabled(rating > 0f);
		up.setEnabled(rating < 4f);	
	}
}
