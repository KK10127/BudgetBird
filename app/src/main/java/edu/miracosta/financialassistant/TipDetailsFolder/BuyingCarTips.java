package edu.miracosta.financialassistant.TipDetailsFolder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.miracosta.financialassistant.R;

public class BuyingCarTips extends AppCompatActivity {

    /**
     * <p>This method starts the activity</p>
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buying_car_tips);

        Intent intent = getIntent();
    }
}
