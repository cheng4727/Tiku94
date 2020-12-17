package com.example.tiku9_4.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tiku9_4.R;
import com.example.tiku9_4.fragment.CZJLFragment;
import com.example.tiku9_4.fragment.GRZXFragment;
import com.example.tiku9_4.fragment.YZSZFragment;
import com.example.tiku9_4.service.GRZXService;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class GRZXActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView caidan;
    private TextView grxx;
    private View view1;
    private TextView czjl;
    private View view2;
    private TextView yzsz;
    private View view3;
    private LinearLayout layout;
    private FragmentTransaction ft;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_r_z_x);
        initView();
        dianji();
    }

    private void dianji() {
        caidan.setOnClickListener(this);
        grxx.setOnClickListener(this);
        czjl.setOnClickListener(this);
        yzsz.setOnClickListener(this);
    }

    private void initView() {
        caidan = (ImageView) findViewById(R.id.caidan);
        grxx = (TextView) findViewById(R.id.grxx);
        view1 = (View) findViewById(R.id.view1);
        czjl = (TextView) findViewById(R.id.czjl);
        view2 = (View) findViewById(R.id.view2);
        yzsz = (TextView) findViewById(R.id.yzsz);
        view3 = (View) findViewById(R.id.view3);
        layout = (LinearLayout) findViewById(R.id.layout);
        ft=getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.layout,new GRZXFragment());
        ft.commit();
        view1.setVisibility(View.VISIBLE);
        view2.setVisibility(View.INVISIBLE);
        view3.setVisibility(View.INVISIBLE);
        intent=new Intent(GRZXActivity.this, GRZXService.class);
    }

    @Override
    public void onClick(View v) {
        ft=getSupportFragmentManager().beginTransaction();
        switch (v.getId()){
            case R.id.caidan:
                finish();
                stopService(intent);
                break;
            case R.id.grxx:
                ft.replace(R.id.layout,new GRZXFragment());
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.INVISIBLE);
                stopService(intent);
                break;
            case R.id.czjl:
                ft.replace(R.id.layout,new CZJLFragment());
                view2.setVisibility(View.VISIBLE);
                view1.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.INVISIBLE);
                stopService(intent);
                break;
            case R.id.yzsz:
                ft.replace(R.id.layout,new YZSZFragment());
                view3.setVisibility(View.VISIBLE);
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.INVISIBLE);
                startService(intent);
                break;
        }
        ft.commit();
    }
}