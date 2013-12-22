package com.example.sqlite1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import static com.example.sqlite1.StringUtils.*;

public class DeleteActivity extends Activity {

	private TextView tvName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.delete_view);
		tvName = (TextView) findViewById(R.id.nameEdit);		
	}
	
	public void deleteDB(View view) {
		final String name = tvName.getText().toString();
		
		if (isEmpty(name)) {
			Toast.makeText(this, "Please provide the country name!", Toast.LENGTH_SHORT).show();
		}
		else {
			Intent returnIntent = new Intent();
			returnIntent.putExtra("countryName", name);
			setResult(RESULT_OK, returnIntent);
			finish();					
		}		
	}
	
}
