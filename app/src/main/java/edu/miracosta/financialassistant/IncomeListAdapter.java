package edu.miracosta.financialassistant;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import edu.miracosta.financialassistant.model.Income;
import lecho.lib.hellocharts.model.Line;

public class IncomeListAdapter extends ArrayAdapter<Income> {

    private Context mContext;
    private List<Income> mIncomes;
    private int mResourceID;

    public IncomeListAdapter(Context c, int rId, List<Income> incomes){
        super(c, rId, incomes);
        mContext = c;
        mResourceID = rId;
        mIncomes = incomes;
    }


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
        incomeValueTextView.setText(String.valueOf(selectedIncome.getIncomeValue()));
        incomeNameTextView.setText(selectedIncome.getIncomeName());

        incomesListLinearLayout.setTag(selectedIncome);
        return view;
    }
}
