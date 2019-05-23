package edu.miracosta.financialassistant;

import android.content.Intent;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import edu.miracosta.financialassistant.database.DBHelper;
import edu.miracosta.financialassistant.model.Account;
import edu.miracosta.financialassistant.model.Expense;


public class EmergencyFund extends AppCompatActivity
{
    //XML variables
    private TextView userNameTextView;
    private TextView budgetTextView;
    private TextView monthlyIncomeTextView;
    private TextView emergencyFundAmountTextView;
    private EditText amountEditText;

    //Member variables
    private double emergencyFundAmount;
    private Intent intent;
    private Account mAccount;
    private Expense mExpense;
    private DBHelper mDB;

    //NumberFormatters
    NumberFormat mCurrencyFormat = NumberFormat.getCurrencyInstance();
    NumberFormat mNumberFormat = NumberFormat.getNumberInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_fund);

        userNameTextView = findViewById(R.id.UserNameTextView);
        budgetTextView = findViewById(R.id.BudgetTextView);
        monthlyIncomeTextView = findViewById(R.id.MonthlyIncomeTextView);
        emergencyFundAmountTextView = findViewById(R.id.EmergencyFundAmountTextView);
        amountEditText = findViewById(R.id.amountEditTextSF);

        mDB = new DBHelper(this);

        intent = getIntent();
        mAccount = intent.getParcelableExtra("Account");

        emergencyFundAmount = mAccount.getEmergencyFundAmount();

        //Placing all the account info into the Text Views
        emergencyFundAmountTextView.setText("$ " + String.valueOf(emergencyFundAmount));
    }




    //Adds a deposit to the fund
    public void addDeposit(View v)
    {
        //Grabs the total fund currently (before the deposit)
        emergencyFundAmount = Double.valueOf(emergencyFundAmountTextView.getText().toString().substring(1).replaceAll(",", ""));

        //Grabs how much is being deposited
        double deposit;
        deposit = Double.valueOf(amountEditText.getText().toString());

        //Creates an expense object from the deopist
        //mExpense = new Expense(deposit, "Deposited Into Emergency Fund.", "Emergency Fund");

        //Then stores the expense in the ExpenseDataBase


        double emergencyFund = mDB.getEmergencyFund(mAccount.getId());
        emergencyFund += deposit;
        mDB.setEmergencyFund(mAccount.getId(), emergencyFund);

        //emergencyFundAmount = emergencyFundAmount + deposit;

        //Update model
        mAccount.setEmergencyFundAmount(emergencyFund);

        //Displays the new balance
        emergencyFundAmountTextView.setText(mCurrencyFormat.format(emergencyFund));

        Toast.makeText(this, "Amount deposited successfully!", Toast.LENGTH_SHORT).show();
    }

    //Withdraws from the fund
    public void withdraw(View v)
    {
        //Grabs the E.F amount
        emergencyFundAmount = Double.valueOf(emergencyFundAmountTextView.getText().toString().substring(1).replaceAll(",", ""));

        //Grabs how much to withdraw
        double withdrawAmount;
        withdrawAmount = Double.valueOf(amountEditText.getText().toString());

        double emergencyFund = mDB.getEmergencyFund(mAccount.getId());
        emergencyFund -= withdrawAmount;
        if (emergencyFund < 0)
            emergencyFund = 0.0;
        mDB.setEmergencyFund(mAccount.getId(), emergencyFund);

        //emergencyFundAmount = emergencyFundAmount + deposit;

        //Update model
        mAccount.setEmergencyFundAmount(emergencyFund);
        amountEditText.setText("");

        //Displays the new balance
        emergencyFundAmountTextView.setText(mCurrencyFormat.format(emergencyFund));

        Toast.makeText(this, "Amount withdrawn successfully!", Toast.LENGTH_SHORT).show();
    }

    //Returns to the main screen
    public void back(View v)
    {
        this.finish();
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }
}
