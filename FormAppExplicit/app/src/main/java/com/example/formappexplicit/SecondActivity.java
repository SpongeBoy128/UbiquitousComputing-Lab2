package com.example.formappexplicit;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView displayMessage = findViewById(R.id.displayMessage);

        String name = getIntent().getStringExtra("USER_NAME");

        displayMessage.setText("Thank you " + name + ", your request is being processed");
    }
}