package com.example.test;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    Controller c = new Controller();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tv_message = findViewById(R.id.tv_message);
        final EditText et_account = findViewById(R.id.et_account);
        final EditText et_password = findViewById(R.id.et_password);
        final Button btn_login = findViewById(R.id.btn_login);
        final Button btn_regist = findViewById(R.id.btn_regist);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(c.login(et_account.getText().toString(),et_password.getText().toString())){
                    Intent intent =  new Intent(MainActivity.this,MenuActivity.class);
                    startActivity(intent);
                }else{
                    tv_message.setText("帳號或密碼錯誤");
                    tv_message.setTextColor(Color.argb(255, 231, 76, 60));
                }

            }
        });

        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MainActivity.this,registActivity.class);
                startActivity(intent);
            }
        });
        
        

    }
}

