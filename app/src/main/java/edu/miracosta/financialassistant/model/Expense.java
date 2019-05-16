package edu.miracosta.financialassistant.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Expense implements Parcelable
{
    private long mId;
    private String expenseName;
    private double ExpenseCost;

    public Expense()
    {
        mId = -1;
        expenseName = "";
        ExpenseCost = 0;
    }

    public Expense(long id, String expenseDescription, double expenseCost) {
        this.mId = id;
        this.expenseName = expenseDescription;
        this.ExpenseCost = expenseCost;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getExpenseDescription() {
        return expenseName;
    }

    public void setExpenseDescription(String expenseDescription) {
        expenseName = expenseDescription;
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
        dest.writeString(expenseName);
        dest.writeDouble(ExpenseCost);
    }

    //Need a mechanism to create a new expense object form a parcel
    private Expense(Parcel parcel)
    {
        mId = parcel.readLong();
        expenseName = parcel.readString();
        ExpenseCost = parcel.readDouble();
    }

    public static final Parcelable.Creator<Expense> CREATOR = new Creator<Expense>() {
        @Override
        public Expense createFromParcel(Parcel source) {
            return new Expense(source);
        }

        @Override
        public Expense[] newArray(int size) {
            return new Expense[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
}
