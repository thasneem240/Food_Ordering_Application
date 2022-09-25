package com.example.food_ordering_application;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RegUserListModel regUserListModel;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        EditText email = view.findViewById(R.id.email);
        EditText password = view.findViewById(R.id.password);

        Button loginButton = view.findViewById(R.id.loginButton);
        TextView txtregister = view.findViewById(R.id.txtRegister);

        txtregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                email.setText("");
                password.setText("");

                Intent intent = FirstActivity_Common.getIntent(getActivity(),"RegisterFragment");
                startActivity(intent);
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String logEmail = email.getText().toString();
                String logPassword = password.getText().toString();

                /* Validate the Input */

                if(checkFieldHasEmpty(logEmail,logPassword))
                {
                    RegUser regUser = regUserListModel.getRegUser(logEmail);

                    if(regUser == null)
                    {
                        showErrorMessage();
                        password.setText("");
                    }
                    else
                    {
                        if(regUser.getPassword().equals(logPassword))
                        {
                            // Fully Validated

                            showSuccessMessage();
                            email.setText("");
                            password.setText("");

                           /* SecondActivity.setLoginStatus("YES");
                            SecondActivity.setCurrentUser(regUser);*/

                            Intent intent = SecondActivity.getIntent(getActivity(),"YES",regUser);
                            startActivity(intent);


                        }
                        else
                        {
                            showErrorMessage();
                            password.setText("");
                        }

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


    private boolean checkFieldHasEmpty(String logEmail,String logPassword)
    {
        boolean isEmpty = true;

        if(logEmail.isEmpty() || logPassword.isEmpty() )
        {
            isEmpty = false;
        }

        return isEmpty;
    }

    private void showSuccessMessage()
    {
        String message = " You are Successfully Logged in";

        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    private void showErrorMessage()
    {
        String message = "Login Failed!! Invalid User Name OR Password ";

        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

}