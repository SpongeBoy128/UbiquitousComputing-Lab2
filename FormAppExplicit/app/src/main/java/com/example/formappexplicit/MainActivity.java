package com.example.formappexplicit;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText nameInput = findViewById(R.id.nameInput);
        EditText passwordInput = findViewById(R.id.passwordInput);
        EditText phoneInput = findViewById(R.id.phoneInput);
        EditText emailInput = findViewById(R.id.emailInput);
        Button submitButton = findViewById(R.id.submitButton);

        nameInput.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                passwordInput.requestFocus();
                return true;
            }
            return false;
        });

        passwordInput.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                phoneInput.requestFocus();
                return true;
            }
            return false;
        });

        phoneInput.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                emailInput.requestFocus();
                return true;
            }
            return false;
        });

        emailInput.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                submitButton.performClick();
                return true;
            }
            return false;
        });

        submitButton.setOnClickListener(v -> {

            String name = nameInput.getText().toString();
            String phone = phoneInput.getText().toString();
            String email = emailInput.getText().toString();

            if (!name.matches("^[A-Za-z ]+$")) {
                nameInput.setError("Name must contain only letters");
                return;
            }

            if (!phone.matches("^[0-9]+$")) {
                phoneInput.setError("Phone must contain only digits");
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailInput.setError("Invalid email format");
                return;
            }

            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("USER_NAME", name);
            startActivity(intent);
        });
    }
}