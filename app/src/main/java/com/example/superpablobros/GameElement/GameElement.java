package com.example.superpablobros.GameElement;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.superpablobros.GameManagement.BitmapBank;
import com.example.superpablobros.MainActivity;

/**
 * 
 */
public class GameElement {

    MainActivity mainActivity;

    float m_iX;
    float m_iY;
    int m_iOriginSpriteWidth;
    int m_iOriginSpriteHeight;

    private Paint paint;

    /**
     * Default constructor
     */
    public GameElement(MainActivity mainActivity, BitmapBank bitmapBank, int p_iX, int p_iY, int p_iWidth, int p_iHeight) {
        this.mainActivity = mainActivity;
        m_iX = p_iX;
        m_iY = p_iY;
        m_iOriginSpriteWidth = p_iWidth;
        m_iOriginSpriteHeight = p_iHeight;
    }

    public GameElement() {
    }

    public float getM_iX() { return m_iX; }

    /**
     * 
     */
    public com.example.superpablobros.Coords Coords;




    /**
     * 
     */
    public void update() {}

    /**
     * 
     */
    public void draw(Canvas canvas) {}

    /**
     * 
     */
    public void getCoords() {
        // TODO implement here
    }

}