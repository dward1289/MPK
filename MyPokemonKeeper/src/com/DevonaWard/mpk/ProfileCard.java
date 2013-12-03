package com.DevonaWard.mpk;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;


public class ProfileCard extends Activity {

	Typeface font;
	TextView thePTP;
	ImageView attachImage;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_card);
		
		getActionBar().setDisplayShowHomeEnabled(false);
		
		thePTP = (TextView)findViewById(R.id.thepct);
		font = Typeface.createFromAsset(getAssets(), "robotobold.ttf");
		thePTP.setTypeface(font);	
		
		attachImage = (ImageView)findViewById(R.id.imageAttach);
		
		attachImage.setClickable(true);
		attachImage.setOnTouchListener(new OnTouchListener() {  
  
		@Override
		public boolean onTouch(View arg0, MotionEvent arg1) {

			theOptions();
			
			return false;
		}
         });
	}

	//Option Alert displayed when image is tapped.
	public void theOptions(){
		
		//Creating alert Dialog		 
		AlertDialog.Builder optionsGiven = new AlertDialog.Builder(ProfileCard.this);
		 
		//Alert Title
		optionsGiven.setTitle("Attach Photo");
		 
		// Setting Dialog Message
		optionsGiven.setMessage("How would you like to attach a photo?");

		optionsGiven.setPositiveButton("Choose Avatar",
		        new DialogInterface.OnClickListener() {
		 
		            public void onClick(DialogInterface dialog, int which) {
		                
		            }
		        });

		optionsGiven.setNeutralButton("Capture Photo",
		        new DialogInterface.OnClickListener() {
		 
		            public void onClick(DialogInterface dialog, int which) {
		               
		            }
		        });

		optionsGiven.setNegativeButton("Upload from Gallery",
		        new DialogInterface.OnClickListener() {
		 
		            public void onClick(DialogInterface dialog, int which) {
		               
		            }
		        });
		//Display Options
		optionsGiven.show();


	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile_menu, menu);
		return true;
	}
}

