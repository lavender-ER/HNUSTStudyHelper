package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.StrictMode;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.spider.login;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    private Button btn;
    private String username;
    private String password;
    private EditText Edit_pwd;
    private EditText Edit_user;


    public boolean isNetworkAvailable(Activity activity) {
        Context context = activity.getApplicationContext();
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return false;
        } else {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

            if (networkInfo != null && networkInfo.length > 0) {
                for (int i = 0; i < networkInfo.length; i++) {
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        try {
            Intent intent = getIntent();
            System.out.println("我giao" + intent.getStringExtra("out"));
            if (intent.getStringExtra("out").equals("true")) {


                Toast toast = Toast.makeText(getApplicationContext(), "退出成功！", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER | Gravity.TOP, 0, 0);
                toast.show();

                System.out.println("12345678934567890546789");
                SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("login", "");
                editor.apply();
            }
        } catch (Exception e) {
            System.out.println("我giaogiao");

        }

        btn = (Button) findViewById(R.id.signin);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (isNetworkAvailable(Login.this)) {

                    Edit_user = (EditText) findViewById(R.id.account_input);
                    Edit_pwd = (EditText) findViewById(R.id.password_input);

                    username = Edit_user.getText().toString();
                    password = Edit_pwd.getText().toString();

                    SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();

                    editor.putString("login", "1");
                    editor.putString("username", username);
                    editor.putString("password", password);
                    System.out.println("bwduicfbwuorf" + username);
                    System.out.println("qehfurqbegnot" + password);

                    editor.commit();


                    Intent intent = new Intent(Login.this, MainActivity.class);
                    login lg = new login(username, password);
                    System.out.println("bwduicfbwuorf" + username);
                    System.out.println("qehfurqbegnot" + password);


                    Map<String, String> cookies = new HashMap<String, String>();
                    try {
                        cookies = lg.getCookies();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (lg.getFlag() == 0) {
                        Toast toast = Toast.makeText(getApplicationContext(), "密码好像错误哦~", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER | Gravity.TOP, 0, 0);
                        toast.show();
                    } else {
                        System.out.println("ftuvuobnip"+cookies);
                        intent.putExtra("SERVERID", cookies.get("SERVERID"));
                        intent.putExtra("JSESSIONID", cookies.get("JSESSIONID"));
                        Toast toast = Toast.makeText(getApplicationContext(), "正在努力加载，客官稍等~", Toast.LENGTH_SHORT);
//                        toast.setGravity(Gravity.TOP, 100, 20);
                        toast.setGravity(Gravity.CENTER | Gravity.TOP, 0, 0);
                        toast.show();

                        startActivity(intent);
                    }
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "网络出了一点小问题,请检查网络再试哦~", Toast.LENGTH_LONG);
//                        toast.setGravity(Gravity.TOP, 100, 20);
                    toast.setGravity(Gravity.CENTER | Gravity.TOP, 0, 0);
                    toast.show();
                }
            }
        });


    }
}
