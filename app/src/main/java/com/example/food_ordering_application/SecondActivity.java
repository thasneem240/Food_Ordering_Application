package com.example.food_ordering_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SecondActivity extends AppCompatActivity
{

    private static final String STATUS = "status";
    private static final String USER = "regUser";

    private static String loginStatus = "NO";
    private static RegUser currentUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ImageView imageBucket = findViewById(R.id.imageBucket);
        ImageView imageAccount = findViewById(R.id.imageAccount);

        Intent intent = getIntent();
        loginStatus = intent.getStringExtra(STATUS);
        currentUser = (RegUser) intent.getSerializableExtra(USER);


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
                    Intent intent = FirstActivity_Common.getIntent(SecondActivity.this,
                            "AccountFragment",currentUser);
                    startActivity(intent);
                }
            }
        });

    }

    public static Intent getIntent(Context context, String status, RegUser regUser)
    {
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra(STATUS,status);
        intent.putExtra(USER,regUser);

        return intent;
    }

    public static String getLoginStatus()
    {
        return loginStatus;
    }

    public static void setLoginStatus(String status)
    {
        loginStatus = status;
    }

    public static void setCurrentUser(RegUser user)
    {
        currentUser = user;
    }

    public static RegUser getCurrentUser()
    {
        return currentUser;
    }
}