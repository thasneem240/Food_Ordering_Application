package com.example.food_ordering_application;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment
{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RegUser regUser = null;
    private ArrayList<OrderHistory> orderHistoryListOfUser;
    private OrderHistoryListModel orderHistoryListModel;

    private ArrayList<BucketData> bucketDataListOfUser;
    private BucketDataListModel bucketDataListModel;

    public AccountFragment()
    {
        // Required empty public constructor
    }

    public AccountFragment(RegUser regUser)
    {
       this.regUser = regUser;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance(String param1, String param2)
    {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        orderHistoryListModel = new OrderHistoryListModel();
        orderHistoryListModel.loadOrderHistory(getActivity().getApplicationContext());
        orderHistoryListOfUser = orderHistoryListModel.getOrderHistoryOfSpecificUser(regUser.getEmailAddress());

        bucketDataListModel = new BucketDataListModel();
        bucketDataListModel.loadBucketData(getActivity().getApplicationContext());
        bucketDataListOfUser = bucketDataListModel.getBucketListOfSpecificUser(regUser.getEmailAddress());

        setSpecificBucketListToOrderHistoryList();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        TextView userName = view.findViewById(R.id.userName);
        TextView orderHistory = view.findViewById(R.id.orderHistory);
        Button buttonLogout = view.findViewById(R.id.buttonLogout);

        if(regUser != null)
        {
            userName.setText("Hi " + regUser.getUserName());

        }


        orderHistory.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });


        buttonLogout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showSuccessMessage();
                Intent intent = SecondActivity.getIntent(getActivity(),"NO",null);
                startActivity(intent);
            }
        });


        return view;
    }


    private void showSuccessMessage()
    {
        String message = " You have Successfully Logged out! ";

        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }


    /* Add the relevant bucket tyo Relevant user History */

    private void setSpecificBucketListToOrderHistoryList()
    {
        for (BucketData bucketData: bucketDataListOfUser)
        {
            for (OrderHistory orderHistory:orderHistoryListOfUser)
            {
                if(bucketData.getDateTime().equals(orderHistory.getDateTime()))
                {
                    orderHistory.addSpecificBucket(bucketData);
                    break;
                }
            }
        }
    }
}