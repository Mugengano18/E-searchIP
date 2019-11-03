package com.android1.shoplarity.models;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android1.shoplarity.R;
import com.android1.shoplarity.credentials;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class shoesloc extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.loc)
    EditText location;
    @BindView(R.id.findShoes)
    Button findShoes;
    @BindView(R.id.SavedShoes)Button saved;
    private DatabaseReference SearchedLocationReference;
    private ValueEventListener SearchedLocationReferenceListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SearchedLocationReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(credentials.FIREBASE_SEARCHED_LOCATION);
        SearchedLocationReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    String loc=dataSnapshot1.getValue().toString();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoesloc);
        ButterKnife.bind(this);
        findShoes.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==findShoes){
            Intent intention =new Intent(shoesloc.this, ShoesActivity.class);
            String Loc=location.getText().toString();
            saveLocationToFirebase(Loc);
            intention.putExtra("location", Loc);
            startActivity(intention);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        SearchedLocationReference.removeEventListener(SearchedLocationReferenceListener);
    }
    public void saveLocationToFirebase(String location) {
        SearchedLocationReference.push().setValue(location);
    }

}
