package com.example.loginregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    TextView btn;

    private EditText PersonName, PersonEmail, PersonPassword, PersonComfirmPassword;
    private TextView Person;

    Button btnRegister;
    private Object String;
    private CharSequence s;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        btn = findViewById(R.id.textView2);
        PersonName = findViewById(R.id.PersonName);
        PersonEmail = findViewById(R.id.PersonEmail);
        PersonPassword = findViewById(R.id.PersonPassword);
        PersonComfirmPassword = findViewById(R.id.PersonComfirmPassword);

        btnRegister = findViewById(R.id.button);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCrededentials();
            }
        });

        checkCrededentials();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });
    }

    private void checkCrededentials() {
        String username = PersonName.getText().toString();
        String email = PersonEmail.getText().toString();
        String password = PersonPassword.getText().toString();
        String ComfirmPassword = PersonComfirmPassword.getText().toString();

        if (username.isEmpty() || username.length() < 7) {
            showError(PersonName, "Your username is not valid.");
        } else if (email.isEmpty() || !email.contains("@")) {
            showError(PersonEmail, "Your email is not valid");
        } else if (password.isEmpty() || password.length() < 7) {
            showError(PersonPassword, "Your password is not valid");
        } else if (ComfirmPassword.isEmpty() || !ComfirmPassword.equals(password)) {
            showError(PersonComfirmPassword, "Password not match");
        } else {
            Toast.makeText(this, "Registration Success", Toast.LENGTH_SHORT).show();
        }
    }

    private void showError(EditText person, java.lang.String s) {
        person.setError(s);
        person.requestFocus();
    }
}

