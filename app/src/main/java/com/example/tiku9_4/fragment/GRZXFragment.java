package com.example.tiku9_4.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tiku9_4.R;
import com.example.tiku9_4.adapter.MxclAdapter;
import com.example.tiku9_4.bean.Mxcl;
import com.example.tiku9_4.net.OkHttpLo;
import com.example.tiku9_4.net.OkHttpTo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class GRZXFragment extends Fragment {
    private ImageView tx;
    private TextView name;
    private TextView sex;
    private TextView tel;
    private TextView sfzh;
    private TextView zcsj;
    private GridView gridView;
    private List<Mxcl> mMxcl;
    private String[] cp;
    private MxclAdapter mxclAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grxx, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        huoqu();
    }

    private void huoqu() {
        OkHttpTo okHttpTo=new OkHttpTo();
        okHttpTo.setUrl("get_root")
                .setJSONObject("UserName","user1")
                .setJSONObject("Password","1234")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.d("--------", "onResponse: ");
                        name.setText("姓名："+jsonObject.optString("name"));
                        sex.setText("性别："+jsonObject.optString("sex"));
                        tel.setText("手机号码："+jsonObject.optString("tel"));
                        sfzh.setText("身份证号："+jsonObject.optString("idnumber"));
                        zcsj.setText("注册时间："+jsonObject.optString("registered_time"));
                        if (jsonObject.optString("sex").equals("男")){
                            tx.setImageResource(R.drawable.touxiang_2);
                        }else {
                            tx.setImageResource(R.drawable.touxiang_1);
                        }
                        JSONArray jsonArray=jsonObject.optJSONArray("plate");
                        cp=new String[jsonArray.length()];
                        for (int i=0;i<jsonArray.length();i++){
                            cp[i]=jsonArray.optString(i);
                        }
                        huoqu1();
                    }

                    @Override
                    public void onFailure(IOException e) {
                        Log.d("--------", "onResponse: ");
                    }
                }).start();
    }

    private void huoqu1() {
        mMxcl.clear();
        OkHttpTo okHttpTo=new OkHttpTo();
        okHttpTo.setUrl("get_vehicle")
                .setJSONObject("UserName","user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray=jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject1=jsonArray.optJSONObject(i);
                            for (int y=0;y<cp.length;y++)
                            {
                                if (jsonObject1.optString("plate").equals(cp[y]))
                                {
                                    mMxcl.add(new Mxcl(jsonObject1.optString("brand"),
                                            jsonObject1.optString("plate")));
                                }
                            }
                        }
                        setadapter();
                    }

                    @Override
                    public void onFailure(IOException e) {

                    }
                }).start();
    }

    private void setadapter() {
        mxclAdapter=new MxclAdapter(mMxcl, getContext());
        gridView.setAdapter(mxclAdapter);
    }

    private void initView() {
        tx = (ImageView) getView().findViewById(R.id.tx);
        name = (TextView) getView().findViewById(R.id.name);
        sex = (TextView) getView().findViewById(R.id.sex);
        tel = (TextView) getView().findViewById(R.id.tel);
        sfzh = (TextView) getView().findViewById(R.id.sfzh);
        zcsj = (TextView) getView().findViewById(R.id.zcsj);
        gridView = (GridView) getView().findViewById(R.id.gridView);
        mMxcl=new ArrayList<>();
    }
}
