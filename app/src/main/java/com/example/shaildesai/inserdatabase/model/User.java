package com.example.shaildesai.inserdatabase.model;

public class User {

    public int id;
    public String name;
    public String email;
    public int phonenumber;
    public String password;


    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id =id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;

    }

    public String getEmail(){
        return email;
    }

    public void setEmail(){
        this.email = email;
    }

    public int getPhonenumber(){
        return phonenumber;
    }

    public void setPhonenumber(){
        this.phonenumber = phonenumber;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(){
        this.password =password;
    }

}
