package com.example.shaildesai.inserdatabase.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
    private Button alert;
    private TextView loginlink;
    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;
    private SQLiteOpenHelper sqLiteHelper;



    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        getSupportActionBar().hide();
        initViews();
        initListner();
        initObjects();
        Loandailog();

    }

    private void initViews(){


        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        submit = (Button) findViewById(R.id.submit);
        alert= (Button) findViewById(R.id.alert);
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
        case R.id.alert:
            Loandailog();
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
            databaseHelper.fetch();

        }
        else{
            Toast.makeText(this, "Not inserted", Toast.LENGTH_SHORT).show();
        }




        }

    public void Loandailog(){
        alert.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        activity);

                // set title
                alertDialogBuilder.setTitle("Your Title");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Click yes to exit!")
                        .setCancelable(false)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                Intent intent = new Intent(activity, MainActivity.class);
                                startActivity(intent);
                                // if this button is clicked, close
                                // current activity

                            }
                        })
                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });
    }






    private void emptyInputEditText(){
        name.setText(null);
        email.setText(null);
        password.setText(null);
    }





    }

