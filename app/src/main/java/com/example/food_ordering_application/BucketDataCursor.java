package com.example.food_ordering_application;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.food_ordering_application.FoodAppDBSchema.BucketTable;

public class BucketDataCursor extends CursorWrapper
{

    public BucketDataCursor(Cursor cursor)
    {
        super(cursor);
    }

    public BucketData getBucketData()
    {
        String emailAddress = getString(getColumnIndex(BucketTable.Cols.EMAIlADDRESS));
        String dateTime = getString(getColumnIndex(BucketTable.Cols.DATETIME));
        String foodName = getString(getColumnIndex(BucketTable.Cols.FOODNAME));
        String restName = getString(getColumnIndex(BucketTable.Cols.RESTNAME));
        int itemCount = Integer.parseInt(getString(getColumnIndex(BucketTable.Cols.ITEMCOUNT)));
        int foodImageId = Integer.parseInt(getString(getColumnIndex(BucketTable.Cols.FOODID)));
        int restImageId = Integer.parseInt(getString(getColumnIndex(BucketTable.Cols.RESTID)));
        Double foodPrice = Double.parseDouble(getString(getColumnIndex(BucketTable.Cols.FOODPRICE)));


        RestData restData = new RestData(restImageId,restName);
        FoodData foodData = new FoodData(foodImageId,foodName,foodPrice,restName);

        BucketData bucketData = new BucketData(restData,foodData,itemCount);
        bucketData.setEmailAddress(emailAddress);
        bucketData.setDateTime(dateTime);

        return bucketData;
    }
}