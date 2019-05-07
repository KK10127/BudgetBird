package edu.miracosta.financialassistant.funds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

import edu.miracosta.financialassistant.R;
import edu.miracosta.financialassistant.model.Account;


public class EmergencyFund extends AppCompatActivity
{
    private TextView userNameTextView;
    private TextView budgetTextView;
    private TextView monthlyIncomeTextView;
    private TextView emergencyFundAmountTextView;
    private EditText amountDepositedEditText;

    private double emergencyFundAmount;
    private Intent intent;
    private Account mAccount;

    //NumberFormatters
    NumberFormat mCurrencyFormat = NumberFormat.getCurrencyInstance();
    NumberFormat mNumberFormat = NumberFormat.getNumberInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_fund);

        userNameTextView = findViewById(R.id.usernameTextView);
        budgetTextView = findViewById(R.id.budgetTextView);
        monthlyIncomeTextView = findViewById(R.id.incomeTextView);
        emergencyFundAmountTextView = findViewById(R.id.EmergencyFundAmountTextView);
        amountDepositedEditText = findViewById(R.id.AmountDepositedEditText);

        intent = getIntent();
        mAccount = intent.getParcelableExtra("Account");
        emergencyFundAmount = mAccount.getEmergencyFundAmount();

        //Placing all the account info into the Text Views
        userNameTextView.setText(mAccount.getUserName());
        monthlyIncomeTextView.setText(mCurrencyFormat.format(mAccount.getMonthlyIncome()));
        budgetTextView.setText(mCurrencyFormat.format(mAccount.getBudget()));
        emergencyFundAmountTextView.setText("$ " + String.valueOf(mAccount.getEmergencyFundAmount()));
    }

    //Adds a deposit to the fund
    public void addDeposit(View v)
    {
        emergencyFundAmount = Double.valueOf(emergencyFundAmountTextView.getText().toString().substring(1));

        double deposit;
        deposit = Double.valueOf(amountDepositedEditText.getText().toString());

        emergencyFundAmount = emergencyFundAmount + deposit;

        //Update model
        mAccount.setEmergencyFundAmount(emergencyFundAmount);

        emergencyFundAmountTextView.setText(mCurrencyFormat.format(emergencyFundAmount));
    }

    //Returns to the main screen
    public void back(View v)
    {
        this.finish();
    }
}
