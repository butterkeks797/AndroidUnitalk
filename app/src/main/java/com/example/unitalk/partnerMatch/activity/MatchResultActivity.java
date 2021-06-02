package com.example.unitalk.partnerMatch.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.unitalk.DAO.MatchDAO;
import com.example.unitalk.MyDatabaseHelper;
import com.example.unitalk.R;
import com.example.unitalk.bean.MatchResult;
import com.example.unitalk.languageTest.activity.LanguageTestActivity;
import com.example.unitalk.partnerMatch.MatchAdapter;

import java.util.List;

public class MatchResultActivity extends AppCompatActivity implements MatchAdapter.Callback {
    private MyDatabaseHelper dbHelper;
    private MatchDAO matchDAO;
    private ListView list_match_res;
    private MatchAdapter matchAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_result);
        // 获取用户Spinner选择的匹配条件信息
        Intent intent = getIntent();
        String match_target_language = intent.getStringExtra("match_target_language");
        String match_intention = intent.getStringExtra("match_intention");
        Log.d("match_target_language", match_target_language);
        Log.d("match_intention", match_intention);

        list_match_res = (ListView) findViewById(R.id.list_match_res);

        matchDAO = new MatchDAO(this);
        List<MatchResult> partnerList = matchDAO.query(match_target_language);
        matchAdapter = new MatchAdapter(this, this, R.layout.match_result_item, partnerList);
        list_match_res.setAdapter(matchAdapter);
        //list_match_res.setOnItemClickListener(this);

    }


    @Override
    public void click(View v) {
        int position = (int) v.getTag();
        //Toast.makeText(this, "button click --> apply", Toast.LENGTH_SHORT).show();

        new AlertDialog.Builder(this, R.style.AlertDialogTheme)
                .setTitle("😊")
                .setMessage("已向ta发送语伴申请！")
                .show();

    }
}
