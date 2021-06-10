package com.example.superpablobros.GameElement;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.superpablobros.GameManagement.BitmapBank;

/**
 * 
 */
public class GameElement {

    int m_iX;
    int m_iY;
    int m_iOriginSpriteWidth;
    int m_iOriginSpriteHeight;

    private Paint paint;

    /**
     * Default constructor
     */
    public GameElement(BitmapBank bitmapBank, int p_iX, int p_iY, int p_iWidth, int p_iHeight) {
        m_iX = p_iX;
        m_iY = p_iY;
        m_iOriginSpriteWidth = p_iWidth;
        m_iOriginSpriteHeight = p_iHeight;

    }


    /**
     * 
     */
    public com.example.superpablobros.Coords Coords;




    /**
     * 
     */
    public void step() {
        // TODO implement here
    }

    /**
     * 
     */
    public void draw(Canvas canvas) {
        //canvas.drawBitmap(bmpBank.getCurrentBirdFrame(), 150, 150, null);
    }

    /**
     * 
     */
    public void getCoords() {
        // TODO implement here
    }

}