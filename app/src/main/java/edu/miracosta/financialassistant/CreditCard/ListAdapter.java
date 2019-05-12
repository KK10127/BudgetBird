package edu.miracosta.financialassistant.CreditCard;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

import edu.miracosta.financialassistant.R;

public class ListAdapter extends ArrayAdapter<String>
{
    private Context mContext;
    private int mResourceID;
    private List<String> mStringList;

    public ListAdapter(Context context, int resource, List<String> choices) {
        super(context, resource, choices);
        mContext = context;
        mResourceID = resource;
        mStringList = choices;
    }

    public View getView(int pos, View convertView, ViewGroup parent)
    {
        final String selectedString = mStringList.get(pos);

        LayoutInflater inflater =
                (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(mResourceID, null);

        TableRow tableRow = (TableRow) view.findViewById(R.id.tableRow);
        ImageView logoImageView = (ImageView) view.findViewById(R.id.logoImageView);
        TextView description = (TextView) view.findViewById(R.id.optionDescriptionTextView);

        description.setText(selectedString);

        return view;
    }
}
