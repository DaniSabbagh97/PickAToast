package com.example.pickatoast.pickatoast.Services;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class ChangeWindowService {

    public static void jumpTo(Context context,Class activity){
        Intent i = new Intent(context,activity);
        context.startActivity(i);
        ((Activity)context).finish();
    }
}
