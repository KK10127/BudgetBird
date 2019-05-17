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

        intent = getIntent();
        mAccount = intent.getParcelableExtra("Account");
        emergencyFundAmount = mAccount.getEmergencyFundAmount();

        //Placing all the account info into the Text Views
        userNameTextView.setText(mAccount.getEmail());
        monthlyIncomeTextView.setText(mCurrencyFormat.format(mAccount.getMonthlyIncome()));
        budgetTextView.setText(mCurrencyFormat.format(mAccount.getBudget()));
        emergencyFundAmountTextView.setText("$ " + String.valueOf(mAccount.getEmergencyFundAmount()));
    }

    //Adds a deposit to the fund
    public void addDeposit(View v)
    {
        //Grabs the total fund currently (before the deposit)
        emergencyFundAmount = Double.valueOf(emergencyFundAmountTextView.getText().toString().substring(1));

        //Grabs how much is being deposited
        double deposit;
        deposit = Double.valueOf(amountEditText.getText().toString());

        //Creates an expense object from the deopist
        mExpense = new Expense(-1, deposit, "Deposited Into Emergency Fund.");
        //Then stores the expense in the ExpenseDataBase
        mDB.addExpense(mExpense);

        emergencyFundAmount = emergencyFundAmount + deposit;

        //Update model
        mAccount.setEmergencyFundAmount(emergencyFundAmount);

        //Displays the new balance
        emergencyFundAmountTextView.setText(mCurrencyFormat.format(emergencyFundAmount));
    }

    //Withdraws from the fund
    public void withdraw(View v)
    {
        //Grabs the E.F amount
        emergencyFundAmount = Double.valueOf(emergencyFundAmountTextView.getText().toString().substring(1));

        //Grabs how much to withdraw
        double withdrawAmount;
        withdrawAmount = Double.valueOf(amountEditText.getText().toString());

        //calculates new balance
        emergencyFundAmount = emergencyFundAmount - withdrawAmount;

        //update model
        mAccount.setEmergencyFundAmount(emergencyFundAmount);

        //Displays balance
        emergencyFundAmountTextView.setText(mCurrencyFormat.format(emergencyFundAmount));
    }

    //Returns to the main screen
    public void back(View v)
    {
        this.finish();
    }
}
