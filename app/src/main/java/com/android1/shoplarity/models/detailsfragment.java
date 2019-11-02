package com.android1.shoplarity.models;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android1.shoplarity.Apifolder.Business;
import com.android1.shoplarity.Apifolder.Category;
import com.android1.shoplarity.R;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class detailsfragment extends Fragment {
    @BindView(R.id.image2)
    ImageView image2;
    @BindView(R.id.store)
    TextView store;
    @BindView(R.id.cat)
    TextView cat;
    @BindView(R.id.rate)
    TextView rate;
    @BindView(R.id.saveButton)
    Button save;
    @BindView(R.id.help)
    Button help;
    @BindView(R.id.websiteTextView)
    TextView website;
    @BindView(R.id.PhoneText)
    TextView Phone;
    @BindView(R.id.ExactLocation)
    TextView location;
    private Business Details;
    public detailsfragment() {
        // Required empty public constructor
    }



    public static detailsfragment newInstance(Business products){
        detailsfragment detailsStore = new detailsfragment();
        Bundle args =new Bundle();
        args.putParcelable("Details", Parcels.wrap(products));
        detailsStore.setArguments(args);
        return detailsStore;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Details = Parcels.unwrap(getArguments().getParcelable("store"));
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detailsfragment, container, false);
        ButterKnife.bind(this, view);

        Picasso.get().load(Details.getImageUrl()).into(image2);
        List<String>categories=new ArrayList<>();
        for (Category category:Details.getCategories()){
            categories.add(category.getTitle());
        }
        store.setText(Details.getName());
        cat.setText(android.text.TextUtils.join(",",categories));
        rate.setText(Double.toString(Details.getRating()));
        Phone.setText(Details.getPhone());
        location.setText(Details.getLocation().toString());
        // Inflate the layout for this fragment
        return view;
    }

}
