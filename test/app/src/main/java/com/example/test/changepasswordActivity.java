package com.example.test;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class changepasswordActivity extends AppCompatActivity {

    Controller c = new Controller();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);

        final EditText et_password = findViewById(R.id.et_password);
        final EditText et_newpassword = findViewById(R.id.et_newPassword);
        final EditText et_newpassword2 = findViewById(R.id.et_confirmPassword);
        final Button btn_change = findViewById(R.id.btn_change);
        final Button btn_back = findViewById(R.id.btn_back);
        final TextView tv_message = findViewById(R.id.tv_message);

        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] result = {""};
                result =  c.changePassword(et_password.getText().toString(), et_newpassword.getText().toString(), et_newpassword2.getText().toString());
                if (result[0].equals("Fail")) {
                    tv_message.setText(result[1]);
                    tv_message.setTextColor(Color.argb(255, 231, 76, 60));
                }else{
                    finish();
                }

            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
