package edu.miracosta.financialassistant.model;

import java.util.Date;
import java.util.Objects;

public class SpendingEachDay
{
    private Long mId;
    private Double value;
    private Date date;

    public SpendingEachDay()
    {
        mId = null;
        this.value = null;
        this.date = null;
    }
    public SpendingEachDay(long id, double value, Date date) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpendingEachDay that = (SpendingEachDay) o;
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
        return "SpendingEachDay{" +
                "mId=" + mId +
                ", value=" + value +
                ", date=" + date +
                '}';
    }
}
