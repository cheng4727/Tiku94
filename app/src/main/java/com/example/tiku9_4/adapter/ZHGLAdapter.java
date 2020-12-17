package com.example.tiku9_4.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tiku9_4.R;
import com.example.tiku9_4.bean.ZHGL;

import java.util.List;

public class ZHGLAdapter extends BaseAdapter {
    private List<ZHGL> zhgls;
    private int yz;
    private MyOnClick myOnClick;
    private LinearLayout layout;
    private TextView xuhao;
    private ImageView chebiao;
    private TextView chepai;
    private TextView chehzu;
    private TextView yue;
    private CheckBox Cb;
    private Button cz;


    public interface MyOnClick {
        void onClick(String cph, int yuE);
    }

    public void setMyOnClick(MyOnClick myOnClick) {
        this.myOnClick = myOnClick;
    }

    public ZHGLAdapter(List<ZHGL> zhgls, int yz) {
        this.zhgls = zhgls;
        this.yz = yz;

    }

    @Override
    public int getCount() {
        return zhgls.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ZHGL zhgl = zhgls.get(i);
        Log.e("sssssss", "getView: "+zhgls.get(i).getBrand());
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.zhgl_item, null);
        layout = (LinearLayout) view.findViewById(R.id.layout);
        xuhao = (TextView) view.findViewById(R.id.xuhao);
        chebiao = (ImageView) view.findViewById(R.id.chebiao);
        chepai = (TextView) view.findViewById(R.id.chepai);
        chehzu = (TextView) view.findViewById(R.id.chehzu);
        yue = (TextView) view.findViewById(R.id.yue);
        Cb = (CheckBox) view.findViewById(R.id.Cb);
        cz = (Button) view.findViewById(R.id.cz);
        xuhao.setText(zhgl.getId());
        chepai.setText(zhgl.getPlate());
        chehzu.setText("车主："+zhgl.getOwner());
        yue.setText("余额"+zhgl.getBalance());

        switch (zhgl.getBrand()){
            case "奔驰":
                chebiao.setImageResource(R.mipmap.benci);
                break;
            case "宝马":
                chebiao.setImageResource(R.mipmap.bmw);
                break;
            case "中华":
                chebiao.setImageResource(R.mipmap.zhonghua);
                break;
            case "奥迪":
                chebiao.setImageResource(R.mipmap.audi);
                break;
        }
        if (Integer.parseInt(zhgl.getBalance())<yz){
            layout.setBackgroundResource(R.drawable.biankuang2);
        }else {
            layout.setBackgroundResource(R.drawable.biankuang1);
        }
        Cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                zhgl.setRecharge(isChecked);
            }
        });
        cz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myOnClick.onClick(zhgl.getPlate(),Integer.parseInt(zhgl.getBalance()));
            }
        });
        return view;
    }
}
