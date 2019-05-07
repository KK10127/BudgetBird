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


public class StudentLoanFund extends AppCompatActivity
{
    private TextView mUserNameTextView;
    private TextView mMonthlyIncomeTextView;
    private TextView mBudgetTextView;
    private TextView mLoanFundAmountTextView;
    private EditText mLoanFundDepositEditText;

    private Intent intent;
    private Account mAccount;
    private double mLoanFundTotal;

    //NumberFormatters
    NumberFormat mCurrencyFormat = NumberFormat.getCurrencyInstance();
    NumberFormat mNumberFormat = NumberFormat.getNumberInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_loan_fund);

        mUserNameTextView = findViewById(R.id.UserNameTextViewSL);
        mMonthlyIncomeTextView = findViewById(R.id.MonthlyIncomeTextViewSL);
        mBudgetTextView = findViewById(R.id.BudgetTextViewSL);
        mLoanFundAmountTextView = findViewById(R.id.MonthlyAmountToPayTextView);
        mLoanFundDepositEditText = findViewById(R.id.LoanFundAmountEditText);

        intent = getIntent();
        mAccount = intent.getParcelableExtra("Account");
        mLoanFundTotal = mAccount.getStudentLoanFund();


        //Placing all the account info into the Text Views
        mUserNameTextView.setText(mAccount.getUserName());
        mMonthlyIncomeTextView.setText(mCurrencyFormat.format(mAccount.getMonthlyIncome()));
        mBudgetTextView.setText(mCurrencyFormat.format(mAccount.getBudget()));
        mLoanFundAmountTextView.setText("$ " + String.valueOf(mAccount.getStudentLoanFund()));
    }

    //Adds a deposit to the fund
    public void addDepositSL(View v)
    {
        mLoanFundTotal = Double.valueOf(mLoanFundAmountTextView.getText().toString().substring(1));

        double deposit;
        deposit = Double.valueOf(mLoanFundDepositEditText.getText().toString());

        mLoanFundTotal = mLoanFundTotal + deposit;

        //Update model
        mAccount.setEmergencyFundAmount(mLoanFundTotal);

        mLoanFundAmountTextView.setText(mCurrencyFormat.format(mLoanFundTotal));
    }

    //Returns to the main screen
    public void backSL(View v)
    {
        this.finish();
    }
}
