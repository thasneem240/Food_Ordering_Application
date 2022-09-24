package com.example.food_ordering_application;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BucketFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BucketFragment extends Fragment
{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView itemsTotalTxt;
    private TextView totalCostTxt;
    private BucketDataList bucketDataList;
    private double deliveryFee = 250.0;


    public BucketFragment()
    {
        // Required empty public constructor
        bucketDataList = BucketDataList.getInstanceOfBucketList();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BucketFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BucketFragment newInstance(String param1, String param2) {
        BucketFragment fragment = new BucketFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bucket, container, false);

        ScrollView bucketScrollView = view.findViewById(R.id.bucketScrollView);
        TextView textEmpty = view.findViewById(R.id.textEmpty);
        RecyclerView bucketRecyclerView = (RecyclerView)view.findViewById(R.id.bucketRecyclerView);
        itemsTotalTxt = view.findViewById(R.id.itemsTotalTxt);
        totalCostTxt = view.findViewById(R.id.totalCostTxt);
        TextView deliveryFeeTxt = view.findViewById(R.id.deliveryFeeTxt);
        Button checkoutButton = view.findViewById(R.id.checkoutButton);


        if(!bucketDataList.isEmpty())
        {
            bucketScrollView.setVisibility(View.VISIBLE);
            textEmpty.setVisibility(View.GONE);

            deliveryFeeTxt.setText(String.valueOf(deliveryFee));
            itemsTotalTxt.setText(String.valueOf(bucketDataList.getTotal()));
            totalCostTxt.setText(String.valueOf(getFinalAmount()));

            bucketRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            //Create Adapter for the recyclerview
            BucketFragment.MyAdapter adapter = new BucketFragment.MyAdapter();

            // Hook it up
            bucketRecyclerView.setAdapter(adapter);
        }
        else
        {
            bucketScrollView.setVisibility(View.GONE);
            textEmpty.setVisibility(View.VISIBLE);
        }


        checkoutButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(SecondActivity.getLoginStatus().equals("NO"))
                {
                    Toast.makeText(getActivity(), "Please Login in order to Checkout", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getActivity(), " Checkout Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }


    /* Private inner Class for View holder */

    private class MyDataVHolder extends RecyclerView.ViewHolder
    {
        TextView nameOfFood;
        TextView eachItemCost;
        TextView itemTotal;
        TextView totItemCost;
        ImageView foodImage;
        ImageView plus;
        ImageView minus;
        Button buttonRemove;

        public MyDataVHolder(@NonNull View itemView)
        {
            super(itemView);
            nameOfFood = itemView.findViewById(R.id.nameOfFood);
            eachItemCost = itemView.findViewById(R.id.eachItemCost);
            itemTotal = itemView.findViewById(R.id.itemTotal);
            totItemCost = itemView.findViewById(R.id.totItemCost);
            foodImage = itemView.findViewById(R.id.foodImage);
            plus = itemView.findViewById(R.id.plus);
            minus = itemView.findViewById(R.id.minus);
            buttonRemove = itemView.findViewById(R.id.buttonRemove);
        }
    }

    /* Private inner Class for Adapter */

    private class MyAdapter extends RecyclerView.Adapter<BucketFragment.MyDataVHolder>
    {

        @NonNull
        @Override
        public MyDataVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.view_holder_bucket,parent,false);

            BucketFragment.MyDataVHolder myDataVHolder = new BucketFragment.MyDataVHolder(view);

            return  myDataVHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyDataVHolder holder, @SuppressLint("RecyclerView") int position)
        {
            TextView nameOfFood = holder.nameOfFood;
            TextView eachItemCost = holder.eachItemCost;
            TextView itemTotal = holder.itemTotal;
            TextView totItemCost = holder.totItemCost;
            ImageView foodImage = holder.foodImage;
            ImageView plus = holder.plus;
            ImageView minus = holder.minus;
            Button buttonRemove = holder.buttonRemove;


            BucketData bucketData = bucketDataList.get(position);

            FoodData foodData = bucketData.getFoodData();

            foodImage.setImageResource(foodData.getFoodImageId());
            nameOfFood.setText(foodData.getFoodName());

            // Price of each item
            double eachItemPrice = foodData.getPrice();
            eachItemCost.setText(String.valueOf(eachItemPrice));

            itemTotal.setText(String.valueOf(bucketData.getItemCount()));

            // Total cost of particular food
            double totalCostOfParticularFood = (bucketData.getItemCount() * eachItemPrice);
            totItemCost.setText(String.valueOf(totalCostOfParticularFood));




            plus.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    int count = Integer.parseInt(String.valueOf(itemTotal.getText()));
                    count++;
                    bucketData.setItemCount(count);

                    itemTotal.setText(String.valueOf(count));
                    totItemCost.setText(String.valueOf(count*eachItemPrice));
                    itemsTotalTxt.setText(String.valueOf(bucketDataList.getTotal()));
                    totalCostTxt.setText(String.valueOf(getFinalAmount()));

                }
            });

            minus.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    int count = Integer.parseInt(itemTotal.getText().toString());

                    if(count != 1)
                    {
                        count--;
                        bucketData.setItemCount(count);

                        itemTotal.setText(String.valueOf(count));
                        totItemCost.setText(String.valueOf(count*eachItemPrice));
                        itemsTotalTxt.setText(String.valueOf(bucketDataList.getTotal()));
                        totalCostTxt.setText(String.valueOf(getFinalAmount()));
                    }
                }
            });


            buttonRemove.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    bucketDataList.removeBucketData(position);
                }
            });
        }

        @Override
        public int getItemCount()
        {
            return bucketDataList.size();
        }
    }


    public double getFinalAmount()
    {
        return bucketDataList.getTotal() + deliveryFee;
    }

}