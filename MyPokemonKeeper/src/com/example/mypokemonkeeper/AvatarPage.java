package com.example.mypokemonkeeper;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class AvatarPage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.avatar_page);
		
		//Hides logo and displays title of application
		getActionBar().setDisplayShowHomeEnabled(false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.avatar_menu, menu);
		return true;
	}

}

