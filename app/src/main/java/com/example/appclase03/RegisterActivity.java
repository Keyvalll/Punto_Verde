package com.example.appclase03;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.appclase03.model.User;


import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText txt_Email;
    private TextInputEditText txt_Name;
    private TextInputEditText txt_Password;
    private ArrayList<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txt_Email = findViewById(R.id.txt_Email);
        txt_Name = findViewById(R.id.txt_Name);
        txt_Password = findViewById(R.id.txt_password);

        findViewById(R.id.btn_register).setOnClickListener(this);
        findViewById(R.id.link_login).setOnClickListener(this);

        users = new ArrayList<>();
    }

    private void makeRegister(){
        String name= txt_Name.getText().toString();
        String email = txt_Email.getText().toString();
        String password = txt_Password.getText().toString();

        if (name.isEmpty()){
            txt_Name.setError("Require");
            return;
        }
        if (email.isEmpty()){
            txt_Email.setError("Require");
            return;
        }
        if (password.isEmpty()){
            txt_Password.setError("Require");
            return;
        }

        //TODO: Verificar que no existan
        User user = new User(txt_Name.getText().toString(), txt_Email.getText().toString(), txt_Password.getText().toString());

        //TODO: Almacenar un nuevo usuario
        users.add(user);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_register:
                makeRegister();
                break;

            case R.id.link_login:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
