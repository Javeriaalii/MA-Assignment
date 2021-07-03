package com.example.creativehub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
public class signup extends AppCompatActivity {
    ImageView sback;
    EditText newemail,newpass;
    TextView signup;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        sback = (ImageView)findViewById(R.id.sback);
        newemail=(EditText)findViewById(R.id.mail);
        newpass=(EditText)findViewById(R.id.pswrd);
        signup=(TextView)findViewById(R.id.signup);
        mAuth = FirebaseAuth.getInstance();

        sback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent it = new Intent(signup.this,MainActivity.class);
                startActivity(it);
                }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //upload data to firebase database
                //validation
                if (newemail.getText().toString().matches("") || newpass.getText().toString().matches("")) {
                    Toast.makeText(getApplicationContext(), "Fill Required Fields.", Toast.LENGTH_LONG).show();
                } else {
                    String user_id = newemail.getText().toString().trim();
                    String user_pass = newpass.getText().toString().trim();
                    //firebase
                    mAuth.createUserWithEmailAndPassword(user_id, user_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Registration Successful.", Toast.LENGTH_LONG).show();
                                mAuth.signOut();
                                finish();
                                startActivity(new Intent(signup.this, signin.class));


                            } else {
                                Toast.makeText(getApplicationContext(), "Internet Connectivity Failed.", Toast.LENGTH_SHORT).show();
                            }


//mAuth.createUserWithEmailAndPassword(newemail.getText().toString(),newpass.getText().toString())
                /*Intent intent= new Intent(getApplicationContext(),Dashboard.class);
                startActivity(intent);
                Toast toast = Toast. makeText(getApplicationContext(), "Account has been created.", Toast. LENGTH_SHORT);
                toast. show();*/


                        }
                    });//firebase


                }
            }
        });


    }
}