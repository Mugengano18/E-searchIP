package com.android1.shoplarity.models;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.android1.shoplarity.Adapters.clothesAdapter;
import com.android1.shoplarity.R;
import butterknife.BindView;
import butterknife.ButterKnife;


public class ClothesActivity extends AppCompatActivity {
    @BindView(R.id.listview1)
    ListView clotheslist;
    private String[] clothes={"Women's clothes","Men's clothes","Children's clothes"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes);
        ButterKnife.bind(this);
        clothesAdapter clothesAdapter=new clothesAdapter(this,android.R.layout.simple_list_item_1,clothes);
        clotheslist.setAdapter(clothesAdapter);//this is used to show the list of clothes
        clotheslist.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        Intent intent1= new Intent(ClothesActivity.this,womenList.class);
                        startActivity(intent1);
                        break;
                    case 1:
                        Intent intent2=new Intent(ClothesActivity.this,MenList.class);
                        startActivity(intent2);
                        break;
                    case 2:
                        Intent intent3=new Intent(ClothesActivity.this,childrenList.class);
                        startActivity(intent3);
                        break;
                }
            }
        });
    }
    }
