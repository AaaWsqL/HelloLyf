package com.chotabheem.android.hellolyf;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by chota_bheem on 8/8/16.
 */
public class FragmentMain extends Fragment {

    private OnOptionSelectedListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_main, container, false);

        ImageView seeDoctorNow = (ImageView) rootView.findViewById(R.id.mainBtn);
        seeDoctorNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onOptionSelected(new FragmentPatientForm());

            }
        });

        ImageView selectSpecialist = (ImageView) rootView.findViewById(R.id.btn_select_specialist);
        selectSpecialist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onOptionSelected(new FragmentSelectSpecialist());
            }
        });


        return rootView;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnOptionSelectedListener) {
            listener = (OnOptionSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement MyListFragment.OnItemSelectedListener");
        }
    }

}
