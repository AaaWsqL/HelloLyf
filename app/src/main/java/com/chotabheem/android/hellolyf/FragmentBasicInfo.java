package com.chotabheem.android.hellolyf;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chotabheem.android.hellolyf.DataModels.SingletonSignUpData;

/**
 * Created by chota_bheem on 27/7/16.
 */
public class FragmentBasicInfo extends Fragment {
    EditText firstName;
    EditText lastName;
    EditText dob;
    EditText age;
    Button genderMale;
    Button genderFemale;
    Button genderOther;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_basic_info, container, false);

        final TextView textViewfirstName = (TextView) rootView.findViewById(R.id.textview_firstname);
        textViewfirstName.setVisibility(View.INVISIBLE);

        final TextView textViewlastName = (TextView) rootView.findViewById(R.id.textview_lastname);
        textViewlastName.setVisibility(View.INVISIBLE);

        TextView textViewdob = (TextView) rootView.findViewById(R.id.textview_DOB);
        textViewdob.setVisibility(View.INVISIBLE);

        TextView textViewage = (TextView) rootView.findViewById(R.id.textview_age);
        textViewage.setVisibility(View.INVISIBLE);

        firstName = (EditText) rootView.findViewById(R.id.edittext_firstname);
        firstName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    textViewfirstName.setVisibility(View.INVISIBLE);
                    SingletonSignUpData.getInstance().setFirstName(firstName.getText().toString());
                }
                else{
                    textViewfirstName.setVisibility(View.VISIBLE);
                    SingletonSignUpData.getInstance().setFirstName(firstName.getText().toString());
                }


            }
        });



        lastName = (EditText) rootView.findViewById(R.id.edittext_lastname);
        lastName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    textViewlastName.setVisibility(View.INVISIBLE);
                    SingletonSignUpData.getInstance().setLastName(lastName.getText().toString());
                }
                else{
                    textViewlastName.setVisibility(View.VISIBLE);
                    SingletonSignUpData.getInstance().setLastName(lastName.getText().toString());
                }


            }
        });



        dob = (EditText) rootView.findViewById(R.id.edittext_DOB);
        dob.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    SingletonSignUpData.getInstance().setDob(dob.getText().toString());
                }
                else
                    SingletonSignUpData.getInstance().setDob(dob.getText().toString());
            }
        });

        age = (EditText) rootView.findViewById(R.id.edittext_age);
        age.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    SingletonSignUpData.getInstance().setAge(age.getText().toString());
                }
                else
                    SingletonSignUpData.getInstance().setAge(age.getText().toString());
            }
        });



        genderMale = (Button) rootView.findViewById(R.id.mle_btn);
        genderMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SingletonSignUpData.getInstance().setGender("male");
            }
        });

        genderFemale = (Button) rootView.findViewById(R.id.fmle_btn);
        genderFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SingletonSignUpData.getInstance().setGender("female");
            }
        });

        genderOther = (Button) rootView.findViewById(R.id.othr_btn);
        genderOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SingletonSignUpData.getInstance().setFirstName(firstName.getText().toString());
//                Toast.makeText(getActivity().getApplicationContext(), "from edit text "+lastName.getText().toString() , Toast.LENGTH_LONG).show();
//                Toast.makeText(getActivity().getApplicationContext(), "from singleton "+ SingletonSignUpData.getInstance().getFirstName().toString() , Toast.LENGTH_LONG).show();
                SingletonSignUpData.getInstance().setGender("other");
            }
        });



        return rootView;
    }


}
