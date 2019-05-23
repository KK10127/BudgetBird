package edu.miracosta.financialassistant.model;

import java.util.Date;
import java.util.Objects;

public class Trends
{
    private long mId;
    private double value;
    private String date;

    /**
     * <p>Default Constructor
     * instantiates to deafult values</p>
     */
    public Trends()
    {
        mId = 0;
        this.value = 0.0;
        this.date = null;
    }

    /**
     * <p>Main Constructor</p>
     * @param id is the <code>id</code> associated with the Trend
     * @param date is a string representation of the date the trend was created
     * @param value is the value of the expenditure made that date
     */
    public Trends(long id, String date, double value) {
        mId = id;
        this.value = value;
        this.date = date;
    }

    /**
     * <p>Gets the id</p>
     * @return a long that is the <code>id</code> linked to the Trend
     */
    public Long getId() {
        return mId;
    }

    /**
     * <p>Sets the id value from a double passed to the method</p>
     * @param id the value of the id associated with the Trend
     */
    public void setId(Long id) {
        mId = id;
    }

    /**
     * <p>Gets the value of the expenditure</p>
     * @return a double the represents the expenditure made that day
     */
    public Double getValue() {
        return value;
    }

    /**
     * <p>Sets the value of the expenditure</p>
     * @param value the amount of the expenditure made
     */
    public void setValue(Double value) {
        this.value = value;
    }

    /**
     * <p>Gets the date the trend was created</p>
     * @return a string that represent the date the trend was created
     */
    public String getDate() {
        return date;
    }

    /**
     * <p>Sets the date connected to the Trend</p>
     * @param date the string that represents the date the trend was created
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * <p>Compares the calling object to the object in the parameter</p>
     * @param o object to be compared
     * @return true or false, true if the objects match, false if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trends that = (Trends) o;
        return Objects.equals(mId, that.mId) &&
                Objects.equals(value, that.value) &&
                Objects.equals(date, that.date);
    }

    /**
     * <p>converts the Trend data to hashcode</p>
     * @return an int values that represents the Trend Data
     */
    @Override
    public int hashCode() {
        return Objects.hash(mId, value, date);
    }

    /**
     * <p>This returns a <code>Trend</code> object in a string representation</p>
     * @return returns an Trend object as a string
     */
    @Override
    public String toString() {
        return "Trends{" +
                "mId=" + mId +
                ", value=" + value +
                ", date=" + date +
                '}';
    }
}
