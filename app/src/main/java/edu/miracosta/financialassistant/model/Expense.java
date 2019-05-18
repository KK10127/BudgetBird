package edu.miracosta.financialassistant.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Expense implements Parcelable
{
    private long mId;
    private String expenseName;
    private double expenseCost;

    public Expense()
    {
        mId = -1;
        expenseName = "";
        expenseCost = 0;
    }

    public Expense(long id, String expenseDescription, double expenseCost) {
        this.mId = id;
        this.expenseName = expenseDescription;
        this.expenseCost = expenseCost;
    }

    public Expense(String expenseDescription, double expenseCost){
        expenseName = expenseDescription;
        this.expenseCost = expenseCost;
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
        return expenseCost;
    }

    public void setExpenseCost(Integer expenseCost) {
        expenseCost = expenseCost;
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
        dest.writeDouble(expenseCost);
    }

    //Need a mechanism to create a new expense object form a parcel
    private Expense(Parcel parcel)
    {
        mId = parcel.readLong();
        expenseName = parcel.readString();
        expenseCost = parcel.readDouble();
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
