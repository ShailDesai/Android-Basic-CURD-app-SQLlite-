package com.example.shaildesai.inserdatabase.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shaildesai.inserdatabase.R;
import com.example.shaildesai.inserdatabase.helper.InputValidation;
import com.example.shaildesai.inserdatabase.sql.DatabaseHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private final AppCompatActivity activity = MainActivity.this;
     private EditText email;
     private  EditText password;
     private Button submit;
     private DatabaseHelper databaseHelper;
     private InputValidation inputValidation;
     private TextView registrationlink;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        initViews();
        initListners();
        initObjects();
    }

    private void initViews() {
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        submit = (Button) findViewById(R.id.submit);
        registrationlink=(TextView) findViewById(R.id.Registrationlink);

    }

    private void initListners(){
        submit.setOnClickListener(this);
        registrationlink.setOnClickListener(this);

    }

    private void initObjects(){
        databaseHelper = new DatabaseHelper(activity);
        inputValidation = new InputValidation(activity);

    }



    public void verifyFromSQLite() {

        if (databaseHelper.checkUser(email.getText().toString().trim()
                , password.getText().toString().trim())) {

            Intent accountsIntent = new Intent(activity, UserActivity.class);
            accountsIntent.putExtra("Email", email.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountsIntent);
        } else {
            Toast.makeText(this, "invalid userid or password", Toast.LENGTH_LONG).show();
        }
    }

        private void  emptyInputEditText(){
            email .setText(null);
            password.setText(null);
        }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.submit:
                verifyFromSQLite();
                break;
            case R.id.Registrationlink:
                Intent intentRegister = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity((intentRegister));
                break;

        }

    }
}
