package edu.miracosta.financialassistant;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

import edu.miracosta.financialassistant.model.Account;

public class UserSettingsActivity extends AppCompatActivity {

    private Intent mIntent;
    private Account mAccount;
    private Button englishButton;
    private Button spanishButton;


    /**
     * <p>This starts the activity</p>
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);

        //Dont change this. This works to receive the account info
        mIntent = getIntent();
        mAccount = mIntent.getParcelableExtra("Account");

        //assign the buttons
        englishButton = findViewById(R.id.englishButton);
        spanishButton = findViewById(R.id.spanishButton);
    }

    public void setLanguageEnglish(View v){
        Locale myLocale = new Locale("en");
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this,MainActivity.class);
        startActivity(refresh);
        finish();
    }

    public void setLanguageSpanish(View v){
        Locale myLocale = new Locale("es");
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this,MainActivity.class);
        startActivity(refresh);
        finish();
    }

}
