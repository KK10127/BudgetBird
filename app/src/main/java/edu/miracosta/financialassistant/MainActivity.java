package edu.miracosta.financialassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

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

        // create explicit intent
        Intent intent = new Intent(this, SignUpActivity.class);

        // start the new activity
        startActivity(intent);
    }

}
