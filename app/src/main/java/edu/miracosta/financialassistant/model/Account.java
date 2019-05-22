package edu.miracosta.financialassistant.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Account implements Parcelable
{
    private long mId;
    private String mEmail;
    private String mPassword;
    private double mMonthlyIncome;
    private double mBudget;
    private double mEmergencyFundAmount;
    private double mStudentFundAmount;

    public Account(long id, String email, String password, double monthlyIncome, double budget, double emergencyFund, double studentFund) {
        mId = id;
        mEmail = email;
        mPassword = password;
        mMonthlyIncome = monthlyIncome;
        mBudget = budget;
        mEmergencyFundAmount = emergencyFund;
        mStudentFundAmount = studentFund;
    }

    public Account(String email, String password) {
        this(0, email, password, 0.0, 0.0, 0.0, 0.0);
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
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
                ", mPassword=" + mPassword +
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
        dest.writeString(mPassword);
        dest.writeDouble(mMonthlyIncome);
        dest.writeDouble(mBudget);
        dest.writeDouble(mEmergencyFundAmount);
        dest.writeDouble(mStudentFundAmount);
    }

    //Need to make a mechanism to create (Instantiate) a new Account object from a Parcel
    //Private constructor to create a new Account from the parcel
    public Account(Parcel parcel)
    {
        mId = parcel.readLong();
        mEmail = parcel.readString();
        mPassword = parcel.readString();
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
