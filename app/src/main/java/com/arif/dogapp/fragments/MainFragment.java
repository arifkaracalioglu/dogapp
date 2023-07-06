package com.arif.dogapp.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arif.dogapp.R;
import com.arif.dogapp.client.ApiClient;
import com.arif.dogapp.client.ApiInterface;
import com.arif.dogapp.objects.Dog;
import com.arif.dogapp.recyclerviewadapters.RecyclerViewDogAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment {

    //Global Variables
    ArrayList<String> dogList = new ArrayList<>();
    RecyclerView mRecyclerView;
    RecyclerViewDogAdapter mAdapter;
    View v;

    //Navigation Components Variables
    private NavHostFragment mNavHost;
    private NavController mNavController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Navigation Component initialize
        mNavHost = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.mFragmentContainerView);
        mNavController = mNavHost.getNavController();
        mNavController.popBackStack(R.id.splashScreenFragment, true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //View settings
        v = inflater.inflate(R.layout.fragment_main, container, false);
        //RecyclerView Settings
        mRecyclerView = v.findViewById(R.id.mRecyclerViewDogTypes);
        mAdapter = new RecyclerViewDogAdapter(this, dogList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        getDogs();
    }

    private void getDogs() {
        //Clear dog breeds list first
        dogList.clear();

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Dog> call = apiInterface.getDogs();

        call.enqueue(new Callback<Dog>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<Dog> call, Response<Dog> response) {
                Dog dogs = response.body();
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String jsonObject = gson.toJson(dogs);
                JSONObject object;
                JSONObject finalObject = null;

                try {
                    object = new JSONObject(jsonObject);
                    finalObject = new JSONObject(object.getString("message"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                for (int c = 0; c < finalObject.length(); c++) {
                    try {
                        String str = finalObject.names().get(c).toString();
                        dogList.add(str);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                mAdapter.notifyDataSetChanged();

                v.findViewById(R.id.mTextViewLoading).setVisibility(View.GONE);
                v.findViewById(R.id.mRecyclerViewDogTypes).setVisibility(View.VISIBLE);
            } //End of onResponse

            @Override
            public void onFailure(Call<Dog> call, Throwable t) {
                Log.e("DOGS", t.getMessage().toString());
            }
        });
    }

}