package com.example.shaildesai.inserdatabase.sql;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.shaildesai.inserdatabase.activity.firebase;
import com.example.shaildesai.inserdatabase.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME ="Users.db";

    private static final String TABLE_USER = "user";

    private static final String COLUMN_USER_ID ="user_id";

    private static final String COLUMN_USER_NAME = "user_name";

    private static final String COLUMN_USER_EMAIL = "user_email";

    private static final String COLUMN_USER_PHONENUMBER = "user_phonenumber";

    private static final String COLUMN_USER_PASSWORD = "user_password";

    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            +COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
            +COLUMN_USER_EMAIL + " TEXT,"  + COLUMN_USER_PASSWORD + " TEXT" + ")";

    private String DROP_USER_TABLE ="DROP TABLE IF EXISTS " + TABLE_USER;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);

    }

    public void addUser(User user){

            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(COLUMN_USER_NAME, user.getName());
            values.put(COLUMN_USER_EMAIL, user.getEmail());
            values.put(COLUMN_USER_PASSWORD, user.getPassword());

            // Inserting Row
            db.insert(TABLE_USER, null, values);
            db.close();
        }



public void fetch(){
    List<firebase> datalist  = new ArrayList<firebase>();

   Cursor c = getReadableDatabase().query(TABLE_USER,
            new String[] {COLUMN_USER_NAME,COLUMN_USER_EMAIL},
            null,null,null,null,null);
   if(c.moveToLast()){
       do {
           datalist.add(new firebase(c.getString(c.getColumnIndex("user_name")),
                   c.getString(c.getColumnIndex("user_email"))
                  ));
       }while (c.moveToNext());
   }
   if(datalist.size()>0){
       DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");
       for(firebase f :datalist){
           ref.push().setValue(f);
       }



   }

}




    public boolean checkUser(String email){
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;

    }


    public boolean checkUser(String email, String password){
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " =?";
        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int  cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if(cursorCount > 0){
            return true;
        }
        return false;

    }

}
