package com.example.superpablobros;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import io.github.controlwear.virtual.joystick.android.JoystickView;

public class MainActivity extends AppCompatActivity {

    String m_sAngle;
    String m_sDirection;
    String m_sAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //remove top bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //game view
        RelativeLayout gameContainer = (RelativeLayout) findViewById(R.id.gameLayout);

        //player control
        JoystickView joystick = (JoystickView) findViewById(R.id.joystickView);
        Button jump_button = (Button) findViewById(R.id.jump);

        if(Commons.debug){
            InitDebug(gameContainer, joystick, jump_button);
        }else{
            User pablo = new User();
        }
    }

    private void InitDebug(RelativeLayout gameContainer, JoystickView joystick, Button jump_button){
        //current joystick angle text display
        TextView angle_text = new TextView(this);
        angle_text.setId(R.id.debug_angle);
        angle_text.setTextSize(50);
        angle_text.setText("angle : none");

        RelativeLayout.LayoutParams angle_text_params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        angle_text_params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        angle_text_params.setMargins((int)Commons.pxFromDp(this, Commons.debug_elements_margin), (int)Commons.pxFromDp(this, Commons.debug_elements_margin),0,0);
        angle_text.setLayoutParams(angle_text_params);

        gameContainer.addView(angle_text);

        //current joystick direction text display
        TextView direction_text = new TextView(this);
        direction_text.setId(R.id.debug_direction);
        direction_text.setTextSize(50);
        direction_text.setText("direction : none");

        RelativeLayout.LayoutParams direction_text_params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        direction_text_params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        direction_text_params.addRule(RelativeLayout.BELOW, angle_text.getId());

        direction_text_params.setMargins((int)Commons.pxFromDp(this, Commons.debug_elements_margin), (int)Commons.pxFromDp(this, Commons.debug_elements_margin),0,0);
        direction_text.setLayoutParams(direction_text_params);

        gameContainer.addView(direction_text);

        //current action text display
        TextView action_text = new TextView(this);
        action_text.setId(R.id.debug_action);
        action_text.setTextSize(50);
        action_text.setText("action : none");

        RelativeLayout.LayoutParams action_text_params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        action_text_params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        action_text_params.setMargins(0, (int)Commons.pxFromDp(this, Commons.debug_elements_margin),(int)Commons.pxFromDp(this, Commons.debug_elements_margin),0);
        action_text.setLayoutParams(action_text_params);

        gameContainer.addView(action_text);

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
}