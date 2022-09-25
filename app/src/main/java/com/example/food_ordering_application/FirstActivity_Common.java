package com.example.food_ordering_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class FirstActivity_Common extends AppCompatActivity
{

    private static final String CHOICE1 = "Serialized_UserChoice_RestData_Object";
    private static final String CHOICE2 = "Serialized_UserChoice_FoodData_Object";
    private static final String FRAGMENT_NAME = "Name_Of_The_Fragment";
    private FoodData foodData;
    private RestData restData;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_common);


        Intent intent = getIntent();
        String fragmentName = intent.getStringExtra(FRAGMENT_NAME);
        restData = (RestData) intent.getSerializableExtra(CHOICE1);
        foodData = (FoodData) intent.getSerializableExtra(CHOICE2);

        selectFragment(fragmentName);

    }



    public static Intent getIntent(Context context, RestData choice1,FoodData choice2, String fragName)
    {
        Intent intent = new Intent(context, FirstActivity_Common.class);
        intent.putExtra(CHOICE1,choice1);
        intent.putExtra(CHOICE2,choice2);
        intent.putExtra(FRAGMENT_NAME,fragName);
        return intent;
    }


    public static Intent getIntent(Context context, String fragName)
    {
        Intent intent = new Intent(context, FirstActivity_Common.class);
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
                SelectFoodFragment selectFoodFragment = new SelectFoodFragment(restData,foodData);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.commonContainer,selectFoodFragment);
                fragmentTransaction.commit();

            }
            else
            {
                if(fragmentName.equals("BucketFragment"))
                {
                    BucketFragment bucketFragment = new BucketFragment();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.commonContainer,bucketFragment);
                    fragmentTransaction.commit();

                }
                else
                {
                    if(fragmentName.equals("LoginFragment"))
                    {
                        LoginFragment loginFragment = new LoginFragment();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.commonContainer,loginFragment);
                        fragmentTransaction.commit();

                    }
                    else
                    {
                        if(fragmentName.equals("RegisterFragment"))
                        {
                            RegisterFragment registerFragment = new RegisterFragment();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.commonContainer,registerFragment);
                            fragmentTransaction.commit();

                        }
                    }
                }
            }
        }

    }

}