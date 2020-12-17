package com.example.tiku9_4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tiku9_4.R;
import com.example.tiku9_4.bean.Mxcl;

import java.util.List;

public class MxclAdapter extends BaseAdapter {
    private List<Mxcl> mxcls;
    private Context context;
    private ImageView cb;
    private TextView cp;

    public MxclAdapter(List<Mxcl> mxcls, Context context) {
        this.mxcls = mxcls;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mxcls.size();
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
        Mxcl mxcl = mxcls.get(i);
        view = LayoutInflater.from(context).inflate(R.layout.mxcl_item, null);
        cb = (ImageView) view.findViewById(R.id.cb);
        cp = (TextView) view.findViewById(R.id.cp);
        cp.setText(mxcl.getCp());
        if (mxcl.getCb().equals("宝马"))
        {
            cb.setImageResource(R.drawable.baoma);
        }else if (mxcl.getCb().equals("奔驰"))
        {
            cb.setImageResource(R.drawable.benchi);
        }else if (mxcl.getCb().equals("奥迪"))
        {
            cb.setImageResource(R.drawable.audi);
        }else if (mxcl.getCb().equals("中华"))
        {
            cb.setImageResource(R.drawable.zhonghua);
        }
        return view;
    }


}
