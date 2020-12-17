package com.example.tiku9_4;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiku9_4.activity.GRZXActivity;
import com.example.tiku9_4.adapter.ZHGLAdapter;
import com.example.tiku9_4.bean.CZJL;
import com.example.tiku9_4.bean.Chongzhi;
import com.example.tiku9_4.bean.ZHGL;
import com.example.tiku9_4.net.OkHttpLo;
import com.example.tiku9_4.net.OkHttpTo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView caidan;
    private TextView plcz;
    private TextView czjl;
    private ListView listView;
    private List<ZHGL> zhgls;
    private List<Chongzhi> chongzhis=new ArrayList<>();
    private ZHGLAdapter zhglAdapter;
    private int yz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getyz();
        plcz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chongzhis.clear();
                for (ZHGL z: zhgls) {
                    if (z.isRecharge()){
                        chongzhis.add(new Chongzhi(z.getPlate() , Integer.parseInt(z.getBalance())));
                    }
                }
                if (chongzhis.size()==0){
                    Toast.makeText(MainActivity.this,"未选择充值车辆",Toast.LENGTH_SHORT).show();
                }else {
                    showDialog();
                }
            }
        });
        czjl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GRZXActivity.class));
                finish();
            }
        });
    }

    private void getyz() {
        OkHttpTo okHttpTo=new OkHttpTo();
        okHttpTo.setUrl("get_car_threshold")
                .setJSONObject("UserName","user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray=jsonObject.optJSONArray("ROWS_DETAIL");
                        JSONObject jsonObject1=jsonArray.optJSONObject(0);
                        yz=jsonObject1.optInt("threshold");
                        getclxx();
                    }

                    @Override
                    public void onFailure(IOException e) {

                    }
                }).start();
    }

    private void getclxx() {
        Log.i("aaaaaaaaaaaaaa", "onResponse: 1");
        OkHttpTo okHttpTo=new OkHttpTo();
        okHttpTo.setUrl("get_vehicle")
                .setJSONObject("UserName","user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        if (zhgls==null){
                            zhgls=new ArrayList<>();
                        }else {
                            zhgls.clear();
                        }
                        Log.i("aaaaaaaaaaaaaa", "onResponse: 2");
                        JSONArray jsonArray=jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject1=jsonArray.optJSONObject(i);
                            zhgls.add(new ZHGL(jsonObject1.optString("id"),
                                    jsonObject1.optString("owner"),
                                    jsonObject1.optString("balance"),
                                    jsonObject1.optString("plate"),
                                    jsonObject1.optString("brand")));
                        }
                        Log.i("aaaaaaaaaaaaaa", "onResponse: 2");
                        if (zhglAdapter==null){
                            zhglAdapter=new ZHGLAdapter(zhgls,yz);
                            listView.setAdapter(zhglAdapter);
                            setListView();
                        }else {
                            zhglAdapter.notifyDataSetChanged();
                        }

                    }

                    @Override
                    public void onFailure(IOException e) {

                    }
                }).start();
    }

    private void setListView() {
        zhglAdapter.setMyOnClick(new ZHGLAdapter.MyOnClick() {
            @Override
            public void onClick(String cph, int yuE) {
                chongzhis.clear();
                chongzhis.add(new Chongzhi(cph,yuE));
                showDialog();
            }
        });
    }
    private void showDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        View view=View.inflate(this,R.layout.dialog,null);
        builder.setView(view);
        final Dialog dialog=builder.show();
        TextView cph=view.findViewById(R.id.cph);
        final EditText jinE=view.findViewById(R.id.jin_e);
        Button cz=view.findViewById(R.id.cz);
        Button qx=view.findViewById(R.id.qx);
        String s="";
        for (int i=0;i<chongzhis.size();i++){
            s+=","+chongzhis.get(i).getCph();
        }if (s.equals("")){
            cph.setText("车牌号:");
        }else {
            cph.setText("车牌号："+s.substring(1,s.length()));
        }
        jinE.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().startsWith("0")){
                    Toast.makeText(MainActivity.this,"请输入1-999之间的整数",Toast.LENGTH_SHORT).show();;
                    jinE.setText("");
                }
            }
        });
        cz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(jinE.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this,"金额不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    dialog.dismiss();
                    setJinE(Integer.parseInt(jinE.getText().toString()));
                }
            }
        });
        qx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private void setJinE(final int jinE) {
        OkHttpTo okHttpTo;
        for (int i=0;i<chongzhis.size();i++){
            okHttpTo=new OkHttpTo();
            final int a=i;
            if (i==chongzhis.size()-1){
                okHttpTo.setDialog(MainActivity.this);
            }
            okHttpTo.setUrl("set_balance")
                    .setJSONObject("UserName","user1")
                    .setJSONObject("plate",chongzhis.get(i).getCph())
                    .setJSONObject("balance",jinE)
                    .setOkHttpLo(new OkHttpLo() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            if (jsonObject.optString("RESULT").equals("S")){
                                new CZJL(chongzhis.get(a).getCph(),jinE,chongzhis.get(a).getYuE(),"gm",
                                        new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
                                if (a==chongzhis.size()-1){
                                    Toast.makeText(MainActivity.this,"充值成功",Toast.LENGTH_SHORT).show();
                                    getclxx();
                                }
                            }
                        }

                        @Override
                        public void onFailure(IOException e) {

                        }
                    }).start();
        }
    }


    private void initView() {
        caidan = (ImageView) findViewById(R.id.caidan);
        plcz = (TextView) findViewById(R.id.plcz);
        czjl = (TextView) findViewById(R.id.czjl);
        listView = (ListView) findViewById(R.id.list_view);
    }
}