package com.example.test;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class registActivity extends AppCompatActivity {

    Controller c = new Controller();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        final TextView tv_message = findViewById(R.id.tv_message);
        final EditText et_account = findViewById(R.id.et_account);
        final EditText et_password = findViewById(R.id.et_password);
        final EditText et_password2 = findViewById(R.id.et_password2);
        final Button btn_back = findViewById(R.id.btn_back);
        final Button btn_creat = findViewById(R.id.btn_creat);
        final EditText et_name = findViewById(R.id.et_name);
        final EditText et_phone = findViewById(R.id.et_phone);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_creat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username;
                String password;
                String confirmPassword;
                String name;
                String phone;
                String[] result = {""};

                username = et_account.getText().toString();
                password = et_password.getText().toString();
                confirmPassword = et_password2.getText().toString();
                name = et_name.getText().toString();
                phone = et_phone.getText().toString();
                result = c.regist(username, password, confirmPassword, name, phone, 0);
                if (result[0].equals("Fail")) {
                    tv_message.setText(result[1]);
                    tv_message.setTextColor(Color.argb(255, 231, 76, 60));
                }else{
                    finish();
                }

            }
        });

    }
}
