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
    private double mStudentLoanFund;

    public Account(Integer id, String userName, String passWord, Integer monthlyIncome, Integer budget) {
        mId = id;
        mUserName = userName;
        mPassWord = passWord;
        mMonthlyIncome = monthlyIncome;
        mBudget = budget;
        mEmergencyFundAmount = 0;
        mStudentLoanFund = 0;
    }

    public Account(String userName, String passWord, Integer monthlyIncome, Integer budget) {
        mUserName = userName;
        mPassWord = passWord;
        mMonthlyIncome = monthlyIncome;
        mBudget = budget;
        mEmergencyFundAmount = 0;
        mStudentLoanFund = 0;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getPassWord() {
        return mPassWord;
    }

    public void setPassWord(String passWord) {
        mPassWord = passWord;
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

    public double getStudentLoanFund() {
        return mStudentLoanFund;
    }

    public void setStudentLoanFund(double studentLoanFund) {
        mStudentLoanFund = studentLoanFund;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(mId, account.mId) &&
                Objects.equals(mUserName, account.mUserName) &&
                Objects.equals(mPassWord, account.mPassWord) &&
                Objects.equals(mMonthlyIncome, account.mMonthlyIncome) &&
                Objects.equals(mBudget, account.mBudget);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mId, mUserName, mPassWord, mMonthlyIncome, mBudget);
    }

    @Override
    public String toString() {
        return "Account{" +
                "mId=" + mId +
                ", mUserName='" + mUserName + '\'' +
                ", mPassWord='" + mPassWord + '\'' +
                ", mMonthlyIncome=" + mMonthlyIncome +
                ", mBudget=" + mBudget +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(mId);
        dest.writeString(mUserName);
        dest.writeString(mPassWord);
        dest.writeDouble(mMonthlyIncome);
        dest.writeDouble(mBudget);
        dest.writeDouble(mEmergencyFundAmount);
        dest.writeDouble(mStudentLoanFund);
    }

    //Need to make a mechanism to create (Instantiate) a new Account object from a Parcel
    //Private constructor to create a new Account from the parcel
    private Account(Parcel parcel)
    {
        mId = parcel.readInt();
        mUserName = parcel.readString();
        mPassWord = parcel.readString();
        mMonthlyIncome = parcel.readDouble();
        mBudget = parcel.readDouble();
        mEmergencyFundAmount = parcel.readDouble();
        mStudentLoanFund = parcel.readDouble();

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
