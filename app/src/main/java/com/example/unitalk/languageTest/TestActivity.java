package com.example.unitalk.languageTest;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.unitalk.MyDatabaseHelper;
import com.example.unitalk.R;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;
    private TestConfiguration testConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("TestActivity", "pressed.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_test);
        dbHelper = new MyDatabaseHelper(this, "Unitalk.db", null, 2);


        Button languageTestButton = (Button) findViewById(R.id.language_test);
        languageTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TestActivity", "pressed.");
                languageTestHandler();
            }
        });


    }

    public List<Question> getQuestionList() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // 查询Question表中所有的数据
        Cursor cursor = db.query("Question", null, null, null, null, null, null);
        int i = 0;
        List<Question> res = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                // 遍历Cursor对象，取出数据并打印
                Question q = new Question();
                q.setId(cursor.getInt(cursor.getColumnIndex("id")));
                q.setQuestion(cursor.getString(cursor.getColumnIndex("question")));
                q.setAnswer(cursor.getString(cursor.getColumnIndex("answer")));
                q.setPoints(cursor.getInt(cursor.getColumnIndex("points")));
                q.setChoiceA(cursor.getString(cursor.getColumnIndex("choiceA")));
                q.setChoiceB(cursor.getString(cursor.getColumnIndex("choiceB")));
                q.setChoiceC(cursor.getString(cursor.getColumnIndex("choiceC")));
                q.setChoiceD(cursor.getString(cursor.getColumnIndex("choiceD")));
                res.add(q); // 取出信息
                Log.d("TestActivity", "got question " + i);
                i++;
            } while (cursor.moveToNext());
        }
        Log.d("TestActivity", "total count is " + i);
        cursor.close();

        // TODO shuffle questions
        for (Question question : res) {
            if (question == null)
                break;
        }

        return res;
    }


    // 获取测试配置：语种、题目数量
    // 从数据库取出题目
    // 生成测试用题目list
    // 答题逻辑+答题界面
    // 分数计算，反馈至前端并落库
    public void languageTestHandler() {

        List<Question> questionList = getQuestionList();
    }

    public void start() {

    }

    public TestConfiguration getConfig() {

        return new TestConfiguration();
    }

    public void shuffleQuestions() {

    }

    public void setScore() {

    }
}

class TestConfiguration {
    int QuestionNum; // 一次考试的题目数量
    int Language; // 测试语种

    public int getQuestionNum() {
        return QuestionNum;
    }

    public void setQuestionNum(int questionNum) {
        QuestionNum = questionNum;
    }

    public int getLanguage() {
        return Language;
    }

    public void setLanguage(int language) {
        Language = language;
    }

}