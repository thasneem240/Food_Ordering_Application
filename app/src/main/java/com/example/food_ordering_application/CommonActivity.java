package com.example.food_ordering_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class CommonActivity extends AppCompatActivity
{

    private static final String CHOICE = "Serialized_UserChoice_FoodData_Object";
    private static final String FRAGMENT_NAME = "Name_Of_The_Fragment";
    private FoodData foodData;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);


        Intent intent = getIntent();
        String fragmentName = intent.getStringExtra(FRAGMENT_NAME);
        foodData = (FoodData) intent.getSerializableExtra(CHOICE);

        selectFragment(fragmentName);

    }



    public static Intent getIntent(Context context, FoodData choice, String fragName)
    {
        Intent intent = new Intent(context,CommonActivity.class);
        intent.putExtra(CHOICE,choice);
        intent.putExtra(FRAGMENT_NAME,fragName);
        return intent;
    }


    public void selectFragment(String fragmentName)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();

        if(fragmentName == null)
        {
            /* Attach Frag_ListOfRestaurants Fragment into this Main Activity */
            FragGettingStart fragStart = (FragGettingStart) fragmentManager.findFragmentById(R.id.commonContainer);

            // It might already be there!
            if(fragStart== null)
            {
                fragStart = new FragGettingStart();
                fragmentManager.beginTransaction().add(R.id.commonContainer,fragStart).commit();
            }

        }
        else
        {
            if(fragmentName.equals("SelectFoodFragment"))
            {
                SelectFoodFragment selectFoodFragment = new SelectFoodFragment(foodData);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.commonContainer,selectFoodFragment);
                fragmentTransaction.commit();

            }
        }

    }

}