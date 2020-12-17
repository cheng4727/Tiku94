package com.example.tiku9_4;

import android.app.Application;
import android.content.SharedPreferences;

import org.litepal.LitePal;

public class AppClient extends Application {
    private static SharedPreferences preferences;
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
    }
    public static  void  setUserName(String userName){
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("UserName",userName);
        editor.commit();
    }
    public static String getUserName(){
        return preferences.getString("UserName","user1");
    }
    public static String getSex()
    {
        return preferences.getString("Sex","");
    }
    public static void setSex(String Sex)
    {
        preferences.edit().putString("Sex",Sex).apply();
    }

    public static String getChezhu()
    {
        return preferences.getString("Chezhu","");
    }
    public static void setChezhu(String Chezhu)
    {
        preferences.edit().putString("Chezhu",Chezhu).apply();
    }
}
