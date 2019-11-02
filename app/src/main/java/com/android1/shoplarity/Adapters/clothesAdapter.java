package com.android1.shoplarity.Adapters;

import android.content.Context;
import android.widget.ArrayAdapter;


import androidx.annotation.NonNull;

public class clothesAdapter extends ArrayAdapter {
    Context context;
    private String clothes[];


    public clothesAdapter(@NonNull Context context, int resource,String[] clothes) {//this is an array adapter
        super(context, resource);
        this.context=context;
        this.clothes=clothes;
    }
    @Override
    public Object getItem(int position){
        String cloth=clothes[position];
        return String.format(cloth);
    }
    @Override
    public int getCount(){
        return clothes.length;
    }
}
