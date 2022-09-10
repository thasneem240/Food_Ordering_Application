package com.example.food_ordering_application;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Frag_ListOfRestaurants#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Frag_ListOfRestaurants extends Fragment
{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<RestData> dataList;

    public Frag_ListOfRestaurants(ArrayList<RestData> list)
    {
        this.dataList = list;
    }


    public Frag_ListOfRestaurants()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Frag_ListOfRestaurants.
     */
    // TODO: Rename and change types and number of parameters
    public static Frag_ListOfRestaurants newInstance(String param1, String param2)
    {
        Frag_ListOfRestaurants fragment = new Frag_ListOfRestaurants();
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
        View view = inflater.inflate(R.layout.fragment_frag__list_of_restaurants, container, false);

        // Obtain the RecyclerView UI element
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.RestaurantRecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //Create Adapter for the recyclerview
        MyAdapter adapter = new MyAdapter();

        // Hook it up
        recyclerView.setAdapter(adapter);

        return  view;
    }

    /* Private inner Class for View holder */

    private class MyDataVHolder extends RecyclerView.ViewHolder
    {

        ImageView imageOfRest;
        TextView nameOfRest;

        public MyDataVHolder(@NonNull View itemView)
        {
            super(itemView);
            imageOfRest = itemView.findViewById(R.id.imageOfRest);
            nameOfRest = itemView.findViewById(R.id.nameOfRest);
        }
    }

    /* Private inner Class for Adapter */

    private class MyAdapter extends RecyclerView.Adapter<MyDataVHolder>
    {

        @NonNull
        @Override
        public MyDataVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_restaurant,parent,false);

            MyDataVHolder myDataVHolder = new MyDataVHolder(view);

            return  myDataVHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyDataVHolder holder, int position)
        {
            ImageView imageOfRest = holder.imageOfRest;
            TextView nameOfRest = holder.nameOfRest;

            // Single Data
            RestData restData = dataList.get(position);

            // Set the TextView
            nameOfRest.setText(restData.getRestName());

            // Set the ImageView
            //imageOfRest.setBackgroundResource(1);
           // imageOfRest.setImageResource(restData.getRestImageId());
            imageOfRest.setImageResource(restData.getRestImageId());
        }

        @Override
        public int getItemCount()
        {
            int count = dataList.size();
            return count;
        }
    }

}