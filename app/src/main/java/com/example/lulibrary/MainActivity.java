package com.example.lulibrary;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {



    private  FirebaseAuth mAuth;
    TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
         tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.mainPager);
        viewPager.setOffscreenPageLimit(2);

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());

        pagerAdapter.addFragment(new HomeFragment(), "Home");
        pagerAdapter.addFragment(new ProfileFragment(), "Profile");
        pagerAdapter.addFragment(new ContentsFragment(), "Contents");

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);





    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser==null){

            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        }

    }


}
