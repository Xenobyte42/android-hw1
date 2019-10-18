package com.example.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.hw1.fragments.ListFragment;
import com.example.hw1.fragments.NumberViewFragment;

public class MainActivity extends AppCompatActivity implements OnItemSelected {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new ListFragment())
                    .commit();
        }
    }

    @Override
    public void onItemSelected(int number, int color) {
        NumberViewFragment fragment = NumberViewFragment.getInstance(number, color);
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
