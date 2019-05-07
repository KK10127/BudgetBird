package edu.miracosta.financialassistant.Will;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import edu.miracosta.financialassistant.R;


public class CreateWill extends AppCompatActivity
{
    Intent mIntent;
    private String mEstablishWill;
    private String mWifeName;
    private String mChildName;
    private String mAssets;
    private String mBeneficiaries;
    private String mInstructions;
    private String mWill;

    private TextView willTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_will);

        mIntent = getIntent();
        mEstablishWill = mIntent.getStringExtra("EstablishWill");
        mWifeName = mIntent.getStringExtra("WifeName");
        mChildName = mIntent.getStringExtra("ChildrenName");
        mAssets = mIntent.getStringExtra("Assets");

        mWill = willCreator(mEstablishWill, mWifeName, mChildName, mAssets);

        willTextView = findViewById(R.id.WilltextView);

        willTextView.setText(mWill);
    }

    public String willCreator(String establishWill, String wifeName, String childName, String assets)
    {
        return "LAST WILL AND TESTAMENT" + "\n"
                + "OF" + "\n"
                + establishWill + "\n"
                + "I " + establishWill + " revoke my former Wills and Codicils and declare this to be my" +
                " Last Will and Testament" + "\n"
                + "ARTICLE I" + "\n"
                + "IDENTIFICATION OF FAMILY" + "\n"
                + "I am married to " + wifeName + " and all references in this Will to 'my spouse' are" +
                " references to " + wifeName + "\n"
                + "The names of my children are " + childName + " all references in this Will to 'my children'" +
                " are references to the above-names children." + "\n"
                + "ARTICLE II" + "\n"
                + "PAYMENT OF DEBTS AND EXPENSES" + "\n"
                + "I direct my just debts, funeral expenses, and expenses of last illness be first paid from my assets" + "\n"
                + "ARTICLE III" + "\n"
                + "DISPOSITION OF PROPERTY" + "\n"
                + "I direct that my residuary estate be distributed to my spouse, " + wifeName + "." + " If my" +
                " spouse does not survive me, my residuary estate shall be distributed to my children in equal shares."
                + " If a child of mine does not survive me, such deceased child's share shall be distributed in equal shares to the children"
                + " of such deceased child who survive me, by right of representation. If a child of mine does not survive me" +
                " and has no children who survive, such deceased child's share shall" +
                " be distributed in equal shares to my other children, if any, or to their respective children" +
                " by right of representation. If no child of mine survives me, and if none of my deceased children survive" +
                " by children, my residuary estates shall be distributed to my heirs-at-law, their identities" +
                " and respective shares to be determined under the laws of the state, then in effect, as if I had" +
                " died interstate at the time for distribution under this provision.";
    }
}
