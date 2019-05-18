package edu.miracosta.financialassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class IncomeActivity extends AppCompatActivity {

    private EditText incomeNameEditText;
    private EditText incomeDescriptionEditText;
    private EditText incomeAmountEditText;
    private IncomeListAdapter mIncomeListAdapter;
    private Intent mIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        mIntent = getIntent();
    }
}
