package edu.miracosta.financialassistant.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Expenses implements Parcelable
{
    private long mId;
    private String ExpenseDescription;
    private double ExpenseCost;

    public Expenses()
    {
        mId = -1;
        ExpenseDescription = "";
        ExpenseCost = 0;
    }

    public Expenses(long id, double expenseCost, String expenseDescription) {
        this.mId = id;
        this.ExpenseDescription = expenseDescription;
        this.ExpenseCost = expenseCost;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getExpenseDescription() {
        return ExpenseDescription;
    }

    public void setExpenseDescription(String expenseDescription) {
        ExpenseDescription = expenseDescription;
    }

    public double getExpenseCost() {
        return ExpenseCost;
    }

    public void setExpenseCost(Integer expenseCost) {
        ExpenseCost = expenseCost;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }


    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeLong(mId);
        dest.writeString(ExpenseDescription);
        dest.writeDouble(ExpenseCost);
    }

    //Need a mechanism to create a new expense object form a parcel
    private Expenses(Parcel parcel)
    {
        mId = parcel.readLong();
        ExpenseDescription = parcel.readString();
        ExpenseCost = parcel.readDouble();
    }

    public static final Parcelable.Creator<Expenses> CREATOR = new Creator<Expenses>() {
        @Override
        public Expenses createFromParcel(Parcel source) {
            return new Expenses(source);
        }

        @Override
        public Expenses[] newArray(int size) {
            return new Expenses[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
}
