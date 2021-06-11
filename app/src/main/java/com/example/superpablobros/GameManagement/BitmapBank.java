package com.example.superpablobros.GameManagement;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.DisplayMetrics;

import com.example.superpablobros.Commons;
import com.example.superpablobros.MainActivity;
import com.example.superpablobros.PabloSprites;
import com.example.superpablobros.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BitmapBank {
    private Map <PabloSprites, List<Bitmap>> pabloSpritesList;
    private Bitmap spriteSheet;
    private BitmapFactory.Options options;

    public BitmapBank(MainActivity mainActivity) {
        //get all the user sprites
        options = new BitmapFactory.Options();
        options.inScaled = false;
        spriteSheet = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.users, options);

        //pablo sprites
        pabloSpritesList = new HashMap<>();

        pabloSpritesList.put(PabloSprites.STANDING, new ArrayList<>());
        pabloSpritesList.get(PabloSprites.STANDING).add(getScaledExtractedBitmap(mainActivity, 1,26,Commons.PABLO_WIDTH, Commons.BIG_PABLO_HEIGHT));

        pabloSpritesList.put(PabloSprites.RUNNING, new ArrayList<>());
        pabloSpritesList.get(PabloSprites.RUNNING).add(getScaledExtractedBitmap(mainActivity, 43,26,Commons.PABLO_WIDTH, Commons.BIG_PABLO_HEIGHT));
        pabloSpritesList.get(PabloSprites.RUNNING).add(getScaledExtractedBitmap(mainActivity, 60,26,Commons.PABLO_WIDTH, Commons.BIG_PABLO_HEIGHT));
        pabloSpritesList.get(PabloSprites.RUNNING).add(getScaledExtractedBitmap(mainActivity, 77,26,Commons.PABLO_WIDTH, Commons.BIG_PABLO_HEIGHT));
    }

    public Bitmap getFrame(PabloSprites pabloSprites, int p_iFrameNumber){
        return pabloSpritesList.get(pabloSprites).get(p_iFrameNumber);
    }
    public Map <PabloSprites, List <Bitmap>> getPabloSpritesList() { return pabloSpritesList; }

    //function to scale the bitmap
    private Bitmap getScaledExtractedBitmap(MainActivity mainActivity, int x, int y, int width, int height){
        float newProportionFactor = mainActivity.getCurrent_screen().getHeight()/208;
        Bitmap extractedBitmap = Bitmap.createBitmap(spriteSheet, x, y, width, height);
        return Bitmap.createScaledBitmap(extractedBitmap, (int)(extractedBitmap.getWidth()*newProportionFactor), (int)(extractedBitmap.getHeight()*newProportionFactor), false);
    }

    //function to flip the bitmap
    public Bitmap flipBitmap(Bitmap bitmap){
        Matrix m = new Matrix();
        m.preScale(-1, 1);
        Bitmap transformedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, false);
        transformedBitmap.setDensity(DisplayMetrics.DENSITY_DEFAULT);
        return transformedBitmap;
    }
}
