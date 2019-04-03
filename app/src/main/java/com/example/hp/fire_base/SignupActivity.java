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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import org.w3c.dom.Text;

public class SignupActivity extends AppCompatActivity {
    private Button registered;
    private EditText signupEmailEditText,signupPasswordEditText;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        this.setTitle("SignUp Activity" );
        mAuth = FirebaseAuth.getInstance();

        signupEmailEditText = findViewById(R.id.SignupemailId);
        signupPasswordEditText =findViewById(R.id.SignuppasswordId);

        registered = findViewById(R.id.RegisteredButtonId);
        registered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userRegister ();


            }

            private void userRegister() {
                 String email= signupEmailEditText.getText().toString().trim();
                 String password = signupPasswordEditText.getText().toString().trim();

                 if (email.isEmpty())
                 {
                     signupEmailEditText.setError("Enter an email address");
                     signupEmailEditText.requestFocus();
                     return;
                 }

                 if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                 {
                    signupEmailEditText.setError("Enter a valid email address");
                    signupEmailEditText.requestFocus();
                    return;
                 }
                 if (password.isEmpty())
                 {
                     signupPasswordEditText.setError("Enter a password");
                     signupPasswordEditText.requestFocus();
                     return;
                 }
                if (password.length()<6)
                {
                    signupPasswordEditText.setError("Minimum length of a password should be 6");
                    signupPasswordEditText.requestFocus();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            finish();
                            Intent intent = new Intent(getApplicationContext(),SigninActivity .class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);

                            Toast.makeText(getApplicationContext() ,"Register is successful ",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            if (task.getException()instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(getApplicationContext() ,"User is already registered ",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getApplicationContext() ,"Error :"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }

                            }


                    }
                });



            }
        });


    }
}
