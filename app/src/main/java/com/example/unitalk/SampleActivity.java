package com.example.unitalk;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import com.example.unitalk.R;

public class SampleActivity extends AppCompatActivity {

    private BottomBar bottomBar;
    private TextView tv;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);


        toast = Toast.makeText(SampleActivity.this, "", Toast.LENGTH_SHORT);

        findViews();
        initViews();
    }

    /**
     * 选中的tab的icon+title的颜色是  colorPrimary
     */
    private void initViews() {
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tab_userCenter:
                        toast.setText("用户中心");
                        toast.show();
                        break;
                    case R.id.tab_languageTest:
                        toast.setText("语言测试");
                        toast.show();
                        break;
                    case R.id.tab_home:
                        toast.setText("主页");
                        toast.show();
                        break;
                    case R.id.tab_partnerMatch:
                        toast.setText("语伴匹配");
                        toast.show();
                        break;
                    case R.id.tab_friendsList:
                        toast.setText("语伴列表");
                        toast.show();
                        break;
                }
            }
        });

        //当前的tab是tab1，而你又点击了tab1，会调用这个方法
        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tab_userCenter:
                        toast.setText("onTabReSelected---tab1");
                        toast.show();
                        break;
                    case R.id.tab_languageTest:
                        toast.setText("onTabReSelected---tab2");
                        toast.show();
                        break;
                    case R.id.tab_home:
                        toast.setText("onTabReSelected---tab3");
                        toast.show();
                        break;
                    case R.id.tab_partnerMatch:
                        toast.setText("onTabReSelected---tab4");
                        toast.show();
                        break;
                    case R.id.tab_friendsList:
                        toast.setText("onTabReSelected---tab5");
                        toast.show();
                        break;
                }
            }
        });
    }

    private void findViews() {
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        tv = (TextView) findViewById(R.id.tv);
    }
}
