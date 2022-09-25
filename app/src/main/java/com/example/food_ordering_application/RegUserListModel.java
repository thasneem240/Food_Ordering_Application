package com.example.food_ordering_application;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import com.example.food_ordering_application.FoodAppDBSchema.UserDataTable;

public class RegUserListModel
{
    private SQLiteDatabase db;
    private static List<RegUser> regUserList;


    public RegUserListModel()
    {
        regUserList = new ArrayList<>() ;
    }

    public void loadRegUserData(Context context)
    {
        Log.d("Info", "I'm in RegUserListModel : loadRegUserData Method" );

        FoodAppDBHelper dbHelper = new FoodAppDBHelper(context);

        this.db = dbHelper.getWritableDatabase();

        regUserList = getAllRegUserData();

    }

    private List<RegUser> getAllRegUserData()
    {
        ArrayList<RegUser> userList = new ArrayList<>();

        Cursor cursor = db.query(UserDataTable.NAME,null,null,
                null,null,null,null);

        RegUserCursor userDataCursor = new RegUserCursor(cursor);

        try
        {
            userDataCursor.moveToFirst();
            while(!userDataCursor.isAfterLast())
            {
                userList.add(userDataCursor.getRegUser());
                userDataCursor.moveToNext();
            }
        }
        finally
        {
            cursor.close();
        }

        return userList ;

    }

    public void addRegUserData(RegUser regUser)
    {
        Log.d("Info", "I'm in RegUserListModel : addRegUserData Method ");

        regUserList.add(regUser);

        Log.d("Info", ": " + regUser.getUserName() + regUser.getEmailAddress()+
        regUser.getPassword());

        ContentValues cv = new ContentValues();
        cv.put(UserDataTable.Cols.USER_EMAIL,regUser.getEmailAddress());
        cv.put(UserDataTable.Cols.USER_NAME,regUser.getUserName());
        cv.put(UserDataTable.Cols.USER_PASSWORD,regUser.getPassword());

        db.insert(UserDataTable.NAME,null,cv);
    }


    public boolean isEmpty()
    {
        return regUserList.isEmpty();
    }



    public boolean containsSameEmail(String emailAddress)
    {
        boolean isContain = false;

        for (RegUser user: regUserList)
        {
            if(user.getEmailAddress().equals(emailAddress))
            {
                isContain = true;
                break;
            }
        }

        return isContain;
    }


    public RegUser getRegUser(String emailAddress)
    {
        RegUser regUser = null;

        for (RegUser user: regUserList)
        {
            if(user.getEmailAddress().equals(emailAddress))
            {
                regUser = user;
                break;
            }
        }

        return regUser;
    }
}
