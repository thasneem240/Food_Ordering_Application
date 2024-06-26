package com.example.food_ordering_application;

import java.io.Serializable;

public class BucketData implements Serializable
{
    private RestData restData;
    private FoodData foodData;
    private int itemCount;
    private double itemCost;
    private String emailAddress;
    private String dateTime;

    public BucketData(RestData restData, FoodData foodData, int itemCount)
    {
        this.restData = restData;
        this.foodData = foodData;
        this.itemCount = itemCount;
        itemCost  = itemCount * foodData.getPrice();
    }

    public double getItemCost()
    {
        return itemCost;
    }

    private void setItemCost()
    {
        this.itemCost = itemCount * foodData.getPrice();;
    }

    public RestData getRestData()
    {
        return restData;
    }

    public void setRestData(RestData restData)
    {
        this.restData = restData;
    }

    public FoodData getFoodData()
    {
        return foodData;
    }

    public void setFoodData(FoodData foodData)
    {
        this.foodData = foodData;
    }

    public int getItemCount()
    {
        return itemCount;
    }

    public String getEmailAddress()
    {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    public String getDateTime()
    {
        return dateTime;
    }

    public void setDateTime(String dateTime)
    {
        this.dateTime = dateTime;
    }

    public void setItemCount(int itemCount)
    {
        this.itemCount = itemCount;
        setItemCost();
    }
}
