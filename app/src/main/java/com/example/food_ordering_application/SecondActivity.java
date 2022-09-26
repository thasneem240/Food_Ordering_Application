package com.example.food_ordering_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity
{

    private static final String STATUS = "status";
    private static final String USER = "regUser";

    private static String loginStatus = "NO";
    private static RegUser currentUser = null;

    private RestDataListModel restDataListModel;
    private FoodDataListModel foodDataListModel;
    private List<FoodData> specialRandomFoodList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        restDataListModel = new RestDataListModel();
        restDataListModel.loadRestData(getApplicationContext());

        foodDataListModel = new FoodDataListModel();
        foodDataListModel.loadFoodData(getApplicationContext());

        specialRandomFoodList = foodDataListModel.getRandomSpecialFoodList();

        Log.d("RANDOM_LIST", "onCreate: " + specialRandomFoodList.size());


        ImageView imageBucket = findViewById(R.id.imageBucket);
        ImageView imageAccount = findViewById(R.id.imageAccount);
        RecyclerView specialMenuRecyclerView = findViewById(R.id.specialMenuRecyclerView);

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
                    Intent intent = FirstActivity_Common.getIntent(
                            SecondActivity.this,"LoginFragment");
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



        specialMenuRecyclerView.setLayoutManager(new LinearLayoutManager
                (this,LinearLayoutManager.HORIZONTAL,false));

        //Create Adapter for the recyclerview
        SecondActivity.MyAdapter adapter = new SecondActivity.MyAdapter();

        // Hook it up
        specialMenuRecyclerView.setAdapter(adapter);



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


    /* Private inner Class for View holder */

    private class MyDataVHolder extends RecyclerView.ViewHolder
    {
        TextView specialFoodName;
        ImageView specialFoodImage;
        TextView specialFoodPrice;
        Button specialButtonAdd;

        public MyDataVHolder(@NonNull View itemView)
        {
            super(itemView);

            specialFoodName = itemView.findViewById(R.id.specialFoodName);
            specialFoodImage = itemView.findViewById(R.id.special_FoodImage);
            specialFoodPrice = itemView.findViewById(R.id.specialFoodPrice);
            specialButtonAdd = itemView.findViewById(R.id.specialButtonAdd);
        }
    }

    /* Private inner Class for Adapter */

    private class MyAdapter extends RecyclerView.Adapter<SecondActivity.MyDataVHolder>
    {

        @NonNull
        @Override
        public MyDataVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
//            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            //LayoutInflater layoutInflater = LayoutInflater.from(SecondActivity.this);

            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

            View view = layoutInflater.inflate(R.layout.view_holder_special_menu,parent,false);


            SecondActivity.MyDataVHolder myDataVHolder = new SecondActivity.MyDataVHolder(view);

            return  myDataVHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyDataVHolder holder, int position)
        {
            TextView specialFoodName = holder.specialFoodName;
            ImageView specialFoodImage = holder.specialFoodImage;
            TextView specialFoodPrice =  holder.specialFoodPrice;
            Button specialButtonAdd = holder.specialButtonAdd;

            FoodData singleFoodData = specialRandomFoodList.get(position);

            specialFoodName.setText(singleFoodData.getFoodName());
            specialFoodImage.setImageResource(singleFoodData.getFoodImageId());
            specialFoodPrice.setText("LKR " + String.valueOf(singleFoodData.getPrice()));



            specialButtonAdd.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    RestData restData = restDataListModel.getRestData(singleFoodData.getRestName());

                    Intent intent = FirstActivity_Common.getIntent(SecondActivity.this,restData,singleFoodData,"SelectFoodFragment");
                    startActivity(intent);
                }
            });


        }

        @Override
        public int getItemCount()
        {
            return specialRandomFoodList.size();
        }
    }


}