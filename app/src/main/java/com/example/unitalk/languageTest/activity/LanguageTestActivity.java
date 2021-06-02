package com.example.unitalk.languageTest.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.unitalk.MyDatabaseHelper;
import com.example.unitalk.R;
import com.example.unitalk.bean.UserInfo;
import com.example.unitalk.bean.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LanguageTestActivity extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;
    private TestConfiguration testConfiguration;

    private List<Question> questionList;
    private String[] selectionList;

    private int count;
    private int current;
    private boolean wrongMode;
    private int score;
    private UserInfo user;


    private TextView tv_question;
    private RadioButton[] radioButtons;
    private Button btn_next;
    private Button btn_previous;
    private TextView tv_explaination;
    private RadioGroup radioGroup;

    // 获取测试配置：语种、题目数量
    // 从数据库取出题目
    // 生成测试用题目list
    // 答题逻辑+答题界面
    // 分数计算，反馈至前端并落库
    public void initLanguageTest() {
        user = getUserInfo();
        testConfiguration = getConfig();
        questionList = getQuestionList();
        selectionList = new String[testConfiguration.QuestionNum + 2];
        score = 100;
    }

    public void initUIComponents() {
        tv_question = (TextView) findViewById(R.id.question);
        radioButtons = new RadioButton[4];
        radioButtons[0] = (RadioButton) findViewById(R.id.answerA);
        radioButtons[1] = (RadioButton) findViewById(R.id.answerB);
        radioButtons[2] = (RadioButton) findViewById(R.id.answerC);
        radioButtons[3] = (RadioButton) findViewById(R.id.answerD);
        btn_next = (Button) findViewById(R.id.btn_next);
        btn_previous = (Button) findViewById(R.id.btn_previous);
        tv_explaination = (TextView) findViewById(R.id.explaination);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

    }

    public void refreshButtonsWith(Question q) {
        String questionContent = current + 1 + ": " + q.getQuestion();
        tv_question.setText(questionContent);
        radioButtons[0].setText("A. " + q.getChoiceA());
        radioButtons[1].setText("B. " + q.getChoiceB());
        radioButtons[2].setText("C. " + q.getChoiceC());
        radioButtons[3].setText("D. " + q.getChoiceD());
        tv_explaination.setText("本题答案：" + q.getAnswer());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("LanguageTestActivity", "pressed.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_languagetest);
        dbHelper = new MyDatabaseHelper(this, "Unitalk.db", null, 2);
        initLanguageTest();
        initUIComponents();
        count = testConfiguration.QuestionNum;
        current = 0;
        wrongMode = false;


        Question q = questionList.get(0);
        refreshButtonsWith(q);

        radioGroup.clearCheck();

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (current < count - 1) {
                    current++;
                    Question q = questionList.get(current);
                    refreshButtonsWith(q);
                    Log.d("LanguageTestActivity", "q.selected.charAt(0) " + selectionList[current].charAt(0) + " = " + (selectionList[current].charAt(0) - 'A'));
                    if (!selectionList[current].equals("")) {
                        radioButtons[selectionList[current].charAt(0) - 'A'].setChecked(true);
                    }
                    radioGroup.clearCheck();
                } else if (current == count - 1 && wrongMode == true) {
                    saveScoreOrNot();
                } else {
                    final List<Integer> wrongList = checkAnswer(questionList);

                    new AlertDialog.Builder(LanguageTestActivity.this)
                            .setTitle("提示")
                            .setMessage("您的分数为：" + score + "\n" + "您答对了" + (questionList.size() - wrongList.size()) +
                                    "道题目，答错了" + wrongList.size() + "道题目。是否查看错题？")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    wrongMode = true;
                                    List<Question> newList = new ArrayList<>();
                                    for (int i = 0; i < wrongList.size(); i++) {
                                        newList.add(questionList.get(wrongList.get(i)));
                                    }
                                    questionList.clear();
                                    for (int i = 0; i < newList.size(); i++) {
                                        questionList.add(newList.get(i));
                                    }

                                    current = 0;
                                    count = questionList.size();

                                    Question q = questionList.get(current);
                                    refreshButtonsWith(q);
                                    tv_explaination.setVisibility(View.VISIBLE);
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    saveScoreOrNot();
                                }
                            })
                            .show();


                }
            }
        });

        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (current > 0) {
                    current--;
                    Question q = questionList.get(current);
                    refreshButtonsWith(q);

                    radioGroup.clearCheck();
                    if (!selectionList[current].equals("")) {
                        radioButtons[selectionList[current].charAt(0) - 'A'].setChecked(true);
                    }
                }
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < 4; i++) {
                    if (radioButtons[i].isChecked() == true) {
                        selectionList[current + 1] = String.valueOf((char) ('A' + i));
                        Log.d("LanguageTestActivity", "selectionList = " + Arrays.toString(selectionList));
                        break;
                    }
                }
            }
        });
    }

    private void saveScore(int score) {
        // todo 这里需要写db。
        LanguageTestActivity.this.finish();
    }

    private void saveScoreOrNot() {
        new AlertDialog.Builder(LanguageTestActivity.this)
                .setTitle("提示")
                .setMessage("错题查看完毕，需要保存成绩吗？")
                .setPositiveButton("保存成绩", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        saveScore(score);
                    }
                })
                .setNegativeButton("放弃本次成绩", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LanguageTestActivity.this.finish();
                    }
                })
                .show();
    }

    private List<Integer> checkAnswer(List<Question> list) {
        List<Integer> wrongList = new ArrayList<Integer>();
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).getAnswer().equals(selectionList[i + 1])) {
                wrongList.add(i);
                score = score - list.get(i).getPoints();
            }
        }
        Log.d("LanguageTestActivity", "wrongList = " + wrongList.toString() + "; score = " + score);
        return wrongList;
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
                Log.d("LanguageTestActivity", "got question " + i);
                i++;
            } while (cursor.moveToNext() && i < testConfiguration.QuestionNum);
        }
        Log.d("LanguageTestActivity", "total count is " + i);
        cursor.close();

        // TODO shuffle questions
        for (Question question : res) {
            if (question == null)
                break;
        }
        return res;
    }


    public void start() {

    }

    public TestConfiguration getConfig() {
        TestConfiguration t = new TestConfiguration();
        t.Language = "英语";
        t.QuestionNum = 5;
        return t;
    }

    public UserInfo getUserInfo() {
        // Todo 从MainActivity中获得user的信息，用于写入score
        // 暂时mock，等旭晓的代码
        user = new UserInfo();
        user.setNickname("mock名字");
        user.setScore(80);
        return user;
    }

    public void shuffleQuestions() {

    }

    public void setScore() {

    }
}


class TestConfiguration {
    int QuestionNum; // 一次考试的题目数量
    String Language; // 测试语种
}