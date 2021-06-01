package com.example.unitalk;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.support.v4.app.Fragment;

import com.example.unitalk.friendsList.FriendsListFragment;
import com.example.unitalk.home.HomeFragment;
import com.example.unitalk.languageTest.LanguageTestFragment;
import com.example.unitalk.partnerMatch.PartnerMatchFragment;
import com.example.unitalk.userCenter.UserCenterFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        // final View layout = findViewById(R.id.contentContainer);

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        //bottomBar = BottomBar.attach(this, savedInstanceState);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                Object obj = null;
                switch (tabId) {
                    case R.id.tab_userCenter:
                        obj = new UserCenterFragment();
                        break;
                    case R.id.tab_languageTest:
                        obj = new LanguageTestFragment();
                        break;
                    case R.id.tab_home:
                        obj = new HomeFragment();
                        break;
                    case R.id.tab_partnerMatch:
                        obj = new PartnerMatchFragment();
                        break;
                    case R.id.tab_friendsList:
                        obj = new FriendsListFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.contentContainer, (Fragment) obj).commit();
            }
        });
        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                // 已经选择了这个标签，又点击一次。即重选。

                //什么也不做
            }
        });
    }
}
