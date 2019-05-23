package edu.miracosta.financialassistant.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.miracosta.financialassistant.model.Account;
import edu.miracosta.financialassistant.model.Expense;
import edu.miracosta.financialassistant.model.Income;
import edu.miracosta.financialassistant.model.Trends;

import static android.support.constraint.Constraints.TAG;

public class DBHelper extends SQLiteOpenHelper
{
    //Define the data base version, name and table name
    public static final String DATABASE_NAME = "BudgetBirdDatabase";
    private static final int DATABASE_VERSION = 1;


    // DEFINE THE FIELDS FOR THE ACCOUNT TABLE
    public static final String ACCOUNT_TABLE = "AccountInfo";
    private static final String ACCOUNT_KEY_FIELD_ID = "_id";
    private static final String FIELD_EMAIL = "email";
    private static final String FIELD_PASSWORD = "password";
    private static final String FIELD_INCOME = "income";
    private static final String FIELD_BUDGET = "budget";
    private static final String FIELD_EMERGENCY_FUND = "emergency_fund";
    private static final String FIELD_STUDENT_FUND = "student_fund";

    // DEFINE THE MONTHLY EXPENSES TABLE
    public static final String MONTHLY_EXPENSES_TABLE = "MonthlyExpenses";
    private static final String MONTHLY_EXPENSES_KEY_FIELD_ID = "_id";
    private static final String FIELD_EXPENSE_NAME = "expense_name";
    private static final String FIELD_EXPENSE_DESCRIPTION = "expense_description";
    private static final String FIELD_EXPENSE_VALUE = "expense_value";

    // DEFINE THE MONTHLY INCOMES TABLE
    public static final String MONTHLY_INCOMES_TABLE = "MonthlyIncomes";
    private static final String MONTHLY_INCOMES_KEY_FIELD_ID = "_id";
    private static final String FIELD_INCOME_NAME = "income_name";
    private static final String FIELD_INCOME_VALUE = "income_value";

    // DEFINE THE TREND TABLE
    public static final String TRENDS_TABLE = "Activity";
    private static final String TRENDS_KEY_FIELD_ID = "_id";
    private static final String FIELD_TREND_DATE = "date";
    private static final String FIELD_TREND_SPENT = "how_much_spent";


    //private static final int DATABASE_VERSION = 1;

    //Define the fields (Column Names) for the table
    private static final String KEY_FIELD_ID = "_id";
    private static final String FIELD_DESCRIPTION= "desctiption";
    //private static final String FIELD_COST = "ExpenseCost";

    /**
     * <p>Contructor for the DB</p>
     * @param context the context the constructor is called in
     */
    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * <p>Creates the databses and tables</p>
     * @param db an SQLite database object
     */
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // Create the account table
        String accountTable = "CREATE TABLE IF NOT EXISTS " + ACCOUNT_TABLE + "("
                + ACCOUNT_KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIELD_EMAIL + " TEXT UNIQUE, "
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
                + MONTHLY_EXPENSES_KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIELD_EXPENSE_NAME + " TEXT UNIQUE, "
                + FIELD_EXPENSE_DESCRIPTION + " TEXT, "
                + FIELD_EXPENSE_VALUE + " REAL "
                + ")";

        // execute the command
        db.execSQL(monthlyExpensesTable);

        // Create the monthly incomes table
        String monthlyIncomesTable = "CREATE TABLE IF NOT EXISTS " + MONTHLY_INCOMES_TABLE + "("
                + MONTHLY_INCOMES_KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIELD_INCOME_NAME + " TEXT UNIQUE, "
                + FIELD_INCOME_VALUE + " REAL "
                + ")";

        // execute the command
        db.execSQL(monthlyIncomesTable);

        // Create the Trends table
        String activityTable = "CREATE TABLE IF NOT EXISTS " + TRENDS_TABLE + "("
                + TRENDS_KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIELD_TREND_DATE + " TEXT, "
                + FIELD_TREND_SPENT + " REAL "
                + ")";

        // execute the command
        db.execSQL(activityTable);
    }

    /**
     * <p>upgrades the database</p>
     * @param db an SQLite database object
     * @param oldVersion int value of the old database version
     * @param newVersion int value of the new database version
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + MONTHLY_EXPENSES_TABLE);
        onCreate(db);
    }

    //DataBase operations ADD, UPDATE, EDIT, DELETE

    /**
     * <p>Adds and expense object to the Expense table</p>
     * @param expense the Expense being added to the table
     */
    public void addExpense(Expense expense)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_EXPENSE_NAME, expense.getExpenseName());

        //Add key-value pair information for the ExpenseDescription
        values.put(FIELD_EXPENSE_DESCRIPTION, expense.getExpenseDescription());

        //Add key-value pair information for the expense cost
        values.put(FIELD_EXPENSE_VALUE, expense.getExpenseCost());

        //insert row in the table
        long id = db.insert(MONTHLY_EXPENSES_TABLE, null, values);

        //Update the expense with the newly assigned id from the database
        expense.setId(id);

        //Close the connection
        db.close();
    }

    /**
     * <p>Gets every item in the Expense table and returns it in a list</p>
     * @return a list containing all the expenses in the table
     */
    public List<Expense> getAllExpenses()
    {
        List<Expense> expensesList = new ArrayList<Expense>();
        SQLiteDatabase database = getReadableDatabase();

        //A cursor is the result of a database query
        Cursor cursor = database.query(MONTHLY_EXPENSES_TABLE, new String[]{MONTHLY_EXPENSES_KEY_FIELD_ID, FIELD_EXPENSE_NAME, FIELD_EXPENSE_VALUE},
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

    public List<Float> getSpendingTrends() {
        List<Float> spendingTrends = new ArrayList<>();
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
                spendingTrends.add((float) cursor.getDouble(2));
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return spendingTrends;
    }


    /**
     * <p>Deletes an expense from the Expense table</p>
     * @param expense the expense to be deleted
     */
    public void deleteExpense(Expense expense)
    {
        SQLiteDatabase db = getWritableDatabase();

        //Delete the table row
        db.delete(MONTHLY_EXPENSES_TABLE, KEY_FIELD_ID + " = ?",
                new String[] {String.valueOf(expense.getId())});
        db.close();
    }

    /**
     * <p>Deletes all Expenses in the Expense Table</p>
     */
    public void deleteAllExpenses()
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(MONTHLY_EXPENSES_TABLE, null, null);
        db.close();
    }

    /**
     * <p>Updates the Expense tables</p>
     * @param expense a new Expense thats added, so requires updating
     */
    public void updateExpenses(Expense expense)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_DESCRIPTION, expense.getExpenseDescription());
        values.put(FIELD_EXPENSE_VALUE, expense.getExpenseCost());

        db.update(MONTHLY_EXPENSES_TABLE, values, KEY_FIELD_ID + " = ?",
                new String[] {String.valueOf(expense.getId())});
        db.close();
    }

    /**
     * <p>Gets an Expense from the Expense table</p>
     * @param id the id used to find the Expense in the table
     * @return the Expense object your looking for
     */
    public Expense getExpense(long id)
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

    /**
     * <p>Gets the Student Fund from the Account table</p>
     * @param id the id used to find the Student fund in the Account table
     * @return the Student fund as a double
     */
    public double getStudentFund(long id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(ACCOUNT_TABLE,
                new String[]{ACCOUNT_KEY_FIELD_ID, FIELD_EMERGENCY_FUND, FIELD_STUDENT_FUND},
                ACCOUNT_KEY_FIELD_ID + " = ?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        double studentFund = -1;
        if(cursor.getCount() > 0)
        {
            cursor.moveToFirst();

            studentFund = cursor.getDouble(2);
            cursor.close();
        }
        db.close();
        return studentFund;
    }

    /**
     * <p>Gets the Emergency Fund from the Account table</p>
     * @param id the id used to find the Emergency fund in the Account table
     * @return the Emergency fund as a double
     */
    public double getEmergencyFund(long id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(ACCOUNT_TABLE,
                new String[]{ACCOUNT_KEY_FIELD_ID, FIELD_EMERGENCY_FUND, FIELD_STUDENT_FUND},
                ACCOUNT_KEY_FIELD_ID + " = ?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        double emergencyFund = -1;
        if(cursor.getCount() > 0)
        {
            cursor.moveToFirst();


            emergencyFund = cursor.getDouble(1);
            cursor.close();
        }
        db.close();
        return emergencyFund;
    }

    /**
     * <p>Sets the Emergency Fund value in the Account Table</p>
     * @param id id used to find the right Account
     * @param value the values to be set
     */
    public void setEmergencyFund(long id, double value) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE AccountInfo SET emergency_fund='"+ value + "' WHERE " +
                "_id='"+ id + "'");
    }

    /**
     * <p>Sets the Student Fund value in the Account Table</p>
     * @param id id used to find the right Account
     * @param value the values to be set
     */
    public void setStudentFund(long id, double value) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE AccountInfo SET student_fund='"+ value + "' WHERE " +
                "_id='"+ id + "'");
        db.close();
    }


    /**
     * <p>Sets the Spending amount for the user</p>
     * @param value the values of the spending amount to be set
     */
    public void setTotalSpendingAmount(double value) {
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("UPDATE Activity SET how_much_spent='"+ value + "' WHERE _id = (SELECT MAX(_id) FROM Activity)");
        db.close();

        /*
        ContentValues values = new ContentValues();
        values.put(FIELD_TREND_SPENT, value);


        String where = "rowid=(SELECT MIN(rowid) FROM " + TRENDS_TABLE + ")";
        db.update(TRENDS_TABLE, values, where, null);
        db.close();
        */
    }

    public long getDaysCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, TRENDS_TABLE);
        db.close();
        return count;
    }



    public double getTodaysSpending() {
        SQLiteDatabase database = getReadableDatabase();
        double todaysSpending = 0;
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

                todaysSpending = cursor.getDouble(2);
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return todaysSpending;
    }


    //Trends database methods

    /**
     * <p>Adds an Trend object to the Trend table</p>
     * @param trend the Trend being added to the table
     */
    public void addTrend(Trends trend)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        //Add key-value pair information for the ExpenseDescription
        values.put(FIELD_TREND_DATE, trend.getDate().toString());

        //Add key-value pair information for the expense cost
        values.put(FIELD_TREND_SPENT, trend.getValue());

        //insert row in the table
        long id = db.insert(TRENDS_TABLE, null, values);

        //Update the expense with the newly assigned id from the database
        trend.setId(id);

        //Close the connection
        db.close();
    }

    /**
     * <p>Gets every item in the Trend table and returns it in a list</p>
     * @return a list containing all the Trends in the table
     */
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

    /**
     * <p>Deletes a Trends from the Trends table</p>
     * @param trend the trend to be deleted
     */
    public void deleteTrend(Trends trend)
    {
        SQLiteDatabase db = getWritableDatabase();

        //Delete the table row
        db.delete(TRENDS_TABLE, TRENDS_KEY_FIELD_ID + " = ?",
                new String[] {String.valueOf(trend.getId())});
        db.close();
    }

    /**
     * <p>Deletes all trends in the Trends Table</p>
     */
    public void deleteAllTrends()
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TRENDS_TABLE, null, null);
        db.close();
    }

    /**
     * <p>Updates the Trends tables</p>
     * @param trend a new Trend that's added, so requires updating
     */
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

    /**
     * <p>Gets a Trend from the Trend table</p>
     * @param id the id used to find the Trend in the table
     * @return the trend object your looking for
     */
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

    /**
     * <p>Adds and income object to the Income table</p>
     * @param income the Income being added to the table
     */
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

    /**
     * <p>Gets every item in the Income table and returns it in a list</p>
     * @return a list containing all the Incomes in the table
     */
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

    /**
     * <p>Deletes an Income from the Income table</p>
     * @param income the income to be deleted
     */
    public void deleteIncome(Income income)
    {
        SQLiteDatabase db = getWritableDatabase();

        //Delete the table row
        db.delete(MONTHLY_INCOMES_TABLE, MONTHLY_INCOMES_KEY_FIELD_ID + " = ?",
                new String[] {String.valueOf(income.getId())});
        db.close();
    }

    /**
     * <p>Deletes all Incomes in the Income Table</p>
     */
    public void deleteAllIncomes()
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(MONTHLY_INCOMES_TABLE, null, null);
        db.close();
    }

    /**
     * <p>Updates the Income tables</p>
     * @param income a new Income thats added, so requires updating
     */
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

    /**
     * <p>Gets an Income from the Income table</p>
     * @param id the id used to find the Income in the table
     * @return the Income object your looking for
     */
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

    /**
     * <p>Adds and Account object to the Account table</p>
     * @param account the Account being added to the table
     */
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

        /*
        BELOW IS FOR THE TRENDS TABLE

         */
        ContentValues values2 = new ContentValues(); // values for the TRENDS table
        values2.put(FIELD_TREND_DATE, "dummy_data");
        values2.put(FIELD_TREND_SPENT, "0.0");
        db.insert(TRENDS_TABLE, null, values2);


        //Close the connection
        db.close();
    }

    /**
     * <p>Creates a new day and stores it in the Trends Table</p>
     */
    public void startNewDay() {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues(); // values to add a new column
        values.put(FIELD_TREND_DATE, "dummy_data");
        values.put(FIELD_TREND_SPENT, "0.0");

        // add a new column into the table
        db.insert(TRENDS_TABLE, null, values);

        db.close();
    }


    /**
     * <p>Gets an Expense from the Expense table</p>
     * @param email the id used to find the Account in the table
     * @param password to check if its a new Account
     * @return the Account object your looking for, or null if not found
     */
    public Account getAccount(String email, String password) {
        Account account = null;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(ACCOUNT_TABLE,
                null,
                FIELD_EMAIL + " = ?",
                new String[]{email}, null, null, null, null);

        if(cursor.getCount() == 1)
        {
            cursor.moveToFirst();
            Long id = cursor.getLong(0);
            String dbPassword = cursor.getString(2);

            if (password.equals(dbPassword)) {
                account = new Account(cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getDouble(3),
                        cursor.getDouble(4),
                        cursor.getDouble(5),
                        cursor.getDouble(6));
            }
        }

        return account;
    }

    /**
     * <p>Gets every item in the Account table and returns it in a list</p>
     * @return a list containing all the Accounts in the table
     */
    public List<Account> getAllAccounts()
    {
        List<Account> accountList = new ArrayList<Account>();
        SQLiteDatabase database = getReadableDatabase();

        //A cursor is the result of a database query
        Cursor cursor = database.query(ACCOUNT_TABLE,
                null,
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
                Account account = new Account(cursor.getInt(0),
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

    /**
     * <p>Deletes all Accounts in the Account Table</p>
     */
    public void deleteAccounts()
    {
        SQLiteDatabase database = getReadableDatabase();

        //A cursor is the result of a database query
        int count = database.delete(ACCOUNT_TABLE, "1", null);

        database.close();
    }

}
