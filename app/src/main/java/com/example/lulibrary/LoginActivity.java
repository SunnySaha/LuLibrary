package com.example.lulibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    EditText loginemail, loginpass;
    Button loginButton;
    TextView verified, forgetpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginemail = findViewById(R.id.loginemailId);
        loginpass = findViewById(R.id.loginpass);
        loginButton = findViewById(R.id.loginsigninButton);
        verified = findViewById(R.id.loginverification);
        forgetpass = findViewById(R.id.loginforgetpass);

        forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //code for forget password
            }
        });


    }
}
