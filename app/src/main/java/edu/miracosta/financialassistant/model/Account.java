package edu.miracosta.financialassistant.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Account implements Parcelable
{
    private long mId;
    private String mEmail;
    private double mMonthlyIncome;
    private double mBudget;
    private double mEmergencyFundAmount;
    private double mStudentFundAmount;

    public Account(Integer id, String email, Integer monthlyIncome, Integer budget) {
        mId = id;
        mEmail = email;
        mMonthlyIncome = monthlyIncome;
        mBudget = budget;
        mEmergencyFundAmount = 0;
        mStudentFundAmount = 0;
    }

    public Account(String email, Integer monthlyIncome, Integer budget) {
        mEmail = email;
        mMonthlyIncome = monthlyIncome;
        mBudget = budget;
        mEmergencyFundAmount = 0;
        mStudentFundAmount = 0;
    }

    public long getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public double getMonthlyIncome() {
        return mMonthlyIncome;
    }

    public void setMonthlyIncome(double monthlyIncome) {
        mMonthlyIncome = monthlyIncome;
    }

    public double getBudget() {
        return mBudget;
    }

    public void setBudget(double budget) {
        mBudget = budget;
    }

    public double getEmergencyFundAmount() {
        return mEmergencyFundAmount;
    }

    public void setEmergencyFundAmount(double emergencyFundAmount) {
        mEmergencyFundAmount = emergencyFundAmount;
    }

    public double getStudentFundAmount() {
        return mStudentFundAmount;
    }

    public void setStudentFundAmount(double studentFundAmount) {
        mStudentFundAmount = studentFundAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return mId == account.mId &&
                Double.compare(account.mMonthlyIncome, mMonthlyIncome) == 0 &&
                Double.compare(account.mBudget, mBudget) == 0 &&
                Double.compare(account.mEmergencyFundAmount, mEmergencyFundAmount) == 0 &&
                Double.compare(account.mStudentFundAmount, mStudentFundAmount) == 0 &&
                Objects.equals(mEmail, account.mEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mId, mEmail, mMonthlyIncome, mBudget, mEmergencyFundAmount, mStudentFundAmount);
    }

    @Override
    public String toString() {
        return "Account{" +
                "mId=" + mId +
                ", mEmail='" + mEmail + '\'' +
                ", mMonthlyIncome=" + mMonthlyIncome +
                ", mBudget=" + mBudget +
                ", mEmergencyFundAmount=" + mEmergencyFundAmount +
                ", mStudentFundAmount=" + mStudentFundAmount +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeLong(mId);
        dest.writeString(mEmail);
        dest.writeDouble(mMonthlyIncome);
        dest.writeDouble(mBudget);
        dest.writeDouble(mEmergencyFundAmount);
        dest.writeDouble(mStudentFundAmount);
    }

    //Need to make a mechanism to create (Instantiate) a new Account object from a Parcel
    //Private constructor to create a new Account from the parcel
    private Account(Parcel parcel)
    {
        mId = parcel.readInt();
        mEmail = parcel.readString();
        mMonthlyIncome = parcel.readDouble();
        mBudget = parcel.readDouble();
        mEmergencyFundAmount = parcel.readDouble();
        mStudentFundAmount = parcel.readDouble();

    }

    public static final Parcelable.Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel source) {
            return new Account(source);
        }
        /**
         * Allows the creation of an array of account objects from a JSON file.
         * This method repeatedly calls createFromParcel for every object.
         * @param size
         * @return Game array.
         */
        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };
}
