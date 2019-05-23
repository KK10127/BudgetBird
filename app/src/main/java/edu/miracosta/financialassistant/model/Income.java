package edu.miracosta.financialassistant.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * This class is responsible for the income items that will be added
 * by the user to the income activity.
 *
 * The class implements parcelable, so that the income objects
 * can be passed from activities, and used in the custom list
 */
public class Income implements Parcelable {

    private long mId;
    private String incomeName;
    private String incomeDesc;
    private double incomeValue;


    /**
     * <p>Default Constructor
     * initializes member variables to default values</p>
     */
    public Income(){
        mId = -1;
        incomeName = "";
        incomeDesc = "";
        incomeValue = 0;
    }

    /**
     * <p>Main Constructor</p>
     * @param id this is the <code>id</code> associated with the Income
     * @param incomeName this is the name of the income
     * @param incomeCost this is the income amount
     */
    public Income(long id, String incomeName, double incomeCost){
        mId = id;
        this.incomeName = incomeName;
        incomeValue = incomeCost;
    }

    /**
     * <p>Regular Constructor</p>
     * @param incomeCost this hold income amount
     * @param incomeDescription this describes the where the income is coming from
     * @param Name this is the name of the income
     */
    public Income(double incomeCost, String incomeDescription, String Name){
        incomeDesc = incomeDescription;
        incomeValue = incomeCost;
        incomeName = Name;
    }

    /**
     * <p>Gets the Name of the income</p>
     * @return a string representation of the Name of the income
     */
    public String getIncomeName() {
        return incomeName;
    }

    /**
     * <p>This sets the incomes name from a string passed to the method</p>
     * @param incomeName The name of the Income
     */
    public void setIncomeName(String incomeName) {
        this.incomeName = incomeName;
    }

    /**
     * <p>Gets the amount of the income</p>
     * @return a double that represents the incomes value
     */
    public double getIncomeValue() {
        return incomeValue;
    }

    /**
     * <p>Sets the incomes value from a double passed to the method</p>
     * @param incomeValue the value of the income
     */
    public void setIncomeValue(double incomeValue) {
        this.incomeValue = incomeValue;
    }

    /**
     * <p>Sets the id value from a double passed to the method</p>
     * @param id the value of the id associated with the income
     */
    public void setId(long id) {
        mId = id;
    }

    /**
     * <p>Gets the id of the income</p>
     * @return a long that represents the incomes id
     */
    public long getId(){
        return mId;
    }

    /**
     * <p>Gets the description of the income</p>
     * @return a string that represents the incomes description
     */
    public String getIncomeDesc() {
        return incomeDesc;
    }

    /**
     * <p>Sets the description from a string passed to the method</p>
     * @param incomeDesc the description of the income
     */
    public void setIncomeDesc(String incomeDesc) {
        this.incomeDesc = incomeDesc;
    }

    /**
     * <p>Private constructor to create a new Account from the parcel</p>
     * @param in
     */
    protected Income(Parcel in) {
        mId = in.readLong();
        incomeName = in.readString();
        incomeValue = in.readDouble();
    }

    public static final Creator<Income> CREATOR = new Creator<Income>() {
        @Override
        public Income createFromParcel(Parcel in) {
            return new Income(in);
        }

        @Override
        public Income[] newArray(int size) {
            return new Income[size];
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

    /**
     * <p>This converts the data from the account object to parcel.</p>
     * @param dest
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeString(incomeName);
        dest.writeDouble(incomeValue);
    }
}
