package com.DevonaWard.mpk;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;

public class AvatarPage extends Activity {

	Typeface font;
	TextView theTitle;
	
	int image;
	ImageView img1;
	ImageView img2;
	ImageView img3;
	ImageView img4;
	ImageView img5;
	ImageView img6;
	ImageView img7;
	ImageView img8;
	ImageView img9;
	ImageView imgPreview;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.avatar_layout);
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowHomeEnabled(false);
		
		theTitle = (TextView)findViewById(R.id.theavT);
		font = Typeface.createFromAsset(getAssets(), "robotobold.ttf");
		theTitle.setTypeface(font);
		
		img1 = (ImageView)findViewById(R.id.image1);
		img2 = (ImageView)findViewById(R.id.image2);
		img3 = (ImageView)findViewById(R.id.image3);
		img4 = (ImageView)findViewById(R.id.image4);
		img5 = (ImageView)findViewById(R.id.image5);
		img6 = (ImageView)findViewById(R.id.image6);
		img7 = (ImageView)findViewById(R.id.image7);
		img8 = (ImageView)findViewById(R.id.image8);
		img9 = (ImageView)findViewById(R.id.image9);
		imgPreview = (ImageView)findViewById(R.id.avatarPreview);
		
		img1.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					image = (R.drawable.epic);
					imgPreview.setImageResource(image);
				}
				return false;
			}
			
		});
		
		img2.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					image = (R.drawable.female);
					imgPreview.setImageResource(image);
				}
				return false;
			}
			
		});
		
		img3.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					image = (R.drawable.fennekin);
					imgPreview.setImageResource(image);
				}
				return false;
			}
			
		});
		
		img4.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					image = (R.drawable.gcea);
					imgPreview.setImageResource(image);
				}
				return false;
			}
			
		});
		
		img5.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					image = (R.drawable.icy);
					imgPreview.setImageResource(image);
				}
				return false;
			}
			
		});
		
		img6.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					image = (R.drawable.maletrainer);
					imgPreview.setImageResource(image);
				}
				return false;
			}
			
		});
		
		img7.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					image = (R.drawable.pichukachu);
					imgPreview.setImageResource(image);
				}
				return false;
			}
			
		});
		
		img8.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					image = (R.drawable.pokemont);
					imgPreview.setImageResource(image);
				}
				return false;
			}
			
		});
		
		img9.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					image = (R.drawable.pokeman);
					imgPreview.setImageResource(image);
				}
				return false;
			}
			
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.avatar_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	    case android.R.id.home:
	    	Intent intent = new Intent(this, ProfileCard.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            break;
	        case R.id.selectImage:
	            imageSelected();
	            break;
	        default:
	            break;
	    }
	    return true;
	}
	
	public void imageSelected(){
		Intent intent = new Intent(this, ProfileCard.class);
		intent.putExtra("AvatarImage", image);
		startActivity(intent); 
	}
}