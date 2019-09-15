package com.example.appclase03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.appclase03.ui.fragments.RegisterFragment;

public class Register_Fragment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register__activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, RegisterFragment.newInstance())
                    .commitNow();
        }
    }
}
