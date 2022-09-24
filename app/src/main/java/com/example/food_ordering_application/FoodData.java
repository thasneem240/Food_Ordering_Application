package com.example.food_ordering_application;

import java.io.Serializable;

public class FoodData implements Serializable
{
    private String foodName;
    private int foodImageId;
    private double price;
    private int itemCount = 1; // remove this
    private String restName;


    public FoodData(int foodImageId, String foodName, double price, String restName)
    {
        this.foodImageId = foodImageId;
        this.foodName = foodName;
        this.price = price;
        this.restName = restName;
    }

    public String getRestName()
    {
        return restName;
    }

    public void setRestName(String restName)
    {
        this.restName = restName;
    }

    public int getFoodImageId()
    {
        return foodImageId;
    }

    public void setFoodImageId(int foodImageId)
    {
        this.foodImageId = foodImageId;
    }

    public String getFoodName()
    {
        return foodName;
    }

    public void setFoodName(String foodName)
    {
        this.foodName = foodName;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
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
