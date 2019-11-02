package com.android1.shoplarity.models;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.android1.shoplarity.Adapters.DetailsPagerAdapter;
import com.android1.shoplarity.Apifolder.Business;
import com.android1.shoplarity.R;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class StoredetailsActivity extends AppCompatActivity {
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private DetailsPagerAdapter AdapterView;
    List<Business>Details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storedetails);
        ButterKnife.bind(this);
        Details= Parcels.unwrap(getIntent().getParcelableExtra("store"));
        int FirstPosition=getIntent().getIntExtra("position",0);

        AdapterView=new DetailsPagerAdapter(getSupportFragmentManager(),Details);
        viewPager.setAdapter(AdapterView);
        viewPager.setCurrentItem(FirstPosition);
    }
}
