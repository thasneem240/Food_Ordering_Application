package com.example.food_ordering_application;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.food_ordering_application.FoodAppDBSchema.FoodDataTable;
import com.example.food_ordering_application.FoodAppDBSchema.RestDataTable;
import com.example.food_ordering_application.FoodAppDBSchema.UserDataTable;
import com.example.food_ordering_application.FoodAppDBSchema.OrderHistoryTable;
import com.example.food_ordering_application.FoodAppDBSchema.BucketTable;

import java.util.ArrayList;

public class FoodAppDBHelper extends SQLiteOpenHelper
{
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "FoodApp.db";

    public FoodAppDBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        Log.d("Info", "I'm in FoodAppDBHelper : onCreate Method ");

        /* Table For Food Data */

        String queryForFoodTable = String.format("CREATE TABLE %s ( %s TEXT NOT NULL,%s TEXT NOT NULL," +
                        "%s INTEGER, %s REAL,PRIMARY KEY(%s,%s) )",FoodDataTable.NAME,
                FoodDataTable.Cols.FOOD_NAME,FoodDataTable.Cols.REST_NAME,FoodDataTable.Cols.FOOD_IMAGE_ID,
                FoodDataTable.Cols.FOOD_PRICE, FoodDataTable.Cols.FOOD_NAME,FoodDataTable.Cols.REST_NAME );


        /* Table For Restaurant Data */

        String queryForRestTable = String.format("CREATE TABLE %s ( %s TEXT NOT NULL PRIMARY KEY, %s INTEGER)",
                RestDataTable.NAME,RestDataTable.Cols.Rest_NAME,RestDataTable.Cols.Rest_IMAGE_ID );


        /* Table For User Data */

        String queryForUserTable = String.format("CREATE TABLE %s ( %s TEXT NOT NULL PRIMARY KEY, %s TEXT NOT NULL," +
                "%s TEXT NOT NULL)",UserDataTable.NAME,UserDataTable.Cols.USER_EMAIL,UserDataTable.Cols.USER_NAME,
                UserDataTable.Cols.USER_PASSWORD);


        /* Table For Order History */

        String queryForOrderHistoryTable = String.format("CREATE TABLE %s ( %s TEXT NOT NULL,%s TEXT NOT NULL," +
                        "%s REAL, %s REAL,PRIMARY KEY(%s,%s) )",OrderHistoryTable.NAME,
                OrderHistoryTable.Cols.EMAIlADDRESS,OrderHistoryTable.Cols.DATETIME,OrderHistoryTable.Cols.TOTALCOST,
                OrderHistoryTable.Cols.DELIVERYFEE,OrderHistoryTable.Cols.EMAIlADDRESS,OrderHistoryTable.Cols.DATETIME);


        /* Table For Bucket Data */

        String queryForBucketDataTable = String.format("CREATE TABLE %s ( %s TEXT NOT NULL,%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL, %s TEXT, %s INTEGER, %s INTEGER, %s INTEGER, %s REAL, PRIMARY KEY(%s,%s,%s) )",
                BucketTable.NAME,BucketTable.Cols.EMAIlADDRESS,BucketTable.Cols.DATETIME,BucketTable.Cols.FOODNAME,
        BucketTable.Cols.RESTNAME,BucketTable.Cols.ITEMCOUNT,BucketTable.Cols.FOODID,BucketTable.Cols.RESTID,
                BucketTable.Cols.FOODPRICE,BucketTable.Cols.EMAIlADDRESS,
                BucketTable.Cols.DATETIME,BucketTable.Cols.FOODNAME);


        /* Execute the Query */
        sqLiteDatabase.execSQL(queryForFoodTable);
        sqLiteDatabase.execSQL(queryForRestTable);
        sqLiteDatabase.execSQL(queryForUserTable);
        sqLiteDatabase.execSQL(queryForOrderHistoryTable);
        sqLiteDatabase.execSQL(queryForBucketDataTable);


        Log.d("Info", "I'm Exited From FoodAppDBHelper : onCreate Method ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {

    }





}
