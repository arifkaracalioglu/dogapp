package com.arif.dogapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.arif.dogapp.R;
import com.bumptech.glide.Glide;

public class DogDetailsFragment extends Fragment {

    ImageView mImageViewDogDetail;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_dog_detail, container,false);
        mImageViewDogDetail = mView.findViewById(R.id.mImageViewDogDetail);
        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();

        String url = getArguments().getString("url");
        Glide.with(getActivity()).load(url).into(mImageViewDogDetail);
    }
}
