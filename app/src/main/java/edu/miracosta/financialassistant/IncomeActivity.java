package edu.miracosta.financialassistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import edu.miracosta.financialassistant.database.DBHelper;
import edu.miracosta.financialassistant.model.Income;

public class IncomeActivity extends AppCompatActivity {

    private DBHelper db;
    private List<Income> allIncomesList;

    private EditText incomeNameEditText;
    private EditText incomeDescriptionEditText;
    private EditText incomeAmountEditText;
    private Button addIncomeButton;
    private IncomeListAdapter mIncomeListAdapter;
    private ListView incomesListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        //Connect the EditTexts, and Button
        incomeNameEditText = findViewById(R.id.expenseNameEditText);
        incomeDescriptionEditText = findViewById(R.id.expenseDescriptionEditText);
        incomeAmountEditText = findViewById(R.id.expenseAmountEditText);
        addIncomeButton = findViewById(R.id.addIncomeButton);

        //create the database
        db = new DBHelper(this);

        //get all incomes from the DB
        allIncomesList = db.getAllIncomes();

        incomesListView = findViewById(R.id.expensesListView);
        mIncomeListAdapter = new IncomeListAdapter(this, R.layout.income_list_item, allIncomesList);
        incomesListView.setAdapter(mIncomeListAdapter);

    }

    public void addIncome(View v){
        String name = incomeNameEditText.getText().toString();
        String desc = incomeDescriptionEditText.getText().toString();
        Double value = Double.parseDouble(incomeAmountEditText.getText().toString());

        Income income = new Income(value, desc, name);
        db.addIncome(income);
        mIncomeListAdapter.add(income);
        mIncomeListAdapter.notifyDataSetChanged();
    }
}
