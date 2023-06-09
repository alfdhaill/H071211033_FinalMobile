package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    public static final String language = "en-US";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        FragmentManager fragmentManager = getSupportFragmentManager();
        MoviesFragment fragment1 = new MoviesFragment();
        TvshowsFragment fragment2 = new TvshowsFragment();
        FavoritesFragment fragment3 = new FavoritesFragment();

        Fragment fragment =
                fragmentManager.findFragmentByTag(MoviesFragment.class.getSimpleName());
        if (!(fragment instanceof MoviesFragment)) {
            fragmentManager
                    .beginTransaction()
                    .add(R.id.cl_container, fragment1,
                            MoviesFragment.class.getSimpleName())
                    .commit();
        }

        bottomNavigationView.setOnItemSelectedListener(item -> {

            FragmentTransaction transaction = fragmentManager.beginTransaction();

            if (item.getItemId() == R.id.item_1) {

                transaction.replace(R.id.cl_container, fragment1);
            } else if (item.getItemId() == R.id.item_2) {

                transaction.replace(R.id.cl_container, fragment2);
            } else if (item.getItemId() == R.id.item_3) {

                transaction.replace(R.id.cl_container, fragment3);
            } else {

                return false;
            }

            transaction.commit();
            return true;
        });
    }
}