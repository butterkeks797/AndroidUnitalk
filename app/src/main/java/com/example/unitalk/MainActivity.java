package com.example.unitalk;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.unitalk.R;
import com.example.unitalk.friendsList.FriendsListFragment;
import com.example.unitalk.friendsList.NewFriendFragment;
import com.example.unitalk.home.HomeFragment;
import com.example.unitalk.languageTest.LanguageTestFragment;
import com.example.unitalk.partnerMatch.PartnerMatchFragment;
import com.example.unitalk.userCenter.UserCenterFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private HomeFragment homeFragment = new HomeFragment();
    private UserCenterFragment userCenterFragment = new UserCenterFragment();
    private LanguageTestFragment languageTestFragment = new LanguageTestFragment();
    private PartnerMatchFragment partnerMatchFragment = new PartnerMatchFragment();
    private FriendsListFragment friendsListFragment = new FriendsListFragment();
    private NewFriendFragment newFriendFragment = new NewFriendFragment();

    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        //Toolbar 待做

        final View layout = findViewById(R.id.contentContainer);

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        //bottomBar = BottomBar.attach(this, savedInstanceState);
        bottomBar.selectTabWithId(R.id.tab_home);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                Object obj = null;
                switch (tabId) {
                    case R.id.tab_userCenter:
                        obj = userCenterFragment;
                        break;
                    case R.id.tab_languageTest:
                        obj = languageTestFragment;
                        break;
                    case R.id.tab_home:
                        obj = homeFragment;
                        break;
                    case R.id.tab_partnerMatch:
                        obj = partnerMatchFragment;
                        break;
                    case R.id.tab_friendsList:
                        obj = friendsListFragment;
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



        /*
        //2、调用对象的set方法，回传接口对象
        homeFragment.setOnButtonClick(new HomeFragment.OnButtonClick() {
            //3、实现接口对象的方法，
            @Override
            public void onClick(View view) {
                //切换到TwoFragment
                fm.beginTransaction()
                        //替换为TwoFragment
                        .replace(R.layout.fragment_home,new UserCenterFragment())
                        .commit();
            }
        });
*/

        int id = getIntent().getIntExtra("id", 0);
        if (id == 1) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contentContainer,userCenterFragment)
                    .addToBackStack(null)
                    .commit();
            bottomBar.selectTabWithId(R.id.tab_userCenter);
        }
        else if (id == 2) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contentContainer,new LanguageTestFragment())
                    .addToBackStack(null)
                    .commit();
            bottomBar.selectTabWithId(R.id.tab_languageTest);
        }
        else if (id == 3) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contentContainer,new PartnerMatchFragment())
                    .addToBackStack(null)
                    .commit();
            bottomBar.selectTabWithId(R.id.tab_partnerMatch);
        }
        else if (id == 4) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contentContainer,new FriendsListFragment())
                    .addToBackStack(null)
                    .commit();
            bottomBar.selectTabWithId(R.id.tab_friendsList);
        }




    }


}
