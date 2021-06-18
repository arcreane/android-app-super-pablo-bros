package com.example.superpablobros.GameManagement;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;

import com.example.superpablobros.Commons;
import com.example.superpablobros.MainActivity;

import io.github.controlwear.virtual.joystick.android.JoystickView;

public class MainThread extends Thread {
    private final MainActivity mainActivity;
    private final SurfaceHolder surfaceHolder;
    private final GameArea gameArea;
    private final GameManager gameManager;

    // flag to hold game state
    private boolean running;

    public MainThread(MainActivity mainActivity, SurfaceHolder surfaceHolder, GameArea gameArea, GameManager gameManager) {
        super();
        this.mainActivity = mainActivity;
        this.surfaceHolder = surfaceHolder;
        this.gameArea = gameArea;
        this.gameManager = gameManager;
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
        paint.setColor(Color.rgb(97, 133, 248));
        paint.setTextSize(70);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        while (running) {
            Canvas canvas = null;
            beginTime = System.nanoTime();
            // try locking the canvas for exclusive pixel editing
            // in the surface
            this.mainActivity.getJoystick().setOnMoveListener(new JoystickView.OnMoveListener() {
                @Override
                public void onMove(int angle, int strength) {
                    if (strength > 0) {
                        if (angle < 180 && angle > 0) {
                            if (gameManager.getPablo().getM_iXVelocity() < Commons.PABLO_MAX_HORIZONTAL_VELOCITY) {
                                gameManager.getPablo().setM_iXVelocity(gameManager.getPablo().getM_iXVelocity() + Commons.PABLO_VELOCITY_RATE);
                            } else {
                                gameManager.getPablo().setM_iXVelocity(Commons.PABLO_MAX_HORIZONTAL_VELOCITY);
                            }
                            gameManager.getPablo().setM_iDirection(1);
                            gameManager.getPablo().setM_bRunning(true);
                        } else if (angle > 180 && angle < 360) {
                            if (gameManager.getPablo().getM_iXVelocity() < Commons.PABLO_MAX_HORIZONTAL_VELOCITY) {
                                gameManager.getPablo().setM_iXVelocity(gameManager.getPablo().getM_iXVelocity() + Commons.PABLO_VELOCITY_RATE);
                            } else {
                                gameManager.getPablo().setM_iXVelocity(Commons.PABLO_MAX_HORIZONTAL_VELOCITY);
                            }
                            gameManager.getPablo().setM_iDirection(-1);
                            gameManager.getPablo().setM_bRunning(true);
                        } else {
                            gameManager.getPablo().setM_iXVelocity(0);
                            gameManager.getPablo().setM_bRunning(false);
                        }
                    }
                    else{
                        gameManager.getPablo().setM_iXVelocity(0);
                        gameManager.getPablo().setM_bRunning(false);
                    }
                }
            });
            this.mainActivity.getJump_button().setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch(event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            if(gameManager.getPablo().getM_iYVelocity()<Commons.PABLO_MAX_HORIZONTAL_VELOCITY) {
                                gameManager.getPablo().setM_iYVelocity(gameManager.getPablo().getM_iYVelocity()+Commons.PABLO_MAX_VERTICAL_VELOCITY);
                            }
                            gameManager.getPablo().setM_bJumping(true);
                            break;
                        case MotionEvent.ACTION_UP:
                            // RELEASED
                            break;
                    }
                    return true;
                }
            });
            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    if(this.gameManager.update()){
                        // render state to the screen draws the canvas on the panel
                        this.gameArea.doDraw(canvas);
                    }
                    else
                    {
                        running = false;
                    }
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
