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
 * Use the {@link FragmentCostBreakdown#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCostBreakdown extends Fragment
{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<OrderHistory> orderHistoryList;
    private int position;

    private OrderHistory orderHistory;

    public FragmentCostBreakdown()
    {
        // Required empty public constructor
    }

    public FragmentCostBreakdown(ArrayList<OrderHistory> orderHistoryList, int position)
    {
        this.orderHistoryList = orderHistoryList;
        this.position = position;

        orderHistory = orderHistoryList.get(position);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentCostBreakdown.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentCostBreakdown newInstance(String param1, String param2) {
        FragmentCostBreakdown fragment = new FragmentCostBreakdown();
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

        View view = inflater.inflate(R.layout.fragment_cost_breakdown, container, false);

        ImageView breakRestImage = view.findViewById(R.id.breakRestImage);
        TextView breakRestName = view.findViewById(R.id.breakRestName);
        TextView breakDateTime = view.findViewById(R.id.breakDateTime);
        TextView breakItemsTotalTxt = view.findViewById(R.id.breakItemsTotalTxt);
        TextView breakDeliveryFeeTxt = view.findViewById(R.id.breakDeliveryFeeTxt);
        TextView breakTotalCostTxt = view.findViewById(R.id.breakTotalCostTxt);

        RecyclerView breakRecyclerView = view.findViewById(R.id.breakRecyclerView);


        BucketData bucketData = orderHistory.getBucketDataList().get(0);

        breakRestImage.setImageResource(bucketData.getRestData().getRestImageId());
        breakRestName.setText(bucketData.getRestData().getRestName());
        breakDateTime.setText(orderHistory.getDateTime());
        breakItemsTotalTxt.setText(String.valueOf("LKR " + orderHistory.getTotalCost()));
        breakDeliveryFeeTxt.setText(String.valueOf("LKR " + orderHistory.getDeliveryFee()));
        breakTotalCostTxt.setText(String.valueOf("LKR " + orderHistory.getFinalAmount()));


        breakRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //Create Adapter for the recyclerview
        FragmentCostBreakdown.MyAdapter adapter = new FragmentCostBreakdown.MyAdapter();

        // Hook it up
        breakRecyclerView.setAdapter(adapter);



        return view;
    }



    /* Private inner Class for View holder */

    private class MyDataVHolder extends RecyclerView.ViewHolder
    {
        ImageView breakFoodImage;
        TextView breakFoodName;
        TextView breakTotalFoodItem;
        TextView breakFoodPrice;
        TextView breakFoodTotalPrice;


        public MyDataVHolder(@NonNull View itemView)
        {
            super(itemView);

            breakFoodImage = itemView.findViewById(R.id.breakFoodImage);
            breakFoodName = itemView.findViewById(R.id.breakFoodName);
            breakTotalFoodItem = itemView.findViewById(R.id.break_TotalFoodItem);
            breakFoodPrice = itemView.findViewById(R.id.breakFoodPrice);
            breakFoodTotalPrice = itemView.findViewById(R.id.breakFoodTotalPrice);
        }
    }


    /* Private inner Class for Adapter */

    private class MyAdapter extends RecyclerView.Adapter<FragmentCostBreakdown.MyDataVHolder>
    {
        ArrayList<BucketData> bucketDatList = orderHistory.getBucketDataList();

        @NonNull
        @Override
        public MyDataVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.view_holder_breakdown,parent,false);

            FragmentCostBreakdown.MyDataVHolder myDataVHolder = new FragmentCostBreakdown.MyDataVHolder(view);

            return  myDataVHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyDataVHolder holder, int position)
        {
            ImageView breakFoodImage = holder.breakFoodImage;
            TextView breakFoodName = holder.breakFoodName;
            TextView breakTotalFoodItem = holder.breakTotalFoodItem;
            TextView breakFoodPrice = holder.breakFoodPrice;
            TextView breakFoodTotalPrice = holder.breakFoodTotalPrice;

            BucketData bucketdata = bucketDatList.get(position);

            breakFoodImage.setImageResource(bucketdata.getFoodData().getFoodImageId());
            breakFoodName.setText(bucketdata.getFoodData().getFoodName());
            breakTotalFoodItem.setText(bucketdata.getItemCount() + " items");
            breakFoodPrice.setText("LKR " + String.valueOf(bucketdata.getFoodData().getPrice()));
            breakFoodTotalPrice.setText("LKR " + String.valueOf(bucketdata.getItemCost()));


        }

        @Override
        public int getItemCount()
        {
            return bucketDatList.size();
        }
    }


}