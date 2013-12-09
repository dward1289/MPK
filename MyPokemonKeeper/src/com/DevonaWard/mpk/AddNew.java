package com.DevonaWard.mpk;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class AddNew extends Activity {

	Typeface font;
	Typeface font2;
	TextView theTitle;
	TextView theType;
	TextView theName;
	TextView theDate;
	TextView theLvl;
	EditText lvlNum;
	Spinner pokemonType;
	Spinner pokemonName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addnew_layout);
		
		getActionBar().setDisplayShowHomeEnabled(false);
		
		theTitle = (TextView)findViewById(R.id.theTitle);
		theType = (TextView)findViewById(R.id.typeText);
		theName = (TextView)findViewById(R.id.pokemonText);
		theDate = (TextView)findViewById(R.id.dateText);
		theLvl = (TextView)findViewById(R.id.lvlNumber);
		lvlNum = (EditText)findViewById(R.id.lvlText);
		pokemonName = (Spinner)findViewById(R.id.pokemonSpinner);
		pokemonType = (Spinner)findViewById(R.id.typeSpinner);
		
		font = Typeface.createFromAsset(getAssets(), "robotobold.ttf");
		font2 = Typeface.createFromAsset(getAssets(), "robotothin.ttf");
		theTitle.setTypeface(font);
		theType.setTypeface(font2);
		theName.setTypeface(font2);
		theDate.setTypeface(font2);
		theLvl.setTypeface(font2);
		lvlNum.setTypeface(font2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case android.R.id.home:
		    	Intent intent = new Intent(this, CapturedList.class);
	            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            startActivity(intent);            
	            break;
	        case R.id.shareIt2:
	            break;	        
	        default:
	            break;
	    }
	    return true;
	}
	

}
