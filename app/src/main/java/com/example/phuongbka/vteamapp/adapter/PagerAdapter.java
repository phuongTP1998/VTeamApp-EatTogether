package com.example.phuongbka.vteamapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.phuongbka.vteamapp.fragment.HomeFragment;
import com.example.phuongbka.vteamapp.fragment.MessageFragment;
import com.example.phuongbka.vteamapp.fragment.PersonalFragment;

/**
 * Created by phuongbka on 11/27/17.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    public PagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag = null;
        switch (position){
            case 0:
                frag = new HomeFragment();
                break;
            case 1:
                frag = new MessageFragment();
                break;
            case 2:
                frag = new PersonalFragment();
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
