package com.example.deliverable1test;
public class Users
{
    private String userName;
    private String password;

    // Empty constructor is a requirement
    public Users()
    {

    }
    public Users(String userName, String password)
    {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName()
    {
        return userName;
    }

    public String getPassword()
    {
        return password;
    }

}
