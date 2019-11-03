package com.android1.shoplarity.models;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.android1.shoplarity.Adapters.womenllistAdapter;
import com.android1.shoplarity.Apifolder.Category;
import com.android1.shoplarity.Apifolder.Location;
import com.android1.shoplarity.R;
import com.android1.shoplarity.network.Client;
import com.android1.shoplarity.network.Api;
import com.android1.shoplarity.Apifolder.Business;
import com.android1.shoplarity.Apifolder.Apiresponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class womenList extends AppCompatActivity {

    // displaying the resources with the recyclerview



    @BindView(R.id.recycler_view1) RecyclerView recyclerView1;
    womenllistAdapter adapter;
    List<Business> womanLists;
    @BindView(R.id.progressBar1)
    ProgressBar bar1;
    @BindView(R.id.error)
    TextView error1;
    public static final String Tag =HomeActivity.class.getSimpleName();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_women_list);
        ButterKnife.bind(this);
        womanLists=new ArrayList<>();
        recyclerView1.setHasFixedSize(true);




        Api clothe1= Client.getclient();
        Call<Apiresponse> call=clothe1.getCategory("women clothes","USA");
        call.enqueue(new Callback<Apiresponse>(){
            @Override
            public void onResponse(Call<Apiresponse> call, Response<Apiresponse> response) {
                hideProgressbar();
                if(response.isSuccessful()){
                   womanLists = response.body().getBusinesses();
                   adapter=new womenllistAdapter(womenList.this,womanLists);
                   recyclerView1.setAdapter(adapter);
                   RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(womenList.this);
                   recyclerView1.setLayoutManager(layoutManager);
                    showClothes();
                }else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<Apiresponse> call, Throwable t) {
                Log.e(Tag,"onFailure:",t);
                hideProgressbar();
                failureSms();
            }

        });
}
    private void failureSms(){
        error1.setText("please check your connection");
        error1.setVisibility(View.VISIBLE);
    }
    private void showUnsuccessfulMessage() {
        error1.setText("Something went wrong. Please try again later");
        error1.setVisibility(View.VISIBLE);
    }

    private void showClothes() {
        recyclerView1.setVisibility(View.VISIBLE);
    }

    private void hideProgressbar() {
        bar1.setVisibility(View.GONE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);


        MenuItem menuItem = menu.findItem(R.id.action_search);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}












































































































































































