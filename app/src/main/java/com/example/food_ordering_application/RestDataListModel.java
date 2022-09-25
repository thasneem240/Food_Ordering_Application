package com.example.food_ordering_application;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.example.food_ordering_application.FoodAppDBSchema.RestDataTable;

import com.example.food_ordering_application.FoodAppDBSchema.RestDataTable;

import java.util.ArrayList;
import java.util.List;

public class RestDataListModel {
    private SQLiteDatabase db;
    private static List<RestData> restDataList;

    public RestDataListModel() {
        restDataList = new ArrayList<>();
    }

    public void loadRestData(Context context) {
        Log.d("Info", "I'm in RestDataListModel : loadRestData Method ");

        FoodAppDBHelper dbHelper = new FoodAppDBHelper(context);


        /* Check Whether database Created or Not */
        //boolean checkDB = dbHelper.getReadableDatabase() != null;

        this.db = dbHelper.getWritableDatabase();

        int totRows = getRowCount();

        Log.d("ROWS_Of_Rest table = ", "" + totRows);

        if (totRows == 0)
        {

            Log.d("Info", "I'm in RestDataListModel : loadRestData Method in True, So" +
                    "Database didn't exist");
            loadInitialRestData();

        }
        else
        {
            Log.d("Info", "I'm in RestDataListModel : loadRestData Method in False, So" +
                    "Database Already exist");

            restDataList = getAllRestData();
        }

        Log.d("Info", "I'm exited from loadRestData Method ");
    }

    private List<RestData> getAllRestData()
    {
        ArrayList<RestData> restList = new ArrayList<>();

        Cursor cursor = db.query(RestDataTable.NAME, null, null,
                null, null, null, null);

        RestDataCursor restDataCursor = new RestDataCursor(cursor);

        try {
            restDataCursor.moveToFirst();
            while (!restDataCursor.isAfterLast())
            {
                restList.add(restDataCursor.getRestData());
                restDataCursor.moveToNext();
            }
        } finally
        {
            cursor.close();
        }

        return restList;

    }


    public void addRestData(RestData restData) {
        Log.d("Info", "I'm in RestDataListModel : addRestData Method ");

        restDataList.add(restData);

        ContentValues cv = new ContentValues();
        cv.put(RestDataTable.Cols.Rest_NAME, restData.getRestName());
        cv.put(RestDataTable.Cols.Rest_IMAGE_ID, restData.getRestImageId());

        db.insert(RestDataTable.NAME, null, cv);

    }

    public RestData get(int i) {
        return restDataList.get(i);
    }

    public int size() {
        return restDataList.size();
    }

    /* When Creating the database It will load the necessary information into the Database */

    private void loadInitialRestData() {
        Log.d("Info", "I'm in RestDataListModel : loadInitialRestData Method ");

        //Add Restaurant List into Database
        ArrayList<RestData> initialRestDataList = getInitialRestList();

        for (RestData data : initialRestDataList) {
            addRestData(data);
        }

        Log.d("Info", "I'm Exited From RestDataListModel : loadInitialRestData Method ");

    }

    private ArrayList<RestData> getInitialRestList() {
        Log.d("Info", "I'm in RestDataListModel : getInitialRestData Method ");

        ArrayList<RestData> restDataList = new ArrayList<>();

        restDataList.add(new RestData(R.drawable.kfc, "KFC - Dehiwala"));
        restDataList.add(new RestData(R.drawable.pizzahut, "Pizza hut - Dehiwala"));
        restDataList.add(new RestData(R.drawable.mcdonald, "McDonald's - Mount Lavinia"));
        restDataList.add(new RestData(R.drawable.rioicecream, "Rio Ice Cream - Wellawatte"));
        restDataList.add(new RestData(R.drawable.elite, "Elite Indian Restaurant - Dehiwala"));
        restDataList.add(new RestData(R.drawable.cakehut, "Cake Hut - Dehiwala"));
        restDataList.add(new RestData(R.drawable.dinemore, "Dinemore - Wellawatte"));
        restDataList.add(new RestData(R.drawable.royalbakery, "Royal Bakery - Wellawatte"));
        restDataList.add(new RestData(R.drawable.burgerking, "Burger King - Kollupitiya"));
        restDataList.add(new RestData(R.drawable.streetburger, "Street Burger - Dehiwala"));

        //Have to Add For New Restaurant List

        Log.d("Info", "I'm Exited From RestDataListModel : getInitialRestData Method ");

        return restDataList;
    }


    /* Get the Total raw count */
    private int getRowCount()
    {
        int count = -1;
        try {
            Cursor cursor = null;
            cursor = db.rawQuery("SELECT * FROM " + RestDataTable.NAME, null);

            cursor.moveToFirst();

            count = cursor.getCount();

            cursor.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return count;

    }

}


