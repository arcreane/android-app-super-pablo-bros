package com.example.superpablobros.GameManagement;

import android.graphics.Paint;
import android.view.SurfaceHolder;

import com.example.superpablobros.Commons;
import com.example.superpablobros.GameElement.Pablo;
import com.example.superpablobros.MainActivity;

/**
 *
 */
public class GameManager {

    Pablo pablo;
    Paint paint = new Paint();
    GameArea gameArea;
    MainThread mainThread;
    BitmapBank bitmapBank;
    /**
     * Default constructor
     */
    public GameManager(MainActivity mainActivity) {
        bitmapBank = new BitmapBank(mainActivity);
        gameArea = new GameArea(mainActivity, this, bitmapBank);
        mainActivity.getGameContainer().addView(gameArea);
        pablo = new Pablo(bitmapBank, mainActivity.getCurrent_screen().getWidth()/2, mainActivity.getCurrent_screen().getHeight()-(Commons.BIG_PABLO_HEIGHT*(mainActivity.getCurrent_screen().getHeight()/208)), Commons.PABLO_WIDTH, Commons.BIG_PABLO_HEIGHT);
    }

    private void getSprites() {

    }

    public void startThread(SurfaceHolder surfaceholder){
        mainThread = new MainThread(surfaceholder, gameArea);
        mainThread.setRunning(true);
        mainThread.start();
    }
    /**
     *
     */
    public int VisualAspect;

    /**
     *
     */
    public GameArea getGameArea() {
        return this.gameArea;
    }

    /**
     *
     */
    public MainThread getMainThread() {
        return this.mainThread;
    }

    /**
     *
     */
    public Pablo getPablo() {
        return this.pablo;
    }


}