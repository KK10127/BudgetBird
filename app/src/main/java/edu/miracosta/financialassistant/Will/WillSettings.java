package edu.miracosta.financialassistant.Will;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import edu.miracosta.financialassistant.R;


public class WillSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_will_settings);
    }

    public void goBack(View v)
    {
        this.finish();
    }
}
