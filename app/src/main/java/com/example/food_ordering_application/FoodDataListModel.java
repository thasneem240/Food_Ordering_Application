package com.example.food_ordering_application;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.food_ordering_application.FoodAppDBSchema.FoodDataTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FoodDataListModel {
    private SQLiteDatabase db;
    private static List<FoodData> foodDataList;


    public FoodDataListModel() {
        foodDataList = new ArrayList<>();
    }

    public void loadFoodData(Context context) {
        Log.d("Info", "I'm in FoodDataListModel : load FoodData Method");

        FoodAppDBHelper dbHelper = new FoodAppDBHelper(context);

        this.db = dbHelper.getWritableDatabase();

        int totRows = getRowCount();

        Log.d("ROWS_Of Food table = ", "" + totRows);

        if (totRows == 0) {

            Log.d("Info", "I'm in FoodDataListModel : loadFoodData Method in True, So" +
                    "Database didn't exist");

            loadInitialFoodData();

        } else {
            Log.d("Info", "I'm in FoodDataListModel : loadFoodData Method in False, So" +
                    "Database Already exist");

            foodDataList = getAllFoodData();
        }

    }

    private void loadInitialFoodData() {
        Log.d("Info", "I'm in FoodDataListModel : loadInitialFoodData Method ");

        //Add Restaurant List into Database
        ArrayList<FoodData> initialFoodDataList = getInitialFoodList();

        for (FoodData data : initialFoodDataList) {
            addFoodData(data);
        }

        Log.d("Info", "I'm Exited From FoodDataListModel : loadInitialRestData Method ");

    }

    public void addFoodData(FoodData foodData) {
        Log.d("Info", "I'm in FoodDataListModel : addFoodData Method ");

        Log.d("Info", "I'm in FoodDataListModel foodName :" + foodData.getFoodName());

        foodDataList.add(foodData);


        ContentValues cv = new ContentValues();
        cv.put(FoodDataTable.Cols.FOOD_NAME, foodData.getFoodName());
        cv.put(FoodDataTable.Cols.REST_NAME, foodData.getRestName());
        cv.put(FoodDataTable.Cols.FOOD_IMAGE_ID, foodData.getFoodImageId());
        cv.put(FoodDataTable.Cols.FOOD_PRICE, foodData.getPrice());

        db.insert(FoodDataTable.NAME, null, cv);
    }


    private List<FoodData> getAllFoodData() {
        ArrayList<FoodData> foodList = new ArrayList<>();

        Cursor cursor = db.query(FoodAppDBSchema.FoodDataTable.NAME, null, null,
                null, null, null, null);

        FoodDataCursor foodDataCursor = new FoodDataCursor(cursor);

        try {
            foodDataCursor.moveToFirst();
            while (!foodDataCursor.isAfterLast()) {
                foodList.add(foodDataCursor.getFoodData());
                foodDataCursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return foodList;

    }

    public ArrayList<FoodData> getTheSpecificRestFoodList(String restName) {
        Log.d("Info", "Restaurant Name :" + restName);
        ArrayList<FoodData> foodList = new ArrayList<>();

        for (FoodData data : foodDataList) {
            if (data.getRestName().equals(restName)) {
                foodList.add(data);
            }
        }

        return foodList;
    }


    private ArrayList<FoodData> getInitialFoodList() {
        ArrayList<FoodData> foodList = new ArrayList<>();

        /* Add food List for the Restaurants(10 Restaurants) */
        setFoodListForRestaurant(foodList, "KFC - Dehiwala");
        setFoodListForRestaurant(foodList, "Pizza hut - Dehiwala");
        setFoodListForRestaurant(foodList, "McDonald's - Mount Lavinia");
        setFoodListForRestaurant(foodList, "Burger King - Kollupitiya");
        setFoodListForRestaurant(foodList, "Street Burger - Dehiwala");
        setFoodListForRestaurant(foodList, "Cake Hut - Dehiwala");
        setFoodListForRestaurant(foodList, "Rio Ice Cream - Wellawatte");
        setFoodListForRestaurant(foodList, "Elite Indian Restaurant - Dehiwala");
        setFoodListForRestaurant(foodList, "Dinemore - Wellawatte");
        setFoodListForRestaurant(foodList, "Royal Bakery - Wellawatte");

        /* Have to Add For New Restaurant Food List */

        /**************************************************************************************/

//        setFoodListForRestaurant(foodList, "New Family Restaurant");

        /**************************************************************************************/

        return foodList;

    }

    private void setFoodListForRestaurant(ArrayList<FoodData> foodList, String restName) {
        if (restName.equals("KFC - Dehiwala")) {
            foodList.add(new FoodData(R.drawable.kfc1, "KFC Buriyani - Regular", 470.00, restName));
            foodList.add(new FoodData(R.drawable.kfc2, "Hot drumlets 6Pcs", 1090.00, restName));
            foodList.add(new FoodData(R.drawable.kfc3, "Submarine Regular", 650.00, restName));
            foodList.add(new FoodData(R.drawable.kfc4, "Bucket/12Pc", 5650.00, restName));
            foodList.add(new FoodData(R.drawable.kfc5, "Chicken 1Pc", 580.00, restName));
            foodList.add(new FoodData(R.drawable.kfc6, "Vegie burger", 780.00, restName));
            foodList.add(new FoodData(R.drawable.kfc7, "Pepsi 500ML", 200.00, restName));
            foodList.add(new FoodData(R.drawable.kfc8, "7 Up 500ML", 250.00, restName));
        } else {
            if (restName.equals("Pizza hut - Dehiwala")) {
                foodList.add(new FoodData(R.drawable.pizza1, "Hot and Spicy Chicken pizza", 890.00, restName));
                foodList.add(new FoodData(R.drawable.pizza2, "Chicken Wings in BBQ Sauce - 6PCs", 900.00, restName));
                foodList.add(new FoodData(R.drawable.pizza3, "Cheesy Garlic Bread Supreme", 925.00, restName));
                foodList.add(new FoodData(R.drawable.pizza4, "Tandoori Chicken Pizza", 800.00, restName));
                foodList.add(new FoodData(R.drawable.pizza5, "Cheese Lovers Pizza", 820.00, restName));
                foodList.add(new FoodData(R.drawable.pizza6, "Devilled Chicken Pizza", 880.00, restName));
                foodList.add(new FoodData(R.drawable.pizza7, "Chicken Tri Party", 850.00, restName));
                foodList.add(new FoodData(R.drawable.pizza8, "Spicy Seafood Pizza", 1210.00, restName));
                foodList.add(new FoodData(R.drawable.pizza9, "Cinnamon Rolls", 400.00, restName));
            } else {
                if (restName.equals("McDonald's - Mount Lavinia") || restName.equals("Burger King - Kollupitiya")
                        || restName.equals("Street Burger - Dehiwala")) {
                    foodList.add(new FoodData(R.drawable.burger1, "French Fries", 869.00, restName));
                    foodList.add(new FoodData(R.drawable.burger2, "Potato Wedges", 814.00, restName));
                    foodList.add(new FoodData(R.drawable.burger3, "Spicy Chicken Submarine", 1072.00, restName));
                    foodList.add(new FoodData(R.drawable.burger4, "Crispy Chicken Burger", 1098.00, restName));
                    foodList.add(new FoodData(R.drawable.burger5, "Beef Submarine", 1132.00, restName));
                    foodList.add(new FoodData(R.drawable.burger6, "Chicken Kottu", 932.00, restName));
                    foodList.add(new FoodData(R.drawable.burger7, "Crispy Chicken Shawarma", 1099.00, restName));
                    foodList.add(new FoodData(R.drawable.burger8, "Tandoori Club Sandwich", 920.00, restName));
                } else {
                    if (restName.equals("Cake Hut - Dehiwala")) {
                        foodList.add(new FoodData(R.drawable.cake1, "Yummy Chocolate Cake(1Kg)", 1300.00, restName));
                        foodList.add(new FoodData(R.drawable.cake2, "Rose Swirl Cupcake(6PCs)", 1000.00, restName));
                        foodList.add(new FoodData(R.drawable.cake3, "Chocolate Swirl Cupcake with Springles(6pcs)", 1200.00, restName));
                        foodList.add(new FoodData(R.drawable.cake4, "Chocolate Swirl Cupcake with Springles(1pcs)", 200.00, restName));
                        foodList.add(new FoodData(R.drawable.cake5, "Vanilla Cupcake with Sprinkles(6pcs)", 1080.00, restName));
                        foodList.add(new FoodData(R.drawable.cake6, "Vanilla Cupcake with Sprinkles(1pcs)", 200.00, restName));
                    } else {
                        if (restName.equals("Rio Ice Cream - Wellawatte")) {
                            foodList.add(new FoodData(R.drawable.ice1, "Chocolate Oreo Ice Cream Sundae(Large)", 750.00, restName));
                            foodList.add(new FoodData(R.drawable.ice2, "Fruit Salad with Ice Cream(Large)", 650.00, restName));
                            foodList.add(new FoodData(R.drawable.ice3, "Chocolate Sundae", 620.00, restName));
                            foodList.add(new FoodData(R.drawable.ice4, "Waffle Bowl Chocolate Ice Cream", 580.00, restName));
                            foodList.add(new FoodData(R.drawable.ice5, "Oreo Smoothie", 650.00, restName));
                            foodList.add(new FoodData(R.drawable.ice6, "Strawberry and Vanilla Ice Cream Sundae", 750.00, restName));
                        } else {
                            if (restName.equals("Elite Indian Restaurant - Dehiwala") || restName.equals("Dinemore - Wellawatte")
                                    || restName.equals("Royal Bakery - Wellawatte")) {
                                foodList.add(new FoodData(R.drawable.elite1, "Nasi Goreng", 979.00, restName));
                                foodList.add(new FoodData(R.drawable.elite2, "Wattalappam", 363.00, restName));
                                foodList.add(new FoodData(R.drawable.elite3, "Chicken Fried rice", 924.00, restName));
                                foodList.add(new FoodData(R.drawable.elite4, "Mushroom in Hot Butter", 759.00, restName));
                                foodList.add(new FoodData(R.drawable.elite5, "Egg Fried rice", 638.00, restName));
                                foodList.add(new FoodData(R.drawable.elite6, "Milkshake", 418.00, restName));
                                foodList.add(new FoodData(R.drawable.elite7, "Faluda", 979.00, restName));
                                foodList.add(new FoodData(R.drawable.elite8, "Fresh Lime Juice", 352.00, restName));
                                foodList.add(new FoodData(R.drawable.elite9, "Chicken Lollipop", 869.00, restName));
                                foodList.add(new FoodData(R.drawable.elite10, "prawn 65", 1309.00, restName));
                            } else
                            {
                                // For newly Added Food DATA

                                if(restName.equals("New Family Restaurant"))
                                {
                                    /*******************************************************************************************************/

                                    foodList.add(new FoodData(R.drawable.new_eggburger,"Egg Burger", 600.00, restName));
                                    foodList.add(new FoodData(R.drawable.new_noodles,"Noodles", 800.00, restName));
                                    foodList.add(new FoodData(R.drawable.new_vegetable_salad,"Vegetable Salad", 700.00, restName));
                                    foodList.add(new FoodData(R.drawable.new_plain_salad,"Plain Salad", 900.00, restName));
                                    foodList.add(new FoodData(R.drawable.new_pizza,"Pizza", 1600.00, restName));
                                    foodList.add(new FoodData(R.drawable.new_eggpizza,"Egg Pizza", 1800.00, restName));
                                    foodList.add(new FoodData(R.drawable.new_sandwich,"Sandwich", 1000.00, restName));
                                    foodList.add(new FoodData(R.drawable.new_friedrice,"Fried Rice & Kebab", 1500.00, restName));
                                    foodList.add(new FoodData(R.drawable.new_burger_eith_fries,"Burger with Fries", 950.00, restName));
                                    foodList.add(new FoodData(R.drawable.new_pastha,"Pasta", 1100.00, restName));

                                    /*******************************************************************************************************/
                                }
                            }

                        }

                    }
                }

            }
        }
    }


    /* Get the Total raw count */
    private int getRowCount() {
        int count = -1;
        try {
            Cursor cursor = null;
            cursor = db.rawQuery("SELECT * FROM " + FoodDataTable.NAME, null);

            cursor.moveToFirst();

            count = cursor.getCount();

            cursor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;

    }


    public List<FoodData> getRandomSpecialFoodList() {
        List<FoodData> randomList = new ArrayList<>();

        Random random = new Random();

        while (randomList.size() != 15) {
            int result = random.nextInt(foodDataList.size());
            FoodData foodData = foodDataList.get(result);

            if (randomList.isEmpty()) {
                randomList.add(foodData);
            } else {
                if (!containsTheSameFood(randomList, foodData)) {
                    randomList.add(foodData);
                }
            }
        }

        return randomList;
    }


    private boolean containsTheSameFood(List<FoodData> list, FoodData foodData)
    {
        boolean containSameFood = false;

        for (FoodData data : list) {
            if (data.getFoodName().equals(foodData.getFoodName())) {
                containSameFood = true;
                break;
            }

        }

        return containSameFood;

    }


}


