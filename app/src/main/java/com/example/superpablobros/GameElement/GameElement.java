package com.example.superpablobros.GameElement;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.superpablobros.Coords;
import com.example.superpablobros.GameManagement.BitmapBank;

import java.util.*;

/**
 * 
 */
public class GameElement {

    int m_iOriginSpriteX;
    int m_iOriginSpriteY;
    int m_iOriginSpriteWidth;
    int m_iOriginSpriteHeight;

    private Paint paint;

    /**
     * Default constructor
     */
    public GameElement(BitmapBank bitmapBank, int p_iX, int p_iY, int p_iWidth, int p_iHeight) {
        m_iOriginSpriteX = p_iX;
        m_iOriginSpriteY = p_iY;
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