package edu.miracosta.financialassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import edu.miracosta.financialassistant.database.DBHelper;
import edu.miracosta.financialassistant.model.Expense;

public class ExpensesActivity extends AppCompatActivity {

    private DBHelper db;
    private List<Expense> allExpensesList;

    private EditText expenseNameEditText;
    private EditText expenseDescriptionEditText;
    private EditText expenseAmountEditText;
    private Button addExpenseButton;
    private ExpenseListAdapter mExpenseListAdapter;
    private ListView expensesListView;

    /**
     * <p>This starts the activity</p>
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);

        expenseNameEditText = findViewById(R.id.expenseNameEditText);
        expenseDescriptionEditText = findViewById(R.id.expenseDescriptionEditText);
        expenseAmountEditText = findViewById(R.id.expenseAmountEditText);
        addExpenseButton = findViewById(R.id.addExpenseButton);

        //get all expenses from the DB
        db = new DBHelper(this);

        //loading the list from database
        allExpensesList = db.getAllExpenses();

        Log.i("EXPENSES", allExpensesList.toString());

        expensesListView = findViewById(R.id.expensesListView);
        mExpenseListAdapter = new ExpenseListAdapter(this, R.layout.expense_list_item, allExpensesList);
        expensesListView.setAdapter(mExpenseListAdapter);
    }

    /**
     * <p>This adds an Expense to the database, then displays the expense on the listView</p>
     * @param v this is used to connect the method to the onClick attribute
     */
    public void addExpense(View v){
        String name = expenseNameEditText.getText().toString();
        String desc = expenseDescriptionEditText.getText().toString();
        Double value = Double.parseDouble(expenseAmountEditText.getText().toString());
        Log.i("EXPENSES", allExpensesList.toString());
        Expense expense = new Expense(value, desc, name);
        db.addExpense(expense);
        mExpenseListAdapter.add(expense);
        mExpenseListAdapter.notifyDataSetChanged();
    }

    /**
     * <p>This method takes you to the details of the expense item in the ListView</p>
     * @param v this connects the method to the onClick attribute.
     */
    public void viewExpenseDetails(View v){
        Expense selectedExpense = (Expense) v.getTag();
        Intent detailsIntent = new Intent(this, ExpenseDetails.class);
        detailsIntent.putExtra("SelectedExpense", selectedExpense);
        startActivity(detailsIntent);
        finish();
    }
}
