package com.android1.shoplarity.models;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android1.shoplarity.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.registerText)TextView register;
    public static final String TAG= LoginActivity.class.getSimpleName();
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.password)EditText Password;
    @BindView(R.id.LoginButton)
    Button login;
    private FirebaseAuth Auth;
    private FirebaseAuth.AuthStateListener AuthListen;
    private ProgressDialog AuthDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        Auth=FirebaseAuth.getInstance();
        register.setOnClickListener(this);
        login.setOnClickListener(this);
        AuthListen = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser client= firebaseAuth.getCurrentUser();
                if(client != null){
                    Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };

        CreateDialogue();

    }
    //this is a dialogue box which helps the user to know well if yes or no the program is working
    private void CreateDialogue(){
        AuthDialog=new ProgressDialog(this);
        AuthDialog.setTitle("Loading...");
        AuthDialog.setMessage("Getting the Authentication...");
        AuthDialog.setCancelable(false);
    }

    @Override
    public void onClick(View v) {
        if(v==register){
            Intent intent=new Intent(LoginActivity.this,SignUpActivity.class);
            startActivity(intent);
            finish();
        }
        if (v==login){
            signin();
        }
    }

    //this AuthListener is in charge of listening to the happening event so that it opens and close the activity depending on the current situation
    @Override
    public void onStart(){
        super.onStart();
        Auth.addAuthStateListener(AuthListen);
    }

    @Override
    public void onStop(){
        super.onStop();
        if (AuthListen != null){
            Auth.removeAuthStateListener(AuthListen);
        }
    }

    private void signin(){
        String email3=email.getText().toString().trim();
        String pass3=Password.getText().toString().trim();
        if(email3.equals("")){
            email.setError("Enter your email,Please!!");
            return;
        }
        if(pass3.equals("")){
            Password.setError("Enter your Password");
            return;
        }
        AuthDialog.show();
        Auth.signInWithEmailAndPassword(email3,pass3).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                AuthDialog.dismiss();
                Log.d(TAG,"signin",task.getException());
            }
        });
    }
}
