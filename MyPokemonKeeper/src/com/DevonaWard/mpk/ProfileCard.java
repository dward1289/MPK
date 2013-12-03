package com.DevonaWard.mpk;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class ProfileCard extends Activity {

	//Title
	Typeface font;
	Typeface font2;
	TextView thePTP;
	
	//TextViews
	TextView theUsername;
	TextView theFriendCode;
	TextView theFavPoke;
	
	//EditTextView
	EditText unInput;
	EditText theCode1;
	EditText theCode2;
	EditText theCode3;
	EditText favInput;
	
	//Photo data
	ImageView attachImage;
    private static final int PHOTO_CODE = 100;
    private static int RESULT_LOAD_IMAGE = 1;
	Bitmap photo;
	Bitmap resized;
	
	//The Strings for the data
	String unData;
	String fcData;
	String fpData;
	String c1;
	String c2;
	String c3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_card);
		
		//Hide application logo from action bar
		getActionBar().setDisplayShowHomeEnabled(false);
		
		//Add the font to text views and edit texts
		thePTP = (TextView)findViewById(R.id.thepct);
		theUsername = (TextView)findViewById(R.id.theUsername);
		theFriendCode = (TextView)findViewById(R.id.theFriendCode);
		theFavPoke = (TextView)findViewById(R.id.theFavP);
		unInput = (EditText)findViewById(R.id.userTxt);
		theCode1 = (EditText)findViewById(R.id.code1);
		theCode2 = (EditText)findViewById(R.id.code2);
		theCode3 = (EditText)findViewById(R.id.code3);
		favInput = (EditText)findViewById(R.id.theFavTxt);
		
		font = Typeface.createFromAsset(getAssets(), "robotobold.ttf");
		thePTP.setTypeface(font);
		font2 = Typeface.createFromAsset(getAssets(), "robotothin.ttf");
		theUsername.setTypeface(font2);
		theFriendCode.setTypeface(font2);
		theFavPoke.setTypeface(font2);
		unInput.setTypeface(font2);
		theCode1.setTypeface(font2);
		theCode2.setTypeface(font2);
		theCode3.setTypeface(font2);
		favInput.setTypeface(font2);
		
		
		//ImageView on touch listener
		attachImage = (ImageView)findViewById(R.id.imageAttach);
		attachImage.setOnTouchListener(new OnTouchListener() {  
  
		@Override
		public boolean onTouch(View arg0, MotionEvent arg1) {

			if(arg1.getAction() == MotionEvent.ACTION_DOWN){
				theOptions();
			}
			return true;
		}
         });
	}

	//Option Alert displayed when image is tapped.
	public void theOptions(){
		
		//Create alert Dialog		 
		AlertDialog.Builder optionsGiven = new AlertDialog.Builder(ProfileCard.this);
		 
		//Alert Title
		optionsGiven.setTitle("Attach Photo");
		 
		// Setting Dialog Message
		optionsGiven.setMessage("How would you like to attach a photo?");

		optionsGiven.setPositiveButton("Choose Avatar",
		        new DialogInterface.OnClickListener() {
		 
		            public void onClick(DialogInterface dialog, int which) {
		            	selectAvatar();
		            }
		        });

		optionsGiven.setNeutralButton("Capture Photo",
		        new DialogInterface.OnClickListener() {
		 
		            public void onClick(DialogInterface dialog, int which) {
		            	Intent cameraIntent=new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		    			startActivityForResult(cameraIntent, PHOTO_CODE);
		            }
		        });

		optionsGiven.setNegativeButton("Upload from Gallery",
		        new DialogInterface.OnClickListener() {
		 
		            public void onClick(DialogInterface dialog, int which) {
		            	Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		            	startActivityForResult(i, RESULT_LOAD_IMAGE);
		            }
		        });
		//Display Options
		optionsGiven.show();


	}
	
	    //Displays image if captured or uploaded from gallery
		@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			if (requestCode == PHOTO_CODE) { 
	            photo = (Bitmap) data.getExtras().get("data"); 
	            resized = Bitmap.createScaledBitmap(photo,(int)(photo.getWidth()*2), (int)(photo.getHeight()*2), true);
	            attachImage.setImageBitmap(resized);    
			}
			else if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
		            Uri selectedImage = data.getData();
		            String[] filePathColumn = { MediaStore.Images.Media.DATA };
		 
		            Cursor cursor = getContentResolver().query(selectedImage,
		                    filePathColumn, null, null, null);
		            cursor.moveToFirst();
		 
		            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
		            String picturePath = cursor.getString(columnIndex);
		            cursor.close();

		            resized = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(picturePath),(int)(BitmapFactory.decodeFile(picturePath).getWidth()*2), (int)(BitmapFactory.decodeFile(picturePath).getHeight()*2), true);

		            attachImage.setImageBitmap(resized);		         
		        }
		}
		
	public void selectAvatar(){
		Intent intent = new Intent(this, AvatarPage.class);
	    startActivity(intent);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile_menu, menu);
		return true;
	}
}

