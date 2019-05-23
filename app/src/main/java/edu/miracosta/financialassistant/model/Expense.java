package edu.miracosta.financialassistant.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Expense implements Parcelable
{
    private long mId;
    private String mExpenseName;
    private String mExpenseDescription;
    private double mExpenseCost;


    /**
     * <p>Default constructor
     * instantiate the member variables to default values</p>
     */
    public Expense()
    {
        mId = -1;
        mExpenseName = "";
        mExpenseCost = 0;
    }

    /**
     * <p>Main Constructor</p>
     * @param id this is the <code>id</code> associated with the Expense
     * @param expenseDescription this is the <code>expenseDescription</code> associated with the Expense
     * @param expenseCost this is the <code>expenseCost</code> associated with the Expense
     */
    public Expense(long id, String expenseDescription, double expenseCost) {
        this.mId = id;
        this.mExpenseName = expenseDescription;
        this.mExpenseCost = expenseCost;
    }

    /**
     * <p>Regular Constructor</p>
     * @param value this is the <code>value</code> associated with the Expense
     * @param description this is the <code>description</code> associated with the Expense
     * @param name this is the <code>name</code> associated with the Expense
     */
    public Expense(double value, String description, String name){
        mExpenseCost = value;
        mExpenseDescription = description;
        mExpenseName = name;
    }

    /**
     * <p>Gets the id</p>
     * @return the <code>id</code> as a long
     */
    public long getId() {
        return mId;
    }

    /**
     * <p>Sets the id</p>
     * @param id this is the number that is passed to set the <code>id</code>
     */
    public void setId(long id) {
        mId = id;
    }

    /**
     * <p>Gets the Expense Description</p>
     * @return the <code>expenseDescription</code> as a string
     */
    public String getExpenseDescription() {
        return mExpenseDescription;
    }

    /**
     * <p>Sets the Expense Description</p>
     * @param expenseDescription this is the number that is passed to set the <code>expenseDescription</code>
     */
    public void setExpenseDescription(String expenseDescription) {
        mExpenseDescription = expenseDescription;
    }

    /**
     * <p>Gets the Expense Name</p>
     * @return the <code>expenseName</code> as a string
     */
    public String getExpenseName(){
        return mExpenseName;
    }

    /**
     * <p>Sets the Expense Name</p>
     * @param expenseName this is the number that is passed to set the <code>expenseName</code>
     */
    public void setExpenseName(String expenseName)
    {
        mExpenseName = expenseName;
    }

    /**
     * <p>Gets the Expense Cost</p>
     * @return the <code>expenseCost</code> as a double
     */
    public double getExpenseCost() {
        return mExpenseCost;
    }

    /**
     * <p>Sets the Expense Cost</p>
     * @param expenseCost this is the number that is passed to set the <code>expenseCost</code>
     */
    public void setExpenseCost(double expenseCost) {
        mExpenseCost = expenseCost;
    }

    /**
     * <p>This compares the parameter object to the calling object</p>
     * @param obj this is the object to be compared to the calling object
     * @return a true if the objects data match, false if they do not
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * <p>Creates a string representation of the Expense object</p>
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * <p>This converts the data from the account object to parcel.</p>
     * @param dest
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeLong(mId);
        dest.writeString(mExpenseName);
        dest.writeDouble(mExpenseCost);
    }

    /**
     * <p>Private constructor to create a new Account from the parcel</p>
     * @param parcel
     */
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

    /**
     * <p>Not used</p>
     * @return an int
     */
    @Override
    public int describeContents() {
        return 0;
    }
}
