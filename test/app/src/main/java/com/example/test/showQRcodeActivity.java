package com.example.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class showQRcodeActivity extends AppCompatActivity {


    Controller c = new Controller();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_qrcode);

        final TextView tv_qrcode = findViewById(R.id.tv_qrcode);

        tv_qrcode.setText(c.getUserQRCodeID());
    }
}
