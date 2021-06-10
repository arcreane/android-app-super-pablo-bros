package com.example.superpablobros.GameManagement;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.SurfaceHolder;

import com.example.superpablobros.Commons;

public class MainThread extends Thread {
    private final SurfaceHolder surfaceHolder;
    // The actual view that handles inputs and draws to the surface
    private final GameArea gameArea;

    // flag to hold game state
    private boolean running;

    public MainThread(SurfaceHolder surfaceHolder, GameArea gameArea) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gameArea = gameArea;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }


    @Override
    public void run() {
        long beginTime;                      // the time when the cycle begun
        long timeMilliSec; // the time it took for the cycle to execute
        long sleepTime;                      // ms to sleep (<0 if we're behind)
        long targetTime = Commons.FRAME_PERIOD;

        int framesSkipped = 0;    // number of frames being skipped
        Paint paint;
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(70);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        while (running) {
            Canvas canvas = null;
            beginTime = System.nanoTime();
            // try locking the canvas for exclusive pixel editing
            // in the surface
            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
//                    if(this.birdView.update()){
//                        // render state to the screen draws the canvas on the panel
//                        this.birdView.doDraw(canvas);
//                    }
//                    else
//                    {
//                        running = false;
//                    }

                    this.gameArea.doDraw(canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                // in case of an exception the surface is not left in an inconsistent state
                if (canvas != null) {
                    try {
                        this.surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }    // end finally

            timeMilliSec = (System.nanoTime() - beginTime) / 1000000;
            sleepTime = targetTime - timeMilliSec;
            try {
                if (sleepTime > 0) {
                    // if sleepTime > 0 we're OK
                        Thread.sleep(sleepTime);
                } else {
                    while (sleepTime < 0 && framesSkipped < Commons.MAX_FRAME_SKIPS) {
                        // we need to catch up
//                        this.gameArea.update(); // update without rendering
                        sleepTime += Commons.FRAME_PERIOD;    // add frame period to check if in next frame
                        framesSkipped++;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        birdView.displayGameOver();
    }
}
