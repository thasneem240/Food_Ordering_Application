package com.example.food_ordering_application;

import java.io.Serializable;

public class RegUser implements Serializable
{
    private String emailAddress;
    private String userName;
    private String password;



    public RegUser(String emailAddress, String userName, String password)
    {
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getEmailAddress()
    {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
