package com.example.food_ordering_application;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.food_ordering_application.FoodAppDBSchema.RestDataTable;

import java.util.ArrayList;
import java.util.List;

public class RestDataListModel
{
    private SQLiteDatabase db;
    private List<RestData> restDataList ;

    public RestDataListModel()
    {
    }

    public void loadRestData(Context context)
    {
        this.db = new FoodAppDBHelper(context).getWritableDatabase();
        restDataList = getAllRestData();
    }

    private List<RestData> getAllRestData()
    {
        ArrayList<RestData> restList = new ArrayList<>();

        Cursor cursor = db.query(RestDataTable.NAME,null,null,
                null,null,null,null);

        RestDataCursor restDataCursor = new RestDataCursor(cursor);

        try
        {
            restDataCursor.moveToFirst();
            while(!restDataCursor.isAfterLast())
            {
                restList.add(restDataCursor.getRestData());
                restDataCursor.moveToNext();
            }
        }
        finally
        {
            cursor.close();
        }

        return restList ;

    }


    public void addRestData(RestData restData)
    {
        restDataList.add(restData);

        ContentValues cv = new ContentValues();
        cv.put(RestDataTable.Cols.Rest_NAME,restData.getRestName());
        cv.put(RestDataTable.Cols.Rest_IMAGE_ID,restData.getRestImageId());

        db.insert(RestDataTable.NAME,null,cv);
    }

}


