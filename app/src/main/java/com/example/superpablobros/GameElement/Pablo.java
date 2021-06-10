package com.example.superpablobros.GameElement;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.superpablobros.Commons;
import com.example.superpablobros.GameManagement.BitmapBank;
import com.example.superpablobros.GameManagement.GameArea;
import com.example.superpablobros.PabloSprites;

/**
 * 
 */
public class Pablo extends GameElement {

    Bitmap currentSprite;
    int m_iXVelocity;
    int m_iYVelocity;

    /**
     * Default constructor
     */
    public Pablo(BitmapBank bitmapBank, int p_iX, int p_iY, int p_iWidth, int p_iHeight) {
        super(bitmapBank, p_iX, p_iY, p_iWidth, p_iHeight);
        m_iXVelocity = 0;
    }

    /**
     *
     */
    public void setM_iXVelocity(int p_iXVelocity) {
        this.m_iXVelocity = p_iXVelocity;
    }

    public void setCurrentSprite(Bitmap aimedSprite){
        this.currentSprite = aimedSprite;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(currentSprite, m_iX, m_iY, null);
    }

    /**
     * 
     */
    public int m_iLP;


    /**
     * 
     */
    public void Die() {
        // TODO implement here
    }

    /**
     * 
     */
    public void OnMove() {
        // TODO implement here
    }

    /**
     * 
     */
    public void OnTap() {
        // TODO implement here
    }

    /**
     * 
     */
    public void getLp() {
        // TODO implement here
    }

    /**
     * 
     */
    public void setLp() {
        // TODO implement here
    }



}