package com.samuel;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WinnieActivity extends Activity {
	  /** Called when the activity is first created. */
		static int level;
		static int tummy;
		static int sleep;
		static int food;
		Button feed, steal, gym, fight, sleeping;
		TextView display0, display2, display3, display4;
		MediaPlayer song;
		
	    @Override
	    public void onCreate(Bundle samuel) {
	        super.onCreate(samuel);
	        setContentView(R.layout.main);
	        song = MediaPlayer.create(WinnieActivity.this, R.raw.baby);
			song.start();
	        level = 1;
	        tummy = 50;
	        sleep = 50;
	        food = 3;
	        feed = (Button) findViewById(R.id.bfeed);
	        steal = (Button) findViewById(R.id.bsteal);
	        gym = (Button) findViewById(R.id.bgym);
	        fight = (Button) findViewById(R.id.bfight);
	        sleeping = (Button) findViewById(R.id.bsleep);
	        display0 = (TextView) findViewById(R.id.tvDisplay0);
	        display2 = (TextView) findViewById(R.id.tvDisplay2);
	        display3 = (TextView) findViewById(R.id.tvDisplay3);
	        display4 = (TextView) findViewById(R.id.tvDisplay4);
	        
	        feed.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					tummy += 50;
					if (food<1){
						display0.setText("Status: No more food");
					}
					else{
					food --;

					display0.setText("Status: Yumm, Yumm, Yummm");
						
					
					if(tummy > 60){
						display2.setText("Tummy Status: I'm a CircleBob");
						display3.setText("Mood: VERY VERY VERY HAPPY");
					}
					else if (tummy > 40){
						display2.setText("Tummy Status: I'm full");
						display3.setText("Mood: Satifying, feel like bangbang~");
					}
					else if (tummy > 20){
						display2.setText("Tummy Status: Very Hungry :(");
						display3.setText("Mood: why don't you let me have food :(");
					}
					
					else if (tummy < 0){
						display2.setText("Tummy Status: Empty... Dying");
						display3.setText("Mood: Are you trying to kill me dumbo?");
					}}
					

				}});
	        
	        
	        steal.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					food ++;
					tummy -= 10;
					display0.setText("Status: Got Punch in the eye...");

				}});
	        
	        gym.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					food ++;
					tummy -= 20;
					sleep -=20;
					level ++;
					display4.setText("Level: "+ level);
					display0.setText("Status: Very comfy~ <3");
					
					
					if (tummy < sleep){
						if(tummy > 60){
							display2.setText("Tummy Status: I'm a CircleBob");
							display3.setText("Mood: VERY VERY VERY HAPPY");
						}
						else if (tummy > 40){
							display2.setText("Tummy Status: I'm full");
							display3.setText("Mood: Satifying, feel like bangbang~");
						}
						else if (tummy > 20){
							display2.setText("Tummy Status: Very Hungry :(");
							display3.setText("Mood: why don't you let me have food :(");
							display0.setText("Status: I need to eat before I can bang bang");
						}
						
						else if (tummy < 0){
							display2.setText("Tummy Status: Empty... Dying");
							display3.setText("Mood: Are you trying to kill me dumbo?");
							display0.setText("Status: I need to eat before I can bang bang");
						}
						
					}
					else{
						if (sleep < 20)
							display0.setText("Status: I need sleep before I can bang bang");
						
						
					}
						
					
					
					
					
					

				}});
	        fight.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					level++;
					tummy -= 20;
					sleep -=20;
					display4.setText("Level: "+ level);
					display0.setText("Status: Elbow, knee, slap combo!");
					
					
					
					if (tummy < sleep){
						if(tummy > 60){
							display2.setText("Tummy Status: I'm a CircleBob");
							display3.setText("Mood: VERY VERY VERY HAPPY");
						}
						else if (tummy > 40){
							display2.setText("Tummy Status: I'm full");
							display3.setText("Mood: Satifying, feel like bangbang~");
						}
						else if (tummy > 20){
							display2.setText("Tummy Status: Very Hungry :(");
							display3.setText("Mood: why don't you let me have food :(");
							display0.setText("Status: I need to eat before I can fight");
						}
						
						else if (tummy < 0){
							display2.setText("Tummy Status: Empty... Dying");
							display3.setText("Mood: Are you trying to kill me dumbo?");
							display0.setText("Status: I need to eat before I can fight");
						}
						
					}
					else{
						if (sleep < 20)
							display0.setText("Status: I need sleep before I can fight");
						
						
					}
					
					
					
					

				}});
	        sleeping.setOnClickListener(new View.OnClickListener() {
				
	 			@Override
	 			public void onClick(View v) {
	 				level++;
	 				tummy -= 20;
	 				sleep +=200;
	 				display0.setText("Status: Sleeping..zZZ");

	 			}});
	        
	        
	        
	        
	    }
	    
		@Override
		protected void onPause() {
			// TODO Auto-generated method stub
			super.onPause();
			finish();
			song.release();
		}
	}