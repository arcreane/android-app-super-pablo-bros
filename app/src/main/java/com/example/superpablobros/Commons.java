package com.example.superpablobros;

import android.content.Context;

public interface Commons {
        // desired fps
        int MAX_FPS = 30;
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
        int PABLO_VELOCITY = 4;

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
