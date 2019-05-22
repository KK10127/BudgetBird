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

public class StudentFund extends AppCompatActivity {
    private TextView mUserNameTextView;
    private TextView mMonthlyIncomeTextView;
    private TextView mBudgetTextView;
    private TextView mStudentFundTotalTextView;
    private EditText mWithdrawDepositEditText;

    private Intent intent;
    private Account mAccount;
    private double mFundTotal;
    private Expense mExpense;
    private DBHelper mDB;

    //NumberFormatters
    NumberFormat mCurrencyFormat = NumberFormat.getCurrencyInstance();
    NumberFormat mNumberFormat = NumberFormat.getNumberInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_fund);

        mUserNameTextView = findViewById(R.id.UserNameTextView);
        mMonthlyIncomeTextView = findViewById(R.id.MonthlyIncomeTextView);
        mBudgetTextView = findViewById(R.id.BudgetTextView);
        mStudentFundTotalTextView = findViewById(R.id.FundTotalTextView);
        mWithdrawDepositEditText = findViewById(R.id.amountEditTextSF);

        mDB = new DBHelper(this);

        //Dont change this.
        intent = getIntent();
        mAccount = intent.getParcelableExtra("Account");

        mFundTotal = mAccount.getStudentFundAmount();


        //Placing all the account info into the Text Views
        mUserNameTextView.setText(mAccount.getEmail());
        mMonthlyIncomeTextView.setText(mCurrencyFormat.format(mAccount.getMonthlyIncome()));
        mBudgetTextView.setText(mCurrencyFormat.format(mAccount.getBudget()));
        mStudentFundTotalTextView.setText("$ " + String.valueOf(mAccount.getStudentFundAmount()));
    }

    //Adds a deposit to the fund
    public void addDepositSF(View v)
    {
        //Grabs the current total(before the deposit)
        mFundTotal = Double.valueOf(mStudentFundTotalTextView.getText().toString().substring(1).replaceAll(",", ""));

        //Grabs the amount to be deposited into the fund
        double deposit;
        deposit = Double.valueOf(mWithdrawDepositEditText.getText().toString());

        //create an Expense object from data
        mExpense = new Expense(deposit, "Deposited into Student Fund.", "Student Fund");
        //Add the expense to the Expense database
        mDB.addExpense(mExpense);

        //Calculates new total
        mFundTotal = mFundTotal + deposit;

        //Update model
        mAccount.setStudentFundAmount(mFundTotal);

        mStudentFundTotalTextView.setText(mCurrencyFormat.format(mFundTotal));
    }

    public void withdrawSF(View v)
    {
        mFundTotal = Double.valueOf(mStudentFundTotalTextView.getText().toString().substring(1).replaceAll(",", ""));

        double withdrawAmount;
        withdrawAmount = Double.valueOf(mWithdrawDepositEditText.getText().toString());

        //Calculate new balance
        mFundTotal = mFundTotal - withdrawAmount;

        //Updating model
        mAccount.setStudentFundAmount(mFundTotal);

        //Display the balance
        mStudentFundTotalTextView.setText(mCurrencyFormat.format(mFundTotal));
    }

    //Returns to the main screen
    public void backSF(View v)
    {
        this.finish();
    }
}
