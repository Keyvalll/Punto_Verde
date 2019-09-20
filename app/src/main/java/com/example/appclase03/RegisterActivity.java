package com.example.appclase03;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appclase03.model.User;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    //Elementos del view
    private EditText txt_Email;
    private EditText txt_Name;
    private EditText txt_Password;
    private EditText txt_ComfirmPass;
    private ArrayList<User> users;

    //Elementos del filtro
    private String blockCharacterSet = "=~#^|$%&*!/\'¿¡?!#$%&/();,-_[]{}´+¨*";
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //instancias
        txt_Email = findViewById(R.id.txt_email);
        txt_Name = findViewById(R.id.txt_name);
        txt_Password = findViewById(R.id.txt_Password);
        txt_ComfirmPass = findViewById(R.id.txt_confirm_pass);

        //filtros
        txt_Email.setFilters(new InputFilter[] { filter });
        txt_Email.setFilters(new InputFilter[]{new InputFilter.LengthFilter(60)});
        txt_Name.setFilters(new InputFilter[] { filter });
        txt_Name.setFilters(new InputFilter[]{new InputFilter.LengthFilter(100)});
        txt_Password.setFilters(new InputFilter[] { filter });
        txt_Password.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16)});
        txt_ComfirmPass.setFilters(new InputFilter[] { filter });
        txt_ComfirmPass.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16)});


        findViewById(R.id.btn_register).setOnClickListener(this);
        findViewById(R.id.txt_Login).setOnClickListener(this);

        users = new ArrayList<>();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_register:
                makeRegister();
                break;
            case R.id.txt_Login:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;

        }
    }

    //filtro
    private InputFilter filter = new InputFilter() {

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

            if (source != null && blockCharacterSet.contains(("" + source))) {
                return "";
            }
            return null;
        }
    };

    private void makeRegister(){
        String name= txt_Name.getText().toString();
        String email = txt_Email.getText().toString();
        String password = txt_Password.getText().toString();
        String confirm_pass = txt_ComfirmPass.getText().toString();

        if (name.isEmpty()){
            txt_Name.setError("Requerido");
            return;
        }
        if (email.isEmpty()){
            txt_Email.setError("Requerido");
            return;
        }else {
            if (!txt_Email.getText().toString().trim().matches(emailPattern)) {
                Toast.makeText(getApplicationContext(), "Correo electrónico inválido", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if (password.isEmpty()){
            txt_Password.setError("Requerido");
            return;
        }
        if (!password.equals(confirm_pass)){
            txt_ComfirmPass.setError("Diferentes contraseñas");
            return;
        }

        //TODO: Verificar que no existan
        User user = new User(txt_Name.getText().toString(), txt_Email.getText().toString(), txt_Password.getText().toString());

        //TODO: Almacenar un nuevo usuario
        users.add(user);

        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
        finish();
    }
}
