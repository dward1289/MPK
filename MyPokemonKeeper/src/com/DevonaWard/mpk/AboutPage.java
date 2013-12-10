package com.DevonaWard.mpk;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class AboutPage extends Activity {
	
	Typeface font;
	TextView theName;
	TextView theDev;
	TextView theVer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_layout);
		
		getActionBar().setDisplayShowHomeEnabled(false);
		
		theName = (TextView)findViewById(R.id.appName);
		theVer = (TextView)findViewById(R.id.versionName);
		theDev = (TextView)findViewById(R.id.developerName);
		
		font = Typeface.createFromAsset(getAssets(), "robotothin.ttf");

		theName.setTypeface(font);		
		theDev.setTypeface(font);
		theVer.setTypeface(font);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.about_menu, menu);
		return true;
	}

}

