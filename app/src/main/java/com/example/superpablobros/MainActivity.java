package com.example.superpablobros;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.superpablobros.GameManagement.GameManager;

import io.github.controlwear.virtual.joystick.android.JoystickView;

public class MainActivity extends AppCompatActivity {

    //player inputs
    JoystickView joystick;
    Button jump_button;

    //player inputs data
    String m_sAngle;
    String m_sDirection;
    String m_sAction;

    //current player screen
    Display current_screen;

    RelativeLayout gameContainer;
    RelativeLayout debugLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //remove top bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //game view
        gameContainer = (RelativeLayout) findViewById(R.id.gameLayout);
        //player current screen
        current_screen = getWindowManager().getDefaultDisplay();

        //player control
        joystick = (JoystickView) findViewById(R.id.joystickView);
        jump_button = (Button) findViewById(R.id.jump);

        if(Commons.DEBUG){
            debugLayout = (RelativeLayout) findViewById(R.id.debugLayout);
            InitDebug(debugLayout, joystick, jump_button);

        }
        GameManager gameManager = new GameManager(this);
    }

    private void InitDebug(RelativeLayout debugLayout, JoystickView joystick, Button jump_button){
        //current joystick angle text display
        TextView angle_text = new TextView(this);
        angle_text.setId(R.id.debug_angle);
        angle_text.setTypeface(null, Typeface.BOLD);
        angle_text.setTextSize(Commons.DEBUG_ELEMENT_TEXT_SIZE);
        angle_text.setText("angle : none");

        RelativeLayout.LayoutParams angle_text_params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        angle_text_params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        angle_text_params.setMargins((int)Commons.pxFromDp(this, Commons.DEBUG_ELEMENTS_MARGIN), (int)Commons.pxFromDp(this, Commons.DEBUG_ELEMENTS_MARGIN),0,0);
        angle_text.setLayoutParams(angle_text_params);

        debugLayout.addView(angle_text);

        //current joystick direction text display
        TextView direction_text = new TextView(this);
        direction_text.setId(R.id.debug_direction);
        direction_text.setTypeface(null, Typeface.BOLD);
        direction_text.setTextSize(Commons.DEBUG_ELEMENT_TEXT_SIZE);
        direction_text.setText("direction : none");

        RelativeLayout.LayoutParams direction_text_params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        direction_text_params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        direction_text_params.addRule(RelativeLayout.BELOW, angle_text.getId());

        direction_text_params.setMargins((int)Commons.pxFromDp(this, Commons.DEBUG_ELEMENTS_MARGIN), (int)Commons.pxFromDp(this, Commons.DEBUG_ELEMENTS_MARGIN),0,0);
        direction_text.setLayoutParams(direction_text_params);

        debugLayout.addView(direction_text);

        //current action text display
        TextView action_text = new TextView(this);
        action_text.setId(R.id.debug_action);
        action_text.setTypeface(null, Typeface.BOLD);
        action_text.setTextSize(Commons.DEBUG_ELEMENT_TEXT_SIZE);
        action_text.setText("action : none");

        RelativeLayout.LayoutParams action_text_params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        action_text_params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        action_text_params.addRule(RelativeLayout.BELOW, direction_text.getId());
        action_text_params.setMargins((int)Commons.pxFromDp(this, Commons.DEBUG_ELEMENTS_MARGIN), (int)Commons.pxFromDp(this, Commons.DEBUG_ELEMENTS_MARGIN),0,0);
        action_text.setLayoutParams(action_text_params);

        debugLayout.addView(action_text);

        //current screen width in px display
        TextView current_screen_width_px = new TextView(this);
        current_screen_width_px.setId(R.id.debug_screen_width_px);
        current_screen_width_px.setTypeface(null, Typeface.BOLD);
        current_screen_width_px.setTextSize(Commons.DEBUG_ELEMENT_TEXT_SIZE);
        current_screen_width_px.setText("screen width :"+String.valueOf(current_screen.getWidth())+"px");

        RelativeLayout.LayoutParams current_screen_width_px_params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        current_screen_width_px_params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        current_screen_width_px_params.setMargins(0, (int)Commons.pxFromDp(this, Commons.DEBUG_ELEMENTS_MARGIN),(int)Commons.pxFromDp(this, Commons.DEBUG_ELEMENTS_MARGIN),0);
        current_screen_width_px.setLayoutParams(current_screen_width_px_params);

        debugLayout.addView(current_screen_width_px);

        //current screen width in px display
        TextView current_screen_height_px = new TextView(this);
        current_screen_height_px.setId(R.id.debug_screen_height_px);
        current_screen_height_px.setTypeface(null, Typeface.BOLD);
        current_screen_height_px.setTextSize(Commons.DEBUG_ELEMENT_TEXT_SIZE);
        current_screen_height_px.setText("screen height :"+String.valueOf(current_screen.getHeight())+"px");

        RelativeLayout.LayoutParams current_screen_height_px_params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        current_screen_height_px_params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        current_screen_height_px_params.addRule(RelativeLayout.BELOW, current_screen_width_px.getId());
        current_screen_height_px_params.setMargins(0, (int)Commons.pxFromDp(this, Commons.DEBUG_ELEMENTS_MARGIN),(int)Commons.pxFromDp(this, Commons.DEBUG_ELEMENTS_MARGIN),0);
        current_screen_height_px.setLayoutParams(current_screen_height_px_params);

        debugLayout.addView(current_screen_height_px);

        //set joystick event listener
        joystick.setOnMoveListener(new JoystickView.OnMoveListener() {
            @Override
            public void onMove(int angle, int strength) {
                if(angle<180 && angle>0){
                    m_sAngle = String.valueOf(angle)+" °";
                    m_sDirection  = "→";
                }else if(angle>180 && angle<360){
                    m_sAngle = String.valueOf(angle)+" °";
                    m_sDirection  = "←";
                }
                else{
                    m_sAngle = "none";
                    m_sDirection  = "none";
                }
                angle_text.setText("angle : "+m_sAngle);
                direction_text.setText("direction : "+m_sDirection);
            }
        });

        //set jump button event listener
        jump_button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // PRESSED
                        m_sAction = "jump";
                        break;
                    case MotionEvent.ACTION_UP:
                        // RELEASED
                        m_sAction = "none";
                        break;
                }
                action_text.setText("action : "+m_sAction);
                return true;
            }
        });
    }

    public RelativeLayout getGameContainer(){
        return this.gameContainer;
    }
    public Display getCurrent_screen() { return current_screen; }
}