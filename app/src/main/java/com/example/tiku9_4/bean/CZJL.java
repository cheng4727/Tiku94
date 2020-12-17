package com.example.tiku9_4.bean;

import org.litepal.crud.LitePalSupport;

public class CZJL extends LitePalSupport {
    private String cph;

    private int jinE;

    private int yuE;

    private String czr;

    private String time;

    public CZJL(String cph, int jinE, int yuE, String czr, String time) {
        this.cph = cph;
        this.jinE = jinE;
        this.yuE = yuE;
        this.czr = czr;
        this.time = time;
    }

    public String getCph() {
        return cph;
    }

    public void setCph(String cph) {
        this.cph = cph;
    }

    public int getJinE() {
        return jinE;
    }

    public void setJinE(int jinE) {
        this.jinE = jinE;
    }

    public int getYuE() {
        return yuE;
    }

    public void setYuE(int yuE) {
        this.yuE = yuE;
    }

    public String getCzr() {
        return czr;
    }

    public void setCzr(String czr) {
        this.czr = czr;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
