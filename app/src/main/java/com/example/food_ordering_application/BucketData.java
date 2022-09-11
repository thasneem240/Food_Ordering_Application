package com.example.food_ordering_application;

import java.io.Serializable;

public class BucketData implements Serializable
{
    private RestData restData;
    private FoodData foodData;
    private int itemCount;


    public BucketData(RestData restData, FoodData foodData, int itemCount)
    {
        this.restData = restData;
        this.foodData = foodData;
        this.itemCount = itemCount;
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

    public void setItemCount(int itemCount)
    {
        this.itemCount = itemCount;
    }
}
