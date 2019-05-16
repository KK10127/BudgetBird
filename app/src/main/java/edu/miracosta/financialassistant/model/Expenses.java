package edu.miracosta.financialassistant.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Expenses implements Parcelable
{
    private long mId;
    private String expenseName;
    private double expenseValue;

    public Expenses()
    {
        mId = -1;
        expenseName = "";
        expenseValue = 0;
    }

    public Expenses(long id, double expenseCost, String expenseDescription) {
        this.mId = id;
        this.expenseName = expenseDescription;
        this.expenseValue = expenseCost;
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
        return expenseValue;
    }

    public void setExpenseCost(Integer expenseCost) {
        expenseValue = expenseCost;
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
        dest.writeDouble(expenseValue);
    }

    //Need a mechanism to create a new expense object form a parcel
    private Expenses(Parcel parcel)
    {
        mId = parcel.readLong();
        expenseName = parcel.readString();
        expenseValue = parcel.readDouble();
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
