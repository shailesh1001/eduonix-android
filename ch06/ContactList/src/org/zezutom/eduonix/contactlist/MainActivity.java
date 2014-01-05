package org.zezutom.eduonix.contactlist;

import java.util.ArrayList;
import java.util.List;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.PhoneLookup;
import android.app.Activity;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static int index = 0;
	
	private static TextView tvName = null;
	
	private List<String> names = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tvName = (TextView) findViewById(R.id.name);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void getContacts(View view) {
		index = 0;
		Uri contacts = ContactsContract.Contacts.CONTENT_URI;		
	    Cursor cursor = null;
		
		try {
			cursor = getContentResolver().query(contacts, null, null, null, null);
			cursor.moveToFirst();
			
			// fetch all of the contact names
			do {
				final int columnIndex = cursor.getColumnIndex(PhoneLookup.DISPLAY_NAME);
				final String displayName = cursor.getString(columnIndex);
				names.add(displayName);
			} while (cursor.moveToNext());
			
		} 
		finally {
			if (cursor != null && !cursor.isClosed())
				cursor.close();
		}
		
		// display the 1st name
		tvName.setText(names.get(index));		
	}
	
	public void getNext(View view) {
		if (index < names.size() - 1)
			// display the next name
			tvName.setText(names.get(++index));			
	}
	
	public void getPrev(View view) {
		if (index > 0)
			// display the previous name
			tvName.setText(names.get(--index));			
	}
	
}
