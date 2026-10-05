package com.example.formappimplicit;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int generatedCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText nameInput = findViewById(R.id.nameInput);
        EditText passwordInput = findViewById(R.id.passwordInput);
        EditText phoneInput = findViewById(R.id.phoneInput);
        EditText emailInput = findViewById(R.id.emailInput);
        Button submitButton = findViewById(R.id.submitButton);

        LinearLayout verificationLayout = findViewById(R.id.verificationLayout);
        EditText codeVerificationInput = findViewById(R.id.codeVerificationInput);
        Button validateButton = findViewById(R.id.validateButton);

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

            generatedCode = new Random().nextInt(9000) + 1000;

            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:")); // Ensures strictly email applications capture intent
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "App Security Authentication Challenge");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Hello " + name + ",\n\nYour random verification token string sequence code is: " + generatedCode);

            if (emailIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(emailIntent);

                verificationLayout.setVisibility(View.VISIBLE);
            } else {
                Toast.makeText(MainActivity.this, "Security code generated: " + generatedCode + " (No mail handler installed)", Toast.LENGTH_LONG).show();
                verificationLayout.setVisibility(View.VISIBLE);
            }
        });

        validateButton.setOnClickListener(v -> {
            String enteredCode = codeVerificationInput.getText().toString().trim();

            if (enteredCode.equals(String.valueOf(generatedCode))) {
                Toast.makeText(MainActivity.this, "Account Validated Successfully!", Toast.LENGTH_LONG).show();
            } else {
                codeVerificationInput.setError("Invalid validation code sequence. Try again.");
            }
        });
    }
}