package com.chotabheem.android.hellolyf;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by chota_bheem on 9/8/16.
 */
public class FragmentSelectSpecialist extends Fragment {

    private OnOptionSelectedListener listener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_select_specialist, container, false);

        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.vPagerSpecialist);
        viewPager.setAdapter(new CustomAdapter(getChildFragmentManager()));

        Button homeBtn = (Button) rootView.findViewById(R.id.home_btn);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onOptionSelected(new FragmentMain());
            }
        });


        return rootView;
    }
    public static class CustomAdapter extends FragmentPagerAdapter {
        public CustomAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new FragmentPage1();
                case 1:
                    return new FragmentPage2();
                case 2:
                    return new FragmentPage3();
                default:
                    return null;

            }
        }

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
