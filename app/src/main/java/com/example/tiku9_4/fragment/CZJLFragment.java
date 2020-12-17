package com.example.tiku9_4.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tiku9_4.R;
import com.example.tiku9_4.adapter.CZJLAdapter;
import com.example.tiku9_4.bean.CZJL;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CZJLFragment extends Fragment {
    private ImageView tx;
    private TextView name;
    private TextView zzc;
    private ListView listView;
    private LinearLayout wls;
    private List<CZJL> czjls;
    private CZJLAdapter czjlAdapter;
    private int i=0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.czjl_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        tx = (ImageView) getView().findViewById(R.id.tx);
        name = (TextView) getView().findViewById(R.id.name);
        zzc = (TextView) getView().findViewById(R.id.zzc);
        listView = (ListView) getView().findViewById(R.id.listView);
        wls = (LinearLayout) getView().findViewById(R.id.wls);
    }
}
