package edu.miracosta.financialassistant.model;

import java.util.Deque;

public class PastTrends
{
    private Deque<SpendingEachDay> mSpendingDeque;

    public PastTrends(Deque<SpendingEachDay> spendingDeque) {
        spendingDeque = new Deque<SpendingEachDay>()
        mSpendingDeque = spendingDeque;
    }
}
