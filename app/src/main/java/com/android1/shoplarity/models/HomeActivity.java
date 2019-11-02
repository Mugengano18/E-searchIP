package com.android1.shoplarity.models;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.android1.shoplarity.Adapters.categoryAdapter;
import com.android1.shoplarity.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity{
    @BindView(R.id.grid1)
    GridView imageGrid;
    private String[]names={"Clothes","Shoes","Cars","Furniture","Phones","Antique"};
    private int[] foto_id={R.drawable.foto1,R.drawable.foto2,R.drawable.foto3,R.drawable.foto4,R.drawable.foto6,R.drawable.foto7};
    @BindView(R.id.hello)
    TextView greet;
    private  FirebaseAuth Auth;
    private FirebaseAuth.AuthStateListener AuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        categoryAdapter categoryAdapter=new categoryAdapter(HomeActivity.this,foto_id,names);
        imageGrid.setAdapter(categoryAdapter);
        imageGrid.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //this is switch case to help me get to a specific activity depending on the pressed Image
                switch (position){
                    case 0:
                        Intent intent1=new Intent(HomeActivity.this, ClothesActivity.class);
                        startActivity(intent1);
                        break;
                    case 1:
                        Intent intent2=new Intent(HomeActivity.this, ShoesActivity.class);
                        startActivity(intent2);
                        break;
                    case 2:
                        Intent intent3=new Intent(HomeActivity.this, carsActivity.class);
                        startActivity(intent3);
                        break;
                    case 3:
                        Intent intent4=new Intent(HomeActivity.this, furnitureActivity.class);
                        startActivity(intent4);
                        break;
                    case 4:
                        Intent intent5=new Intent(HomeActivity.this, mobilephoneactivity.class);
                        startActivity(intent5);
                        break;
                    case 5:
                        Intent intent6=new Intent(HomeActivity.this, AntiquityActivity.class);
                        startActivity(intent6);
                        break;

                }

                Toast.makeText(getApplicationContext(),((TextView) view.findViewById(R.id.textView)).getText(),Toast.LENGTH_SHORT).show();
            }
        });
        greet.setText("Welcome  ");//this is the name entered by the user

        Auth=FirebaseAuth.getInstance();
        AuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser client=firebaseAuth.getCurrentUser();
                if(client != null){
                    getSupportActionBar().setTitle(client.getDisplayName());
                }
            }
        };
    }
    @Override
    public void onStart(){
        super.onStart();
        Auth.addAuthStateListener(AuthListener);
    }

    @Override
    public void onStop(){
        super.onStop();
        if(AuthListener != null){
            Auth.removeAuthStateListener(AuthListener);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

}
