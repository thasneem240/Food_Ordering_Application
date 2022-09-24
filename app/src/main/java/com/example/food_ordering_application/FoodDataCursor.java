package com.example.food_ordering_application;

import android.database.Cursor;
import android.database.CursorWrapper;

public class FoodDataCursor extends CursorWrapper
{

    public FoodDataCursor(Cursor cursor)
    {
        super(cursor);
    }

    public FoodData getFoodData()
    {
        String foodName = getString(getColumnIndex(FoodAppDBSchema.FoodDataTable.Cols.FOOD_NAME));
        int foodImageId = Integer.parseInt(getString(getColumnIndex(FoodAppDBSchema.FoodDataTable.Cols.FOOD_IMAGE_ID)));
        double foodPrice = Double.parseDouble(getString(getColumnIndex(FoodAppDBSchema.FoodDataTable.Cols.FOOD_PRICE)));
        String restName = getString(getColumnIndex(FoodAppDBSchema.FoodDataTable.Cols.REST_NAME));

        FoodData foodData = new FoodData(foodImageId,foodName,foodPrice,restName);

        return foodData;
    }
}