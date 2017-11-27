package com.example.phuongbka.vteamapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import devlight.io.library.ntb.NavigationTabBar;

public class MainActivity extends AppCompatActivity {
    String color = "#000000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        final NavigationTabBar navigationTabBar = findViewById(R.id.nava_tab_bar);
//        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
//        models.add(
//                new NavigationTabBar.Model.Builder(
//                        getResources().getDrawable(R.drawable.ic_autorenew_black_24dp),
//                        Color.parseColor(color)
//                ).title("Heart").badgeTitle("NTB")
//                        .build()
//        );
//        models.add(
//                new NavigationTabBar.Model.Builder(
//                        getResources().getDrawable(R.drawable.ic_block_black_24dp),
//                        Color.parseColor(color)
//                ).title("Cup")
//                        .badgeTitle("with")
//                        .build()
//        );
//        models.add(
//                new NavigationTabBar.Model.Builder(
//                        getResources().getDrawable(R.drawable.ic_bookmark_border_black_24dp),
//                        Color.parseColor(color)
//                ).title("Diploma")
//                        .badgeTitle("state")
//                        .build()
//        );
//        models.add(
//                new NavigationTabBar.Model.Builder(
//                        getResources().getDrawable(R.drawable.ic_border_style_black_24dp),
//                        Color.parseColor(color)
//                ).title("Flag")
//                        .badgeTitle("icon")
//                        .build()
//        );
//
//        navigationTabBar.setModels(models);
//
//        navigationTabBar.setTitleMode(NavigationTabBar.TitleMode.ACTIVE);
//        navigationTabBar.setBadgeGravity(NavigationTabBar.BadgeGravity.BOTTOM);
//        navigationTabBar.setBadgePosition(NavigationTabBar.BadgePosition.CENTER);
//        navigationTabBar.setIsBadged(true);
//        navigationTabBar.setIsTitled(true);
//        navigationTabBar.setIsTinted(true);
//        navigationTabBar.setIsBadgeUseTypeface(true);
//        navigationTabBar.setBadgeBgColor(Color.RED);
//        navigationTabBar.setBadgeTitleColor(Color.RED);
//        navigationTabBar.setIsSwiped(true);
//        navigationTabBar.setBgColor(Color.WHITE);
//        navigationTabBar.setBadgeSize(10);
//        navigationTabBar.setTitleSize(10);
//        navigationTabBar.setIconSizeFraction((float) 0.5);
    }
}
