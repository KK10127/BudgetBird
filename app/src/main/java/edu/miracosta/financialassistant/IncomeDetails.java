package edu.miracosta.financialassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;

import edu.miracosta.financialassistant.database.DBHelper;
import edu.miracosta.financialassistant.model.Income;

public class IncomeDetails extends AppCompatActivity {

    private TextView incomeDetailsTitle;
    private TextView incomeDetailsValue;
    private TextView incomeDetailsDesc;
    private Button incomeDetailsButton;
    private DBHelper db;
    private Income mIncome;

    /**
     * <p>This starts the activity</p>
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_details);

        db = new DBHelper(this);

        //Connect the views and Button
        incomeDetailsTitle = findViewById(R.id.incomeDetailsTitle);
        incomeDetailsValue = findViewById(R.id.incomeDetailsValue);
        incomeDetailsDesc = findViewById(R.id.incomeDetailsDesc);
        incomeDetailsButton = findViewById(R.id.incomeDetailsButton);

        //Get the Income object from the intent
        Intent intent = getIntent();
        mIncome = intent.getParcelableExtra("SelectedIncome");

        //Set the corresponding views
        NumberFormat format = NumberFormat.getCurrencyInstance();

        incomeDetailsTitle.setText(mIncome.getIncomeName());
        incomeDetailsValue.setText(format.format(mIncome.getIncomeValue()));
        incomeDetailsDesc.setText(mIncome.getIncomeDesc());
    }

    /**
     * <p>This removes an income from the income database</p>
     * @param v
     */
    public void removeIncome(View v){
        db.deleteIncome(mIncome);
        Intent intent = new Intent(this, IncomeActivity.class);
        startActivity(intent);
        finish();
    }
}
