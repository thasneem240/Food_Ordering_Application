package com.example.food_ordering_application;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.food_ordering_application.FoodAppDBSchema.FoodDataTable;

import java.util.ArrayList;

public class FoodAppDBHelper extends SQLiteOpenHelper
{
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "FoodApp.db";
    private Context contextOfApplication;

    public FoodAppDBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, VERSION);
        contextOfApplication = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        /* Table For Food Data */

        String queryForFoodTable = String.format("CREATE TABLE %s ( %s TEXT NOT NULL,%s TEXT NOT NULL," +
                        "%s INTEGER, %s REAL,PRIMARY KEY(%s,%s) );",FoodDataTable.NAME,
                FoodDataTable.Cols.FOOD_NAME,FoodDataTable.Cols.REST_NAME,FoodDataTable.Cols.FOOD_IMAGE_ID,
                FoodDataTable.Cols.FOOD_PRICE, FoodDataTable.Cols.FOOD_NAME,FoodDataTable.Cols.REST_NAME );


        /* Execute the Query */
        sqLiteDatabase.execSQL(queryForFoodTable);
        loadInitialFoodData();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {

    }


    private void loadInitialFoodData()
    {
        ArrayList<FoodData> InitialFoodDataList = getInitialFoodList();
        SQLiteDatabase db = new FoodAppDBHelper(contextOfApplication).getWritableDatabase();

        /* Add Food List into Database */
        for (FoodData data: InitialFoodDataList)
        {
            addFoodData(data,db);
        }

    }

    private ArrayList<FoodData> getInitialFoodList()
    {
        ArrayList<FoodData> foodDataList = new ArrayList<>();

        /* Add food List for the Restaurants(10 Restaurants) */
        setFoodListForRestaurant(foodDataList,"KFC - Dehiwala");
        setFoodListForRestaurant(foodDataList,"Pizza hut - Dehiwala");
        setFoodListForRestaurant(foodDataList,"McDonald's - Mount Lavinia");
        setFoodListForRestaurant(foodDataList,"Burger King - Kollupitiya");
        setFoodListForRestaurant(foodDataList,"Street Burger - Dehiwala");
        setFoodListForRestaurant(foodDataList,"Cake Hut - Dehiwala");
        setFoodListForRestaurant(foodDataList,"Rio Ice Cream - Wellawatte");
        setFoodListForRestaurant(foodDataList,"Elite Indian Restaurant - Dehiwala");
        setFoodListForRestaurant(foodDataList,"Dinemore - Wellawatte");
        setFoodListForRestaurant(foodDataList,"Royal Bakery - Wellawatte");

        /* Have to Add For New Restaurant Food List*/



        return  foodDataList;

    }

    private void setFoodListForRestaurant(ArrayList<FoodData> foodDataList, String restName)
    {
        if(restName.equals("KFC - Dehiwala"))
        {
            foodDataList.add(new FoodData(R.drawable.kfc1,"KFC Buriyani - Regular",470.00,restName));
            foodDataList.add(new FoodData(R.drawable.kfc2,"Hot drumlets 6Pcs",1090.00,restName));
            foodDataList.add(new FoodData(R.drawable.kfc3,"Submarine Regular",650.00,restName));
            foodDataList.add(new FoodData(R.drawable.kfc4,"Bucket/12Pc",5650.00,restName));
            foodDataList.add(new FoodData(R.drawable.kfc5,"Chicken 1Pc",580.00,restName));
            foodDataList.add(new FoodData(R.drawable.kfc6,"Vegie burger",780.00,restName));
            foodDataList.add(new FoodData(R.drawable.kfc7,"Pepsi 500ML",200.00,restName));
            foodDataList.add(new FoodData(R.drawable.kfc8,"7 Up 500ML",250.00,restName));
        }
        else
        {
            if(restName.equals("Pizza hut - Dehiwala"))
            {
                foodDataList.add(new FoodData(R.drawable.pizza1,"Hot and Spicy Chicken pizza",890.00,restName));
                foodDataList.add(new FoodData(R.drawable.pizza2,"Chicken Wings in BBQ Sauce - 6PCs",900.00,restName));
                foodDataList.add(new FoodData(R.drawable.pizza3,"Cheesy Garlic Bread Supreme",925.00,restName));
                foodDataList.add(new FoodData(R.drawable.pizza4,"Tandoori Chicken Pizza",800.00,restName));
                foodDataList.add(new FoodData(R.drawable.pizza5,"Cheese Lovers Pizza",820.00,restName));
                foodDataList.add(new FoodData(R.drawable.pizza6,"Devilled Chicken Pizza",880.00,restName));
                foodDataList.add(new FoodData(R.drawable.pizza7,"Chicken Tri Party",850.00,restName));
                foodDataList.add(new FoodData(R.drawable.pizza8,"Spicy Seafood Pizza",1210.00,restName));
                foodDataList.add(new FoodData(R.drawable.pizza9,"Cinnamon Rolls",400.00,restName));
            }
            else
            {
                if(restName.equals("McDonald's - Mount Lavinia") || restName.equals("Burger King - Kollupitiya")
                        || restName.equals("Street Burger - Dehiwala"))
                {
                    foodDataList.add(new FoodData(R.drawable.burger1,"French Fries",869.00,restName));
                    foodDataList.add(new FoodData(R.drawable.burger2,"Potato Wedges",814.00,restName));
                    foodDataList.add(new FoodData(R.drawable.burger3,"Spicy Chicken Submarine",1072.00,restName));
                    foodDataList.add(new FoodData(R.drawable.burger4,"Crispy Chicken Burger",1098.00,restName));
                    foodDataList.add(new FoodData(R.drawable.burger5,"Beef Submarine",1132.00,restName));
                    foodDataList.add(new FoodData(R.drawable.burger6,"Chicken Kottu",932.00,restName));
                    foodDataList.add(new FoodData(R.drawable.burger7,"Crispy Chicken Shawarma",1099.00,restName));
                    foodDataList.add(new FoodData(R.drawable.burger8,"Tandoori Club Sandwich",920.00,restName));
                }
                else
                {
                    if(restName.equals("Cake Hut - Dehiwala"))
                    {
                        foodDataList.add(new FoodData(R.drawable.cake1,"Yummy Chocolate Cake(1Kg)",1300.00,restName));
                        foodDataList.add(new FoodData(R.drawable.cake2,"Rose Swirl Cupcake(6PCs)",1000.00,restName));
                        foodDataList.add(new FoodData(R.drawable.cake3,"Chocolate Swirl Cupcake with Springles(6pcs)",1200.00,restName));
                        foodDataList.add(new FoodData(R.drawable.cake4,"Chocolate Swirl Cupcake with Springles(1pcs)",200.00,restName));
                        foodDataList.add(new FoodData(R.drawable.cake5,"Vanilla Cupcake with Sprinkles(6pcs)",1080.00,restName));
                        foodDataList.add(new FoodData(R.drawable.cake6,"Vanilla Cupcake with Sprinkles(1pcs)",200.00,restName));
                    }
                    else
                    {
                        if(restName.equals("Rio Ice Cream - Wellawatte"))
                        {
                            foodDataList.add(new FoodData(R.drawable.ice1,"Chocolate Oreo Ice Cream Sundae(Large)",750.00,restName));
                            foodDataList.add(new FoodData(R.drawable.ice2,"Fruit Salad with Ice Cream(Large)",650.00,restName));
                            foodDataList.add(new FoodData(R.drawable.ice3,"Chocolate Sundae",620.00,restName));
                            foodDataList.add(new FoodData(R.drawable.ice4,"Waffle Bowl Chocolate Ice Cream",580.00,restName));
                            foodDataList.add(new FoodData(R.drawable.ice5,"Oreo Smoothie",650.00,restName));
                            foodDataList.add(new FoodData(R.drawable.ice6,"Strawberry and Vanilla Ice Cream Sundae",750.00,restName));
                        }
                        else
                        {
                            if(restName.equals("Elite Indian Restaurant - Dehiwala") || restName.equals("Dinemore - Wellawatte")
                                    || restName.equals("Royal Bakery - Wellawatte"))
                            {
                                foodDataList.add(new FoodData(R.drawable.elite1,"Nasi Goreng",979.00,restName));
                                foodDataList.add(new FoodData(R.drawable.elite2,"Wattalappam",363.00,restName));
                                foodDataList.add(new FoodData(R.drawable.elite3,"Chicken Fried rice",924.00,restName));
                                foodDataList.add(new FoodData(R.drawable.elite4,"Mushroom in Hot Butter",759.00,restName));
                                foodDataList.add(new FoodData(R.drawable.elite5,"Egg Fried rice",638.00,restName));
                                foodDataList.add(new FoodData(R.drawable.elite6,"Milkshake",418.00,restName));
                                foodDataList.add(new FoodData(R.drawable.elite7,"Faluda",979.00,restName));
                                foodDataList.add(new FoodData(R.drawable.elite8,"Fresh Lime Juice",352.00,restName));
                                foodDataList.add(new FoodData(R.drawable.elite9,"Chicken Lollipop",869.00,restName));
                                foodDataList.add(new FoodData(R.drawable.elite10,"prawn 65",1309.00,restName));
                            }
                            else
                            {
                                // For newly Added Food DATA
                            }

                        }

                    }
                }

            }
        }
    }


    public void addFoodData(FoodData foodData,SQLiteDatabase db)
    {

        ContentValues cv = new ContentValues();
        cv.put(FoodDataTable.Cols.FOOD_NAME,foodData.getFoodName());
        cv.put(FoodDataTable.Cols.REST_NAME,foodData.getRestName());
        cv.put(FoodDataTable.Cols.FOOD_IMAGE_ID,foodData.getFoodImageId());
        cv.put(FoodDataTable.Cols.FOOD_PRICE,foodData.getPrice());

        db.insert(FoodDataTable.NAME,null,cv);
    }
}
