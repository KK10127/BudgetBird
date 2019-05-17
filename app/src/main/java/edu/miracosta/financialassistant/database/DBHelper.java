
package edu.miracosta.financialassistant.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import edu.miracosta.financialassistant.model.Expense;

public class DBHelper extends SQLiteOpenHelper
{
    //Define the data base version, name and table name
    public static final String DATABASE_NAME = "BudgetBirdDatabase";
    private static final int DATABASE_VERSION = 1;


    // DEFINE THE FIELDS FOR THE ACCOUNT TABLE
    private static final String ACCOUNT_TABLE = "AccountInfo";
    private static final String ACCOUNT_KEY_FIELD_ID = "_id";
    private static final String FIELD_FIRST_NAME = "first_name";
    private static final String FIELD_LAST_NAME = "last_name";
    private static final String FIELD_EMERGENCY_FUND = "emergency_fund";
    private static final String FIELD_STUDENT_FUND = "student_fund";

    // DEFINE THE MONTHLY EXPENSES TABLE
    private static final String MONTHLY_EXPENSES_TABLE = "MonthlyExpenses";
    private static final String MONTHLY_EXPENSES_KEY_FIELD_ID = "_id";
    private static final String FIELD_EXPENSE_NAME = "expense_name";
    private static final String FIELD_EXPENSE_VALUE = "expense_value";

    // DEFINE THE MONTHLY INCOMES TABLE
    private static final String MONTHLY_INCOMES_TABLE = "MonthlyIncomes";
    private static final String MONTHLY_INCOMES_KEY_FIELD_ID = "_id";
    private static final String FIELD_INCOME_NAME = "income_name";
    private static final String FIELD_INCOME_VALUE = "income_value";

    // DEFINE THE ACTIVITY TABLE
    private static final String ACTIVITY_TABLE = "Activity";
    private static final String ACTIVITY_KEY_FIELD_ID = "_id";
    private static final String FIELD_DATE = "date";
    private static final String FIELD_TOTAL_SPENDING = "total_spending";


    //private static final int DATABASE_VERSION = 1;

    //Define the fields (Column Names) for the table
    private static final String KEY_FIELD_ID = "_id";
    private static final String FIELD_DESCRIPTION= "description";
    private static final String FIELD_COST = "ExpenseCost";

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // Create the account table
        String accountTable = "CREATE TABLE IF NOT EXISTS " + ACCOUNT_TABLE + "("
                + ACCOUNT_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_EMERGENCY_FUND + " REAL, "
                + FIELD_STUDENT_FUND + " REAL "
                + ")";

        // execute the command
        db.execSQL(accountTable);

        // Create the monthly expenses table
        String monthlyExpensesTable = "CREATE TABLE IF NOT EXISTS " + MONTHLY_EXPENSES_TABLE + "("
                + MONTHLY_EXPENSES_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_EXPENSE_NAME + " TEXT, "
                + FIELD_EXPENSE_VALUE + " REAL "
                + ")";

        // execute the command
        db.execSQL(monthlyExpensesTable);

        // Create the monthly incomes table
        String monthlyIncomesTable = "CREATE TABLE IF NOT EXISTS " + MONTHLY_INCOMES_TABLE + "("
                + MONTHLY_INCOMES_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_INCOME_NAME + " TEXT, "
                + FIELD_INCOME_VALUE + " REAL "
                + ")";

        // execute the command
        db.execSQL(monthlyIncomesTable);

        // Create the activity table
        String activityTable = "CREATE TABLE IF NOT EXISTS " + ACTIVITY_TABLE + "("
                + ACTIVITY_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_INCOME_NAME + " TEXT, "
                + FIELD_INCOME_VALUE + " REAL "
                + ")";

        // execute the command
        db.execSQL(activityTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }

    //DataBase operations ADD, UPDATE, EDIT, DELETE

    public void addExpense(Expense expense)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        //Add key-value pair information for the ExpenseDescription
        values.put(FIELD_DESCRIPTION, expense.getExpenseDescription());

        //Add key-value pair information for the expense cost
        values.put(FIELD_COST, expense.getExpenseCost());

        //insert row in the table
        long id = db.insert(DATABASE_TABLE, null, values);

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
                                                cursor.getInt(1),
                                                cursor.getString(2));
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
        db.delete(DATABASE_TABLE, KEY_FIELD_ID + " = ?",
                new String[] {String.valueOf(expense.getId())});
        db.close();
    }

    public void deleteAllExpenses()
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(DATABASE_TABLE, null, null);
        db.close();
    }

    public void updateExpenses(Expense expense)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_DESCRIPTION, expense.getExpenseDescription());
        values.put(FIELD_COST, expense.getExpenseCost());

        db.update(DATABASE_TABLE, values, KEY_FIELD_ID + " = ?",
                new String[] {String.valueOf(expense.getId())});
        db.close();
    }

    public Expense getExpense(int id)
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(DATABASE_TABLE,
                new String[]{KEY_FIELD_ID, FIELD_COST, FIELD_DESCRIPTION},
                KEY_FIELD_ID + " = ?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        Expense expense = null;
        if(cursor != null)
        {
            cursor.moveToFirst();

            expense = new Expense(cursor.getLong(0),
                                   cursor.getInt(1),
                                   cursor.getString(2));

            cursor.close();
        }
        db.close();
        return expense;
    }
}
