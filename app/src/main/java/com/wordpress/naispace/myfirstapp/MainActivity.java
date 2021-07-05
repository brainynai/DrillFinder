package com.wordpress.naispace.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;   // Add this line

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.wordpress.naispace.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

/*
    // REPLACE the ENTIRE onCreate() method as follows:
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);   // Construct a TextView UI component
        textView.setText("Hello, from Taco Bell!"); // Set the text message for TextView
        setContentView(textView);  // this Activity sets its content to the TextView
    }
    */
}
