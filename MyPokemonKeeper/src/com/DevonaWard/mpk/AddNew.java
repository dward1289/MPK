package com.DevonaWard.mpk;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
	int size = 1024;
	byte theByte;
	InputStream inputStreamq;
	String bufferString;
	JSONArray JSONobject;
	Context context = this;
	ProgressDialog dialog;
	//List all Pokemon
	ArrayList<String> pNameList = new ArrayList<String>();
	//Pokemon types list
	ArrayList<String>listNormal = new ArrayList<String>();
	ArrayList<String>listFighting = new ArrayList<String>();
	ArrayList<String>listRock = new ArrayList<String>();
	ArrayList<String>listFire = new ArrayList<String>();
	ArrayList<String>listPoison = new ArrayList<String>();
	ArrayList<String>listGhost = new ArrayList<String>();
	ArrayList<String>listWater = new ArrayList<String>();
	ArrayList<String>listGround = new ArrayList<String>();
	ArrayList<String>listDragon = new ArrayList<String>();
	ArrayList<String>listElectric = new ArrayList<String>();
	ArrayList<String>listFlying = new ArrayList<String>();
	ArrayList<String>listDark = new ArrayList<String>();
	ArrayList<String>listGrass = new ArrayList<String>();
	ArrayList<String>listPsychic = new ArrayList<String>();
	ArrayList<String>listSteel = new ArrayList<String>();
	ArrayList<String>listIce = new ArrayList<String>();
	ArrayList<String>listBug = new ArrayList<String>();
	ArrayList<String>listFairy= new ArrayList<String>();
	ArrayList<String>itemList= new ArrayList<String>();
	ArrayAdapter<String> spinnerAdapter;
	String pName;
	ImageView help;
	String typeSelected;
	String nameSelected;
	String pType;
	String selDate;
	String selLVL;
	String imageTXT;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addnew_layout);
		
		//Hide application logo from action bar
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowHomeEnabled(false);
		
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
		
		
		//Help image onTouch
		help = (ImageView)findViewById(R.id.helpImg);
		help.setOnTouchListener(new OnTouchListener() {  
  
		@Override
		public boolean onTouch(View arg0, MotionEvent arg1) {

			if(arg1.getAction() == MotionEvent.ACTION_DOWN){
				helpHint();
			}
			return true;
		}
         });
		
		DownloadJSONTask task = new DownloadJSONTask();
		task.execute();
		
		
		
		pokemonType.setOnItemSelectedListener(new OnItemSelectedListener(){
			
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
				 			
			if(pos == 0){
				itemList = listNormal;				
				populateSpinner();	
			}
			if(pos == 1){
				itemList = listFighting;	
				populateSpinner();
			}
			if(pos == 2){
				itemList = listRock;	
				populateSpinner();
			}
			if(pos == 3){
				itemList = listFire;	
				populateSpinner();
			}
			if(pos == 4){
				itemList = listPoison;	
				populateSpinner();
			}
			if(pos == 5){
				itemList = listGhost;	
				populateSpinner();
			}
			if(pos == 6){
				itemList = listWater;	
				populateSpinner();
			}
			if(pos == 7){
				itemList = listGround;	
				populateSpinner();
			}
			if(pos == 8){
				itemList = listDragon;	
				populateSpinner();
			}
			if(pos == 9){
				itemList = listElectric;	
				populateSpinner();
			}
			if(pos == 10){
				itemList = listFlying;	
				populateSpinner();
			}
			if(pos == 11){
				itemList = listDark;	
				populateSpinner();
			}
			if(pos == 12){
				itemList = listGrass;	
				populateSpinner();
			}
			if(pos == 13){
				itemList = listPsychic;	
				populateSpinner();
			}
			if(pos == 14){
				itemList = listSteel;	
				populateSpinner();
			}
			if(pos == 15){
				itemList = listIce;	
				populateSpinner();
			}
			if(pos == 16){
				itemList = listBug;	
				populateSpinner();
			}
			if(pos == 17){
				itemList = listFairy;	
				populateSpinner();
			}
			
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}	
	});
		
		pokemonName.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
				nameSelected = parent.getItemAtPosition(pos).toString();
				String lower = nameSelected.toLowerCase(Locale.ENGLISH);
				imageTXT = "http://img.pokemondb.net/artwork/"+lower+".jpg";
			    Log.i("THE IMAGE", imageTXT);
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}	
	});
		
	}

	private class DownloadJSONTask extends AsyncTask<String, Void, String> {
		
		//Status update while getting data.
		@Override
	    protected void onPreExecute() {        
	        dialog = new ProgressDialog(context);
	        dialog.setTitle("Please Wait");
	        dialog.setMessage("Loading 600+ Pokemon.\nThis will take a little time.");
	        dialog.setIndeterminate(true);
	        dialog.show();            
	    }
		
		//Load data
	    @Override
	    protected String doInBackground(String... strings) {
	    	
	    	 try{
	    		 inputStreamq = getAssets().open("gistfile1.json");
                 BufferedInputStream bin = new BufferedInputStream(inputStreamq);
                 
                 byte[] contentBytes = new byte[1024];
                 int bytesRead = 0;
                 StringBuffer responseBuffer = new StringBuffer();
                 
                 while((bytesRead = bin.read(contentBytes)) != -1){
                         bufferString = new String(contentBytes,0,bytesRead);
                         responseBuffer.append(bufferString);
                 }
                 
                 try {
                     JSONArray jsonArray = new JSONArray(responseBuffer.toString());
                     
                     int n = jsonArray.length();
                     for(int i = 0;i<n; i++){
                             JSONObject jsonObject = jsonArray.getJSONObject(i);
                             pName = jsonObject.getString("name");
                             pNameList.add(pName);
                             
                             pType = jsonObject.getString("type");
                             
                             //Sort data by category
                             if(pType.contains("Normal")){
                            	 pName = jsonObject.getString("name");
                            	 listNormal.add(pName);
                             }
                             if(pType.contains("Fighting")){
                            	 pName = jsonObject.getString("name");
                            	 listFighting.add(pName);
                             }
                             if(pType.contains("Rock")){
                            	 pName = jsonObject.getString("name");
                            	 listRock.add(pName);
                             }
                             if(pType.contains("Fire")){
                            	 pName = jsonObject.getString("name");
                            	 listFire.add(pName);
                             }
                             if(pType.contains("Poison")){
                            	 pName = jsonObject.getString("name");
                            	 listPoison.add(pName);
                             }
                             if(pType.contains("Ghost")){
                            	 pName = jsonObject.getString("name");
                            	 listGhost.add(pName);
                             }
                             if(pType.contains("Water")){
                            	 pName = jsonObject.getString("name");
                            	 listWater.add(pName);
                             }
                             if(pType.contains("Ground")){
                            	 pName = jsonObject.getString("name");
                            	 listGround.add(pName);
                             }
                             if(pType.contains("Dragon")){
                            	 pName = jsonObject.getString("name");
                            	 listDragon.add(pName);
                             }if(pType.contains("Electric")){
                            	 pName = jsonObject.getString("name");
                            	 listElectric.add(pName);
                             }if(pType.contains("Flying")){
                            	 pName = jsonObject.getString("name");
                            	 listFlying.add(pName);
                             }if(pType.contains("Dark")){
                            	 pName = jsonObject.getString("name");
                            	 listDark.add(pName);
                             }if(pType.contains("Grass")){
                            	 pName = jsonObject.getString("name");
                            	 listGrass.add(pName);
                             }
                             if(pType.contains("Psychic")){
                            	 pName = jsonObject.getString("name");
                            	 listPsychic.add(pName);
                             }
                             if(pType.contains("Steel")){
                            	 pName = jsonObject.getString("name");
                            	 listSteel.add(pName);
                             }
                             if(pType.contains("Ice")){
                            	 pName = jsonObject.getString("name");
                            	 listIce.add(pName);
                             }
                             if(pType.contains("Bug")){
                            	 pName = jsonObject.getString("name");
                            	 listBug.add(pName);
                             }                                                 
                     }
                 } catch (JSONException e1) {
                     // TODO Auto-generated catch block
                     e1.printStackTrace();
             }
                 
                 return responseBuffer.toString();
         }catch (Exception e){
                 Log.e("DATA REAT ERROR", "getURLStringResponse");
         }
			return null;
		}

	    @Override
        protected void onPostExecute(String result){
                        populateSpinner();
                        dialog.dismiss();     
        }        
}
	

	//Date picker created
		 public void selectDate(View view) {
	   	  	DialogFragment newFragment = new SelectDateFragment();
	   	  	newFragment.show(getFragmentManager(), "DatePicker");
	   	  }
	   	  
		 //Display Date in text view
		 public void populateSetDate(int year, int month, int day) {
			 
	   	  	theDate.setText("Caught on "+getMonth(month).toString()+" "+day+", "+year+".");
	   	  }
		 
		 //Get date from date picker
	   	  @SuppressLint("ValidFragment")
		  public class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
	   		  @Override
	   		  public Dialog onCreateDialog(Bundle savedInstanceState) {
	   			  final Calendar calendar = Calendar.getInstance();
	   			  int yy = calendar.get(Calendar.YEAR);
	   			  int mm = calendar.get(Calendar.MONTH);
	   			  int dd = calendar.get(Calendar.DAY_OF_MONTH);
	   			  return new DatePickerDialog(getActivity(), this, yy, mm, dd);
	   	  }
	   	   
	   		  public void onDateSet(DatePicker view, int yy, int mm, int dd) {
	   			  populateSetDate(yy, mm+1, dd);
	   		  }
	   	  }
	   	  
	public String getMonth(int month) {
	   	    return new DateFormatSymbols().getMonths()[month-1];
	   	}
	
	public void helpHint(){
		
		//Create alert Dialog		 
		AlertDialog.Builder helper = new AlertDialog.Builder(AddNew.this);
		 
		//Alert Title
		helper.setTitle("Level");
		 
		// Setting Dialog Message
		helper.setMessage("The number located at the right of a Pokemon's name during battle.");

		helper.setPositiveButton("OK",
		        new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						
					}
		        });
		//Display Help
		helper.show();
	}
	public void populateSpinner(){
		//Spinner adapter
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, itemList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAdapter.notifyDataSetChanged();
        pokemonName.setAdapter(spinnerAdapter);
	}
	
	//Check data before saving
	public void checkData(){
		if(theDate.getText().toString().trim().equals("")){	 
			AlertDialog.Builder userErr = new AlertDialog.Builder(AddNew.this);
			userErr.setTitle("Date");
			userErr.setMessage("Please select date.");
			userErr.setPositiveButton("OK",
			        new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog, int which) {			            
			            }
			        });
			userErr.show();	
		}else if(lvlNum.getText().toString().trim().equals("") || lvlNum.getText().toString().length() < 0){	 
			AlertDialog.Builder userErr = new AlertDialog.Builder(AddNew.this);
			userErr.setTitle("Level");
			userErr.setMessage("Please enter the level of the Pokemon.");
			userErr.setPositiveButton("OK",
			        new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog, int which) {			            
			            }
			        });
			userErr.show();	
		}
		
		if(lvlNum.getText().toString().length() != 0 && theDate.getText().toString().length() != 0){
			typeSelected = pokemonType.getSelectedItem().toString();
			nameSelected = pokemonName.getSelectedItem().toString();
			selDate = theDate.getText().toString();
			selLVL = lvlNum.getText().toString();
			
			DBHandler db = new DBHandler(this);
			db.addPokemon(new thePokemon(nameSelected, typeSelected,selDate, selLVL, imageTXT));
			List<thePokemon> pokemon = db.getAllPokemon();
			for (thePokemon cn : pokemon) {
	            String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Type: " + cn.getType()+ "Date: " + cn.getDate()+ " ,LVL: " + cn.getLevel()+ " ,Image: " + cn.getImage();
	            // Writing Pokemon to log
	            Log.i("SQLite Working",log);
	    }
			Toast.makeText(getApplicationContext(), "Pokemon Saved", Toast.LENGTH_SHORT).show();
			
			Intent intent = new Intent(this, CapturedList.class);
			finish();
            startActivity(intent);
		}
	}
	
	//Share data intent
	public void shareData(){
		typeSelected = pokemonType.getSelectedItem().toString();
		nameSelected = pokemonName.getSelectedItem().toString();
		
		Intent mShareIntent = new Intent();
		mShareIntent.setAction(Intent.ACTION_SEND);
		mShareIntent.setType("text/plain");
		mShareIntent.putExtra(Intent.EXTRA_TEXT, "I just captured "+nameSelected+"!" + " It's a "+typeSelected+" type of Pokemon.");
		startActivity(mShareIntent);
	}
	
	//Saves data to database.
	public void saveData(View v){
		checkData();
		

		
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
	        case R.id.saved:
	        	Intent intent2 = new Intent(this, CapturedList.class);
	            startActivity(intent2); 
	            break;
	        case R.id.shareIt2:
	        	shareData();
	            break;	        
	        default:
	            break;
	    }
	    return true;
	}
	

}
