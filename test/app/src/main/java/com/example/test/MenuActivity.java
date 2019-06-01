package com.example.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        final Button btn_scanQRcode = findViewById(R.id.btn_scanQRcode);
        final Button btn_showQRcode = findViewById(R.id.btn_showQRcode);
        final Button btn_showInfo = findViewById(R.id.btn_showInfo);
        final Button btn_changePassword = findViewById(R.id.btn_changePassword);
        final Button btn_logout = findViewById(R.id.btn_logout);

        btn_scanQRcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn_showQRcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MenuActivity.this,showQRcodeActivity.class);
                startActivity(intent);
            }
        });

        btn_showInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MenuActivity.this,InfoActivity.class);
                startActivity(intent);
            }
        });
        btn_changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MenuActivity.this,changepasswordActivity.class);
                startActivity(intent);
            }
        });
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
