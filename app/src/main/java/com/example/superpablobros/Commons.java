package com.example.superpablobros;

import android.content.Context;

public interface Commons {

        boolean debug = true;

        float debug_elements_margin = 20;

        //values transformers
        static float dpFromPx(final Context context, final float px) {
                return px / context.getResources().getDisplayMetrics().density;
        }

        static float pxFromDp(final Context context, final float dp) {
                return dp * context.getResources().getDisplayMetrics().density;
        }
}
