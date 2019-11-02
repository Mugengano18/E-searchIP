package com.android1.shoplarity.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.ListFragment;

import com.android1.shoplarity.Apifolder.Business;
import com.android1.shoplarity.models.detailsfragment;

import java.util.List;

public class DetailsPagerAdapter extends FragmentPagerAdapter {
    private List<Business>MoreDetails;

    public DetailsPagerAdapter(@NonNull FragmentManager fm, List<Business> moreDetails) {
        super(fm);
        MoreDetails = moreDetails;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return detailsfragment.newInstance(MoreDetails.get(position));
    }

    @Override
    public int getCount() {
        return MoreDetails.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return MoreDetails.get(position).getName();
    }
}
