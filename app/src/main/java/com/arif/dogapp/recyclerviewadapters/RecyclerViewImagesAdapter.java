package com.arif.dogapp.recyclerviewadapters;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.arif.dogapp.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerViewImagesAdapter extends RecyclerView.Adapter<RecyclerViewImagesAdapter.RecyclerViewHolderImage>{

    //Global Variables
    ArrayList<String> mImageList;
    Fragment fragment;
    LayoutInflater layoutInflater;
    View mView;
    //Navigation Components
    NavHostFragment mNavHost;
    NavController mNavController;

    public RecyclerViewImagesAdapter(Fragment fragment, ArrayList<String> mImageList){
        mNavHost = (NavHostFragment) fragment.getActivity().getSupportFragmentManager().findFragmentById(R.id.mFragmentContainerView);
        mNavController = mNavHost.getNavController();
        this.fragment = fragment;
        this.layoutInflater = fragment.getLayoutInflater();
        this.mImageList = mImageList;
    }

    @NonNull
    @Override
    public RecyclerViewHolderImage onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mView = layoutInflater.inflate(R.layout.recycler_view_images,parent,false);
        return new RecyclerViewHolderImage(mView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolderImage holder, int position) {
        Glide.with(fragment.getView()).load(mImageList.get(position)).into(holder.mImageViewDog);
        holder.mImageViewDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("url",mImageList.get(holder.getAdapterPosition()));
                mNavController.navigate(R.id.action_dogsFragment_to_dogDetailsFragment,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageList.size()-1;
    }

    public class RecyclerViewHolderImage extends RecyclerView.ViewHolder {

        ImageView mImageViewDog;

        public RecyclerViewHolderImage(@NonNull View itemView) {
            super(itemView);
            mImageViewDog = itemView.findViewById(R.id.mImageViewDog);
        }
    }
}
