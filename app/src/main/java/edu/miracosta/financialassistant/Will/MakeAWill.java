package edu.miracosta.financialassistant.Will;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.NumberFormat;

import edu.miracosta.financialassistant.R;
import edu.miracosta.financialassistant.model.Account;

public class MakeAWill extends AppCompatActivity
{
    private EditText mEstablishWillEditText;
    private EditText mWifeEditText;
    private EditText mChildrenEditText;
    private EditText mAssestEditText;

    private Account mAccount;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_awill);

        mEstablishWillEditText = findViewById(R.id.EstablishWilleditText);
        mWifeEditText = findViewById(R.id.nameOfWifeEditText);
        mChildrenEditText = findViewById(R.id.nameOfChildrenEditText);
        mAssestEditText = findViewById(R.id.AssetsEditText);

        intent = getIntent();
        mAccount = intent.getParcelableExtra("Account");
    }

    public void createWill(View v)
    {
        Intent intent = new Intent(this, CreateWill.class);
        intent.putExtra("EstablishWill", mEstablishWillEditText.getText().toString());
        intent.putExtra("WifeName", mWifeEditText.getText().toString());
        intent.putExtra("ChildrenName", mChildrenEditText.getText().toString());
        intent.putExtra("Assets", mAssestEditText.getText().toString());
        startActivity(intent);
    }

    public void help(View v)
    {
        Intent intent = new Intent(this, WillSettings.class);
        startActivity(intent);
    }
}
