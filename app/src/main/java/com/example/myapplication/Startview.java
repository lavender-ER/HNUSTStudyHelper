package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.os.StrictMode;

import com.example.spider.login;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Startview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startview);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        SharedPreferences.Editor editor2 = getSharedPreferences("data", MODE_PRIVATE).edit();

        SharedPreferences pref2 = getSharedPreferences("data", MODE_PRIVATE);
        if (pref2.getString("login", "").equals("1")) {


            Handler handler = new Handler();


            Runnable runnable = new Runnable() {
                @Override
                public void run() {

                    SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);


                    System.out.println("bwuorf" + sp.getString("username", "1805020205"));
                    System.out.println("qgnot" + sp.getString("password", "L123456789"));
                    login lg = new login(sp.getString("username", "1805020211"), sp.getString("password", "20001212a"));
                    Map<String, String> cookies = new HashMap<String, String>();
                    try {
                        cookies = lg.getCookies();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(Startview.this, MainActivity.class);
                    intent.putExtra("SERVERID", cookies.get("SERVERID"));
                    intent.putExtra("JSESSIONID", cookies.get("JSESSIONID"));
                    startActivity(intent);
                }
            };
            handler.postDelayed(runnable, 500);


//            startActivity(new Intent(this, LoginActivity.class));
//
//            editor2.putString("login", "2");
//
//            editor2.commit();
//

        } else {
            Handler handler = new Handler();

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(Startview.this, Login.class);
                    startActivity(intent);
                }
            };

            handler.postDelayed(runnable, 2000);

        }

    }
}