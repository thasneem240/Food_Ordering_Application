package com.example.food_ordering_application;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import com.example.food_ordering_application.FoodAppDBSchema.OrderHistoryTable;

public class OrderHistoryListModel
{
    private SQLiteDatabase db;
    private static ArrayList<OrderHistory> orderHistoryList;

    public OrderHistoryListModel()
    {
        orderHistoryList = new ArrayList<>();
    }


    public void loadOrderHistory(Context context)
    {
        Log.d("Info", "I'm in OrderHistoryListModel : loadOrderHistory Method ");

        FoodAppDBHelper dbHelper = new FoodAppDBHelper(context);

        this.db = dbHelper.getWritableDatabase();

        orderHistoryList = getAllOrderHistoryData();

        Log.d("Info", "I'm exited from loadOrderHistory Method ");
    }


    private ArrayList<OrderHistory> getAllOrderHistoryData()
    {
        ArrayList<OrderHistory> orderList = new ArrayList<>();

        Cursor cursor = db.query(OrderHistoryTable.NAME, null, null,
                null, null, null, null);

        OrderHistoryCursor orderHistoryCursor = new OrderHistoryCursor(cursor);

        try
        {
            orderHistoryCursor.moveToFirst();
            while (!orderHistoryCursor.isAfterLast())
            {
                orderList.add(orderHistoryCursor.getOrderHistory());
                orderHistoryCursor.moveToNext();
            }
        } finally
        {
            cursor.close();
        }

        return orderList;
    }



    public void addOrderHistory(OrderHistory orderHistory)
    {
        Log.d("Info", "I'm in OrderHistoryListModel : addOrderHistory Method ");

        orderHistoryList.add(orderHistory);

        ContentValues cv = new ContentValues();
        cv.put(OrderHistoryTable.Cols.EMAIlADDRESS, orderHistory.getEmailAddress());
        cv.put(OrderHistoryTable.Cols.DATETIME, orderHistory.getDateTime());
        cv.put(OrderHistoryTable.Cols.TOTALCOST, orderHistory.getTotalCost());
        cv.put(OrderHistoryTable.Cols.DELIVERYFEE, orderHistory.getDeliveryFee());

        db.insert(OrderHistoryTable.NAME, null, cv);

    }

}
