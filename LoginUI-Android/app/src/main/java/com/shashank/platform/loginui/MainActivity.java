package com.shashank.platform.loginui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    int count = 0;
    private EditText username_info;
    private EditText password_info;


    private HashMap<String, String > userinfos = new HashMap<String, String>(){{
        put("admin","admin");
        put("xinbo","123456");
        put("shumahe","123456");
    }};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);


   }

    @Override
    protected void onResume() {
        super.onResume();
        //showConnectedDevice();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //BleManager.getInstance().disconnectAllDevice();
        //BleManager.getInstance().destroy();
    }

    private int password_check(String user,String password){

        if (user.isEmpty() || password.isEmpty())
            return R.string.INVALID_INPUT;
        if (userinfos.containsKey(user)){
            if (password.equals(userinfos.get(user)))
                return R.string.CORRECT_INFO;
            else{
                return R.string.WRONG_PASSWORD;
            }
        }
        else{
            return R.string.NO_USER;
        }

    }

    public void click_sigin(View view) {
        username_info = (EditText) findViewById(R.id.user);
        password_info = (EditText) findViewById(R.id.password);
        String username = username_info.getText().toString();
        String password = password_info.getText().toString();
        switch (password_check(username,password)){
            case R.string.CORRECT_INFO:
                Intent intent =new Intent(MainActivity.this,heartbeatActivity.class);
                startActivity(intent);
                break;
            case R.string.INVALID_INPUT:
                Toast.makeText(MainActivity.this, "请输入登陆信息", Toast.LENGTH_SHORT).show();
                break;
            case R.string.WRONG_PASSWORD:
                Toast.makeText(MainActivity.this, "密码错误，请检查后输入", Toast.LENGTH_SHORT).show();
                break;
            case R.string.NO_USER:
                Toast.makeText(MainActivity.this, "该用户没有注册，请联系管理员", Toast.LENGTH_SHORT).show();
                break;
        }
//        if ( password_check(username,password) == R.string.CORRECT_INFO) {
//            Intent intent =new Intent(MainActivity.this,heartbeatActivity.class);
//            startActivity(intent);
//        }
//        else{
//            Toast.makeText(MainActivity.this, "wrong password", Toast.LENGTH_SHORT).show();
//        }
    }



}
