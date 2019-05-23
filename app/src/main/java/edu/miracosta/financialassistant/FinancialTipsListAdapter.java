package edu.miracosta.financialassistant;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;

import edu.miracosta.financialassistant.model.Expense;

public class FinancialTipsListAdapter extends ArrayAdapter<String>
{
    private Context mContext;
    private List<String> mTips;
    private int mResourceID;

    /**
     * <p>Regular constructor</p>
     * @param context The context of the current class
     * @param resource the resource Id
     * @param objects a List of strings
     */
    public FinancialTipsListAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        mContext = context;
        mTips = objects;
        mResourceID = resource;
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

        String selectedTip = mTips.get(position);

        LayoutInflater inflater =
                (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceID, null);

        //TODO: Finish the getView method
        LinearLayout tipLinearLayout =
                view.findViewById(R.id.tipLinearLayout);

        TextView tipTextView =
                view.findViewById(R.id.tipTextView);


        //Set the each text view
        NumberFormat format = NumberFormat.getCurrencyInstance();

        tipTextView.setText(selectedTip);

        tipLinearLayout.setTag(selectedTip);
        return view;
    }
}
