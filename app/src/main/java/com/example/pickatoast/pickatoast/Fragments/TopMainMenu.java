package com.example.pickatoast.pickatoast.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.pickatoast.pickatoast.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopMainMenu extends Fragment{



    public TopMainMenu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        return inflater.inflate(R.layout.fragment_top_main_menu, container, false);
    }


}
