package com.arif.dogapp.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arif.dogapp.R;
import com.arif.dogapp.client.ApiClientImages;
import com.arif.dogapp.client.ApiInterfaceImages;
import com.arif.dogapp.objects.MessageImages;
import com.arif.dogapp.recyclerviewadapters.RecyclerViewImagesAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DogsFragment extends Fragment {

    //RecyclerView variables
    RecyclerView mRecyclerViewImages;
    RecyclerViewImagesAdapter mAdapter;
    //Global variables
    private View v;
    ArrayList<String> mListImages = new ArrayList<>();
    //Navigation Component Variables
    private NavHostFragment mNavHost;
    private NavController mNavController;
    //UI Elements
    TextView mTextViewImagesTitle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mNavHost = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.mFragmentContainerView);
        mNavController = mNavHost.getNavController();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_dogs, container, false);
        mRecyclerViewImages = v.findViewById(R.id.mRecyclerViewImages);
        mTextViewImagesTitle = v.findViewById(R.id.mTextViewImagesTitle);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        mAdapter = new RecyclerViewImagesAdapter(this, mListImages);
        mRecyclerViewImages.setAdapter(mAdapter);
        mRecyclerViewImages.setLayoutManager(new GridLayoutManager(this.getActivity(),4));
        getDogImages();
    }

    private void getDogImages(){

        ApiClientImages client = new ApiClientImages(getArguments().getString("dogbreed"));

        ApiInterfaceImages apiInterface = client.getClientImages().create(ApiInterfaceImages.class);
        Call<MessageImages> call = apiInterface.getDogImages();

        call.enqueue(new Callback<MessageImages>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<MessageImages> call, Response<MessageImages> response) {
                mTextViewImagesTitle.setVisibility(View.GONE);

                for (int controller = 0; controller < response.body().getMessage().length-1; controller++){
                    mListImages.add(response.body().getMessage()[controller]);
                }

                mAdapter.notifyDataSetChanged();
            } //End of onResponse

            @Override
            public void onFailure(Call<MessageImages> call, Throwable t) {
                Log.e("DOGS", t.getMessage().toString());
            }
        });
    }


}