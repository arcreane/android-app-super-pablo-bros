package com.example.superpablobros;

import android.content.Context;

public interface Commons {
        // desired fps
        int MAX_FPS = 30;
        // maximum number of frames to be skipped
        int MAX_FRAME_SKIPS = 1;
        // the frame period
        int FRAME_PERIOD = 1000 / MAX_FPS;

        int BIG_PABLO_HEIGHT = 32;
        int SMALL_PABLO_HEIGHT = 16;
        int PABLO_WIDTH = 16;


        //debug
        boolean debug = false;
        float debug_elements_margin = 20;

        //values transformers
        static float dpFromPx(final Context context, final float px) {
                return px / context.getResources().getDisplayMetrics().density;
        }

        static float pxFromDp(final Context context, final float dp) {
                return dp * context.getResources().getDisplayMetrics().density;
        }
}
