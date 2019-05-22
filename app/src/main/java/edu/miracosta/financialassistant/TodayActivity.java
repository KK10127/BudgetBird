package edu.miracosta.financialassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Date;

import edu.miracosta.financialassistant.database.DBHelper;
import edu.miracosta.financialassistant.model.Account;

public class TodayActivity extends AppCompatActivity {

    private Intent mIntent;
    private Account mAccount;
    private double amountToAdd;
    private double totalSpendingAmount;
    LocalDate date;


    private EditText amountEditText;
    private Button addAmountButton;
    private Button newDayButton;
    private TextView spendingTotalTextView;

    NumberFormat format;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today);

        format = NumberFormat.getCurrencyInstance();

        //Dont change this
        mIntent = getIntent();
        mAccount = mIntent.getParcelableExtra("Account");

        amountEditText = findViewById(R.id.amountEditText);
        addAmountButton = findViewById(R.id.addAmountButton);
        newDayButton = findViewById(R.id.newDayButton);

        DBHelper db = new DBHelper(this);

        // get the current day's total spending (e.g. the top most column of the database): its value
        // set to 'amountToAdd'
        totalSpendingAmount = db.getTodaysSpending(mAccount.getId());

        // set the spendingTotalTextView value
        spendingTotalTextView = findViewById(R.id.spendingTotalTextView);
        spendingTotalTextView.setText(format.format(totalSpendingAmount));

        // thats it lol
        db.close();
    }

    /**
     * This method adds an amount to
     */
    public void addAmount(View v) {
        // TODO: Make sure the amount in the amountEditText is not zero or negative
        amountToAdd = Integer.parseInt(amountEditText.getText().toString());
        if(amountToAdd <= 0)
        {
            return;
        }
        else
        {
            // grab the total spending amount and add 'amountToAdd'
            totalSpendingAmount += amountToAdd;

            // place back into the database
            DBHelper db = new DBHelper(this);
            db.setTotalSpendingAmount(mAccount.getId(), totalSpendingAmount);

            // update the view
            spendingTotalTextView.setText(format.format(totalSpendingAmount));
            amountEditText.setText("");

            Toast.makeText(this, "Amount added!", Toast.LENGTH_SHORT).show();

            // done!
            db.close();
        }
    }

    public void startNewDay(View v) {
        // add a new row into the database initialized to a 0.0 value

        // update the view

        // update the model
    }

}
