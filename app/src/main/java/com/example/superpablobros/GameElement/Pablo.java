package com.example.superpablobros.GameElement;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;

import com.example.superpablobros.Commons;
import com.example.superpablobros.GameManagement.BitmapBank;
import com.example.superpablobros.GameManagement.GameArea;
import com.example.superpablobros.MainActivity;
import com.example.superpablobros.PabloSprites;

/**
 * 
 */
public class Pablo extends GameElement {

    BitmapBank bitmapBank;
    Bitmap currentSprite;

    float m_iVelocity;
    boolean running;
    int m_iDirection;
    int m_iFrameNb;

    /**
     * Default constructor
     */
    public Pablo(MainActivity mainActivity, BitmapBank bitmapBank, int p_iX, int p_iY, int p_iWidth, int p_iHeight) {
        super(mainActivity, bitmapBank, p_iX, p_iY, p_iWidth, p_iHeight);
        this.bitmapBank = bitmapBank;
        this.m_iFrameNb = 0;
        this.currentSprite = this.bitmapBank.getFrame(PabloSprites.STANDING, this.m_iFrameNb);
        m_iVelocity = 0;
        m_iDirection = 0;
    }

    /**
     *
     */
    public void setM_iVelocity(float p_iVelocity) {
        this.running = true;
        this.m_iVelocity = p_iVelocity;
    }

    /**
     *
     */
    public float getM_iVelocity() { return m_iVelocity; }


    /**
     *
     */
    public void setM_iDirection(int p_iDirection) {
        this.running = true;
        this.m_iDirection = p_iDirection;
    }

    /**
     *
     */
    public void setRunning(boolean running) {
        this.running = running;
    }

    /**
     *
     */
    public void setCurrentSprite(Bitmap aimedSprite){
        this.currentSprite = aimedSprite;
    }

    @Override
    public void draw(Canvas canvas) {
        if(m_iDirection<0){
            this.currentSprite = bitmapBank.flipBitmap(this.currentSprite);
        }
        canvas.drawBitmap(this.currentSprite, this.m_iX, this.m_iY, null);
    }

    @Override
    public void update() {
        super.update();
        if(running){
            m_iX = m_iX + (m_iVelocity * (this.mainActivity.getCurrent_screen().getHeight() / 208)) * this.m_iDirection;
            currentSprite = this.bitmapBank.getFrame(PabloSprites.RUNNING, (int)Math.floor(m_iFrameNb / 3));
            Log.d("framerate",String.valueOf((int)Math.floor(m_iFrameNb / 5)));
            Log.d("framerate",String.valueOf(m_iFrameNb));

            if(this.m_iFrameNb<8){
                this.m_iFrameNb+=1;
            }
            else {
                this.m_iFrameNb = 0;
            }
        }
        else {
            this.m_iFrameNb = 0;
            currentSprite = this.bitmapBank.getFrame(PabloSprites.STANDING, this.m_iFrameNb);
        }
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