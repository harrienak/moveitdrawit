package com.seordmoret.app.moveitdrawit.Util;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;

public class Utils {

    public static int getColor(Context context, @ColorRes int id) {
        return ContextCompat.getColor(context, id);
    }

    public static Drawable getDrawable(Context context, @DrawableRes int id){
        return ContextCompat.getDrawable(context, id);
    }
}
