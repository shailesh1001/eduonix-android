package com.example.wordcount;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	private Map<String, Integer> wordCounts;
	
	public void processText(View view) {
		EditText inputText = (EditText) findViewById(R.id.inputText);
		String[] words = inputText.getText().toString().split(" ");
		
		for (String word : words) {
			if (wordCounts.containsKey(word))
				wordCounts.put(word, wordCounts.get(word) + 1);
			else
				wordCounts.put(word, 1);
		}
		
		// Output results
		EditText outputText = (EditText) findViewById(R.id.outputText);
		
		for (String key : wordCounts.keySet()) {
			final String wordCountOut = String.format("%s: %d", key, wordCounts.get(key));
			outputText.setText(String.format("%s%s\n", outputText.getText(), wordCountOut));
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		wordCounts = new HashMap<String, Integer>();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
