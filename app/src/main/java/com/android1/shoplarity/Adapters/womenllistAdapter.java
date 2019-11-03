package com.android1.shoplarity.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android1.shoplarity.Apifolder.Business;
import com.android1.shoplarity.R;
import com.android1.shoplarity.credentials;
import com.android1.shoplarity.models.StoredetailsActivity;
import com.android1.shoplarity.models.detailsfragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class womenllistAdapter extends RecyclerView.Adapter<womenllistAdapter.womanViewHolder>{

    private Context context;
    private List<Business> womenclothess;

    public womenllistAdapter(Context context, List<Business> womenclothess) {
        this.context = context;
        this.womenclothess = womenclothess;
    }

    @NonNull
    @Override
    public womanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater insert=LayoutInflater.from(context);
        View v=insert.inflate(R.layout.womanlist,null);
        womanViewHolder wHolder= new womanViewHolder(v);
        return wHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull womanViewHolder holder, int position) {
        holder.bindList(womenclothess.get(position));
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,womenclothess.get(position).getName(),Toast.LENGTH_LONG).show();
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(womenclothess.get(position).getUrl()));
                context.startActivity(webIntent);
//                Intent intent= new Intent(context, StoredetailsActivity.class);
//                intent.putExtra("details", Parcels.wrap(womenclothess));
//                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return womenclothess.size();
    }

    class womanViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //creating the ui element
        @BindView(R.id.womanImg) ImageView image1;
        @BindView(R.id.storename)TextView text5;
//        @BindView(R.id.category)TextView cat;
//        @BindView(R.id.location)TextView loc;
//        @BindView(R.id.phonen)TextView phone;
//        @BindView(R.id.rate)TextView rate;
        @BindView(R.id.ParentLayout)
        RelativeLayout parent;
        @BindView(R.id.fav)
        Button favorite;

        private Context cont;
        public womanViewHolder(@NonNull View itemView) {
            super(itemView);
            //accessing the views from itemview
            ButterKnife.bind(this,itemView);
            context=itemView.getContext();
            favorite.setOnClickListener(this);
        }


        public void bindList(Business business) {
            text5.setText(business.getName());
//            cat.setText(business.getCategories().get(0).getTitle());
//            loc.setText(business.getLocation().getAddress1());
//            phone.setText(business.getPhone());
//            rate.setText("rate " + String.valueOf(business.getRating()));
            Picasso.get().load(business.getImageUrl()).into(image1);
        }

        @Override
        public void onClick(View v) {
            if (v==favorite){
                DatabaseReference productRef = FirebaseDatabase
                        .getInstance()
                        .getReference(credentials.FIREBASE_CHILD);
                productRef.push().setValue(womenclothess);
            }
        }
    }



}
