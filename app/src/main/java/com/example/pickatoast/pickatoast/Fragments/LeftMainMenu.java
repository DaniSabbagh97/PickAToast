package com.example.pickatoast.pickatoast.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pickatoast.pickatoast.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeftMainMenu extends Fragment {

    public LeftMainMenu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_left_main_menu, container, false);
    }
}
