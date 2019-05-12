package edu.miracosta.financialassistant.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import edu.miracosta.financialassistant.model.Expenses;

public class DBHelper extends SQLiteOpenHelper
{
    //Define the data base version, name and table name
    public static final String DATABASE_NAME = "AllExpenses";
    private static final String DATABASE_TABLE = "Expenses";
    private static final int DATABASE_VERSION = 1;

    //Define the fields (Column Names) for the table
    private static final String KEY_FIELD_ID = "_id";
    private static final String FIELD_DESCRIPTION= "desctiption";
    private static final String FIELD_COST = "ExpenseCost";

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String table = "CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE + "("
                + KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_DESCRIPTION + " TEXT, "
                + FIELD_COST + " INTEGER" + ")";

        db.execSQL(table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }

    //DataBase operations ADD, UPDATE, EDIT, DELETE

    public void addExpense(Expenses expense)
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

    public List<Expenses> getAllExpenses()
    {
        List<Expenses> expensesList = new ArrayList<Expenses>();
        SQLiteDatabase database = getReadableDatabase();

        //A cursor is the result of a database query
        Cursor cursor = database.query(DATABASE_TABLE, new String[]{KEY_FIELD_ID, FIELD_COST, FIELD_DESCRIPTION},
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
                Expenses expense = new Expenses(cursor.getLong(0),
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

    public void deleteExpense(Expenses expense)
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

    public void updateExpenses(Expenses expense)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_DESCRIPTION, expense.getExpenseDescription());
        values.put(FIELD_COST, expense.getExpenseCost());

        db.update(DATABASE_TABLE, values, KEY_FIELD_ID + " = ?",
                new String[] {String.valueOf(expense.getId())});
        db.close();
    }

    public Expenses getExpense(int id)
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(DATABASE_TABLE,
                new String[]{KEY_FIELD_ID, FIELD_COST, FIELD_DESCRIPTION},
                KEY_FIELD_ID + " = ?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        Expenses expense = null;
        if(cursor != null)
        {
            cursor.moveToFirst();

            expense = new Expenses(cursor.getLong(0),
                                   cursor.getInt(1),
                                   cursor.getString(2));

            cursor.close();
        }
        db.close();
        return expense;
    }
}
