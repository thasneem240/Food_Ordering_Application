package com.example.food_ordering_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity
{

    //private ArrayList<RestData> restData;
    private static String loginStatus = "NO";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageBucket = findViewById(R.id.imageBucket);
        ImageView imageAccount = findViewById(R.id.imageAccount);

        /* Attach Frag_ListOfRestaurants Fragment into this Main Activity */
        FragmentManager fragmentManager = getSupportFragmentManager();
        Frag_ListOfRestaurants fragListRest = (Frag_ListOfRestaurants) fragmentManager.findFragmentById(R.id.ListOfFoodContainer);

        // It might already be there!
        if(fragListRest == null)
        {
            fragListRest = new Frag_ListOfRestaurants();
            fragmentManager.beginTransaction().add(R.id.ListOfFoodContainer,fragListRest).commit();
        }

        imageBucket.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = FirstActivity_Common.getIntent(SecondActivity.this,"BucketFragment");
                startActivity(intent);
            }
        });

        imageAccount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(loginStatus.equals("NO"))
                {
                    Intent intent = FirstActivity_Common.getIntent(SecondActivity.this,"LoginFragment");
                    startActivity(intent);
                }
                else
                {

                }
            }
        });

    }

    public static String getLoginStatus()
    {
        return loginStatus;
    }
}