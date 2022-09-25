package com.example.food_ordering_application;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.util.Log;

import com.example.food_ordering_application.FoodAppDBSchema.OrderHistoryTable;

public class OrderHistoryCursor extends CursorWrapper
{

    public OrderHistoryCursor(Cursor cursor)
    {
        super(cursor);
    }

    public OrderHistory getOrderHistory()
    {
        String emailAddress = getString(getColumnIndex(OrderHistoryTable.Cols.EMAIlADDRESS));
        String dateTime = getString(getColumnIndex(OrderHistoryTable.Cols.DATETIME));
        double totalCost = Double.parseDouble(getString(getColumnIndex(OrderHistoryTable.Cols.TOTALCOST)));
        double deliveryFee = Double.parseDouble(getString(getColumnIndex(OrderHistoryTable.Cols.DELIVERYFEE)));


       OrderHistory orderHistory = new OrderHistory(emailAddress,dateTime,totalCost,deliveryFee);

       return orderHistory;
    }


}