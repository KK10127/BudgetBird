package edu.miracosta.financialassistant.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IncomeTest {

    private Income testIncome;

    @Before
    public void setUp() throws Exception {
        testIncome = new Income(2500, "My 2 bedroom apartment", "Rent");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getIncomeName() {
        assertEquals("Testing the getIncomeName() Method.", "Rent", testIncome.getIncomeName());
    }

    @Test
    public void setIncomeName() {
        testIncome.setIncomeName("Rent Rent!");
        assertEquals("Testing the setIncomeName() Method.", "Rent Rent!", testIncome.getIncomeName());
    }

    @Test
    public void getIncomeValue() {
        assertEquals("Testing the getIncomeValue() Method.", String.valueOf(2500), String.valueOf(testIncome.getIncomeValue()));
    }

    @Test
    public void setIncomeValue() {
        testIncome.setIncomeValue(500);
        assertEquals("Testing setIncomeValue()!", String.valueOf(500), String.valueOf(testIncome.getIncomeValue()));
    }

    @Test
    public void setId() {
        testIncome.setId(-1);
        assertEquals("Testing setId()!", String.valueOf(-1), String.valueOf(testIncome.getId()));
    }

    @Test
    public void getId() {
        assertEquals("Testing the getId() Method.", String.valueOf(0) , String.valueOf(testIncome.getId()));

    }

    @Test
    public void getIncomeDesc() {
        assertEquals("Testing the getIncomeDesc() Method.", "My 2 bedroom apartment" , testIncome.getIncomeDesc());

    }


}