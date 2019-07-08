package id.technoit.dev.appiresto.controllers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

import id.technoit.dev.appiresto.Main2Activity;
import id.technoit.dev.appiresto.home;

public class SessionManager {

   SharedPreferences sharedPreferences;
     SharedPreferences.Editor editor;
     Context context;
    int PRIVATE_MODE = 0;

    public static final String PREF_NAME = "LOGIN";
    public static final String LOGIN = "IS_LOGIN";
    public static final String ID_USER = "ID_USER";
    public static final String NAMA_USER = "NAMA_USER";

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createSession(String username, String nama){
        editor.putBoolean(LOGIN, true);
        editor.putString(ID_USER, username);
        editor.putString(NAMA_USER, nama);
        editor.commit();
    }

    private boolean isLoggin(){
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public void checkLogin(){
        if(!this.isLoggin()){
            Intent i = new Intent(context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
//            ((Main2Activity)context).finish();
        }
    }
    public void checkLoginHome(){
        if(!this.isLoggin()){
            Intent i = new Intent(context, LoginActivity.class);
            context.startActivity(i);
            ((home)context).finish();
        }
    }

    public HashMap<String, String> getUserDetail(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(ID_USER, sharedPreferences.getString(ID_USER, null));
        user.put(NAMA_USER, sharedPreferences.getString(NAMA_USER, null));

        return user;
    }

    public void logout(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, Main2Activity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
//        ((LoginActivity)context).finish();
    }

    public void logoutHome(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, home.class);
        context.startActivity(i);
        ((LoginActivity)context).finish();
    }

}
