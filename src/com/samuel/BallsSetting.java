package com.samuel;

import java.util.Random;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class BallsSetting extends SurfaceView implements Runnable {
	Thread ourThread = null;
	SurfaceHolder ourHolder;
	boolean isRunning = false;
	Bitmap smile = BitmapFactory.decodeResource(getResources(),
			R.drawable.smile);
	Bitmap dead = BitmapFactory.decodeResource(getResources(), R.drawable.dead);
	Bitmap evil = BitmapFactory.decodeResource(getResources(), R.drawable.evil);
	Typeface font;
	static float x1 = 50, y1 = 0, x2 = 225, y2 = 0, x3 = 400, y3 = 0, x4 = 113,
			y4 = 0, x5 = 340, y5 = 0;
	static float speed1 = 15, speed2 = 19, speed3 = 23, speed4 = 17,
			speed5 = 21;
	static int count = 0;
	int goal = 20;
	int level = 1;
	int time = 60;
	boolean click = false;
	boolean green = true;
	static float xpos = 50, ypos = 50;
	long before = System.currentTimeMillis();
	MediaPlayer song;
	static boolean Point1Alive = true, Point2Alive = true, Point3Alive = true,
			Point4Alive = true, Point5Alive = true;
	static boolean Point1good = true, Point2good = true, Point3good = true,
			Point4good = true, Point5good = true;

	public BallsSetting(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		ourHolder = getHolder();
		font = Typeface.createFromAsset(context.getAssets(), "G-Unit.TTF");
		song = MediaPlayer.create(context, R.raw.haha);
		SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());
		boolean music = getPrefs.getBoolean("checkbox", true);
		if(music)
			song.start();
		
	}

	public void pause() {
		isRunning = false;
		while (true) {
			try {
				ourThread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		ourThread = null;
		song.release();
	}

	public void check(float x, float y) {
		if (x < x1 + 35 && x > x1 - 35 && y > y1 - 35 && y < y1 + 35
				&& Point1Alive) {
			if (Point1good) {
				count++;
				green = true;
				click = true;
				xpos = x;
				ypos = y;
			} else {
				if(count != 0)
					count--;
				click = true;
				green = false;
				xpos = x;
				ypos = y;
			}
			Point1Alive = false;
		}
		if (x < x2 + 35 && x > x2 - 35 && y > y2 - 35 && y < y2 + 35
				&& Point2Alive) {
			if (Point2good) {
				count++;
				green = true;
				click = true;
				xpos = x;
				ypos = y;
			} else {
				if(count != 0)
					count--;
				click = true;
				green = false;
				xpos = x;
				ypos = y;
			}
			Point2Alive = false;
		}
		if (x < x3 + 35 && x > x3 - 35 && y > y3 - 35 && y < y3 + 35
				&& Point3Alive) {
			if (Point3good) {
				count++;
				click = true;
				green = true;
				xpos = x;
				ypos = y;
			} else {
				if(count != 0)
					count--;
				click = true;
				green = false;
				xpos = x;
				ypos = y;
			}
			Point3Alive = false;
		}
		if (x < x4 + 35 && x > x4 - 35 && y > y4 - 35 && y < y4 + 35
				&& Point4Alive) {
			if (Point4good) {
				count++;
				click = true;
				green = true;
				xpos = x;
				ypos = y;
			} else {
				if(count != 0)
					count--;
				click = true;
				green = false;
				xpos = x;
				ypos = y;
			}
			Point4Alive = false;
		}
		if (x < x5 + 35 && x > x5 - 35 && y > y5 - 35 && y < y5 + 35
				&& Point5Alive) {
			if (Point5good) {
				count++;
				click = true;
				green = true;
				xpos = x;
				ypos = y;
			} else {
				if(count != 0)
					count--;
				click = true;
				green = false;
				xpos = x;
				ypos = y;
			}
			Point5Alive = false;
		}
	}

	public void resume() {
		isRunning = true;
		ourThread = new Thread(this);
		ourThread.start();

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (isRunning) {

			if (!ourHolder.getSurface().isValid())
				continue;
			Canvas canvas = ourHolder.lockCanvas();
			if (time > 0)
				time = (int) (60 - (System.currentTimeMillis() - before) / 1000);

			else;
				//pause()

			canvas.drawColor(Color.WHITE);

			Paint add = new Paint();
			add.setColor(Color.GREEN);
			add.setTextAlign(Align.CENTER);
			add.setTextSize(50);
			add.setTypeface(font);

			Paint minus = new Paint();
			minus.setColor(Color.RED);
			minus.setTextAlign(Align.CENTER);
			minus.setTextSize(50);
			minus.setTypeface(font);

			Paint textPaint = new Paint();
			textPaint.setARGB(50, 254, 20, 20);
			textPaint.setTextAlign(Align.CENTER);
			textPaint.setTextSize(30);
			textPaint.setTypeface(font);
			canvas.drawText("Time Left: " + time, canvas.getWidth() - 150, 50,
					textPaint);
			if (count >= goal) {
				count = 0;
				level++;
				before = System.currentTimeMillis();
			}
			canvas.drawText("Level " + level + ": Reach " + goal + " Points",
					canvas.getWidth() / 2, 200, textPaint);
			canvas.drawText("Points: " + count, canvas.getWidth() / 2, 240,
					textPaint);
			if (click) {
				if (green)
					canvas.drawText("+1Point", xpos + 5, ypos - 3, add);

				else
					canvas.drawText("-1Point", xpos + 5, ypos - 3, minus);

			}

			if (Point1Alive) {
				if (Point1good)
					canvas.drawBitmap(smile, x1 - 35, y1 - 35, null);
				else
					canvas.drawBitmap(evil, x1 - 35, y1 - 35, null);
			} else
				canvas.drawBitmap(dead, x1 - 35, y1 - 35, null);

			if (Point2Alive) {
				if (Point2good)
					canvas.drawBitmap(smile, x2 - 35, y2 - 35, null);
				else
					canvas.drawBitmap(evil, x2 - 35, y2 - 35, null);
			} else
				canvas.drawBitmap(dead, x2 - 35, y2 - 35, null);

			if (Point3Alive) {
				if (Point3good)
					canvas.drawBitmap(smile, x3 - 35, y3 - 35, null);
				else
					canvas.drawBitmap(evil, x3 - 35, y3 - 35, null);
			} else
				canvas.drawBitmap(dead, x3 - 35, y3 - 35, null);

			if (Point4Alive) {
				if (Point4good)
					canvas.drawBitmap(smile, x4 - 35, y4 - 35, null);
				else
					canvas.drawBitmap(evil, x4 - 35, y4 - 35, null);
			} else
				canvas.drawBitmap(dead, x4 - 35, y4 - 35, null);

			if (Point5Alive) {
				if (Point5good)
					canvas.drawBitmap(smile, x5 - 35, y5 - 35, null);
				else
					canvas.drawBitmap(evil, x5 - 35, y5 - 35, null);
			} else
				canvas.drawBitmap(dead, x5 - 35, y5 - 35, null);

			if (y1 < canvas.getHeight())
				y1 += speed1;
			else {
				Random rand = new Random();
				x1 = rand.nextInt(400) + 10;
				y1 = 0;
				Point1Alive = true;
				Point1good = rand.nextBoolean();
				speed1 = rand.nextInt(level * 10) + 5;
			}
			if (y2 < canvas.getHeight())
				y2 += speed2;
			else {
				Random rand = new Random();
				x2 = rand.nextInt(400) + 10;
				y2 = 0;
				Point2Alive = true;
				Point2good = rand.nextBoolean();
				speed2 = rand.nextInt(level * 10) + 5;
			}
			if (y3 < canvas.getHeight())
				y3 += speed3;
			else {
				Random rand = new Random();
				x3 = rand.nextInt(400) + 10;
				y3 = 0;
				Point3Alive = true;
				Point3good = rand.nextBoolean();
				speed3 = rand.nextInt(level * 10) + 5;
			}
			if (y4 < canvas.getHeight())
				y4 += speed4;
			else {
				Random rand = new Random();
				x4 = rand.nextInt(400) + 10;
				y4 = 0;
				Point4Alive = true;
				Point4good = rand.nextBoolean();
				speed4 = rand.nextInt(level * 10) + 5;
			}
			if (y5 < canvas.getHeight())
				y5 += speed5;
			else {
				Random rand = new Random();
				x5 = rand.nextInt(400) + 10;
				y5 = 0;
				Point5Alive = true;
				Point5good = rand.nextBoolean();
				speed5 = rand.nextInt(level * 10) + 5;
			}

			ourHolder.unlockCanvasAndPost(canvas);
		}

	}

}
