package com.example.appclase03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appclase03.utils.AppPreferences;

public class LoginActivity extends AppCompatActivity implements OnClickListener{

    private EditText txtEmail;
    private EditText txtPassword;
    private Button btnLogin;
    private TextView go_to_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtEmail = findViewById(R.id.email);
        txtPassword = findViewById(R.id.password);

        btnLogin = findViewById(R.id.button_login);
        btnLogin.setOnClickListener(this);

        go_to_register = findViewById(R.id.go_register);
        go_to_register.setOnClickListener(this);

    }

    public void onClick (View v){
        switch (v.getId()){
            case R.id.button_login:
                makeLogin();
                break;

            case R.id.go_register:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void makeLogin(){
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();

        if(email.isEmpty()){
            txtEmail.setError("Email is require");
            return;
        }
        if (password.isEmpty()) {
            txtPassword.setError("Password is require");
            return;
        }
        if (email.equalsIgnoreCase( "admin") && password.equalsIgnoreCase("123123123")){

            AppPreferences.getInstance(this).put(AppPreferences.Keys.IS_LOGGED_IN, true);

            Toast.makeText( this, "Has hecho login", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
            finish();

        }else{
            Toast.makeText( this, "No coindicen", Toast.LENGTH_LONG).show();
        }
    }
}
