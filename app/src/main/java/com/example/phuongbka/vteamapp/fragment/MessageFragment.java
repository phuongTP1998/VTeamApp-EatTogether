package com.example.phuongbka.vteamapp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phuongbka.vteamapp.R;

/**
 * Created by phuongbka on 11/27/17.
 */

public class MessageFragment extends android.support.v4.app.Fragment {
    public MessageFragment(){};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_message,container,false);
    }
}
