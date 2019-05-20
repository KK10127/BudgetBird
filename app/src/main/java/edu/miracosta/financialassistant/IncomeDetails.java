package edu.miracosta.financialassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;

import edu.miracosta.financialassistant.model.Income;

public class IncomeDetails extends AppCompatActivity {

    private TextView incomeDetailsTitle;
    private TextView incomeDetailsValue;
    private TextView incomeDetailsDesc;
    private Button incomeDetailsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_details);

        //Connect the views and Button
        incomeDetailsTitle = findViewById(R.id.incomeDetailsTitle);
        incomeDetailsValue = findViewById(R.id.incomeDetailsValue);
        incomeDetailsDesc = findViewById(R.id.incomeDetailsDesc);
        incomeDetailsButton = findViewById(R.id.incomeDetailsButton);

        //Get the Income object from the intent
        Intent intent = getIntent();
        Income income = intent.getParcelableExtra("SelectedIncome");

        //Set the corresponding views
        NumberFormat format = NumberFormat.getCurrencyInstance();

        incomeDetailsTitle.setText(income.getIncomeName());
        incomeDetailsValue.setText(format.format(income.getIncomeValue()));
        incomeDetailsDesc.setText(income.getIncomeDesc());
    }

    public void removeIncome(){
        //TODO: Finish the code for removing and income object
    }
}
