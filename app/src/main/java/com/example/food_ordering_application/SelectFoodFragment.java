package com.example.food_ordering_application;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
    private FoodData selectFood;

    public SelectFoodFragment(FoodData selectFood)
    {
        this.selectFood = selectFood;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_food, container, false);

        TextView nameOfSelectedFood = view.findViewById(R.id.nameOfSelectedFood);
        TextView textPrice = view.findViewById(R.id.textPrice);
        TextView totalItem = view.findViewById(R.id.totItem);
        ImageView imageOfSelectFood = view.findViewById(R.id.imageOfSelectFood);

        Button buttonAddToBucket = view.findViewById(R.id.buttonAddToBucket);
        ImageView imagePlus = view.findViewById(R.id.imagePlus);
        ImageView imageMinus = view.findViewById(R.id.imageMinus);


        nameOfSelectedFood.setText(selectFood.getFoodName());
        textPrice.setText(selectFood.getFoodName());
        totalItem.setText(String.valueOf(selectFood.getItemCount()));
        imageOfSelectFood.setImageResource(selectFood.getFoodImageId());


        buttonAddToBucket.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });


        imagePlus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int count = Integer.parseInt(totalItem.getText().toString());
                count++;
                selectFood.setItemCount(count);
            }
        });

        imageMinus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int count = Integer.parseInt(totalItem.getText().toString());

                if(count != 0)
                {
                    count--;
                    selectFood.setItemCount(count);
                }
            }
        });

        return view;
    }
}