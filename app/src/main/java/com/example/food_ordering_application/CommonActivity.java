package com.example.food_ordering_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class CommonActivity extends AppCompatActivity
{

    private static final String CHOICE = "Serialized_UserChoice_FoodData_Object";;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);

        /* Attach Frag_ListOfRestaurants Fragment into this Main Activity */
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragGettingStart fragStart = (FragGettingStart) fragmentManager.findFragmentById(R.id.comonContainer);

        // It might already be there!
        if(fragStart== null)
        {
            fragStart = new FragGettingStart();
            fragmentManager.beginTransaction().add(R.id.comonContainer,fragStart).commit();
        }
    }



    public static Intent getIntent(Context context, FoodData choice)
    {
        Intent intent = new Intent(context,CommonActivity.class);
        intent.putExtra(CHOICE,choice);

        return intent;
    }

}