package com.example.food_ordering_application;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Frag_ListOfFoods#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Frag_ListOfFoods extends Fragment
{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RestData restData;
    private ArrayList<FoodData> dataList;

    public Frag_ListOfFoods(RestData restData,ArrayList<FoodData> dataList)
    {
        this.restData = restData;
        this.dataList = dataList;

    }

    public Frag_ListOfFoods()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Frag_ListOfFoods.
     */
    // TODO: Rename and change types and number of parameters
    public static Frag_ListOfFoods newInstance(String param1, String param2)
    {
        Frag_ListOfFoods fragment = new Frag_ListOfFoods();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag__list_of_foods, container, false);

        // Obtain the RecyclerView UI element
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.FoodRecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //Create Adapter for the recyclerview
        Frag_ListOfFoods.MyAdapter adapter = new Frag_ListOfFoods.MyAdapter();

        // Hook it up
        recyclerView.setAdapter(adapter);

        return  view;
    }


    /* Private inner Class for View holder */

    private class MyDataVHolder extends RecyclerView.ViewHolder
    {

        ImageView imageOfFood;
        TextView foodName,foodPrice;
        Button buttonAdd;

        public MyDataVHolder(@NonNull View itemView)
        {
            super(itemView);
            imageOfFood = itemView.findViewById(R.id.imageOfFood);
            foodName = itemView.findViewById(R.id.foodName);
            foodPrice = itemView.findViewById(R.id.foodPrice);
            buttonAdd = itemView.findViewById(R.id.buttonAdd);
        }
    }

    /* Private inner Class for Adapter */

    private class MyAdapter extends RecyclerView.Adapter<Frag_ListOfFoods.MyDataVHolder>
    {

        @NonNull
        @Override
        public MyDataVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_food,parent,false);

            Frag_ListOfFoods.MyDataVHolder myDataVHolder = new Frag_ListOfFoods.MyDataVHolder(view);

            return  myDataVHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyDataVHolder holder, int position)
        {
            ImageView imageOfFood = holder.imageOfFood;
            TextView foodName = holder.foodName;
            TextView foodPrice = holder.foodPrice;
            Button buttonAdd = holder.buttonAdd;

            // Single Data
            FoodData foodData = dataList.get(position);

            imageOfFood.setImageResource(foodData.getFoodImageId());
            foodName.setText(foodData.getFoodName());
            foodPrice.setText("LKR " + String.valueOf(foodData.getPrice()));

            buttonAdd.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Intent intent = FirstActivity_Common.getIntent(getActivity(),restData,foodData,"SelectFoodFragment");
                    startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount()
        {
            int count = dataList.size();
            return count;
        }
    }
}