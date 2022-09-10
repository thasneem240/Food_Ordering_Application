package com.example.food_ordering_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{

    private ArrayList<RestData> restData;

    public MainActivity()
    {
        restData = new ArrayList<RestData>();

        restData.add(new RestData(R.drawable.kfc,"KFC - Dehiwala"));
        restData.add(new RestData(R.drawable.pizzahut,"Pizza hut - Dehiwala"));
        restData.add(new RestData(R.drawable.mcdonald,"McDonald's - Mount Lavinia"));
        restData.add(new RestData(R.drawable.rioicecream,"Rio Ice Cream - Wellawatte"));
        restData.add(new RestData(R.drawable.elite,"Elite Indian Restaurant - Dehiwala"));
        restData.add(new RestData(R.drawable.cakehut,"Cake Hut - Dehiwala"));
        restData.add(new RestData(R.drawable.dinemore,"Dinemore - Wellawatte"));
        restData.add(new RestData(R.drawable.royalbakery,"Royal Bakery - Wellawatte"));
        restData.add(new RestData(R.drawable.burgerking,"Burger King - Kollupitiya"));
        restData.add(new RestData(R.drawable.streetburger,"Street Burger - Dehiwala"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Attach Frag_ListOfRestaurants Fragment into this Main Activity */
        FragmentManager fragmentManager = getSupportFragmentManager();
        Frag_ListOfRestaurants fragListRest = (Frag_ListOfRestaurants) fragmentManager.findFragmentById(R.id.ListOfFoodContainer);

        // It might already be there!
        if(fragListRest == null)
        {
            fragListRest = new Frag_ListOfRestaurants(restData);
            fragmentManager.beginTransaction().add(R.id.ListOfFoodContainer,fragListRest).commit();
        }

    }
}