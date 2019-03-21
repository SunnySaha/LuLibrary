package com.example.lulibrary;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
    private FirebaseAuth mAuth;


    String Email, Pass, verId, Birthdate, dep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        FirebaseApp.initializeApp(this);

        vercityid = findViewById(R.id.versityId);
        dateBirth = findViewById(R.id.dateofBirth);
        email = findViewById(R.id.emailId);
        pass = findViewById(R.id.pass);
        mAuth = FirebaseAuth.getInstance();


        departmentchoice = findViewById(R.id.departmentSpinner);


        signupButton = findViewById(R.id.signupButton);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(RegisterActivity.this, "Click Worked", Toast.LENGTH_SHORT).show();

                Email = email.getText().toString();
                Pass = pass.getText().toString();
                verId = vercityid.getText().toString();
                dep = departmentchoice.getSelectedItem().toString();
                Birthdate = dateBirth.getText().toString();

                mAuth.createUserWithEmailAndPassword(Email, Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        Toast.makeText(RegisterActivity.this, "auth complete", Toast.LENGTH_SHORT).show();

                        if(task.isSuccessful()){

                            Toast.makeText(RegisterActivity.this, "Registration Successfull", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(i);
                            finish();
                        }




                    }




                });


//
            }


        });//end the code of Signup BUtton


    }//end of OnCreate
}//end of class
