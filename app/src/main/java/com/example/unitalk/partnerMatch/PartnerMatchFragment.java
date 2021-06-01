package com.example.unitalk.partnerMatch;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;

import com.example.unitalk.R;
import com.example.unitalk.partnerMatch.activity.MatchResultActivity;

public class PartnerMatchFragment extends Fragment {

    private Button btn_match;
    private Spinner spinner_target_lang;
    private Spinner spinner_intention;
    //private ArrayAdapter<String> adapter;
    private String str_target_lang;
    private String str_intention;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_partnermatch, null);

        spinner_target_lang = (Spinner) view.findViewById(R.id.spinner_target_lang);
        spinner_intention = (Spinner) view.findViewById(R.id.spinner_intention);
        //添加Spinner事件监听
        spinner_target_lang.setOnItemSelectedListener(new ProvOnItemSelectedListener1());
        spinner_intention.setOnItemSelectedListener(new ProvOnItemSelectedListener2());
        //adapter = new ArrayAdapter<String>(this, R.layout.)
        //设置默认值
        spinner_target_lang.setVisibility(View.VISIBLE);
        spinner_intention.setVisibility(View.VISIBLE);

        btn_match = (Button) view.findViewById(R.id.btn_match);
        btn_match.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MatchResultActivity.class);
                intent.putExtra("match_target_language", str_target_lang);
                intent.putExtra("match_intention", str_intention);
                startActivity(intent);
            }
        });
        return view;
    }


    //OnItemSelected监听器
    private class ProvOnItemSelectedListener1 implements OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            //拿到被选择项的值
            str_target_lang = (String) spinner_target_lang.getSelectedItem();
            Log.d("str_target_lang", str_target_lang);

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    private class ProvOnItemSelectedListener2 implements OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            //拿到被选择项的值
            str_intention = (String) spinner_intention.getSelectedItem();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }


}
