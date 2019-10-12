package com.example.skusamzas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends Activity {

    EditText username,password;
    Button b1;
    Intent in;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String USERNAME = "usernameKey";
    public static final String PASSWORD = "passwordKey";
    SharedPreferences sharedpreferences;


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);

        b1=findViewById(R.id.loginButton);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user  = username.getText().toString();
                String pass  = password.getText().toString();

                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(USERNAME, user);
                editor.putString(PASSWORD, pass);
                editor.commit();

                in = new Intent(Login.this,MainActivity.class);
                startActivity(in);
            }
        });
    }
}
