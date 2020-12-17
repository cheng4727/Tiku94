package com.example.tiku9_4.bean;

public class Chongzhi {
    private String cph;
    private int yuE;

    public Chongzhi(String cph, int yuE) {
        this.cph = cph;
        this.yuE = yuE;
    }

    public String getCph() {
        return cph;
    }

    public void setCph(String cph) {
        this.cph = cph;
    }

    public int getYuE() {
        return yuE;
    }

    public void setYuE(int yuE) {
        this.yuE = yuE;
    }

    @Override
    public String toString() {
        return "Chongzhi{" +
                "cph='" + cph + '\'' +
                ", yuE=" + yuE +
                '}';
    }
}
