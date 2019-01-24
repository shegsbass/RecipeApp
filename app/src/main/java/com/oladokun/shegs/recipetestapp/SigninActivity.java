package com.oladokun.shegs.recipetestapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SigninActivity extends AppCompatActivity {

    private TextView Button;
    EditText email, password;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);


        Button = findViewById(R.id.getstarted);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        /*Button.setOnClickListener(this);*/

        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDataEntered();
            }
        });
    }

    boolean isEmail (EditText text){
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
    boolean isEmpty (EditText text){
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
    boolean isPassword (EditText text){
        CharSequence str = text.getText().toString();
        return isEmpty(password);
    }

    void checkDataEntered(){
        if (isEmail(email) == false){
            email.setError("Enter a valid email address");
        }else if(isPassword(password) == password.length()  < 6 == false){
            password.setError("Invalid! Your password should be 6 character long.");

        }

        else{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }


    public void onClickhelp (View v){
        showDialog(0);}


    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case 0:
                return new AlertDialog.Builder(this)
                        .setIcon(R.drawable.restur)
                        .setTitle("How to Login")
                        .setNegativeButton("OK",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        Toast.makeText(getBaseContext(),
                                                "You can login now!", Toast.LENGTH_SHORT).show();
                                    }
                                }).setMessage("1. Input a valid Email Address else you won't be able to login\n" +
                                "2. Enter any password of your choice above six (6) characters")
                        .create();
        }
        return null;
    }
}

