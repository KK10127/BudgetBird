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
    private double studentFundAmount;
    private Expense mExpense;
    private DBHelper mDB;

    //NumberFormatters
    NumberFormat mCurrencyFormat = NumberFormat.getCurrencyInstance();
    NumberFormat mNumberFormat = NumberFormat.getNumberInstance();

    /**
     * <p>This starts the activity</p>
     * @param savedInstanceState
     */
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

        studentFundAmount = mAccount.getStudentFundAmount();


        //Placing all the account info into the Text Views
        mUserNameTextView.setText(mAccount.getEmail());
        mMonthlyIncomeTextView.setText(mCurrencyFormat.format(mAccount.getMonthlyIncome()));
        mBudgetTextView.setText(mCurrencyFormat.format(mAccount.getBudget()));
        mStudentFundTotalTextView.setText("$ " + String.valueOf(mAccount.getStudentFundAmount()));
    }

    /**
     * <p>Adds a deposit to the fund</p>
     * @param v this connects the method to the onClick attribute
     */
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
    }

    /**
     * <p>This method withdraws from the fund</p>
     * @param v this connects the method to the onClick attribute
     */
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
    }

    /**
     * <p>Returns to the main screen</p>
     * @param v this connects the method to the onClick attribute
     */
    public void backSF(View v)
    {
        this.finish();
    }


    /**
     * <p>Returns to the main screen</p>
     * <p>Not Connected</p>
     */
    @Override
    public void onBackPressed() {
        this.finish();
    }
}
