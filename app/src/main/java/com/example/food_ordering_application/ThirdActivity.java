package com.example.food_ordering_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity
{

    private ImageView imageOfSelectRest;
    private TextView nameOfSelectRest;
    private FoodDataListModel foodDataListModel;

    private static final String CHOICE = "Serialized_UserChoice_RestData_Object";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d("Info", "I'm in ThirdActivity onCreate Method ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        imageOfSelectRest = findViewById(R.id.imageOfSelectRest);
        nameOfSelectRest = findViewById(R.id.nameOfSelectRest);

        foodDataListModel = new FoodDataListModel();
        foodDataListModel.loadFoodData(getApplicationContext());


        /* Get the Intent */
        Intent intent = getIntent();
        RestData restData = (RestData) intent.getSerializableExtra(CHOICE);

        /* Set the Selected Restaurant */
        imageOfSelectRest.setImageResource(restData.getRestImageId());
        nameOfSelectRest.setText(restData.getRestName());

        /* Get the Food List of the Relevant Restaurant */

        ArrayList<FoodData> foodDataList = foodDataListModel.getTheSpecificRestFoodList(restData.getRestName());

        /* Attach Frag_ListOfFoods Fragment into this Second Activity */

        FragmentManager fragmentManager = getSupportFragmentManager();
        Frag_ListOfFoods frag_listOfFoods = (Frag_ListOfFoods) fragmentManager.findFragmentById(R.id.ListOfFoodContainer);

        // It might already be there!
        if(frag_listOfFoods == null)
        {
            frag_listOfFoods = new Frag_ListOfFoods(restData,foodDataList);
            fragmentManager.beginTransaction().add(R.id.ListOfFoodContainer,frag_listOfFoods).commit();
        }
    }

    public static Intent getIntent(Context context, RestData choice)
    {
        Intent intent = new Intent(context, ThirdActivity.class);
        intent.putExtra(CHOICE,choice);

        return intent;
    }

}