package edu.miracosta.financialassistant.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Expense implements Parcelable
{
    private long mId;
    private String mExpenseName;
    private String mExpenseDescription;
    private double mExpenseCost;


    public Expense()
    {
        mId = -1;
        mExpenseName = "";
        mExpenseCost = 0;
    }

    public Expense(long id, String expenseDescription, double expenseCost) {
        this.mId = id;
        this.mExpenseName = expenseDescription;
        this.mExpenseCost = expenseCost;
    }

    public Expense(double value, String description, String name){
        mExpenseCost = value;
        mExpenseDescription = description;
        mExpenseName = name;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getExpenseDescription() {
        return mExpenseDescription;
    }

    public void setExpenseDescription(String expenseDescription) {
        mExpenseDescription = expenseDescription;
    }

    public String getExpenseName(){
        return mExpenseName;
    }

    public void setExpenseName(String expenseName)
    {
        mExpenseName = expenseName;
    }

    public double getExpenseCost() {
        return mExpenseCost;
    }

    public void setExpenseCost(double expenseCost) {
        mExpenseCost = expenseCost;
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
        dest.writeString(mExpenseName);
        dest.writeDouble(mExpenseCost);
    }

    //Need a mechanism to create a new expense object form a parcel
    private Expense(Parcel parcel)
    {
        mId = parcel.readLong();
        mExpenseName = parcel.readString();
        mExpenseCost = parcel.readDouble();
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
