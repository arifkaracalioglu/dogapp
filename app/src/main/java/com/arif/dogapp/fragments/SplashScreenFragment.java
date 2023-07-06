package com.arif.dogapp.fragments;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.arif.dogapp.R;

public class SplashScreenFragment extends Fragment {

    //Navigation Component Variables
    private NavHostFragment mNavHost;
    private NavController mNavController;

    //UI Elements
    private ProgressBar mProgressBar;
    private TextView mTextViewTitle;

    //Animation variables
    Animation anim;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mNavHost = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.mFragmentContainerView);
        mNavController = mNavHost.getNavController();

        anim = AnimationUtils.loadAnimation(getContext(),R.anim.anim_splash_title);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_splash_screen, container, false);
        mProgressBar = v.findViewById(R.id.mProgressBarSplash);
        mProgressBar.setMax(100);
        mProgressBar.setProgress(0);
        mTextViewTitle = v.findViewById(R.id.mTextViewSplashTitle);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        mTextViewTitle.setAnimation(anim);
        mTextViewTitle.startAnimation(anim);
        new CountDownTimer(5000, 1000) {

            @Override
            public void onTick(long l) {
                mProgressBar.setProgress(mProgressBar.getProgress() + 20);
            }

            @Override
            public void onFinish() {
                mNavController.navigate(R.id.action_splashScreenFragment_to_mainFragment);
            }
        }.start();
    }

}