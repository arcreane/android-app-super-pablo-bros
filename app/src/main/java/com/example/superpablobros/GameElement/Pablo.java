package com.example.superpablobros.GameElement;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import com.example.superpablobros.Commons;
import com.example.superpablobros.GameManagement.BitmapBank;
import com.example.superpablobros.MainActivity;
import com.example.superpablobros.PabloSprites;

/**
 * 
 */
public class Pablo extends GameElement {

    BitmapBank bitmapBank;
    Bitmap currentSprite;

    float m_iXVelocity;
    float m_iYVelocity;
    boolean m_bRunning;
    boolean m_bJumping;
    int m_iDirection;
    int m_iFrameNb;
    float m_fJumpingTime;

    /**
     * Default constructor
     */
    public Pablo(MainActivity mainActivity, BitmapBank bitmapBank, int p_iX, int p_iY, int p_iWidth, int p_iHeight) {
        super(mainActivity, bitmapBank, p_iX, p_iY, p_iWidth, p_iHeight);
        this.bitmapBank = bitmapBank;
        this.m_iFrameNb = 0;
        this.currentSprite = this.bitmapBank.getFrame(PabloSprites.STANDING, this.m_iFrameNb);
        m_iXVelocity = 0;
        m_iDirection = 0;
        m_fJumpingTime = 0;
    }

    /**
     *
     */
    public void setM_iXVelocity(float p_iVelocity) {
        this.m_iXVelocity = p_iVelocity;
    }

    /**
     *
     */
    public float getM_iXVelocity() { return m_iXVelocity; }

    /**
     *
     */
    public void setM_iYVelocity(float p_iYVelocity) {
        this.m_iYVelocity = p_iYVelocity;
    }

    /**
     *
     */
    public float getM_iYVelocity() { return m_iYVelocity; }


    /**
     *  set pablo direction, 1 is right, 0 is left
     */
    public void setM_iDirection(int p_iDirection) {
        this.m_iDirection = p_iDirection;
    }

    /**
     *
     */
    public void setM_bRunning(boolean p_bRunning) { this.m_bRunning = p_bRunning; }

    /**
     *
     */
    public void setM_bJumping(boolean p_bJumping) {
        this.m_bJumping = p_bJumping;
    }

    /**
     *
     */
    public boolean getM_bJumping() { return m_bJumping; }

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

        /**
         *if jumping only show jump sprite
         */
        if(m_bJumping){
            if(m_iY<=(this.mainActivity.getCurrent_screen().getHeight()-(Commons.BIG_PABLO_HEIGHT*(this.mainActivity.getCurrent_screen().getHeight()/208)))) {

                if(m_iY>=0 && this.m_fJumpingTime<Commons.PABLO_MAX_JUMP_TIME){
                    this.m_fJumpingTime+=Commons.PABLO_JUMP_SPEED;
                    m_iY = m_iY - (m_iYVelocity * (this.mainActivity.getCurrent_screen().getHeight() / 208))*(-1*((this.m_fJumpingTime - Commons.PABLO_MAX_JUMP_TIME)/Commons.MAX_FPS));
                }
                else if(this.m_fJumpingTime == Commons.PABLO_MAX_JUMP_TIME){
                    this.m_fJumpingTime+=Commons.PABLO_JUMP_SPEED;
                }
                else{
                    this.m_fJumpingTime+=Commons.PABLO_JUMP_SPEED;
                    m_iY = m_iY + (m_iYVelocity * (this.mainActivity.getCurrent_screen().getHeight() / 208)) * ((this.m_fJumpingTime - Commons.PABLO_MAX_JUMP_TIME)/Commons.MAX_FPS);
                }
                currentSprite = this.bitmapBank.getFrame(PabloSprites.JUMPING, 0);
            }
            else{
                this.m_bJumping = false;
                this.m_iY = (this.mainActivity.getCurrent_screen().getHeight()-(Commons.BIG_PABLO_HEIGHT*(this.mainActivity.getCurrent_screen().getHeight()/208)));
                this.m_fJumpingTime = 0;
            }
        }

        /**
         *Moving horizontaly
         */
        if(m_bRunning){
            m_iX = m_iX + (m_iXVelocity * (this.mainActivity.getCurrent_screen().getHeight() / 208)) * this.m_iDirection;
            if(m_iX<=0){
                m_iX = 0;
            }
            else if(m_iX>=(this.mainActivity.getCurrent_screen().getWidth()-(16*(this.mainActivity.getCurrent_screen().getHeight() / 208)))){
                m_iX = (this.mainActivity.getCurrent_screen().getWidth()-(16*(this.mainActivity.getCurrent_screen().getHeight() / 208)));
            }

            if(m_bRunning && !m_bJumping){
                currentSprite = this.bitmapBank.getFrame(PabloSprites.RUNNING, (int)Math.floor(m_iFrameNb / 3));
                //frame management for the running animation
                if(this.m_iFrameNb<8){
                    this.m_iFrameNb+=1;
                }
                else {
                    this.m_iFrameNb = 0;
                }
            }
        }

        if(!m_bRunning && !m_bJumping) {
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