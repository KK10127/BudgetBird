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

    /**
     * <p>Main Constructor</p>
     * @param id this is the <code>id</code> of the Account object
     * @param email this is the <code>email</code> associated with the account
     * @param password this is the <code>password</code> associated with the account
     * @param monthlyIncome this is the <code>monthlyIncome</code> associated with the account
     * @param budget this is the <code>budget</code> associated with the account
     * @param emergencyFund this is the <code>emergencyFund</code> associated with the account
     * @param studentFund this is the <code>studentFund</code> associated with the account
     */
    public Account(long id, String email, String password, double monthlyIncome, double budget, double emergencyFund, double studentFund) {
        mId = id;
        mEmail = email;
        mPassword = password;
        mMonthlyIncome = monthlyIncome;
        mBudget = budget;
        mEmergencyFundAmount = emergencyFund;
        mStudentFundAmount = studentFund;
    }

    /**
     * <p>Simple constructor</p>
     * @param email this is the <code>email</code> associated with the account
     * @param password this is the <code>password</code> associated with the account
     * <p>Every other member variable is set to 0</p>
     */
    public Account(String email, String password) {
        this(0, email, password, 0.0, 0.0, 0.0, 0.0);
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
     * <p>Gets the email</p>
     * @return the <code>email</code> as a string
     */
    public String getEmail() {
        return mEmail;
    }

    /**
     * <p>Sets the email</p>
     * @param email this is the string that is passed to set the <code>email</code>
     */
    public void setEmail(String email) {
        mEmail = email;
    }

    /**
     * <p>Gets the password</p>
     * @return the <code>password</code> as a string
     */
    public String getPassword() {
        return mPassword;
    }

    /**
     * <p>Sets the password</p>
     * @param password this is the string that is passed to set the <code>password</code>
     */
    public void setPassword(String password) {
        mPassword = password;
    }

    /**
     * <p>Gets the Monthly Income amount</p>
     * @return the <code>monthlyIncome</code> as a double
     */
    public double getMonthlyIncome() {
        return mMonthlyIncome;
    }

    /**
     * <p>Sets the Monthly Income amount</p>
     * @param monthlyIncome this is the double that is passed to set the <code>monthlyIncome</code>
     */
    public void setMonthlyIncome(double monthlyIncome) {
        mMonthlyIncome = monthlyIncome;
    }

    /**
     * <p>Gets the Budget amount</p>
     * @return the <code>budget</code> as a double
     */
    public double getBudget() {
        return mBudget;
    }

    /**
     * <p>Sets the Budget amount</p>
     * @param budget this is the double that is passed to set the <code>budget</code>
     */
    public void setBudget(double budget) {
        mBudget = budget;
    }

    /**
     * <p>Gets the Emergency Fund amount</p>
     * @return the <code>emergencyFund</code> as a double
     */
    public double getEmergencyFundAmount() {
        return mEmergencyFundAmount;
    }

    /**
     * <p>Sets the Emergency Fund amount</p>
     * @param emergencyFundAmount this is the double that is passed to set the <code>emergencyFund</code>
     */
    public void setEmergencyFundAmount(double emergencyFundAmount) {
        mEmergencyFundAmount = emergencyFundAmount;
    }

    /**
     * <p>Gets the Student Fund amount</p>
     * @return the <code>studentFund</code> as a double
     */
    public double getStudentFundAmount() {
        return mStudentFundAmount;
    }

    /**
     * <p>Sets the Student Fund amount</p>
     * @param studentFundAmount this is the double that is passed to set the <code>studentFund</code>
     */
    public void setStudentFundAmount(double studentFundAmount) {
        mStudentFundAmount = studentFundAmount;
    }

    /**
     * <p>This method compares to Account objects together to see if the equal</p>
     * @param o the object to be compared to the calling object
     * @return a true if the objects match, false if they do not
     */
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

    /**
     * <p>This create a hashcode of the account member variables</p>
     * @return an int representation of the Account data.
     */
    @Override
    public int hashCode() {
        return Objects.hash(mId, mEmail, mMonthlyIncome, mBudget, mEmergencyFundAmount, mStudentFundAmount);
    }

    /**
     * <p>This returns a <code>Account</code> object in a string representation</p>
     * @return returns an Account object as a string
     */
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

    /**
     * <p>Not Used</p>
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

    /**
     * <p>Private constructor to create a new Account from the parcel</p>
     * @param parcel
     */
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
