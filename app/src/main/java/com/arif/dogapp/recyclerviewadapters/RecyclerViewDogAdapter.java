package com.arif.dogapp.recyclerviewadapters;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.arif.dogapp.R;

import java.util.ArrayList;

public class RecyclerViewDogAdapter extends RecyclerView.Adapter<RecyclerViewDogAdapter.RecyclerViewHolderDog>{

    //Global Variables
    ArrayList<String> mDogList;
    Fragment fragment;
    LayoutInflater layoutInflater;
    //Navigation Components
    NavHostFragment mNavHost;
    NavController mNavController;

    public RecyclerViewDogAdapter(Fragment fragment, ArrayList<String> mDogList){
        mNavHost = (NavHostFragment) fragment.getActivity().getSupportFragmentManager().findFragmentById(R.id.mFragmentContainerView);
        mNavController = mNavHost.getNavController();
        this.fragment = fragment;
        this.layoutInflater = fragment.getLayoutInflater();
        this.mDogList = mDogList;
    }

    @NonNull
    @Override
    public RecyclerViewHolderDog onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = layoutInflater.inflate(R.layout.recycler_view_dogs,parent,false);
        return new RecyclerViewHolderDog(mView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolderDog holder, int position) {
        holder.mTextViewTitle.setText(mDogList.get(position));
        holder.mTextViewTitle.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("dogbreed", mDogList.get(position));
            mNavController.navigate(R.id.action_mainFragment_to_dogsFragment, bundle);
        });
    }

    @Override
    public int getItemCount() {
        return mDogList.size();
    }

    public class RecyclerViewHolderDog extends RecyclerView.ViewHolder {

        TextView mTextViewTitle;

        public RecyclerViewHolderDog(@NonNull View itemView) {
            super(itemView);
           mTextViewTitle = itemView.findViewById(R.id.mTextViewDogTitle);
        }
    }
}
