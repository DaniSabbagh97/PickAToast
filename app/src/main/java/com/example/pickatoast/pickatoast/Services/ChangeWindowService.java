package com.example.pickatoast.pickatoast.Services;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class ChangeWindowService {

    private static Class lastClass;

    public static void jumpTo(Context context,Class activity){
        setLastClass(context);
        Intent i = new Intent(context,activity);
        context.startActivity(i);
        ((Activity)context).finish();
    }

    public static Class getLastClass() {
        return lastClass;
    }

    public static void setLastClass(Class c) {
        lastClass = c;
    }
    public static void setLastClass(Context c) {
        lastClass = c.getClass();
    }

}
