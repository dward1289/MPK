package com.DevonaWard.mpk;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class CapturedList extends Activity {

	Typeface font;
	TextView theTitle;
	Bitmap bmp;
	URL newurl;
	ListView theListCap;
	SimpleAdapter adapter;
	List<thePokemon> pokemon;
	int IDI;
	thePokemon cm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.captured_list);
		
		getActionBar().setDisplayShowHomeEnabled(false);
		
		theTitle = (TextView)findViewById(R.id.theTitle);		
		theListCap = (ListView)findViewById(R.id.CapturedList);
		font = Typeface.createFromAsset(getAssets(), "robotobold.ttf");
		theTitle.setTypeface(font);
		
		
		
		final DBHandler db = new DBHandler(this);
		pokemon = db.getAllPokemon();
		
		ArrayList<theItems> pokemonResults = GetPokemon();
        theListCap.setAdapter(new PokemonAdapter(this, pokemonResults));
 
        theListCap.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = theListCap.getItemAtPosition(position);
                theItems fullObject = (theItems)o;
                Toast.makeText(CapturedList.this, "You have chosen: " + " " + fullObject.getName(), Toast.LENGTH_LONG).show();
                
                db.deletePokemon(cm.getID());
                db.close();

            }
        });
    }

    private ArrayList<theItems> GetPokemon(){
     ArrayList<theItems> results = new ArrayList<theItems>();
     
     for (thePokemon cn : pokemon) {
     theItems sr = new theItems();
     	 sr.setId(cn.getID());
         sr.setName(cn.getName());
         sr.setDate(cn.getDate());
         sr.setType(cn.getType());
         sr.setLevel("Level "+cn.getLevel());
         sr.setImage(cn.getImage());
         
         String log = " ,Name: " + cn.getName() + " ,Type: " + cn.getType()+ "Date: " + cn.getDate()+ " ,LVL: " + cn.getLevel()+ " ,Image: " + cn.getImage();
         // Writing Pokemon to log
         Log.i("SQLite Working",log);
         results.add(sr);
    }
     return results;
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
