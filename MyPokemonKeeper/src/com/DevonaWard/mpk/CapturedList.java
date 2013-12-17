package com.DevonaWard.mpk;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
	ArrayList<theItems> results;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.captured_list);
		
		getActionBar().setDisplayShowHomeEnabled(false);
		
		theTitle = (TextView)findViewById(R.id.theTitle);		
		theListCap = (ListView)findViewById(R.id.CapturedList);
		font = Typeface.createFromAsset(getAssets(), "robotobold.ttf");
		theTitle.setTypeface(font);
		
		
		//Load data from SQLite database
		final DBHandler db = new DBHandler(this);
		pokemon = db.getAllPokemon();
		
		ArrayList<theItems> pokemonResults = GetPokemon();
        theListCap.setAdapter(new PokemonAdapter(this, pokemonResults));
 
        theListCap.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, final int position, long id) {
                Object o = theListCap.getItemAtPosition(position);
                theItems fullObject = (theItems)o;
                Toast.makeText(CapturedList.this, "You have chosen: " + " " + fullObject.getName(), Toast.LENGTH_LONG).show();
                
              //Create alert Dialog		 
    			AlertDialog.Builder helper = new AlertDialog.Builder(CapturedList.this);
    			 
    			//Alert Title
    			helper.setTitle("Delete Pokemon");
    			 
    			// Setting Dialog Message
    			helper.setMessage("Delete the selected Pokemon from your Captured List?");

    			helper.setPositiveButton("YES",
    			        new DialogInterface.OnClickListener() {

    						@Override
    						public void onClick(DialogInterface dialog, int which) {
    							db.deletePokemon(results.get(position).getId());
    			                db.close();
    			                Intent intent = getIntent();
    			                finish();
    			                startActivity(intent);
    						}
    			        });
    			helper.setNegativeButton("NO",
    			        new DialogInterface.OnClickListener() {

    						@Override
    						public void onClick(DialogInterface dialog, int which) {
    							
    						}
    			        });
    			//Display Help
    			helper.show();
                
                

            }
        });
    }

	//Populate list view
    private ArrayList<theItems> GetPokemon(){
    results = new ArrayList<theItems>();
     
     for (thePokemon cn : pokemon) {
     theItems sr = new theItems();
     	 sr.setId(cn.getID());
         sr.setName(cn.getName());
         sr.setDate(cn.getDate());
         sr.setType("Type: "+cn.getType());
         sr.setLevel("Level "+cn.getLevel());
         sr.setImage(cn.getImage());
  
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
	
	public void confirmDelete(){			
			
		}		
}
