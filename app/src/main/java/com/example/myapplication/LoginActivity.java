package com.example.loginregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    TextView btn;
    Button btnlogin;
    EditText PersonEmail, PersonPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn = findViewById(R.id.signup);
        PersonEmail = findViewById(R.id.PersonEmail);
        PersonPassword = findViewById(R.id.PersonPassword);

        btnlogin = findViewById(R.id.button_login);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCrededentials();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            }
        });
    }

    private void checkCrededentials() {
        String email = PersonEmail.getText().toString();
        String password = PersonPassword.getText().toString();

        if (email.isEmpty() || !email.contains("@")) {
            showError(PersonEmail, "Your email is not valid");
        } else if (password.isEmpty() || password.length() < 7) {
            showError(PersonPassword, "Your password is not valid");
        } else {
            Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
        }
    }

    private void showError(EditText person, java.lang.String s) {
        person.setError(s);
        person.requestFocus();
    }
}