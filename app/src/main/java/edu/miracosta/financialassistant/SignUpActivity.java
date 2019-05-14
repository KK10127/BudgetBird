package edu.miracosta.financialassistant;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.net.MulticastSocket;

/**
 * Resposible for handling the signup process for the application.
 */
public class SignUpActivity extends AppCompatActivity {

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;


    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //Connecting the EditTexts
        firstNameEditText = findViewById(R.id.firstNameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        //instantiate the firebase authenticator
        mAuth = FirebaseAuth.getInstance();
    }

    public void createNewAccount(View v){
        createAccount(emailEditText.getText().toString(), passwordEditText.getText().toString());
    }

    /**
     * This method checks the user input to make sure the email and password are valid
     * @return if the input is valid
     */
    private boolean isValidInput(){
        boolean valid = true;
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if(TextUtils.isEmpty(email)){
            valid = false;
            emailEditText.setError("Email required");
        }

        if(TextUtils.isEmpty(password)){
            valid = false;
            passwordEditText.setError("Password required");
        }

        return valid;
    }

    /**
     * This method creates a new account for a user using their email and password
     * @param email the desired email to sign up with
     * @param password the user-set password
     */
    private void createAccount(String email, String password){
        if(!isValidInput())
            return;
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //send a verification email to the user
                            mUser = mAuth.getCurrentUser();
                            mUser.sendEmailVerification();
                            Toast.makeText(SignUpActivity.this, "Account created, please verify email sent to: " +
                                    mUser.getEmail(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
