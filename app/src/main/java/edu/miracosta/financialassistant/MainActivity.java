package edu.miracosta.financialassistant;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * The first activity (other than the splash screen) that the user will encounter.
 * Responsible for authenticating users and adding new ones with firebase when possible.
 */
public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = "FinancialAssistant";

    private ImageView appLogoImageView;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button signUpButton;

    //Adding FireBase variables
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // connect the components
        appLogoImageView = findViewById(R.id.appLogoImageView);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.logInButton);
        signUpButton = findViewById(R.id.signUpButton);

        app = FirebaseApp.initializeApp(this);
        //instantiate the firebase authenticator
        mAuth = FirebaseAuth.getInstance();

        //get the current user
        mUser = mAuth.getCurrentUser();

        //if the user is not null (already signed in), go to monthlyOverview activity
        if(mUser != null){
            Intent mOverviewIntent = new Intent(this, MonthlyOverview.class);
            //user signed into, proceed
            startActivity(mOverviewIntent);

            //finish the current activity
            finish();
        }

    }

    /**
     * Method is called when the "Log In!" button is clicked.
     * Verifies the user's credentials and allows them passed when verified.
     * @param v The button R.id.logInButton
     */
    public void logIn(View v) {
        // TODO: Gather the details from the 2 EditText fields. Convert to Strings.
        // TODO: Check the credentials with whatever means we're using to store user/password combos.
        // TODO: No idea how to fucking do that.


        // for now clicking log-in will just take us to the MonthlyOverviewActivity
        Intent intent = new Intent(this, MonthlyOverview.class);
        startActivity(intent);
    }

    /**
     * Method is called when the "Sign up!" button is clicked.
     * Takes the user to the sign-up activity where they can create an account with the application.
     * @param v The button R.id.signUpActivity
     */
    public void signUp(View v) {
        // DONE: Immediately launches the sign-up activity.
        Log.d(LOG_TAG, "Sign-up button clicked!");
        signIn(emailEditText.getText().toString(), passwordEditText.getText().toString());

        // create explicit intent
        //Intent intent = new Intent(this, SignUpActivity.class);

        // start the new activity
        //startActivity(intent);
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
     * If the user is not logged in, this method will handle the login input
     * @param email user entered email
     * @param password user entered password
     */
    private void signIn(String email, String password){
        //first, if the input is NOT valid, return
        if(!isValidInput())
            return;

        //otherwise, continue with the sign in process
        mAuth.signInWithEmailAndPassword(emailEditText.getText().toString(), passwordEditText.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            mUser = mAuth.getCurrentUser();
                            if(mUser.isEmailVerified()){
                                //Proceed
                                Intent intent = new Intent(MainActivity.this, MonthlyOverview.class);
                                startActivity(intent);
                                finish();
                            }else{
                                //The user is not yet email verified
                                Toast.makeText(MainActivity.this, "Account not yet verified, email sent to: "
                                                + mUser.getEmail(), Toast.LENGTH_LONG).show();
                            }
                        }else{
                            //The email / password combination was invalid
                            Toast.makeText(MainActivity.this, "Invalid email or password, try again", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
}
