package edu.miracosta.financialassistant;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import edu.miracosta.financialassistant.database.DBHelper;
import edu.miracosta.financialassistant.model.Expense;
import edu.miracosta.financialassistant.model.Income;
import edu.miracosta.financialassistant.model.Account;
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
    private TextView emergencyFundTextView;
    private TextView studentFundTextView;

    private Toolbar toolBar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    // variables pertaining to the database
    private double emergencyFund, studentFund;
    private double incomeTotal, expenseTotal;
    private double budgetGap;
    private Intent mIntent;
    private Account mAccount;

    // for use with handling incoming database values
    private List<Expense> allExpenses;
    private List<Income> allIncomes;

    //
    private static int EXPENSE_COLOR = Color.rgb(255, 80, 130);
    private static int EMERGENCY_COLOR = Color.rgb(223, 174, 193);
    private static int STUDENT_COLOR = Color.rgb(165, 225, 228);
    private static int BUDGET_COLOR = Color.rgb(165,228,176);

    /**
     *<p>This method starts the app from where it was put onPause()</p>
     */
    @Override
    public void onResume(){
        super.onResume();
        // put your code here...

        NumberFormat format = NumberFormat.getCurrencyInstance();

        // wire up necessary components
        pieChartView = findViewById(R.id.pieChartView);
        budgetTextView = findViewById(R.id.budgetTextView);
        emergencyFundTextView = findViewById(R.id.emergencyFundTextView);
        studentFundTextView = findViewById(R.id.studentFundTextView);

        //intent stuff
        mIntent = getIntent();
        mAccount = mIntent.getParcelableExtra("Account");


        emergencyFundTextView.setText(String.valueOf(mAccount.getEmergencyFundAmount()));

        // TODO: gather needed details from the database
        // TODO: The user's monthly budget, monthly expenses, emergency fund amount
        DBHelper db = new DBHelper(this);

        allExpenses = db.getAllExpenses();
        allIncomes = db.getAllIncomes();
        studentFund = db.getStudentFund(mAccount.getId());
        emergencyFund = db.getEmergencyFund(mAccount.getId());

        // DONE: Total up the expenses/incomes
        expenseTotal = 0;
        incomeTotal = 0;
        for (Expense item : allExpenses) {
            expenseTotal += item.getExpenseCost();
        }
        for(Income item : allIncomes) {
            incomeTotal += item.getIncomeValue();
        }

        // create slices list for the pieData
        List<SliceValue> pieData = new ArrayList<>();

        // creating slices
        SliceValue expensesSlice = new SliceValue((float) expenseTotal, EXPENSE_COLOR);
        SliceValue studentSlice = new SliceValue((float) studentFund, STUDENT_COLOR);
        SliceValue emergencySlice = new SliceValue((float) emergencyFund, EMERGENCY_COLOR);

        if (studentFund != 0)
            studentSlice.setLabel("Student Fund: " + format.format(studentFund));
        else
            studentSlice.setLabel("");

        expensesSlice.setLabel("Expenses: " + format.format(expenseTotal));

        if (emergencyFund != 0)
            emergencySlice.setLabel("Emergency Fund: " + format.format(emergencyFund));
        else
            emergencySlice.setLabel("");

        pieData.add(expensesSlice);
        pieData.add(studentSlice);
        pieData.add(emergencySlice);

        // calculate the budget gap
        budgetGap = incomeTotal - expenseTotal;
        if (budgetGap > 0) {
            SliceValue budgetSlice = new SliceValue((float) budgetGap, BUDGET_COLOR);
            budgetSlice.setLabel("Budget: " + format.format(budgetGap));
            pieData.add(budgetSlice);
        }

        // create pieChartData using the list of pie slices
        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true);
        pieChartData.setHasLabelsOutside(false);
        pieChartData.setHasCenterCircle(true);
        pieChartView.setInteractive(false);

        // set the pieCharData to update the pieChartView
        pieChartView.setPieChartData(pieChartData);

        // update the rest of the views
        budgetTextView.setText(format.format(budgetGap));
        emergencyFundTextView.setText(format.format(emergencyFund));
        studentFundTextView.setText(format.format(studentFund));

        toolBar = findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.navigationView);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.emergency_fund:
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        mIntent = new Intent(MonthlyOverview.this, EmergencyFund.class);
                        mIntent.putExtra("Account", mAccount);
                        startActivity(mIntent);
                        break;
                    case R.id.expenses:
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        mIntent = new Intent(MonthlyOverview.this, ExpensesActivity.class);
                        mIntent.putExtra("Account", mAccount);
                        startActivity(mIntent);
                        break;
                    case R.id.financial_tips:
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        mIntent = new Intent(MonthlyOverview.this, FinancialTipsActivity.class);
                        mIntent.putExtra("Account", mAccount);
                        startActivity(mIntent);
                        break;
                    case R.id.income:
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        mIntent = new Intent(MonthlyOverview.this, IncomeActivity.class);
                        mIntent.putExtra("Account", mAccount);
                        startActivity(mIntent);
                        break;
                    case R.id.user_settings:
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        mIntent = new Intent(MonthlyOverview.this, UserSettingsActivity.class);
                        mIntent.putExtra("Account", mAccount);
                        startActivity(mIntent);
                        break;
                    case R.id.student_fund:
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        mIntent = new Intent(MonthlyOverview.this, StudentFund.class);
                        mIntent.putExtra("Account", mAccount);
                        startActivity(mIntent);
                        break;
                    case R.id.today:
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        mIntent = new Intent(MonthlyOverview.this, TodayActivity.class);
                        mIntent.putExtra("Account", mAccount);
                        startActivity(mIntent);
                        break;
                    case R.id.trends:
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        mIntent = new Intent(MonthlyOverview.this, TrendsActivity.class);
                        mIntent.putExtra("Account", mAccount);
                        startActivity(mIntent);
                        break;
                    default:
                        break;
                }

                return true;
            }
        });

    }

    /**
     * <p>This starts the activity</p>
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monthly_overview);

    }

    /**
     * <p>This method controls when to open the drawer and brings the drawer to the front of everything else</p>
     * @param item this is the item selected from the drawer
     * @return a <code>Boolean</code> value either; true or false.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                mDrawerLayout.bringToFront();
                mDrawerLayout.invalidate();
                return true;
        }


        return super.onOptionsItemSelected(item);
    }

}
