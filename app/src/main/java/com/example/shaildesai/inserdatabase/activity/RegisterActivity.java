package com.example.shaildesai.inserdatabase.activity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shaildesai.inserdatabase.R;
import com.example.shaildesai.inserdatabase.helper.InputValidation;
import com.example.shaildesai.inserdatabase.model.User;
import com.example.shaildesai.inserdatabase.sql.DatabaseHelper;

import static java.sql.DriverManager.println;

public class RegisterActivity extends AppCompatActivity implements ViewStub.OnClickListener {

    private final AppCompatActivity activity = RegisterActivity.this;

    private EditText name;
    private EditText email;
    private EditText password;
    private Button submit;
    private TextView loginlink;
    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;;


    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        getSupportActionBar().hide();

        initViews();
        initListner();
        initObjects();

    }

    private void initViews(){


        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        submit = (Button) findViewById(R.id.submit);
        loginlink = (TextView) findViewById(R.id.Loginlink);

    }

    private void initListner(){
        submit.setOnClickListener(this);
        loginlink.setOnClickListener(this);
    }

    private void initObjects(){
        inputValidation  = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        user = new User();
    }

    @Override
    public void onClick(View v) {
    switch (v.getId()){
        case R.id.submit:
            println("hello my friend" + email);
            postDataToSQLite();
            break;
        case R.id.Loginlink:
            finish();
            break;
    }
    }
    private void postDataToSQLite(){

        println("hello my friend" + email);
        if (!databaseHelper.checkUser(email.getText().toString().trim()))
        {


            user.setName(name.getText().toString().trim());
            user.setEmail(email.getText().toString().trim());
            user.setPassword(password.getText().toString().trim());

            databaseHelper.addUser(user);

            Toast.makeText(this, "Succesfully inserted", Toast.LENGTH_SHORT).show();
            emptyInputEditText();
        }
        else{
            Toast.makeText(this, "Not inserted", Toast.LENGTH_SHORT).show();
        }




        }


    private void emptyInputEditText(){
        name.setText(null);
        email.setText(null);
        password.setText(null);
    }
}
