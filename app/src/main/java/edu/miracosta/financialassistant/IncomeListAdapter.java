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

import edu.miracosta.financialassistant.model.Income;
import lecho.lib.hellocharts.model.Line;

public class IncomeListAdapter extends ArrayAdapter<Income> {

    private Context mContext;
    private List<Income> mIncomes;
    private int mResourceID;

    /**
     * <p>Regular constructor</p>
     * @param c
     * @param rId
     * @param incomes
     */
    public IncomeListAdapter(Context c, int rId, List<Income> incomes){
        super(c, rId, incomes);
        mContext = c;
        mResourceID = rId;
        mIncomes = incomes;
    }

    /**
     * <p>This the the view that populates the listView</p>
     * @param position this grabs the item index of the selected item in the listView
     * @param convertView this view is used to convert
     * @param parent this is the parent of the view.
     * @return a <code>View</code> object
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Income selectedIncome = mIncomes.get(position);

        LayoutInflater inflater =
                (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceID, null);

        //TODO: Finish the getView method
        LinearLayout incomesListLinearLayout =
                view.findViewById(R.id.incomeListLinearLayout);

        TextView incomeValueTextView =
                view.findViewById(R.id.incomeValueTextView);
        TextView incomeNameTextView =
                view.findViewById(R.id.incomeNameTextView);

        //Set the each text view
        NumberFormat format = NumberFormat.getCurrencyInstance();
        incomeValueTextView.setText(format.format(selectedIncome.getIncomeValue()));
        incomeNameTextView.setText(selectedIncome.getIncomeName());

        incomesListLinearLayout.setTag(selectedIncome);
        return view;
    }
}
