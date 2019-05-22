package edu.miracosta.financialassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;

import edu.miracosta.financialassistant.database.DBHelper;
import edu.miracosta.financialassistant.model.Expense;

public class ExpenseDetails extends AppCompatActivity {

    //Variables for the XML layout
    private TextView expenseDetailsTitle;
    private TextView expenseDetailsValue;
    private TextView expenseDetailsDesc;
    private Button expenseDetailsButton;
    private Expense mExpense;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_details);

        db = new DBHelper(this);

        //Connect the views and button
        expenseDetailsTitle = findViewById(R.id.expenseDetailsTitle);
        expenseDetailsValue = findViewById(R.id.expenseDetailsValue);
        expenseDetailsDesc = findViewById(R.id.expenseDetailsDesc);
        expenseDetailsButton = findViewById(R.id.expenseDetailsButton);

        Intent detailsIntent = getIntent();
         mExpense = detailsIntent.getParcelableExtra("SelectedExpense");

        //NumberFormatter for currency
        NumberFormat format = NumberFormat.getCurrencyInstance();

        //setting the views
        expenseDetailsTitle.setText(mExpense.getExpenseName());
        expenseDetailsValue.setText(format.format(mExpense.getExpenseCost()));
        expenseDetailsDesc.setText(mExpense.getExpenseDescription());

    }

    public void removeExpense(View v){
        //TODO: Finish the code for removing an expense
        //TODO: Remove from DB and listAdapter, then return to Expense Activity
        db.deleteExpense(mExpense);
        Intent intent = new Intent(this, ExpensesActivity.class);
        startActivity(intent);
        finish();
    }
}
