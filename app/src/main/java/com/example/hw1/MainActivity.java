package com.example.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.hw1.fragments.ListFragment;


public class MainActivity extends AppCompatActivity {
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
}
