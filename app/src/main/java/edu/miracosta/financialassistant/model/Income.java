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
    private double incomeValue;


    public Income(){
        mId = -1;
        incomeName = "";
        incomeValue = 0;
    }

    public Income(long id, String incomeDescription, double incomeCost){
        mId = id;
        incomeName = incomeDescription;
        incomeValue = incomeCost;
    }

    public Income(double incomeCost, String incomeDescription, String Name){
        incomeName = incomeDescription;
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
