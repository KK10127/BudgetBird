package edu.miracosta.financialassistant;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * Activity is responsible for displaying the user's monthly overview.
 * This will consist of a pie chart and a few data indicators. This activity is essentially
 * the dashboard of the entire app.
 *
 * The following needs to be accomplished in this activity each time it starts:
 *
 * 1) Upon the activity starting, the user's current monthly budget must be updated by using the
 *      database.
 * 2) Additionally, the users monthly expenses should be retrieved and used with the current month's
 *      budget to update the PieChartView. Details on using the PieChartView are below
 * 2) The user's amount of available emergency funding is also retrieved from the database and the
 *      emergencyFundTextView is updates.
 *
 *
 */
public class MonthlyOverview extends AppCompatActivity {

    // define all interacting components
    private PieChartView pieChartView;
    private TextView budgetTextView;
    private Button todayButton;
    private Button trendsButton;
    private TextView emergencyFundTextView;

    // TODO: More variables may need to be defined

    // TODO: A means of navigation needs to be set up! Some way to get to all of our other activities!
    // TODO: I've been researching navigation views but so far nothing yet.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_overview);

        // wire up necessary components
        pieChartView = findViewById(R.id.pieChartView);
        budgetTextView = findViewById(R.id.budgetTextView);
        todayButton = findViewById(R.id.todayButton);
        trendsButton = findViewById(R.id.trendsButton);
        emergencyFundTextView = findViewById(R.id.emergencyFundTextView);

        // create slices list for the pieData
        List<SliceValue> pieData = new ArrayList<>();

        // test slices
        pieData.add(new SliceValue(15, Color.BLUE));
        pieData.add(new SliceValue(25, Color.GRAY));
        pieData.add(new SliceValue(10, Color.RED));
        pieData.add(new SliceValue(60, Color.MAGENTA));

        // create pieChartData using the list of pie slices
        PieChartData pieChartData = new PieChartData(pieData);
        // set the pieCharData to update the pieChart
        pieChartView.setPieChartData(pieChartData);

        // TODO: gather needed details from the database
        // TODO: The user's monthly budget, monthly expenses, emergency fund amount


        // TODO: update the chart update views accordingly



    }

    /**
     * This method takes users to the 'Today' activity
     * @param v
     */
    public void todayClicked(View v) {
        Intent intent = new Intent(this, TodayActivity.class);
        startActivity(intent);

        // TODO: Decide if any data needs to be sent to TodayActivity.java
        // TODO: Update this block if necessary.

    }

    /**
     * This method takes users to the 'Trends' activity
     * @param v
     */
    public void trendsClicked(View v) {
        Intent intent = new Intent(this, TrendsActivity.class);
        startActivity(intent);

        // TODO: Decide if any data needs to be sent to TrendsActivity.java
        // TODO: Update this block if necessary
    }

}
