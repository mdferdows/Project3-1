package com.example.hp.fire_base;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SigninActivity extends AppCompatActivity {
    private Button SignupButton;
    private Button signinButton;
    private EditText signinEmailEditText,signinPasswordEditText;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        this.setTitle("SignIn Activity" );

        mAuth = FirebaseAuth.getInstance();

        signinEmailEditText = findViewById(R.id.SigninemailId);
        signinPasswordEditText = findViewById(R.id.SigninpasswordId);

        SignupButton = findViewById(R.id.SignupId);
        SignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SigninActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });

        signinButton = findViewById(R.id.SignintButton);
        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userLogin();


            }

            private void userLogin() {
                String email= signinEmailEditText.getText().toString().trim();
                String password = signinPasswordEditText.getText().toString().trim();

                if (email.isEmpty())
                {
                    signinEmailEditText.setError("Enter an email address");
                    signinEmailEditText.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    signinEmailEditText.setError("Enter a valid email address");
                    signinEmailEditText.requestFocus();
                    return;
                }
                if (password.isEmpty())
                {
                    signinPasswordEditText.setError("Enter a password");
                    signinPasswordEditText.requestFocus();
                    return;
                }
                if (password.length()<6)
                {
                    signinPasswordEditText.setError("Minimum length of a password should be 6");
                    signinPasswordEditText.requestFocus();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {   finish();
                            Intent intent = new Intent(getApplicationContext(),Menu_activity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }else
                        {
                            Toast.makeText(getApplicationContext() ,"Login Unsuccessful ",Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });

    }
}
