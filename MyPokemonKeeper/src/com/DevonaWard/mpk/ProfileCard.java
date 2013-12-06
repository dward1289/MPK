package com.DevonaWard.mpk;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;


import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


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
	String usernameTxt;
	String friendCodeTxt;
	String favPokemonTxt;
	String c1;
	String c2;
	String c3;
	int image;

	//Save Data
	FileOutputStream fos;
    String fileName;
	String ret;
	String ret2;
	String content;
	String[] SavedFiles;
	Bitmap bitmap;
	
	//Hints
	ImageView help1;
	ImageView help2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_card);
		
		//Hide application logo from action bar
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowHomeEnabled(false);

		
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
				
		//Fonts
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
		attachImage.setDrawingCacheEnabled(true);
		
		//Saved files list
		SavedFiles = getApplicationContext().fileList();
		
		//Get saved data
		if(SavedFiles.length>0){
		readFromFile();
		getImage("ProfileImage.png");
		}
		
		if(SavedFiles.length == 2){
			tempTxtRead();
		}
		
		//ImageView onTouch
		attachImage.setOnTouchListener(new OnTouchListener() {  
  
		@Override
		public boolean onTouch(View arg0, MotionEvent arg1) {

			if(arg1.getAction() == MotionEvent.ACTION_DOWN){
				saveTempData();
				theOptions();
			}
			return true;
		}
         });
		
		//Help 1 onTouch
		help1 = (ImageView)findViewById(R.id.infoHelp1);
		help1.setOnTouchListener(new OnTouchListener() {  
  
		@Override
		public boolean onTouch(View arg0, MotionEvent arg1) {

			if(arg1.getAction() == MotionEvent.ACTION_DOWN){
				helpHint1();
			}
			return true;
		}
         });
		
		//Help 2 onTouch
		help2 = (ImageView)findViewById(R.id.infoHelp2);
		help2.setOnTouchListener(new OnTouchListener() {  
  
		@Override
		public boolean onTouch(View arg0, MotionEvent arg1) {

			if(arg1.getAction() == MotionEvent.ACTION_DOWN){
				helpHint2();
			}
			return true;
		}
         });
		
		
		Bundle extras = getIntent().getExtras();
		if (extras == null) {
		    return;
		    }
		// Get selected image
		image = extras.getInt("AvatarImage");
		if (image != 0) {
			resized = BitmapFactory.decodeResource(getResources(),image);
			attachImage.setImageBitmap(resized);
		} 	
			
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
		            	saveTempData();
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
	
	public void helpHint1(){
		
		//Create alert Dialog		 
		AlertDialog.Builder helper1 = new AlertDialog.Builder(ProfileCard.this);
		 
		//Alert Title
		helper1.setTitle("Username");
		 
		// Setting Dialog Message
		helper1.setMessage("On your Nintendo 3DS, tap the square smiley face at the top.\nLook for the crown logo on the character image.\nYour username is at the bottom of that image.");

		helper1.setPositiveButton("OK",
		        new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						
					}
		        });
		//Display Help
		helper1.show();
	}
	
	public void helpHint2(){
		
		//Create alert Dialog		 
		AlertDialog.Builder helper2 = new AlertDialog.Builder(ProfileCard.this);
		 
		//Alert Title
		helper2.setTitle("Friend Code");
		 
		// Setting Dialog Message
		helper2.setMessage("On your Nintendo 3DS, tap the square smiley face at the top.\nLook for the crown logo on the character image.\nTap the image and your Friend Code is at the bottom of the username.");

		helper2.setPositiveButton("OK",
		        new DialogInterface.OnClickListener() {
		 
		            public void onClick(DialogInterface dialog, int which) {
		            
		            }
		        });

		//Display Options
		helper2.show();
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
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	    case android.R.id.home:
	    	Intent intent = new Intent(this, CapturedList.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            break;
	        case R.id.saveIt:
	            checkData();
	            break;
	        default:
	            break;
	    }
	    return true;
	}
		
	public void checkData(){
		if(attachImage.getDrawable() == null){
			AlertDialog.Builder userErr = new AlertDialog.Builder(ProfileCard.this);
			userErr.setTitle("Profile Photo");
			userErr.setMessage("Please select a Profile Photo.");
			userErr.setPositiveButton("OK",
			        new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog, int which) {			            
			            }
			        });
			userErr.show();	
		}
		if(unInput.getText().toString().trim().equals("")){	 
			AlertDialog.Builder userErr = new AlertDialog.Builder(ProfileCard.this);
			userErr.setTitle("Username");
			userErr.setMessage("Please enter username.");
			userErr.setPositiveButton("OK",
			        new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog, int which) {			            
			            }
			        });
			userErr.show();	
		}else{
			usernameTxt = unInput.getText().toString();
		}
			
			if(theCode1.getText().toString().trim().equals("") || theCode1.getText().toString().length() < 4){	 
			AlertDialog.Builder userErr = new AlertDialog.Builder(ProfileCard.this);
			userErr.setTitle("Friend Code");
			userErr.setMessage("Section 1 of the Friend Code has not been entered.");
			userErr.setPositiveButton("OK",
			        new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog, int which) {			            
			            }
			        });
			userErr.show();	
		}else{
			c1 = theCode1.getText().toString();
		}
			if(theCode2.getText().toString().trim().equals("") || theCode2.getText().toString().length() < 4){
			AlertDialog.Builder userErr = new AlertDialog.Builder(ProfileCard.this);
			userErr.setTitle("Friend Code");
			userErr.setMessage("Section 2 of the Friend Code has not been entered.");
			userErr.setPositiveButton("OK",
			        new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog, int which) {			            
			            }
			        });
			userErr.show();	
		}else{
			c2 = theCode2.getText().toString();
		}
			if(theCode3.getText().toString().trim().equals("") || theCode3.getText().toString().length() < 4){
			AlertDialog.Builder userErr = new AlertDialog.Builder(ProfileCard.this);
			userErr.setTitle("Friend Code");
			userErr.setMessage("Section 3 of the Friend Code has not been entered.");
			userErr.setPositiveButton("OK",
			        new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog, int which) {			            
			            }
			        });
			userErr.show();	
		}else{
			c3 = theCode3.getText().toString();
		}
			
		if(unInput.getText().toString().length() > 0 && theCode1.getText().toString().length() == 4 && theCode2.getText().toString().length() == 4 && theCode3.getText().toString().length() == 4 && attachImage.getDrawable() != null){
			saveData();
		}


	}
	//Save all text entered
	public void saveData() {
		  favPokemonTxt = favInput.getText().toString();
		  
		  fileName = "PTP";			
          content = usernameTxt+"\n"+c1+"\n"+c2+"\n"+c3+"\n"+favPokemonTxt;
          try {
           fos = openFileOutput(fileName, Context.MODE_PRIVATE);
           fos.write(content.getBytes());
           fos.close();
           saveImage(resized);
           //Show that data was saved
           Toast.makeText(
             ProfileCard.this,
             "Saved",
             Toast.LENGTH_LONG).show();
          
          } catch (FileNotFoundException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
          } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
          }
			Log.i("SAVED IT",Arrays.toString(SavedFiles));
			
	}
	
	  //Read text data stored.
	  private String readFromFile() {
		    try {
		        InputStream inputStream = openFileInput(SavedFiles[0]);

		        if ( inputStream != null ) {
		            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		            String receiveString;
		            StringBuilder stringBuilder = new StringBuilder();

		            while ((receiveString = bufferedReader.readLine()) != null ) {
		            	if(receiveString.length() != 0){
		                stringBuilder.append(receiveString + "\n");
		            }
		            	
		            }
		            inputStream.close();
		            ret = stringBuilder.toString();  
		        }
		    }
		    catch (FileNotFoundException e) {
		        Log.e("login activity", "File not found: " + e.toString());
		    } catch (IOException e) {
		        Log.e("login activity", "Can not read file: " + e.toString());
		    }
		    Log.i("CONTENT", ret);
            String[] arr= ret.split("\n");
            unInput.setText(arr[0]);
            theCode1.setText(arr[1]);
            theCode2.setText(arr[2]);
            theCode3.setText(arr[3]);
            favInput.setText(arr[4]);
              
		    return ret; 
		}
	  
	  //Save image in image view
	  public boolean saveImage(Bitmap image) {

		  try {
		  // Use the compress method on the Bitmap object to write image to
		  // the OutputStream
		  FileOutputStream fos = this.openFileOutput("ProfileImage.png", Context.MODE_PRIVATE);

		  // Writing the bitmap to the output stream
		  resized.compress(Bitmap.CompressFormat.PNG, 100, fos);
		  fos.close();

		  return true;
		  } catch (Exception e) {
		  Log.e("saveToInternalStorage()", e.getMessage());
		  return false;
		  }
		  }
	  //Load image	  
	  public Bitmap getImage(String filename) {

		  Bitmap thumbnail = null;
		  try {
		  File filePath = this.getFileStreamPath(filename);
		  FileInputStream fi = new FileInputStream(filePath);
		  thumbnail = BitmapFactory.decodeStream(fi);
		  } catch (Exception ex) {
		  Log.e("getThumbnail() on internal storage", ex.getMessage());
		  }
		  attachImage.setImageBitmap(thumbnail);
		  return thumbnail;
		  }
	  
		//Save all text entered in case user selects Profile Image last.
		public void saveTempData() {
			usernameTxt = unInput.getText().toString();
			  c1 = theCode2.getText().toString();
			  c2 = theCode2.getText().toString();
			  c3 = theCode2.getText().toString();
			  favPokemonTxt = favInput.getText().toString();
			  
			  fileName = "TempTxt";			
	          content = usernameTxt+"\n"+c1+"\n"+c2+"\n"+c3+"\n"+favPokemonTxt;
	          try {
	           fos = openFileOutput(fileName, Context.MODE_PRIVATE);
	           fos.write(content.getBytes());
	           fos.close();
	          } catch (FileNotFoundException e) {
	           // TODO Auto-generated catch block
	           e.printStackTrace();
	          } catch (IOException e) {
	           // TODO Auto-generated catch block
	           e.printStackTrace();
	          }
				Log.i("SAVED IT",Arrays.toString(SavedFiles));
				
		}
		
		  //Read text data stored.
		  private String tempTxtRead() {
			    try {
			        InputStream inputStream = openFileInput(SavedFiles[1]);

			        if ( inputStream != null ) {
			            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			            String receiveString;
			            StringBuilder stringBuilder = new StringBuilder();

			            while ((receiveString = bufferedReader.readLine()) != null ) {
			            	if(receiveString.length() != 0){
			                stringBuilder.append(receiveString + "\n");
			            }
			            	
			            }
			            inputStream.close();
			            ret2 = stringBuilder.toString();  
			        }
			    }
			    catch (FileNotFoundException e) {
			        Log.e("login activity", "File not found: " + e.toString());
			    } catch (IOException e) {
			        Log.e("login activity", "Can not read file: " + e.toString());
			    }
			    Log.i("CONTENT2", ret2);
	            String[] arr2= ret2.split("\n");
	            unInput.setText(arr2[0]);
	            theCode1.setText(arr2[1]);
	            theCode2.setText(arr2[2]);
	            theCode3.setText(arr2[3]);
	            
	              
			    return ret2; 
			}

}
	

    	


