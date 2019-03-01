package com.example.lulibrary;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    EditText vercityid, dateBirth, email, pass;
    Button signupButton;
    Spinner departmentchoice;
    FirebaseAuth firebaseAuth;
    String Email, Pass, verId, Birthdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        FirebaseApp.initializeApp(this);

        vercityid = findViewById(R.id.versityId);
        dateBirth = findViewById(R.id.dateofBirth);
        email = findViewById(R.id.emailId);
        pass = findViewById(R.id.pass);

        departmentchoice = findViewById(R.id.departmentSpinner);
        firebaseAuth = FirebaseAuth.getInstance();


        signupButton = findViewById(R.id.signupButton);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(RegisterActivity.this, "Click Worked", Toast.LENGTH_SHORT).show();

                Email = email.getText().toString();
                Pass = pass.getText().toString();
                verId = vercityid.getText().toString();
                final FirebaseUser user = firebaseAuth.getCurrentUser();

                firebaseAuth.createUserWithEmailAndPassword(Email, Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(RegisterActivity.this, "Auth Worked", Toast.LENGTH_SHORT).show();

                        if(task.isSuccessful()){
                            //here check id and birth date with database
                            Toast.makeText(RegisterActivity.this, "task Successfull", Toast.LENGTH_SHORT).show();

                            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(RegisterActivity.this, "Verification send to your Email", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(i);
                                    finish();
                                }
                            });
                        }
                    }
                });

            }
        });

    }
}
