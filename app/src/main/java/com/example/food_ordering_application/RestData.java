package com.example.food_ordering_application;

public class RestData
{
    private int restImageId;
    private String restName;


    public RestData(int restImageId, String restNname)
    {
        this.restImageId = restImageId;
        this.restName = restNname;
    }

    public int getRestImageId()
    {
        return restImageId;
    }

    public void setRestImageId(int restImageId)
    {
        this.restImageId = restImageId;
    }

    public String getRestName()
    {
        return restName;
    }

    public void setRestName(String restName)
    {
        this.restName = restName;
    }
}
