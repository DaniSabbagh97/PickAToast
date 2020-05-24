package com.example.pickatoast.pickatoast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pickatoast.pickatoast.Services.ChangeWindowService;

public class info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ChangeWindowService.jumpTo(this,ChangeWindowService.getLastClass());
    }
}
