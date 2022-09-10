package com.example.food_ordering_application;

public class FoodData
{
    private int foodImageId;
    private String foodName;
    private double price;


    public FoodData(int foodImageId, String foodName, double price)
    {
        this.foodImageId = foodImageId;
        this.foodName = foodName;
        this.price = price;
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
}
