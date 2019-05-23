package edu.miracosta.financialassistant;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import edu.miracosta.financialassistant.TipDetailsFolder.BuyingCarTips;
import edu.miracosta.financialassistant.TipDetailsFolder.BuyingHouseTips;
import edu.miracosta.financialassistant.TipDetailsFolder.CreditCardTipsNew;
import edu.miracosta.financialassistant.TipDetailsFolder.CreditCardTipsOld;
import edu.miracosta.financialassistant.TipDetailsFolder.CreditScoreTips;
import edu.miracosta.financialassistant.TipDetailsFolder.InvestmentTips;
import edu.miracosta.financialassistant.TipDetailsFolder.PortfolioTips;
import edu.miracosta.financialassistant.model.Account;

//import edu.miracosta.financialassistant.CreditCard.NewCreditCardUser;


public class FinancialTipsActivity extends AppCompatActivity {
    private ListView mListView;
    private List<String> mStringList;
    private FinancialTipsListAdapter mListAdapter;
    private Intent mIntent;
    private Account mAccount;

    /**
     * <p>This starts the activity</p>
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_tips);

        mStringList = new ArrayList<>();

        mStringList.add("Tips for long time Credit Card Holders");//index=0
        mStringList.add("Tips for new Credit Card Holders");//index=1
        mStringList.add("Tips for Buying a House");//index=2
        mStringList.add("Tips for Buying a Car");//index=3
        mStringList.add("How a Credit Score Works?");//index=4
        mStringList.add("Tips to invest in the StockMarket");//index=5
        mStringList.add("How to make a Financial portfolio?");//index=6

        mListView = findViewById(R.id.list);
        mListAdapter = new FinancialTipsListAdapter(this, R.layout.activity_financial_tips_list_adapter, mStringList);
        mListView.setAdapter(mListAdapter);

        //Dont change this. This works so you can receive the account info
        mIntent = getIntent();
        mAccount = mIntent.getParcelableExtra("Account");

    }

    /**
     *<p>This method figures out which item was selected in the listView and opens up the corresponding activity</p>
     * @param l is the variable associated with the listView
     * @param v this is the variable associated with the view
     * @param position this is used to find what item was selected in the listView
     * @param id this is used to find the id of the item selected.
     */
    /**
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id)
    {
        Intent intent;

        //grabs which item was clicked on the listView
        String selectedTip = mStringList.get(position);

        if(mStringList.get(0).equals(selectedTip))
        {
            intent = new Intent(this, CreditCardTipsOld.class);
            startActivity(intent);
        }
        else if(mStringList.get(1).equals(selectedTip))
        {
            intent = new Intent(this, CreditCardTipsNew.class);
            startActivity(intent);
        }
        else if(mStringList.get(2).equals(selectedTip))
        {
            intent = new Intent(this, BuyingHouseTips.class);
            startActivity(intent);
        }
        else if(mStringList.get(3).equals(selectedTip))
        {
            intent = new Intent(this, BuyingCarTips.class);
            startActivity(intent);
        }
        else if(mStringList.get(4).equals(selectedTip))
        {
            intent = new Intent(this, CreditScoreTips.class);
            startActivity(intent);
        }
        else if(mStringList.get(5).equals(selectedTip))
        {
            intent = new Intent(this, InvestmentTips.class);
            startActivity(intent);
        }
        else if(mStringList.get(6).equals(selectedTip))
        {
            intent = new Intent(this, PortfolioTips.class);
            startActivity(intent);
        }
    }
        **/
    public void goToMap(View v){
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }
}
