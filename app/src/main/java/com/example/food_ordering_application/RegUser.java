package com.example.food_ordering_application;

public class RegUser
{
    private String userName;
    private String emailAddress;
    private String password;



    public RegUser(String userName, String emailAddress, String password)
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
