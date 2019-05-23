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

    /**
     * <p>This is a regular constructor</p>
     * @param c The context of the current class
     * @param rId the resource Id
     * @param expenses a List of expenses
     */
    public ExpenseListAdapter(Context c, int rId, List<Expense> expenses){
        super(c, rId, expenses);
        mContext = c;
        mResourceID = rId;
        mExpenses = expenses;
    }

    /**
     * <p>This gets the view of how each item will look in the listView</p>
     * @param position this tells us which item was selected in the listView
     * @param convertView this is a view used for converting
     * @param parent This is the view group that this view belongs too
     * @return a <code>View</code> object
     */
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
