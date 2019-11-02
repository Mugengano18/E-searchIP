package com.android1.shoplarity.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android1.shoplarity.R;

public class categoryAdapter extends BaseAdapter {//the base adapter containes mainly most of the functionality
    Context context;
    private int[] foto_id;
    private String[] description;

    public categoryAdapter(Context context, int[] foto_id, String[] description) {
        this.context = context;
        this.foto_id = foto_id;
        this.description = description;
    }



    @Override
    public int getCount() {
        return description.length;
    }

    @Override
    public Object getItem(int position) {
        return description[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View foto_view=convertView;

        if (foto_view==null){
            LayoutInflater insert=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            foto_view=insert.inflate(R.layout.categoryitems,null);

        }

        ImageView image=(ImageView)foto_view.findViewById(R.id.foto);
        TextView text=(TextView)foto_view.findViewById(R.id.textView);
        image.setImageResource(foto_id[position]);
        text.setText(description[position]);
        return foto_view;
    }
}
