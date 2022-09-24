package com.example.food_ordering_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity
{

    private ImageView imageOfSelectRest;
    private TextView nameOfSelectRest;
    private ArrayList<FoodData> foodDataList1,foodDataList2,
            foodDataList3,foodDataList4,foodDataList5,foodDataList6;

    private static final String CHOICE = "Serialized_UserChoice_RestData_Object";

    public SecondActivity()
    {
      /* foodDataList1 = new ArrayList<>();
       foodDataList2 = new ArrayList<>();
       foodDataList3 = new ArrayList<>();
       foodDataList4 = new ArrayList<>();
       foodDataList5 = new ArrayList<>();
       foodDataList6 = new ArrayList<>();

       foodDataList1.add(new FoodData(R.drawable.kfc1,"KFC Buriyani - Regular",470.00));
       foodDataList1.add(new FoodData(R.drawable.kfc2,"Hot drumlets 6Pcs",1090.00));
       foodDataList1.add(new FoodData(R.drawable.kfc3,"Submarine Regular",650.00));
       foodDataList1.add(new FoodData(R.drawable.kfc4,"Bucket/12Pc",5650.00));
       foodDataList1.add(new FoodData(R.drawable.kfc5,"Chicken 1Pc",580.00));
       foodDataList1.add(new FoodData(R.drawable.kfc6,"Vegie burger",780.00));
       foodDataList1.add(new FoodData(R.drawable.kfc7,"Pepsi 500ML",200.00));
       foodDataList1.add(new FoodData(R.drawable.kfc8,"7 Up 500ML",250.00));


       foodDataList2.add(new FoodData(R.drawable.pizza1,"Hot and Spicy Chicken pizza",890.00));
       foodDataList2.add(new FoodData(R.drawable.pizza2,"Chicken Wings in BBQ Sauce - 6PCs",900.00));
       foodDataList2.add(new FoodData(R.drawable.pizza3,"Cheesy Garlic Bread Supreme",925.00));
       foodDataList2.add(new FoodData(R.drawable.pizza4,"Tandoori Chicken Pizza",800.00));
       foodDataList2.add(new FoodData(R.drawable.pizza5,"Cheese Lovers Pizza",820.00));
       foodDataList2.add(new FoodData(R.drawable.pizza6,"Devilled Chicken Pizza",880.00));
       foodDataList2.add(new FoodData(R.drawable.pizza7,"Chicken Tri Party",850.00));
       foodDataList2.add(new FoodData(R.drawable.pizza8,"Spicy Seafood Pizza",1210.00));
       foodDataList2.add(new FoodData(R.drawable.pizza9,"Cinnamon Rolls",400.00));


       foodDataList3.add(new FoodData(R.drawable.burger1,"French Fries",869.00));
       foodDataList3.add(new FoodData(R.drawable.burger2,"Potato Wedges",814.00));
       foodDataList3.add(new FoodData(R.drawable.burger3,"Spicy Chicken Submarine",1072.00));
       foodDataList3.add(new FoodData(R.drawable.burger4,"Crispy Chicken Burger",1098.00));
       foodDataList3.add(new FoodData(R.drawable.burger5,"Beef Submarine",1132.00));
       foodDataList3.add(new FoodData(R.drawable.burger6,"Chicken Kottu",932.00));
       foodDataList3.add(new FoodData(R.drawable.burger7,"Crispy Chicken Shawarma",1099.00));
       foodDataList3.add(new FoodData(R.drawable.burger8,"Tandoori Club Sandwich",920.00));


       foodDataList4.add(new FoodData(R.drawable.cake1,"Yummy Chocolate Cake(1Kg)",1300.00));
       foodDataList4.add(new FoodData(R.drawable.cake2,"Rose Swirl Cupcake(6PCs)",1000.00));
       foodDataList4.add(new FoodData(R.drawable.cake3,"Chocolate Swirl Cupcake with Springles(6pcs)",1200.00));
       foodDataList4.add(new FoodData(R.drawable.cake4,"Chocolate Swirl Cupcake with Springles(1pcs)",200.00));
       foodDataList4.add(new FoodData(R.drawable.cake5,"Vanilla Cupcake with Sprinkles(6pcs)",1080.00));
       foodDataList4.add(new FoodData(R.drawable.cake6,"Vanilla Cupcake with Sprinkles(1pcs)",200.00));


       foodDataList5.add(new FoodData(R.drawable.ice1,"Chocolate Oreo Ice Cream Sundae(Large)",750.00));
       foodDataList5.add(new FoodData(R.drawable.ice2,"Fruit Salad with Ice Cream(Large)",650.00));
       foodDataList5.add(new FoodData(R.drawable.ice3,"Chocolate Sundae",620.00));
       foodDataList5.add(new FoodData(R.drawable.ice4,"Waffle Bowl Chocolate Ice Cream",580.00));
       foodDataList5.add(new FoodData(R.drawable.ice5,"Oreo Smoothie",650.00));
       foodDataList5.add(new FoodData(R.drawable.ice6,"Strawberry and Vanilla Ice Cream Sundae",750.00));


       foodDataList6.add(new FoodData(R.drawable.elite1,"Nasi Goreng",979.00));
       foodDataList6.add(new FoodData(R.drawable.elite2,"Wattalappam",363.00));
       foodDataList6.add(new FoodData(R.drawable.elite3,"Chicken Fried rice",924.00));
       foodDataList6.add(new FoodData(R.drawable.elite4,"Mushroom in Hot Butter",759.00));
       foodDataList6.add(new FoodData(R.drawable.elite5,"Egg Fried rice",638.00));
       foodDataList6.add(new FoodData(R.drawable.elite6,"Milkshake",418.00));
       foodDataList6.add(new FoodData(R.drawable.elite7,"Faluda",979.00));
       foodDataList6.add(new FoodData(R.drawable.elite8,"Fresh Lime Juice",352.00));
       foodDataList6.add(new FoodData(R.drawable.elite9,"Chicken Lollipop",869.00));
       foodDataList6.add(new FoodData(R.drawable.elite10,"prawn 65",1309.00));*/

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        imageOfSelectRest = findViewById(R.id.imageOfSelectRest);
        nameOfSelectRest = findViewById(R.id.nameOfSelectRest);

        /* Get the Intent */
        Intent intent = getIntent();
        RestData restData = (RestData) intent.getSerializableExtra(CHOICE);

        /* Set the Selected Restaurant */
        imageOfSelectRest.setImageResource(restData.getRestImageId());
        nameOfSelectRest.setText(restData.getRestName());

        ArrayList<FoodData> selectedFoodDataList = getTheSelectedList(restData.getRestName());

        /* Attach Frag_ListOfFoods Fragment into this Second Activity */

        FragmentManager fragmentManager = getSupportFragmentManager();
        Frag_ListOfFoods frag_listOfFoods = (Frag_ListOfFoods) fragmentManager.findFragmentById(R.id.ListOfFoodContainer);

        // It might already be there!
        if(frag_listOfFoods == null)
        {
            frag_listOfFoods = new Frag_ListOfFoods(selectedFoodDataList,restData);
            fragmentManager.beginTransaction().add(R.id.ListOfFoodContainer,frag_listOfFoods).commit();
        }
    }

    public static Intent getIntent(Context context, RestData choice)
    {
        Intent intent = new Intent(context,SecondActivity.class);
        intent.putExtra(CHOICE,choice);

        return intent;
    }


    private ArrayList<FoodData> getTheSelectedList(String str)
    {
        ArrayList<FoodData> dataList = foodDataList6;

        if(str.equals("KFC - Dehiwala"))
        {
            dataList = foodDataList1;
        }
        else
        {
            if(str.equals("Pizza hut - Dehiwala"))
            {
                dataList = foodDataList2;
            }
            else
            {
                if(str.equals("McDonald's - Mount Lavinia") || str.equals("Burger King - Kollupitiya")
                    || str.equals("Street Burger - Dehiwala"))
                {
                    dataList = foodDataList3;
                }
                else
                {
                    if(str.equals("Cake Hut - Dehiwala"))
                    {
                        dataList = foodDataList4;
                    }
                    else
                    {
                        if(str.equals("Rio Ice Cream - Wellawatte"))
                        {
                            dataList = foodDataList5;
                        }
                    }
                }

            }
        }

        return dataList;
    }
}