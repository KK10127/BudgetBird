package edu.miracosta.financialassistant.model;

import java.util.Objects;

public class Income
{
    private long mid;
    private String incomeName;
    private double incomeValue;

    public Income()
    {
        this.mid = -1;
        this.incomeName = "";
        this.incomeValue = 0.0;    }

    public Income(long mid, String incomeName, double incomeValue) {
        this.mid = mid;
        this.incomeName = incomeName;
        this.incomeValue = incomeValue;
    }

    public long getMid() {
        return mid;
    }

    public void setMid(long mid) {
        this.mid = mid;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Income income = (Income) o;
        return mid == income.mid &&
                Double.compare(income.incomeValue, incomeValue) == 0 &&
                Objects.equals(incomeName, income.incomeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mid, incomeName, incomeValue);
    }

    @Override
    public String toString() {
        return "Income{" +
                "mid=" + mid +
                ", incomeName='" + incomeName + '\'' +
                ", incomeValue=" + incomeValue +
                '}';
    }
}
