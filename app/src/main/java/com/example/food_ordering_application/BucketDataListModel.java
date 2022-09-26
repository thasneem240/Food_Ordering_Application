package com.example.food_ordering_application;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import com.example.food_ordering_application.FoodAppDBSchema.BucketTable;

public class BucketDataListModel
{
    private SQLiteDatabase db;
    private static List<BucketData> bucketDataList;

    public BucketDataListModel()
    {
        bucketDataList = new ArrayList<>();
    }


    public void loadBucketData(Context context)
    {
        Log.d("Info", "I'm in BucketDataListModel : loadBucketData Method ");

        FoodAppDBHelper dbHelper = new FoodAppDBHelper(context);

        this.db = dbHelper.getWritableDatabase();

        bucketDataList = getAllBucketData();

        Log.d("Info", "I'm exited from loadBucketData Method ");
    }

    private List<BucketData> getAllBucketData()
    {
        List<BucketData> bucketList = new ArrayList<>();

        Cursor cursor = db.query(BucketTable.NAME, null, null,
                null, null, null, null);

        BucketDataCursor bucketDataCursor = new BucketDataCursor(cursor);


        try
        {
            bucketDataCursor.moveToFirst();
            while (!bucketDataCursor.isAfterLast())
            {
                bucketList.add(bucketDataCursor.getBucketData());
                bucketDataCursor.moveToNext();
            }
        }
        finally
        {
            cursor.close();
        }

        return bucketList;
    }


    public void addBucketData(BucketData bucketData)
    {
        Log.d("Info", "I'm in BucketDataListModel : addBucketData Method ");

        bucketDataList.add(bucketData);

        ContentValues cv = new ContentValues();
        cv.put(BucketTable.Cols.EMAIlADDRESS,bucketData.getEmailAddress());
        cv.put(BucketTable.Cols.DATETIME, bucketData.getDateTime());
        cv.put(BucketTable.Cols.FOODNAME,bucketData.getFoodData().getFoodName());
        cv.put(BucketTable.Cols.RESTNAME,bucketData.getRestData().getRestName());
        cv.put(BucketTable.Cols.ITEMCOUNT,bucketData.getItemCount());
        cv.put(BucketTable.Cols.FOODID,bucketData.getFoodData().getFoodImageId());
        cv.put(BucketTable.Cols.RESTID,bucketData.getRestData().getRestImageId());
        cv.put(BucketTable.Cols.FOODPRICE,bucketData.getFoodData().getPrice());

        db.insert(BucketTable.NAME, null, cv);

    }


    public ArrayList<BucketData> getBucketListOfSpecificUser(String email)
    {
        ArrayList<BucketData> BucketsOfUser = new ArrayList<>();

        for (BucketData bucketData: bucketDataList)
        {
            if(bucketData.getEmailAddress().equals(email))
            {
                BucketsOfUser.add(bucketData);
            }
        }

        return BucketsOfUser;
    }


}
