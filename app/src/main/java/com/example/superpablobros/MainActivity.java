package com.example.superpablobros;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
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

        TextView angle_text = (TextView) findViewById(R.id.angle);
        TextView direction_text = (TextView) findViewById(R.id.direction);
        TextView action_text = (TextView) findViewById(R.id.action);

        Button jump_button = (Button) findViewById(R.id.jump);

        JoystickView joystick = (JoystickView) findViewById(R.id.joystickView);

        //remove top bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

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