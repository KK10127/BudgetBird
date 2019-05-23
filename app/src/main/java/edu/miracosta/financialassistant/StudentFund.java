package edu.miracosta.financialassistant;

import android.content.Intent;
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

public class StudentFund extends AppCompatActivity {
    private TextView mUserNameTextView;
    private TextView mMonthlyIncomeTextView;
    private TextView mBudgetTextView;
    private TextView mStudentFundTotalTextView;
    private EditText mWithdrawDepositEditText;

    private Intent intent;
    private Account mAccount;
    private double studentFundAmount;
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
        mStudentFundTotalTextView = findViewById(R.id.FundTotalTextView);
        mWithdrawDepositEditText = findViewById(R.id.amountEditTextSF);

        mDB = new DBHelper(this);

        //Dont change this.
        intent = getIntent();
        mAccount = intent.getParcelableExtra("Account");

        studentFundAmount = mAccount.getStudentFundAmount();


        //Placing all the account info into the Text Views
        mStudentFundTotalTextView.setText("$ " + String.valueOf(mAccount.getStudentFundAmount()));
    }

    //Adds a deposit to the fund
    public void addDepositSF(View v)
    {
        //Grabs the total fund currently (before the deposit)
        studentFundAmount = Double.valueOf(mStudentFundTotalTextView.getText().toString().substring(1).replaceAll(",", ""));

        //Grabs how much is being deposited
        double deposit;
        deposit = Double.valueOf(mWithdrawDepositEditText.getText().toString());

        //Creates an expense object from the deopist
        //mExpense = new Expense(deposit, "Deposited Into Emergency Fund.", "Emergency Fund");

        //Then stores the expense in the ExpenseDataBase


        double studentFund = mDB.getStudentFund(mAccount.getId());
        studentFund += deposit;
        mDB.setStudentFund(mAccount.getId(), studentFund);

        //emergencyFundAmount = emergencyFundAmount + deposit;

        //Update model
        mAccount.setEmergencyFundAmount(studentFund);

        //Displays the new balance
        mStudentFundTotalTextView.setText(mCurrencyFormat.format(studentFund));

        Toast.makeText(this, "Amount deposited successfully!", Toast.LENGTH_SHORT).show();
    }

    public void withdrawSF(View v)
    {
        //Grabs the sf amount
        studentFundAmount = Double.valueOf(mStudentFundTotalTextView.getText().toString().substring(1).replaceAll(",", ""));

        //Grabs how much to withdraw
        double withdrawAmount;
        withdrawAmount = Double.valueOf(mWithdrawDepositEditText.getText().toString());

        double studentFund = mDB.getEmergencyFund(mAccount.getId());
        studentFund -= withdrawAmount;
        if (studentFund < 0)
            studentFund = 0.0;
        mDB.setStudentFund(mAccount.getId(), studentFund);

        //emergencyFundAmount = emergencyFundAmount + deposit;

        //Update model
        mAccount.setStudentFundAmount(studentFund);

        //Displays the new balance
        mStudentFundTotalTextView.setText(mCurrencyFormat.format(studentFund));

        Toast.makeText(this, "Amount withdrawn successfully!", Toast.LENGTH_SHORT).show();
    }

    //Returns to the main screen
    public void backSF(View v)
    {
        this.finish();
    }


    @Override
    public void onBackPressed() {
        this.finish();
    }
}
