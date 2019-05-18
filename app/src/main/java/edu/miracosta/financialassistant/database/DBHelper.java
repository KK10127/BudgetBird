package edu.miracosta.financialassistant.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import edu.miracosta.financialassistant.model.Account;
import edu.miracosta.financialassistant.model.Expense;
import edu.miracosta.financialassistant.model.Income;
import edu.miracosta.financialassistant.model.Trends;

public class DBHelper extends SQLiteOpenHelper
{
    //Define the data base version, name and table name
    public static final String DATABASE_NAME = "BudgetBirdDatabase";
    private static final int DATABASE_VERSION = 1;


    // DEFINE THE FIELDS FOR THE ACCOUNT TABLE
    private static final String ACCOUNT_TABLE = "AccountInfo";
    private static final String ACCOUNT_KEY_FIELD_ID = "_id";
    private static final String FIELD_EMAIL = "email";
    private static final String FIELD_PASSWORD = "password";
    private static final String FIELD_INCOME = "income";
    private static final String FIELD_BUDGET = "budget";
    private static final String FIELD_EMERGENCY_FUND = "emergency_fund";
    private static final String FIELD_STUDENT_FUND = "student_fund";

    // DEFINE THE MONTHLY EXPENSES TABLE
    private static final String MONTHLY_EXPENSES_TABLE = "MonthlyExpenses";
    private static final String MONTHLY_EXPENSES_KEY_FIELD_ID = "_id";
    private static final String FIELD_EXPENSE_NAME = "expense_name";
    private static final String FIELD_EXPENSE_DESCRIPTION = "expense_description";
    private static final String FIELD_EXPENSE_VALUE = "expense_value";

    // DEFINE THE MONTHLY INCOMES TABLE
    private static final String MONTHLY_INCOMES_TABLE = "MonthlyIncomes";
    private static final String MONTHLY_INCOMES_KEY_FIELD_ID = "_id";
    private static final String FIELD_INCOME_NAME = "income_name";
    private static final String FIELD_INCOME_VALUE = "income_value";

    // DEFINE THE TREND TABLE
    private static final String TRENDS_TABLE = "Activity";
    private static final String TRENDS_KEY_FIELD_ID = "_id";
    private static final String FIELD_TREND_DATE = "date";
    private static final String FIELD_TREND_SPENT = "how_much_spent";


    //private static final int DATABASE_VERSION = 1;

    //Define the fields (Column Names) for the table
    private static final String KEY_FIELD_ID = "_id";
    private static final String FIELD_DESCRIPTION= "desctiption";
    private static final String FIELD_COST = "ExpenseCost";

    //Contructor for the DB
    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // Create the account table
        String accountTable = "CREATE TABLE IF NOT EXISTS " + ACCOUNT_TABLE + "("
                + ACCOUNT_KEY_FIELD_ID + " REAL PRIMARY KEY, "
                + FIELD_EMAIL + " TEXT, "
                + FIELD_PASSWORD + " TEXT, "
                + FIELD_INCOME + " REAL, "
                + FIELD_BUDGET + " TEXT, "
                + FIELD_EMERGENCY_FUND + " REAL, "
                + FIELD_STUDENT_FUND + " REAL "
                + ")";

        // execute the command
        db.execSQL(accountTable);

        // Create the monthly expenses table
        String monthlyExpensesTable = "CREATE TABLE IF NOT EXISTS " + MONTHLY_EXPENSES_TABLE + "("
                + MONTHLY_EXPENSES_KEY_FIELD_ID + " REAL PRIMARY KEY, "
                + FIELD_EXPENSE_NAME + " TEXT, "
                + FIELD_EXPENSE_DESCRIPTION + " TEXT, "
                + FIELD_EXPENSE_VALUE + " REAL "
                + ")";

        // execute the command
        db.execSQL(monthlyExpensesTable);

        // Create the monthly incomes table
        String monthlyIncomesTable = "CREATE TABLE IF NOT EXISTS " + MONTHLY_INCOMES_TABLE + "("
                + MONTHLY_INCOMES_KEY_FIELD_ID + " REAL PRIMARY KEY, "
                + FIELD_INCOME_NAME + " TEXT, "
                + FIELD_INCOME_VALUE + " REAL "
                + ")";

        // execute the command
        db.execSQL(monthlyIncomesTable);

        // Create the Trends table
        String activityTable = "CREATE TABLE IF NOT EXISTS " + TRENDS_TABLE + "("
                + TRENDS_KEY_FIELD_ID + " REAL PRIMARY KEY, "
                + FIELD_TREND_DATE + " TEXT, "
                + FIELD_TREND_SPENT + " REAL "
                + ")";

        // execute the command
        db.execSQL(activityTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + MONTHLY_EXPENSES_TABLE);
        onCreate(db);
    }

    //DataBase operations ADD, UPDATE, EDIT, DELETE

    public void addExpense(Expense expense)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_EXPENSE_NAME, expense.getExpenseName());

        //Add key-value pair information for the ExpenseDescription
        values.put(FIELD_EXPENSE_DESCRIPTION, expense.getExpenseDescription());

        //Add key-value pair information for the expense cost
        values.put(FIELD_COST, expense.getExpenseCost());

        //insert row in the table
        long id = db.insert(MONTHLY_EXPENSES_TABLE, null, values);

        //Update the expense with the newly assigned id from the database
        expense.setId(id);

        //Close the connection
        db.close();
    }

    public List<Expense> getAllExpenses()
    {
        List<Expense> expensesList = new ArrayList<Expense>();
        SQLiteDatabase database = getReadableDatabase();

        //A cursor is the result of a database query
        Cursor cursor = database.query(MONTHLY_EXPENSES_TABLE, new String[]{KEY_FIELD_ID, FIELD_EXPENSE_NAME, FIELD_EXPENSE_VALUE},
                null,
                null,
                null,
                null,
                null,
                null);

        //Collect each row in the table
        if(cursor.moveToFirst())
        {
            do
            {
                Expense expense = new Expense(cursor.getLong(0),
                                                cursor.getString(1),
                                                cursor.getDouble(2));
                expensesList.add(expense);
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return expensesList;
    }



    public void deleteExpense(Expense expense)
    {
        SQLiteDatabase db = getWritableDatabase();

        //Delete the table row
        db.delete(MONTHLY_EXPENSES_TABLE, KEY_FIELD_ID + " = ?",
                new String[] {String.valueOf(expense.getId())});
        db.close();
    }

    public void deleteAllExpenses()
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(MONTHLY_EXPENSES_TABLE, null, null);
        db.close();
    }

    public void updateExpenses(Expense expense)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_DESCRIPTION, expense.getExpenseDescription());
        values.put(FIELD_COST, expense.getExpenseCost());

        db.update(MONTHLY_EXPENSES_TABLE, values, KEY_FIELD_ID + " = ?",
                new String[] {String.valueOf(expense.getId())});
        db.close();
    }

    public Expense getExpense(int id)
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(MONTHLY_EXPENSES_TABLE,
                new String[]{KEY_FIELD_ID, FIELD_EXPENSE_NAME, FIELD_EXPENSE_VALUE},
                KEY_FIELD_ID + " = ?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        Expense expense = null;
        if(cursor != null)
        {
            cursor.moveToFirst();

            expense = new Expense(cursor.getLong(0),
                                   cursor.getString(1),
                                   cursor.getDouble(2));

            cursor.close();
        }
        db.close();
        return expense;
    }

    // TODO: THIS METHOD IS BROKEN        // TODO: THIS METHOD IS BROKEN
    // TODO: THIS METHOD IS BROKEN        // TODO: THIS METHOD IS BROKEN
    // TODO: THIS METHOD IS BROKEN        // TODO: THIS METHOD IS BROKEN        // TODO: THIS METHOD IS BROKEN        // TODO: THIS METHOD IS BROKEN        // TODO: THIS METHOD IS BROKEN


    //Trends database methods
    public void addTrend(Trends trend)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        //Add key-value pair information for the ExpenseDescription
        values.put(FIELD_TREND_DATE, trend.getDate().toString());

        //Add key-value pair information for the expense cost
        values.put(FIELD_COST, trend.getValue());

        //insert row in the table
        long id = db.insert(TRENDS_TABLE, null, values);

        //Update the expense with the newly assigned id from the database
        trend.setId(id);

        //Close the connection
        db.close();
    }

    public List<Trends> getAllTrends()
    {
        List<Trends> trendsList = new ArrayList<Trends>();
        SQLiteDatabase database = getReadableDatabase();

        //A cursor is the result of a database query
        Cursor cursor = database.query(TRENDS_TABLE, new String[]{TRENDS_KEY_FIELD_ID, FIELD_TREND_DATE, FIELD_TREND_SPENT},
                null,
                null,
                null,
                null,
                null,
                null);

        //Collect each row in the table
        if(cursor.moveToFirst())
        {
            do
            {
                Trends trend = new Trends(cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getDouble(2));
                trendsList.add(trend);
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return trendsList;
    }

    public void deleteTrend(Trends trend)
    {
        SQLiteDatabase db = getWritableDatabase();

        //Delete the table row
        db.delete(TRENDS_TABLE, TRENDS_KEY_FIELD_ID + " = ?",
                new String[] {String.valueOf(trend.getId())});
        db.close();
    }

    public void deleteAllTrends()
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TRENDS_TABLE, null, null);
        db.close();
    }

    public void updateTrend(Trends trend)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_TREND_DATE, trend.getDate());
        values.put(FIELD_TREND_SPENT, trend.getValue());

        db.update(TRENDS_TABLE, values, TRENDS_KEY_FIELD_ID + " = ?",
                new String[] {String.valueOf(trend.getId())});
        db.close();
    }

    public Trends getTrend(int id)
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TRENDS_TABLE,
                new String[]{TRENDS_KEY_FIELD_ID, FIELD_TREND_DATE, FIELD_TREND_SPENT},
                TRENDS_KEY_FIELD_ID + " = ?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        Trends trend = null;
        if(cursor != null)
        {
            cursor.moveToFirst();

            trend = new Trends(cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getDouble(2));

            cursor.close();
        }
        db.close();
        return trend;
    }

    //Income database methods

    public void addIncome(Income income)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        //Add key-value pair information for the ExpenseDescription
        values.put(FIELD_INCOME_NAME, income.getIncomeName());

        //Add key-value pair information for the expense cost
        values.put(FIELD_INCOME_VALUE, income.getIncomeValue());

        //insert row in the table
        long id = db.insert(MONTHLY_INCOMES_TABLE, null, values);

        //Update the expense with the newly assigned id from the database
        income.setId(id);

        //Close the connection
        db.close();
    }

    public List<Income> getAllIncomes()
    {
        List<Income> incomesList = new ArrayList<Income>();
        SQLiteDatabase database = getReadableDatabase();

        //A cursor is the result of a database query
        Cursor cursor = database.query(MONTHLY_INCOMES_TABLE, new String[]{MONTHLY_INCOMES_KEY_FIELD_ID, FIELD_INCOME_NAME, FIELD_INCOME_VALUE},
                null,
                null,
                null,
                null,
                null,
                null);

        //Collect each row in the table
        if(cursor.moveToFirst())
        {
            do
            {
                Income income = new Income(cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getDouble(2));

                incomesList.add(income);
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return incomesList;
    }

    public void deleteIncome(Income income)
    {
        SQLiteDatabase db = getWritableDatabase();

        //Delete the table row
        db.delete(MONTHLY_INCOMES_TABLE, MONTHLY_INCOMES_KEY_FIELD_ID + " = ?",
                new String[] {String.valueOf(income.getId())});
        db.close();
    }

    public void deleteAllIncomes()
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(MONTHLY_INCOMES_TABLE, null, null);
        db.close();
    }

    public void updateIncome(Income income)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_INCOME_NAME, income.getIncomeName());
        values.put(FIELD_INCOME_VALUE, income.getIncomeValue());

        db.update(MONTHLY_INCOMES_TABLE, values, MONTHLY_INCOMES_KEY_FIELD_ID + " = ?",
                new String[] {String.valueOf(income.getId())});
        db.close();
    }

    public Income getIncome(int id)
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TRENDS_TABLE,
                new String[]{TRENDS_KEY_FIELD_ID, FIELD_TREND_DATE, FIELD_TREND_SPENT},
                TRENDS_KEY_FIELD_ID + " = ?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        Income income = null;
        if(cursor != null)
        {
            cursor.moveToFirst();

            income = new Income(cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getDouble(2));

            cursor.close();
        }
        db.close();
        return income;
    }

    public void addAccount(Account account)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        //Add key-value pair information for the ExpenseDescription
        values.put(FIELD_EMAIL, account.getEmail());

        //Adds password field
        values.put(FIELD_PASSWORD, account.getPassword());

        values.put(FIELD_BUDGET, account.getBudget());

        //Add key-value pair information for the expense cost
        values.put(FIELD_EMERGENCY_FUND, account.getEmergencyFundAmount());

        //Adds a student fund value to the table
        values.put(FIELD_STUDENT_FUND, account.getStudentFundAmount());

        //insert row in the table
        long id = db.insert(ACCOUNT_TABLE, null, values);

        //Update the expense with the newly assigned id from the database
        account.setId(id);

        //Close the connection
        db.close();
    }

    public List<Account> getAllAccounts()
    {
        List<Account> accountList = new ArrayList<Account>();
        SQLiteDatabase database = getReadableDatabase();

        //A cursor is the result of a database query
        Cursor cursor = database.query(ACCOUNT_TABLE, new String[]{ACCOUNT_KEY_FIELD_ID, FIELD_EMAIL, FIELD_PASSWORD, FIELD_INCOME, FIELD_BUDGET, FIELD_EMERGENCY_FUND, FIELD_STUDENT_FUND},
                null,
                null,
                null,
                null,
                null,
                null);

        //Collect each row in the table
        if(cursor.moveToFirst())
        {
            do
            {
                Account account = new Account(cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getDouble(3),
                        cursor.getDouble(4),
                        cursor.getDouble(5),
                        cursor.getDouble(6));

                accountList.add(account);
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return accountList;
    }


}
