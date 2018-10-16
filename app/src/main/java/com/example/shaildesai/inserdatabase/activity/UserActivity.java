package com.example.shaildesai.inserdatabase.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.shaildesai.inserdatabase.R;

public class UserActivity extends AppCompatActivity {

    private TextView textviewname;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        textviewname = (TextView) findViewById(R.id.textViewname);
        String nameFromIntent = getIntent().getStringExtra("EMAIL");
        textviewname.setText("welcome " + nameFromIntent );

    }
}
