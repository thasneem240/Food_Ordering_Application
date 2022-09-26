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

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentOrderHistory#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentOrderHistory extends Fragment
{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<OrderHistory> orderHistoryList;

    public FragmentOrderHistory()
    {
        // Required empty public constructor
    }

    public FragmentOrderHistory(ArrayList<OrderHistory> orderHistoryList)
    {
        this.orderHistoryList = orderHistoryList;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentOrderHistory.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentOrderHistory newInstance(String param1, String param2) {
        FragmentOrderHistory fragment = new FragmentOrderHistory();
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
        View view = inflater.inflate(R.layout.fragment_order_history, container, false);

        RecyclerView orderHistoryRecyclerView = view.findViewById(R.id.orderHistoryRecyclerView);
        TextView emptyMessage = view.findViewById(R.id.emptyMessage);

        if(orderHistoryList.isEmpty())
        {
            emptyMessage.setVisibility(View.VISIBLE);
            orderHistoryRecyclerView.setVisibility(View.GONE);

        }
        else
        {
            /* orderHistoryList is Not Empty */

            emptyMessage.setVisibility(View.GONE);
            orderHistoryRecyclerView.setVisibility(View.VISIBLE);

            orderHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            //Create Adapter for the recyclerview
           FragmentOrderHistory.MyAdapter adapter = new FragmentOrderHistory.MyAdapter();

            // Hook it up
           orderHistoryRecyclerView.setAdapter(adapter);

       }


        return view;
    }

    /* Private inner Class for View holder */

    private class MyDataVHolder extends RecyclerView.ViewHolder
    {
        ImageView orderRestImage;
        TextView orderRestName;
        TextView order_TotalBucket;
        TextView order_TotalPrice;
        TextView order_Date;

        public MyDataVHolder(@NonNull View itemView)
        {
            super(itemView);
            orderRestImage = itemView.findViewById(R.id.orderRestImage);
            orderRestName = itemView.findViewById(R.id.orderRestName);
            order_TotalBucket = itemView.findViewById(R.id.order_TotalBucket);
            order_TotalPrice = itemView.findViewById(R.id.orderTotalPrice);
            order_Date = itemView.findViewById(R.id.order_Date);
        }
    }


    /* Private inner Class for Adapter */

    private class MyAdapter extends RecyclerView.Adapter<FragmentOrderHistory.MyDataVHolder>
    {

        @NonNull
        @Override
        public MyDataVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            View view = layoutInflater.inflate(R.layout.view_holder_order_history,parent,false);

            FragmentOrderHistory.MyDataVHolder myDataVHolder = new FragmentOrderHistory.MyDataVHolder(view);

            return  myDataVHolder;

        }

        @Override
        public void onBindViewHolder(@NonNull MyDataVHolder holder, int position)
        {
            ImageView orderRestImage = holder.orderRestImage;
            TextView orderRestName = holder.orderRestName;
            TextView order_TotalBucket = holder.order_TotalBucket;
            TextView order_TotalPrice = holder.order_TotalPrice;
            TextView order_Date = holder.order_Date;

            OrderHistory singleOrderHistory = orderHistoryList.get(position);
            BucketData singleBucketData = singleOrderHistory.getBucketDataList().get(0);

            orderRestImage.setImageResource(singleBucketData.getRestData().getRestImageId());
            orderRestName.setText(singleBucketData.getRestData().getRestName());
            order_TotalBucket.setText(String.valueOf(singleOrderHistory.getTotalBucket() + " items"));
            order_TotalPrice.setText( "LKR " + String.valueOf(singleOrderHistory.getFinalAmount()) );
            order_Date.setText(singleOrderHistory.getDateTime());

        }

        @Override
        public int getItemCount()
        {
            return orderHistoryList.size();
        }
    }

}