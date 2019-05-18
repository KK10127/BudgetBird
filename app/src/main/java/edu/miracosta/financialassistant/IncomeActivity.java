package edu.miracosta.financialassistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import java.util.List;

import edu.miracosta.financialassistant.database.DBHelper;
import edu.miracosta.financialassistant.model.Income;

public class IncomeActivity extends AppCompatActivity {

    private DBHelper db;
    private List<Income> allIncomesList;

    private EditText incomeNameEditText;
    private EditText incomeDescriptionEditText;
    private EditText incomeAmountEditText;
    private IncomeListAdapter mIncomeListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        db = new DBHelper(this);

    }
}
