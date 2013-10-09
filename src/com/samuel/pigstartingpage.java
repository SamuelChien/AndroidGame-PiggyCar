package com.samuel;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class pigstartingpage extends Activity{
	MediaPlayer song;
	
	@Override
	protected void onCreate(Bundle samuel) {
		// TODO Auto-generated method stub
		super.onCreate(samuel);
		setContentView(R.layout.newlayout);
		song = MediaPlayer.create(pigstartingpage.this, R.raw.piano);
		SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		boolean music = getPrefs.getBoolean("checkbox", true);
		if(music)
			song.start();
		Thread timer = new Thread(){
			public void run(){
				try{
					sleep(4000);
				
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					Intent openStartingPoint = new Intent("com.samuel.MENU");
					startActivity(openStartingPoint);
				}
				
				
			}
			
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		song.release();
		finish();
	}
	
	}

