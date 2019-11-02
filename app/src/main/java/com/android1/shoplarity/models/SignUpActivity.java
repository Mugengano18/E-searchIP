package com.android1.shoplarity.models;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
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
import com.google.firebase.auth.UserProfileChangeRequest;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG=SignUpActivity.class.getSimpleName();
    @BindView(R.id.nameText)
    EditText name;
    @BindView(R.id.emailText)EditText email;
    @BindView(R.id.passwordText)
    EditText password;
    @BindView(R.id.confirmPassword)EditText confirmpass;
    @BindView(R.id.createUser)
    Button create;
    @BindView(R.id.loginText)TextView login;
    private FirebaseAuth Auth;
    private FirebaseAuth.AuthStateListener AuthListener;
    private ProgressDialog AuthDialog;
    private String Name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        ButterKnife.bind(this);
        Auth=FirebaseAuth.getInstance();
        login.setOnClickListener(this);
        create.setOnClickListener(this);
        CreateAuthListener();
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
        if(v==login){
            Intent intent=new Intent(SignUpActivity.this,LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if(v==create){
            NewUser();
        }
    }

    //the below codes are for checking if the user entered valid credentials
    private boolean  isNameValid(String name2){
        if (name2.equals("")){
            name.setError("Please Enter a valid name");
            return false;
        }
        return true;
    }


    private boolean isEmailValid(String email2){
        boolean isCorrectEmail=(email2 != null && Patterns.EMAIL_ADDRESS.matcher(email2).matches());
        if (!isCorrectEmail){
            email.setError("Enter a valid email");
            return false;
        }
        return  isCorrectEmail;
    }


    //I am going to set the username
    private void  createFirebaseUser(final FirebaseUser user){
        UserProfileChangeRequest profileName=new UserProfileChangeRequest.Builder()
                .setDisplayName(Name).build();

        user.updateProfile(profileName).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Log.d(TAG, user.getDisplayName());
                }
            }
        });
        
    }
    //this is creating a new user
    private void NewUser(){
        Name=name.getText().toString().trim();
        final  String name1= name.getText().toString().trim();
        final  String email1= email.getText().toString().trim();
        final  String pass1= password.getText().toString().trim();
        final  String confirm1= confirmpass.getText().toString().trim();
        boolean validName2=isNameValid(Name);
        boolean validName= isNameValid(name1);
        boolean validEmail=isEmailValid(email1);
        if (!validEmail || !validName  ) return;

        AuthDialog.show();
        Auth.createUserWithEmailAndPassword(email1, pass1)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            createFirebaseUser(task.getResult().getUser());//this is catching the name and saving it into the logcat
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    //this AuthListener is in charge of listening to the happening event so that it opens and close the activity depending on the current situation
    @Override
    public void onStart(){
        super.onStart();
        Auth.addAuthStateListener(AuthListener);
    }

    @Override
    public void onStop(){
        super.onStop();
        if (AuthListener != null){
            Auth.removeAuthStateListener(AuthListener);
        }
    }

    private void CreateAuthListener(){
        AuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final  FirebaseUser user=firebaseAuth.getCurrentUser();
                if(user!= null){
                    Intent intent=new Intent(SignUpActivity.this,HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }
}
