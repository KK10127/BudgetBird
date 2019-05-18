package edu.miracosta.financialassistant.model;

import java.util.Date;
import java.util.Objects;

public class Trends
{
    private long mId;
    private double value;
    private String date;

    public Trends()
    {
        mId = 0;
        this.value = 0.0;
        this.date = null;
    }
    public Trends(long id, String date, double value) {
        mId = id;
        this.value = value;
        this.date = date;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trends that = (Trends) o;
        return Objects.equals(mId, that.mId) &&
                Objects.equals(value, that.value) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mId, value, date);
    }

    @Override
    public String toString() {
        return "Trends{" +
                "mId=" + mId +
                ", value=" + value +
                ", date=" + date +
                '}';
    }
}
