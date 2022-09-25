package com.example.food_ordering_application;

import java.util.ArrayList;

public class OrderHistory
{
    private String emailAddress;
    private String dateTime;
    private double totalCost;
    private double deliveryFee;
    private ArrayList<BucketData> bucketDataList;


    public OrderHistory(String emailAddress, String dateTime, double totalCost, double deliveryFee)
    {
        this.emailAddress = emailAddress;
        this.dateTime = dateTime;
        this.totalCost = totalCost;
        this.deliveryFee = deliveryFee;

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

    public double getTotalCost()
    {
        return totalCost;
    }

    public void setTotalCost(double totalCost)
    {
        this.totalCost = totalCost;
    }

    public double getDeliveryFee()
    {
        return deliveryFee;
    }

    public void setDeliveryFee(double deliveryFee)
    {
        this.deliveryFee = deliveryFee;
    }

    public ArrayList<BucketData> getBucketDataList()
    {
        return bucketDataList;
    }

    public void setBucketDataList(ArrayList<BucketData> bucketDataList)
    {
        this.bucketDataList = bucketDataList;
    }
}
