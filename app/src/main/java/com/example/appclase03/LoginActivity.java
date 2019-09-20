package com.example.appclase03;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appclase03.utils.AppPreferences;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //elementos de view
    private Button btn_Log;
    private EditText txt_email;
    private EditText txt_pass;
    private TextView txt_register;

    //caracteres especiales
    private String blockCharacterSet = "=~#^|$%&*!/\'¿¡?!#$%&();,-_[]{}´+¨*";
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //instanciar
        btn_Log = findViewById(R.id.button_login);
        txt_email = findViewById(R.id.email);
        txt_pass = findViewById(R.id.password);
        txt_register = findViewById(R.id.txt_Register);

        //filtros
        txt_email.setFilters(new InputFilter[] { filter });
        txt_pass.setFilters(new InputFilter[] { filter });
        txt_email.setFilters(new InputFilter[]{new InputFilter.LengthFilter(60)});
        txt_pass.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16)});

        btn_Log.setOnClickListener(this);
        txt_register.setOnClickListener(this);
    }

    //filtro de caracteres especiales
    private InputFilter filter = new InputFilter() {

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

            if (source != null && blockCharacterSet.contains(("" + source))) {
                return "";
            }
            return null;
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_login:
                makeLogin();
                break;

            case R.id.txt_Register:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
        }

    }

    private void makeLogin() {
        String email = txt_email.getText().toString();
        String password = txt_pass.getText().toString();

        if (email.isEmpty()) {
            txt_email.setError("Requiere correo electrónico");
            return;
        }else {
            if (!txt_email.getText().toString().trim().matches(emailPattern)) {
                Toast.makeText(getApplicationContext(),"Correo electrónico inválido", Toast.LENGTH_SHORT).show();
            }
        }
        if (password.isEmpty()) {
            txt_pass.setError("Requiere contraseña");
            return;
        }
        if (email.equalsIgnoreCase("admin@admin.com") && password.equalsIgnoreCase("admin123123")) {

            AppPreferences.getInstance(this).put(AppPreferences.Keys.IS_LOGGED_IN, true);

            Toast.makeText( this, "Has hecho login", Toast.LENGTH_LONG).show();


            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
            finish();


        }
    }
}
