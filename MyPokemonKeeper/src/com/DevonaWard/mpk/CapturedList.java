package com.DevonaWard.mpk;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class CapturedList extends Activity {

	Typeface font;
	TextView theTitle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.captured_list);
		
		getActionBar().setDisplayShowHomeEnabled(false);
		
		theTitle = (TextView)findViewById(R.id.theTitle);
		font = Typeface.createFromAsset(getAssets(), "robotobold.ttf");
		theTitle.setTypeface(font);	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.captured_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.addNew:
	        	addOpen();
	            break;
	        case R.id.profileCard:
	            profileOpen();
	            break;
	        case R.id.aboutMG:
	            aboutOpen();
	            break;
	        default:
	            break;
	    }
	    return true;
	}
	
	public void aboutOpen() {
	    Intent intent = new Intent(this, AboutPage.class);
	    startActivity(intent);
	}
	
	public void profileOpen() {
	    Intent intent = new Intent(this, ProfileCard.class);
	    startActivity(intent);
	}
	
	public void addOpen() {
	    Intent intent = new Intent(this, AddNew.class);
	    startActivity(intent);
	}
}
