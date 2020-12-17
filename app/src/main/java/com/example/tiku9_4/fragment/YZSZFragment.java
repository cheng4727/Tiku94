package com.example.tiku9_4.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiku9_4.MainActivity;
import com.example.tiku9_4.R;
import com.example.tiku9_4.net.OkHttpLo;
import com.example.tiku9_4.net.OkHttpTo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class YZSZFragment extends Fragment implements View.OnClickListener {
    private TextView dangqian;
    private EditText shuru;
    private Button shezhi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.yzszfragment, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        huoqu();
        dianji();
    }

    private void dianji() {
        shezhi.setOnClickListener(this);
    }

    private void huoqu() {
        OkHttpTo okHttpTo=new OkHttpTo();
        okHttpTo.setUrl("get_car_threshold")
                .setDialog(getContext())
                .setJSONObject("UserName","user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray=jsonObject.optJSONArray("ROWS_DETAIL");
                        JSONObject jsonObject1=jsonArray.optJSONObject(0);
                        dangqian.setText("当前1-4号小车账户余额告警阈值为"+jsonObject1.optString("threshold")+"元");
                    }

                    @Override
                    public void onFailure(IOException e) {

                    }
                }).start();
    }

    private void initView() {
        dangqian = (TextView) getView().findViewById(R.id.dangqian);
        shuru = (EditText) getView().findViewById(R.id.shuru);
        shezhi = (Button) getView().findViewById(R.id.shezhi);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shezhi:
                String arr=shuru.getText().toString();
                if (arr.equals("")){
                    Toast.makeText(getContext(),"输入的值不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    shezhi1(arr);

                }
        }
    }

    private void shezhi1(String arr) {
        OkHttpTo okHttpTo=new OkHttpTo();
        okHttpTo.setUrl("set_car_threshold")
                .setJSONObject("UserName","user1")
                .setJSONObject("threshold",arr)
                .setDialog(getContext())
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        if (jsonObject.optString("RESULT").equals("S")){
                            Toast.makeText(getContext(),"设置成功",Toast.LENGTH_SHORT).show();
                        }
                        huoqu();
                    }

                    @Override
                    public void onFailure(IOException e) {

                    }
                }).start();
    }
}
