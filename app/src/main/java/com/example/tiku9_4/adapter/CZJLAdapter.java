package com.example.tiku9_4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tiku9_4.R;
import com.example.tiku9_4.bean.CZJL;

import java.util.List;

public class CZJLAdapter extends BaseAdapter {
    private List<CZJL> czjls;
    private Context context;
    private TextView time;
    private TextView xq;
    private TextView czr;
    private TextView cph;
    private TextView cz;
    private TextView yue;
    private TextView time2;

    @Override
    public int getCount() {
        return czjls.size();
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
        CZJL czjl = czjls.get(i);
        view = LayoutInflater.from(context).inflate(R.layout.grzx_czjl_item, null);
        time = (TextView) view.findViewById(R.id.time);
        xq = (TextView) view.findViewById(R.id.xq);
        czr = (TextView) view.findViewById(R.id.czr);
        cph = (TextView) view.findViewById(R.id.cph);
        cz = (TextView) view.findViewById(R.id.cz);
        yue = (TextView) view.findViewById(R.id.yue);
        time2 = (TextView) view.findViewById(R.id.time2);
        cph.setText("车牌号："+czjl.getCph());
        czr.setText("充值人："+czjl.getCzr());
        cz.setText("充值："+czjl.getJinE());
        yue.setText("余额："+czjl.getYuE());
        time.setText(czjl.getTime().substring(0,10));
        xq.setText(czjl.getTime().substring(czjl.getTime().length()-3));
        time2.setText(czjl.getTime().substring(0,16));
        return view;
    }

}
