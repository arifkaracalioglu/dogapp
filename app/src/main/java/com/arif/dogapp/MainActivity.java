package com.arif.dogapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.arif.dogapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    //Navigation Component Variables
    private NavHostFragment mNavHostFragment;

    //Binding
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        //Navigation Setup
        mNavHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.mFragmentContainerView);
        assert mNavHostFragment != null;
        setContentView(binding.getRoot());

        //Change status bar color
        Window window = this.getWindow();

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.cream));

        //Disable night mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

    }

    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();
    }
}