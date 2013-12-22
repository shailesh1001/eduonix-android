package com.example.mydiary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class InsertActivity extends Activity {

	private TextView tvSubject;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.insert_view);
		tvSubject = (TextView) findViewById(R.id.subjectInsert);
	}
	
	public void insertDB(View view) {
		final String subject = tvSubject.getText().toString();
		
		if (subject == null || subject.trim().isEmpty()) {
			Toast.makeText(this, "Please say what are you up to!", Toast.LENGTH_SHORT).show();
		}
		else {
			Intent returnIntent = new Intent();
			String[] insertResults = {subject}; 
			returnIntent.putExtra("results", insertResults);
			setResult(RESULT_OK, returnIntent);
			finish();					
		}		
	}
}
