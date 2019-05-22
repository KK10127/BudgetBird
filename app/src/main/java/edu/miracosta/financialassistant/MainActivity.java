package edu.miracosta.financialassistant;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import edu.miracosta.financialassistant.database.DBHelper;
import edu.miracosta.financialassistant.model.Account;

import static edu.miracosta.financialassistant.database.DBHelper.ACCOUNT_TABLE;
import static edu.miracosta.financialassistant.database.DBHelper.MONTHLY_EXPENSES_TABLE;
import static edu.miracosta.financialassistant.database.DBHelper.MONTHLY_INCOMES_TABLE;
import static edu.miracosta.financialassistant.database.DBHelper.TRENDS_TABLE;

/**
 * The first activity (other than the splash screen) that the user will encounter.
 * Responsible for authenticating users and adding new ones with firebase when possible.
 */

/**
 * TODO: TO FIX:
 *              1)Delete preset text in activity_main.xml for EMAIL and PASSWORD
 *              2)Instead, set these values as HINTS in the EditText
 *              3)Handle exception of an empty email/password
 *              4) ?? Handle exception of wrong password??
 */

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = "FinancialAssistant";

    private ImageView appLogoImageView;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button signUpButton;
    private Account mAccount;
    private DBHelper db;
    private List<Account> mAccountList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // connect the components
        appLogoImageView = findViewById(R.id.appLogoImageView);
        emailEditText = findViewById(R.id.emailEditTextSU);
        passwordEditText = findViewById(R.id.passwordEditTextSU);
        loginButton = findViewById(R.id.logInButton);
    }

    //temporary
    public void logIn(View v)
    {
        mAccountList = new ArrayList<>();
        db = new DBHelper(this);
        //db.onUpgrade(db.getReadableDatabase(), 1, 2);
        //db.onUpgrade(db.getReadableDatabase(), 1,2);

//        db.getWritableDatabase();

//        SQLiteDatabase database = db.getWritableDatabase();
//
//        database.execSQL("DROP TABLE IF EXISTS " + ACCOUNT_TABLE);
//        database.execSQL("DROP TABLE IF EXISTS " + MONTHLY_EXPENSES_TABLE);
//        database.execSQL("DROP TABLE IF EXISTS " + MONTHLY_INCOMES_TABLE);
//        database.execSQL("DROP TABLE IF EXISTS " + TRENDS_TABLE);
//
//        database.close();
//
//        db.deleteAccounts();

        mAccountList = db.getAllAccounts();

        mAccount = db.getAccount(emailEditText.getText().toString(),
                passwordEditText.getText().toString());

        if(mAccount == null)
        {
            mAccount = new Account(emailEditText.getText().toString(), passwordEditText.getText().toString());
            mAccount.setMonthlyIncome(0.0);
            mAccount.setBudget(0.0);
            mAccount.setEmergencyFundAmount(0.0);
            mAccount.setStudentFundAmount(0.0);

            db.addAccount(mAccount);

            Intent intent= new Intent(this, MonthlyOverview.class);
            intent.putExtra("Account",mAccount);
            startActivity(intent);
        }
        else
        {
            Intent intent= new Intent(this, MonthlyOverview.class);
            intent.putExtra("Account",mAccount);
            startActivity(intent);
        }

    }
}
