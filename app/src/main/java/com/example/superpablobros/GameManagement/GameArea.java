package com.example.superpablobros.GameManagement;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.superpablobros.PabloSprites;

import java.util.*;

/**
 * 
 */
public class GameArea extends SurfaceView implements SurfaceHolder.Callback{
    Paint paint;

    GameManager gameManager;
    BitmapBank bitmapBank;
    /**
     * Default constructor
     */
    public GameArea(Context context, GameManager gameManager, BitmapBank bitmapBank) {
            super(context);
            getHolder().addCallback(this);
            this.gameManager = gameManager;
            this.bitmapBank = bitmapBank;
            setWillNotDraw(false);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.gameManager.startThread(surfaceHolder);
    }
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                gameManager.getMainThread().setRunning(false);
                gameManager.getMainThread().join();
            } catch (Exception e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    public void doDraw(Canvas canvas){
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        gameManager.getPablo().setCurrentSprite(bitmapBank.getFrame(PabloSprites.STANDING, 0));
        gameManager.getPablo().draw(canvas);
    }
    /**
     * 
     */
    public String VisualAspect;

    /**
     * 
     */
    public com.example.superpablobros.Coords Coords;

    /**
     * 
     */
    public void Display() {
        // TODO implement here
    }

}