package com.example.food_ordering_application;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.food_ordering_application.FoodAppDBSchema.UserDataTable;

public class RegUserCursor extends CursorWrapper
{

    public RegUserCursor(Cursor cursor)
    {
        super(cursor);
    }

    public RegUser getRegUser()
    {
        String emailAddress = getString(getColumnIndex(UserDataTable.Cols.USER_EMAIL));
        String userName = getString(getColumnIndex(UserDataTable.Cols.USER_NAME));
        String userPassword = getString(getColumnIndex(UserDataTable.Cols.USER_PASSWORD));

        RegUser regUser = new RegUser(emailAddress,userName,userPassword);

        return regUser;
    }
}