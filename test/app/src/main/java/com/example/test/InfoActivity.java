package com.example.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    Controller c = new Controller();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        final TextView tv_name = findViewById(R.id.tv_name);
        final TextView tv_userid = findViewById(R.id.tv_userid);
        final TextView tv_phone = findViewById(R.id.tv_phone);
        final TextView tv_balance = findViewById(R.id.tv_balance);
        final Button btn_back = findViewById(R.id.btn_back);

        tv_name.setText("Name:"+c.getUserName());
        tv_userid.setText("UserName:"+c.getUserID());
        tv_phone.setText("Phone:"+c.getUserPhone());
        tv_balance.setText("Balance:"+String.valueOf(c.getUserBalance()));

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
