package edu.miracosta.financialassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

//import edu.miracosta.financialassistant.CreditCard.NewCreditCardUser;

public class FinancialTipsActivity extends AppCompatActivity {
    private ListView mListView;
    private List<String> mStringList;
    private ListAdapter mListAdapter;
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.good_cc_usage);

        mIntent = getIntent();
        mIntent.getParcelableExtra("Account");

        mListView = findViewById(R.id.ccListView);

        mStringList.add("Tips for Current Credit Card Holders");
        mStringList.add("Tips for new Credit Card Holders");

        //mListAdapter = new ListAdapter(this, R.layout.tip_item, mStringList);
        mListView.setAdapter(mListAdapter);

    }

    public void viewOptionSelected(View v)
    {
        String selectedString = (String) v.getTag();

        if(mStringList.get(0) == selectedString)
        {
            //Intent intent = new Intent(this, CurrentlyACardHolder.class);
            //startActivity(intent);
        }
        else if(mStringList.get(1) == selectedString)
        {
            //Intent intent = new Intent(this, NewCreditCardUser.class);
            //startActivity(intent);
        }
    }
}
