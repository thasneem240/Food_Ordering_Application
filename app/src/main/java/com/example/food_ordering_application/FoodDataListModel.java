package com.example.food_ordering_application;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.food_ordering_application.FoodAppDBSchema.FoodDataTable;

import java.util.ArrayList;
import java.util.List;

public class FoodDataListModel
{
    private SQLiteDatabase db;
    private List<FoodData> foodDataList ;

    public FoodDataListModel()
    {
    }

    public void loadFoodData(Context context)
    {
        this.db = new FoodAppDBHelper(context).getWritableDatabase();
        foodDataList = getAllFoodData();
    }

    private List<FoodData> getAllFoodData()
    {
        ArrayList<FoodData> foodList = new ArrayList<>();

        Cursor cursor = db.query(FoodAppDBSchema.FoodDataTable.NAME,null,null,
                null,null,null,null);

        FoodDataCursor foodDataCursor = new FoodDataCursor(cursor);

        try
        {
            foodDataCursor.moveToFirst();
            while(!foodDataCursor.isAfterLast())
            {
                foodList.add(foodDataCursor.getFoodData());
                foodDataCursor.moveToNext();
            }
        }
        finally
        {
            cursor.close();
        }

        return foodList ;

    }


    public void addFoodData(FoodData foodData)
    {
        foodDataList.add(foodData);

        ContentValues cv = new ContentValues();
        cv.put(FoodDataTable.Cols.FOOD_NAME,foodData.getFoodName());
        cv.put(FoodDataTable.Cols.REST_NAME,foodData.getRestName());
        cv.put(FoodDataTable.Cols.FOOD_IMAGE_ID,foodData.getFoodImageId());
        cv.put(FoodDataTable.Cols.FOOD_PRICE,foodData.getPrice());

        db.insert(FoodDataTable.NAME,null,cv);
    }

}


