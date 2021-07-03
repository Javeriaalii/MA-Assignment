package com.example.creativehub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class signin extends AppCompatActivity {
    ImageView sback;
    EditText userr,pass;
    TextView signin;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signin);
        sback = (ImageView)findViewById(R.id.sinb);

        userr=(EditText) findViewById(R.id.usrusr);
        pass=(EditText) findViewById(R.id.pswrd);
        mAuth = FirebaseAuth.getInstance();
        signin=(TextView)findViewById(R.id.signintv);
        progressDialog= new ProgressDialog(this);
        FirebaseUser user= mAuth.getCurrentUser();
        if (user!= null)
        {
            finish();//default method.
            Intent intent=new Intent(this,weblink.class);
            startActivity(intent);
            //refer to new Activity
        }
        sback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(signin.this,MainActivity.class);
                startActivity(it);
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a=userr.getText().toString();
                String b= pass.getText().toString();
                login(a, b);

            }
        });

    }
    public void CallForegtPass(View view) {

    }

    public void CallCreateAccount(View view) {


    }
    public void login(String id,String pass) {
        if (id.equals("") && pass.equals("")) {
            Toast.makeText(getApplicationContext(), "Fill Required Fields", Toast.LENGTH_LONG).show();
        }
        else

        progressDialog.setMessage("Validating Credentials...");
        progressDialog.show();
        mAuth.signInWithEmailAndPassword(id, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Login Successful.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), weblink.class);
                    startActivity(intent);
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Invalid Credentials OR Check Internet", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}