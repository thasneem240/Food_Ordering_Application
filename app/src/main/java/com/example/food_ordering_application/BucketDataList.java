package com.example.food_ordering_application;

import android.util.Log;

import java.util.ArrayList;

public class BucketDataList
{
    /* Singleton Object */

    private static ArrayList<BucketData> bucketList;

    private BucketDataList()
    {
        bucketList = new ArrayList<>();
    }

    private static BucketDataList bucketDataList = new BucketDataList();

    public static BucketDataList getInstanceOfBucketList()
    {
        return bucketDataList;
    }

    public static boolean contains(BucketData bucketData)
    {
        boolean isContain = false;

        for (BucketData data: bucketList)
        {
            if(bucketData.getFoodData().getFoodName().equals(data.getFoodData().getFoodName())
            && bucketData.getFoodData().getRestName().equals(data.getFoodData().getRestName()))
            {
                isContain = true;

                break;
            }
        }

        return isContain;
    }


    public static boolean isEmpty()
    {
        return bucketList.isEmpty();
    }


    public static void add(BucketData bucketData)
    {
        bucketList.add(bucketData);
    }

    public static boolean checkBucket(BucketData bucketData)
    {
        boolean isContainSameRestFood = false;

      if(!bucketList.get(0).getRestData().getRestName().equals(bucketData.getRestData().getRestName()))
      {
          Log.d("Info", "checkBucket: isContainSameRestFood = TRUE ");
          isContainSameRestFood = true;
      }

      return isContainSameRestFood;

    }

    public static void updateBucketData(BucketData bucketData)
    {
        for (BucketData data: bucketList)
        {
            if(bucketData.getFoodData().getFoodName().equals(data.getFoodData().getFoodName())
                    && bucketData.getFoodData().getRestName().equals(data.getFoodData().getRestName()))
            {
                data.setItemCount(bucketData.getItemCount());
            }
        }
    }


    public static void setTheBucketEmpty()
    {
       bucketList.clear();
    }

    public static BucketData get(int position)
    {
        return bucketList.get(position);
    }

    public static int size()
    {
        return bucketList.size();
    }

    public static double getTotal()
    {
        double total = 0;

        for (BucketData data: bucketList)
        {
            total = total + data.getItemCost();
        }

        return total;
    }
}
