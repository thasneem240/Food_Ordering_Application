package com.example.food_ordering_application;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment
{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RegUserListModel regUserListModel;

    public RegisterFragment()
    {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
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

        regUserListModel = new RegUserListModel();
        regUserListModel.loadRegUserData(getActivity().getApplicationContext());
    }
    
    private EditText userName;
    private EditText regEmail;
    private EditText regPassword;
    private EditText regRePassword;
    private Button registerButton;
    

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) 
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        userName = view.findViewById(R.id.userName);
        regEmail = view.findViewById(R.id.regEmail);
        regPassword = view.findViewById(R.id.regPassword);
        regRePassword = view.findViewById(R.id.regRePassword);
        registerButton = view.findViewById(R.id.registerButton);


        registerButton.setOnClickListener(new View.OnClickListener() 
        {
            @Override
            public void onClick(View view) 
            {
                String uName = userName.getText().toString();
                String rMail = regEmail.getText().toString();
                String rPassword = regPassword.getText().toString();
                String rePassword = regRePassword.getText().toString();

                /* Create Object of RegUser */

                RegUser regUser = new RegUser(rMail,uName,rPassword);

                /* Validate the Input */

                if(checkFieldHasEmpty(uName,rMail,rPassword,rePassword))
                {
                    /* Validate the Email Address */
                    if(Patterns.EMAIL_ADDRESS.matcher(rMail).matches())
                    {
                        if(rPassword.equals(rePassword))
                        {
                            // Fully verified Input

                            /* Empty database */
                            if(regUserListModel.isEmpty())
                            {
                                regUserListModel.addRegUserData(regUser);
                                showSuccessMessage();
                            }
                            else
                            {
                                /* Check Whether Database has the same Email Address */
                                if(regUserListModel.containsSameEmail(rMail))
                                {
                                    showErrorMessage();
                                    regEmail.setText("");
                                }
                                else
                                {
                                    regUserListModel.addRegUserData(regUser);
                                    showSuccessMessage();
                                }
                            }

                        }
                        else
                        {
                            Toast.makeText(getActivity(), "Password and RePassword do not match!! " +
                                    "Please re-enter password", Toast.LENGTH_LONG).show();
                            regPassword.setText("");
                            regRePassword.setText("");
                        }

                    }
                    else
                    {
                        Toast.makeText(getActivity(), "Invalid Email Address!!", Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    Toast.makeText(getActivity(), "Contains Empty Field!!, Please Enter All " +
                            "the Fields", Toast.LENGTH_SHORT).show();
                }
                
            }
        });


        return view;
    }
    

    /* Check Whether Field Contains Empty Fields or Not */

    private boolean checkFieldHasEmpty(String uName, String rMail, String rPassword, String rePassword)
    {
        boolean isEmpty = true;
        
        if(uName.isEmpty() || rMail.isEmpty() || rPassword.isEmpty() || rePassword.isEmpty())
        {
            isEmpty = false;
        }
        
        return isEmpty;
    }


    private void showSuccessMessage()
    {
        String message = "  Registration completed Successfully ";

        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    private void showErrorMessage()
    {
        String message = "  Registration Failed!! A user with this email Address already exists ";

        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }
}