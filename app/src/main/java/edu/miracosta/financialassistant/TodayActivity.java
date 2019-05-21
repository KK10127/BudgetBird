package edu.miracosta.financialassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import edu.miracosta.financialassistant.model.Account;

public class TodayActivity extends AppCompatActivity {

    private Intent mIntent;
    private Account mAccount;


    private EditText amountEditText;
    private Button addAmountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today);

        //Dont change this
        mIntent = getIntent();
        mAccount = mIntent.getParcelableExtra("Account");

        amountEditText = findViewById(R.id.amountEditText);
        addAmountButton = findViewById(R.id.addAmountButton);

    }

    /**
     * This method adds an amount to
     */
    public void addAmount( ) {
        // TODO: Make sure the amount in the amountEditText is not zero or negative

        // TODO: Grab the amount in the edit text, use the Date class to get the current date as a
        // TODO: string. Store the string/value in the 'Activity database table' using the DB helper.

        // TODO: Make a toast indicating success to the user.
    }

}
