package edu.miracosta.financialassistant;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;

import edu.miracosta.financialassistant.model.Expense;
import edu.miracosta.financialassistant.model.Expense;

public class ExpenseListAdapter extends ArrayAdapter<Expense> {
    private Context mContext;
    private List<Expense> mExpenses;
    private int mResourceID;

    public ExpenseListAdapter(Context c, int rId, List<Expense> expenses){
        super(c, rId, expenses);
        mContext = c;
        mResourceID = rId;
        mExpenses = expenses;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Expense selectedExpense = mExpenses.get(position);

        LayoutInflater inflater =
                (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceID, null);

        //TODO: Finish the getView method
        LinearLayout ExpensesListLinearLayout =
                view.findViewById(R.id.expenseListLinearLayout);

        TextView ExpenseValueTextView =
                view.findViewById(R.id.expenseValueTextView);
        TextView ExpenseNameTextView =
                view.findViewById(R.id.expenseNameTextView);

        //Set the each text view
        NumberFormat format = NumberFormat.getCurrencyInstance();
        ExpenseValueTextView.setText(format.format(selectedExpense.getExpenseCost()));
        ExpenseNameTextView.setText(selectedExpense.getExpenseName());

        ExpensesListLinearLayout.setTag(selectedExpense);
        return view;
    }
}
