package com.example.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

	private RelativeLayout layout;
	private RadioGroup choice;
	
	private RelativeLayout getLayout() {
		if (layout == null) 
			layout = (RelativeLayout) findViewById(R.id.mainContainer);		
		return layout;
	}
	
	private RadioGroup getChoice() {
		if (choice == null)
			choice = (RadioGroup) findViewById(R.id.radioGroup1);
		return choice;
	}

    private int getSelectionId() {
		return getChoice().getCheckedRadioButtonId();    	
    }
	
    private int getColor() {
    	final int selectionId = getSelectionId();
		final RadioButton selection = (RadioButton) getChoice().findViewById(selectionId);				
		
		if (selectionId <= 0)
			return Color.argb(255, 0, 0, 0);
		
		int red = 0, green = 0, blue = 0;
		switch(selection.getId()) {				
			case R.id.radio0:
				red = 255;
				break;
			case R.id.radio1:
				green = 255;
				break;
			case R.id.radio2:
				blue = 255;
				break;
		}
		return Color.argb(255, red, green, blue);
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final Button btn = (Button) findViewById(R.id.button1);
        btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getLayout().setBackgroundColor(getColor());
			}
		});
        
        final Button btnDetail = (Button) findViewById(R.id.button2);
        btnDetail.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String color = "Black";
				switch(getSelectionId()) {
					case R.id.radio0:
						color = "Red";
						break;
					case R.id.radio1:
						color = "Green";
						break;
					case R.id.radio2:
						color = "Blue";
						break;
				}
								
				// Delegate processing from this activity (main) to the other one (detail)
				final Intent intentDetail = new Intent(MainActivity.this, DetailActivity.class);
				intentDetail.putExtra("color", color);
				// start an activity using the new intent, bear in mind the activity must be registered in the manifest
				startActivity(intentDetail);				
			}
		});
        
        final Button btnColorCode = (Button) findViewById(R.id.button3);
        btnColorCode.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final Intent intentColorCode = new Intent(MainActivity.this, ColorCodeActivity.class);
				final int color = getColor();
				intentColorCode.putExtra("color", color);
				intentColorCode.putExtra("colorCode", String.format("#%06X", (0xFFFFFF & color)));
				startActivity(intentColorCode);
			}
		});        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
        
}
