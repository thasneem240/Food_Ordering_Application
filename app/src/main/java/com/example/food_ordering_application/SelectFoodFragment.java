package com.example.food_ordering_application;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SelectFoodFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectFoodFragment extends Fragment
{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RestData selectRest;
    private FoodData selectFood;
    BucketDataList bucketDataList;

    public SelectFoodFragment(RestData selectRest,FoodData selectFood)
    {
        this.selectRest = selectRest;
        this.selectFood = selectFood;
        bucketDataList = BucketDataList.getInstanceOfBucketList();
    }

    public SelectFoodFragment()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelectFoodFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectFoodFragment newInstance(String param1, String param2) {
        SelectFoodFragment fragment = new SelectFoodFragment();
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

    private ConstraintLayout myConstraintLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_food, container, false);

        myConstraintLayout = view.findViewById(R.id.selectFoodConslayout);

        TextView nameOfSelectedFood = view.findViewById(R.id.nameOfSelectedFood);
        TextView textPrice = view.findViewById(R.id.textPrice);
        TextView totalItem = view.findViewById(R.id.totItem);
        ImageView imageOfSelectFood = view.findViewById(R.id.imageOfSelectFood);

        Button buttonAddToBucket = view.findViewById(R.id.buttonAddToBucket);
        ImageView imagePlus = view.findViewById(R.id.imagePlus);
        ImageView imageMinus = view.findViewById(R.id.imageMinus);


        nameOfSelectedFood.setText(selectFood.getFoodName());
        textPrice.setText("LKR " + String.valueOf(selectFood.getPrice()));
        imageOfSelectFood.setImageResource(selectFood.getFoodImageId());

        if(bucketDataList.isEmpty())
        {
            totalItem.setText(String.valueOf(1));
        }
        else
        {
            /* Check Whether Bucket Contains the Same food item */
            if(BucketDataList.contains(selectFood))
            {
                BucketData bucketData = bucketDataList.getBucket(selectFood);

                totalItem.setText(String.valueOf(bucketData.getItemCount()));

            }
            else
            {
                totalItem.setText(String.valueOf(1));
            }
        }

        //totalItem.setText(String.valueOf(selectFood.getItemCount()));


        buttonAddToBucket.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int totItem = Integer.parseInt(totalItem.getText().toString());

                /* Created a bucket Data */
                BucketData bucketData = new BucketData(selectRest,selectFood,totItem);


                if(bucketDataList.isEmpty())
                {
                    bucketDataList.add(bucketData);
                    showSuccessMessage();
                }
                else
                {
                    /* Check Whether Contains only one restaurant food items */

                    if(BucketDataList.checkBucket(selectRest))
                    {

                        String message = " Bucket Already have Different Restaurant Food items, Choose same restaurant food items into the Bucket" +
                                "OR Empty the Current Bucket And Add this food item in to the Bucket";

                        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();

                    }
                    else
                    {
                        /* Check Whether Bucket Contains the Same food item */
                        if(BucketDataList.contains(selectFood))
                        {
                            BucketDataList.updateBucketData(bucketData);

                            String message = " Successfully Updated the Bucket ";

                            Snackbar snackbar = Snackbar.make(myConstraintLayout,message,Snackbar.LENGTH_LONG);
                            snackbar.show();
                        }
                        else
                        {
                            bucketDataList.add(bucketData);
                            showSuccessMessage();
                        }
                    }
                }

            }
        });


        imagePlus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int count = Integer.parseInt(String.valueOf(totalItem.getText()));
                count++;
                selectFood.setItemCount(count);
                totalItem.setText(String.valueOf(count));
            }
        });

        imageMinus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int count = Integer.parseInt(totalItem.getText().toString());

                if(count != 1)
                {
                    count--;
                    selectFood.setItemCount(count);
                    totalItem.setText(String.valueOf(count));
                }
            }
        });

        return view;
    }

    private void showSuccessMessage()
    {
        String message = " Successfully Added to the Bucket ";

        Snackbar snackbar = Snackbar.make(myConstraintLayout,message,Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}