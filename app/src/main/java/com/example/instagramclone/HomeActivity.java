package com.example.instagramclone;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;

import com.instagramclone.fragments.HomeFragment;
import com.instagramclone.fragments.PostFragment;
import com.instagramclone.fragments.ProfileFragment;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        final FragmentManager fragmentManager = getSupportFragmentManager();

        // define your com.example.instagramclone.fragments here
        final Fragment homeFragment = new HomeFragment();
        final Fragment postFragment = new PostFragment();
        final Fragment profileFragment = new ProfileFragment();

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        // handle navigation selection
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment fragment;
                        switch (item.getItemId()) {
                            case R.id.miHome:
                                fragment = homeFragment;
                                break;
                            case R.id.miAddPost:
                                fragment = postFragment;
                                break;
                            case R.id.miProfile:
                                fragment = profileFragment;
                                break;
                            default:
                                fragment = homeFragment;
                                break;
                        }
                        fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                        return true;
                    }
                });
        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.miHome);
    }




}
