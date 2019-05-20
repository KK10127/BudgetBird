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


    public Income(){
        mId = -1;
        incomeName = "";
        incomeDesc = "";
        incomeValue = 0;
    }

    public Income(long id, String incomeName, double incomeCost){
        mId = id;
        this.incomeName = incomeName;
        incomeValue = incomeCost;
    }

    public Income(double incomeCost, String incomeDescription, String Name){
        incomeDesc = incomeDescription;
        incomeValue = incomeCost;
        incomeName = Name;
    }

    public String getIncomeName() {
        return incomeName;
    }

    public void setIncomeName(String incomeName) {
        this.incomeName = incomeName;
    }

    public double getIncomeValue() {
        return incomeValue;
    }

    public void setIncomeValue(double incomeValue) {
        this.incomeValue = incomeValue;
    }

    public void setId(long id) {
        mId = id;
    }

    public long getId(){
        return mId;
    }

    public String getIncomeDesc() {
        return incomeDesc;
    }

    public void setIncomeDesc(String incomeDesc) {
        this.incomeDesc = incomeDesc;
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeString(incomeName);
        dest.writeDouble(incomeValue);
    }
}
