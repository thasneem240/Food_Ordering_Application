package com.example.food_ordering_application;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

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

    public BucketFragment()
    {
        // Required empty public constructor
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
        TextView itemsTotalTxt = view.findViewById(R.id.itemsTotalTxt);
        TextView deliveryFeeTxt = view.findViewById(R.id.deliveryFeeTxt);
        TextView totalCostTxt = view.findViewById(R.id.totalCostTxt);
        Button checkoutButton = view.findViewById(R.id.checkoutButton);


        bucketRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //Create Adapter for the recyclerview
        BucketFragment.MyAdapter adapter = new BucketFragment.MyAdapter();

        // Hook it up
        bucketRecyclerView.setAdapter(adapter);



        return view;
    }

    private class MyAdapter extends RecyclerView.Adapter<BucketFragment.MyDataVHolder>
    {

        @NonNull
        @Override
        public MyDataVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull MyDataVHolder holder, int position)
        {

        }

        @Override
        public int getItemCount()
        {
            return 0;
        }
    }


    /* Private inner Class for View holder */

    private class MyDataVHolder extends RecyclerView.ViewHolder
    {

        public MyDataVHolder(@NonNull View itemView)
        {
            super(itemView);
        }
    }
}