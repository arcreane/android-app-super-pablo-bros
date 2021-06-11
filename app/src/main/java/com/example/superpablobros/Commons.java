package com.example.superpablobros;

import android.content.Context;

public interface Commons {
        // desired fps
        int MAX_FPS = 60;
        // maximum number of frames to be skipped
        int MAX_FRAME_SKIPS = 1;
        // the frame period
        int FRAME_PERIOD = 1000 / MAX_FPS;

        //sizes
        int BIG_PABLO_HEIGHT = 32;
        int SMALL_PABLO_HEIGHT = 16;
        int PABLO_WIDTH = 16;

        int CLASSIC_BLOCK_WIDTH = 16;
        int CLASSIC_BLOCK_HEIGHT = 16;

        //game var
        float PABLO_MAX_VELOCITY = 2.5f;
        float PABLO_VELOCITY_RATE = 0.25f;
        float PABLO_BASIC_VELOCITY = 0.25f;

        //game var
        float PABLO_MAX_JUMP_HEIGHT= 2.5f;
        float PABLO_JUMP_SPEED = 0.25f;

        //debug
        boolean DEBUG = false;
        float DEBUG_ELEMENTS_MARGIN = 10f;
        float DEBUG_ELEMENT_TEXT_SIZE = 20f;

        //values transformers
        static float dpFromPx(final Context context, final float px) {
                return px / context.getResources().getDisplayMetrics().density;
        }

        static float pxFromDp(final Context context, final float dp) {
                return dp * context.getResources().getDisplayMetrics().density;
        }
}
