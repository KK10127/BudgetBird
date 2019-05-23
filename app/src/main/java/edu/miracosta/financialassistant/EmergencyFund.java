package edu.miracosta.financialassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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


    /**
     * <p>This method starts the activity</p>
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_fund);

        emergencyFundAmountTextView = findViewById(R.id.studentFundAmountTextView);
        amountEditText = findViewById(R.id.amountEditText);

        mDB = new DBHelper(this);

        intent = getIntent();
        mAccount = intent.getParcelableExtra("Account");

        emergencyFundAmount = mAccount.getEmergencyFundAmount();

        //Placing all the account info into the Text Views
        emergencyFundAmountTextView.setText("$ " + String.valueOf(emergencyFundAmount));
    }


    /**
     * <p>This method deposits to the fund</p>
     * @param v This is used to connect the method to the onClick attribute
     */
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
    }

    /**
     * <p>This method withdraws from the fund</p>
     * @param v This is used to connect the method to the onClick attribute
     */
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

        //Displays the new balance
        emergencyFundAmountTextView.setText(mCurrencyFormat.format(emergencyFund));
    }

    /**
     * <p>Returns to the main screen</p>
     * @param v This is used to connect the method to the onClick attribute
     */
    public void back(View v)
    {
        this.finish();
    }

    /**
     * <p>Returns to the main screen</p>
     */
    @Override
    public void onBackPressed() {
        this.finish();
    }
}
