package com.samuel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MyBringBackSurface extends SurfaceView implements Runnable{
	
	Thread ourThread = null;
	SurfaceHolder ourHolder;
	boolean isRunning = false;
	Bitmap test = BitmapFactory.decodeResource(getResources(), R.drawable.button3);
	static float xpoint = 0;
	static float ypoint = 0;
	
	public MyBringBackSurface(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		ourHolder = getHolder();
	}

	public void pause(){
		isRunning = false;
		while(true){
			try {
				ourThread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			}
		ourThread = null;
	}
	
	public void resume(float x, float y){
		xpoint = x;
		ypoint = y;
		isRunning = true;
		ourThread = new Thread(this);
		ourThread.start();
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(isRunning){
			
			if(!ourHolder.getSurface().isValid())
				continue;
			Canvas canvas = ourHolder.lockCanvas();
			canvas.drawRGB(02, 02, 02);
			if(xpoint != 0 && ypoint != 0)
				canvas.drawBitmap(test, xpoint-35, ypoint-35, null);
			ourHolder.unlockCanvasAndPost(canvas);
		}
		
	}

}
