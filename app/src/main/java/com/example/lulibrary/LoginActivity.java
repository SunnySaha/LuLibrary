package com.example.lulibrary;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText loginemail, loginpass;
    Button loginButton;
    TextView  forgetpass, Loginregisternow;
    private FirebaseUser user;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginemail = findViewById(R.id.loginemailId);
        loginpass = findViewById(R.id.loginpass);
        loginButton = findViewById(R.id.loginsigninButton);

        forgetpass = findViewById(R.id.loginforgetpass);
        Loginregisternow = findViewById(R.id.loginregisternow);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //code for forget password

                final Dialog dialog = new Dialog(LoginActivity.this);
                dialog.setContentView(R.layout.dialog);
                final TextView text =  dialog.findViewById(R.id.textid);
                final EditText emailReset = dialog.findViewById(R.id.resetEmail);
                final Button resetButton = dialog.findViewById(R.id.resetButton);
                final Button cancelview = dialog.findViewById(R.id.cancelButton);

                cancelview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                resetButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String re_email = emailReset.getText().toString();
                        auth.sendPasswordResetEmail(re_email).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> resetTask) {
                                if(resetTask.isSuccessful()){


                                    Toast.makeText(LoginActivity.this, "Reset Password sent to your email", Toast.LENGTH_LONG).show();
                                    dialog.dismiss();
                                }else{
                                    Toast.makeText(LoginActivity.this, "Unrecognized Email", Toast.LENGTH_LONG).show();

                                }
                            }
                        });
                    }
                });//end of code resetButton


                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });






        Loginregisternow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = loginemail.getText().toString();
                String Password = loginpass.getText().toString();

                if(!TextUtils.isEmpty(Email) && !TextUtils.isEmpty(Password)){

                    auth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(i);
                                finish();
                            }
                        }
                    });
                }
            }
        });


    }


}
