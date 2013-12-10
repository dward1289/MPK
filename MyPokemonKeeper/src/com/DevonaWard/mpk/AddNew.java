package com.DevonaWard.mpk;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
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
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
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
	int size = 1024;
	byte theByte;
	InputStream inputStreamq;
	String bufferString;
	JSONArray JSONobject;
	Context context = this;
	ProgressDialog dialog;
	ArrayList<String> pNameList= new ArrayList<String>();
	String pName;
	ImageView help;
	
	
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
                 return responseBuffer.toString();
         }catch (Exception e){
                 Log.e("DATA REAT ERROR", "getURLStringResponse");
         }
			return null;
		}

	    @Override
        protected void onPostExecute(String result){
                try {
                        JSONArray jsonArray = new JSONArray(result);
                        
                        int n = jsonArray.length();
                        for(int i = 0;i<n; i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                
                                pName= jsonObject.getString("name");
                                pNameList.add(pName);
                        }
                        
                        Log.i("WE HAVE DATA", pNameList.toString());
                        populateSpinner();
                        dialog.dismiss();
                        
                        
                } catch (JSONException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                }
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
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, pNameList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pokemonName.setAdapter(spinnerAdapter);
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
