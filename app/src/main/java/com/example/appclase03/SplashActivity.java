package com.example.appclase03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.appclase03.utils.AppPreferences;

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.Apptheme_NoActionBar);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        boolean isLoggedIn = AppPreferences.getInstance(this).getBoolean(AppPreferences.Keys.IS_LOGGED_IN);

        Intent intent;

        if (!isLoggedIn){
            intent = new Intent(this, LoginActivity.class);
        }else{
            intent = new Intent(this, MainActivity.class);
        }
        startActivity(intent);
        finish();

    }
}
