package edu.miracosta.financialassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.miracosta.financialassistant.model.Account;

public class UserSettingsActivity extends AppCompatActivity {

    private Intent mIntent;
    private Account mAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);

        //Dont change this. This works to receive the account info
        mIntent = getIntent();
        mAccount = mIntent.getParcelableExtra("Account");
    }
}
