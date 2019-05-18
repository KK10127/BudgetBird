package edu.miracosta.financialassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import edu.miracosta.financialassistant.model.Account;

//import edu.miracosta.financialassistant.CreditCard.NewCreditCardUser;

public class FinancialTipsActivity extends AppCompatActivity {
    private ListView mListView;
    private List<String> mStringList;
    private FinancialTipsListAdapter mListAdapter;
    private Intent mIntent;
    private Account mAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_tips);

        //Dont change this. This works so you can receive the account info
        mIntent = getIntent();
        mAccount = mIntent.getParcelableExtra("Account");


        mStringList = new ArrayList<>();

        mStringList.add("Tips for Current Credit Card Holders");
        mStringList.add("Tips for new Credit Card Holders");

        mListView = findViewById(R.id.financeListView);
        mListAdapter = new FinancialTipsListAdapter(this, R.layout.activity_financial_tips_list_adapter, mStringList);
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
