package com.example.listviewdemo1;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String[] countries = getResources().getStringArray(R.array.countries);
		this.setListAdapter(
				new ArrayAdapter<String>(this, 
						R.layout.list_element, R.id.countryName, countries));
		
		ListView listView = getListView();
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(
					AdapterView<?> parent,
					View clickView,
					int position,
					long id) {
				RelativeLayout item = (RelativeLayout) clickView;
				TextView countryText = (TextView) item.getChildAt(0);
				Intent detailIntent = new Intent(MainActivity.this, DetailActivity.class);
				detailIntent.putExtra("country", countryText.getText().toString());
				startActivity(detailIntent);				
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
