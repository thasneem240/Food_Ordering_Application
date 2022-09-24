package com.example.food_ordering_application;

import android.database.Cursor;
import android.database.CursorWrapper;
import com.example.food_ordering_application.FoodAppDBSchema.RestDataTable;

public class RestDataCursor extends CursorWrapper
{

    public RestDataCursor(Cursor cursor)
    {
        super(cursor);
    }

    public RestData getRestData()
    {
        String restName = getString(getColumnIndex(RestDataTable.Cols.Rest_NAME));
        int restImageId = Integer.parseInt(getString(getColumnIndex(RestDataTable.Cols.Rest_IMAGE_ID)));

        RestData restData = new RestData(restImageId,restName);

        return restData;
    }
}