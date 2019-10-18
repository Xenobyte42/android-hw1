package com.example.hw1.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.hw1.R;

public class NumberViewFragment extends Fragment {
    private static final String SAVED_NUMBER = "saved.number";
    private static final String SAVED_COLOR = "saved.color";
    private static final String NUMBER_FROM_LIST = "listfragment.number";
    private static final String COLOR_FROM_LIST = "listfragment.color";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.number_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            String number = arguments.getString(NUMBER_FROM_LIST);
            int color = arguments.getInt(COLOR_FROM_LIST);
            TextView numberView =  view.findViewById(R.id.number_view_text);
            numberView.setText(number);
            numberView.setTextColor(ContextCompat.getColor(getContext(), color));
        }
    }

     public  static NumberViewFragment getInstance(int number, int color) {
        NumberViewFragment fragment = new NumberViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString(NUMBER_FROM_LIST, String.valueOf(number));
        bundle.putInt(COLOR_FROM_LIST, color);
        fragment.setArguments(bundle);
        return fragment;
    }
}
