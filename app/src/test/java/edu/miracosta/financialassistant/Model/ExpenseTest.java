package edu.miracosta.financialassistant.Model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.miracosta.financialassistant.model.Expense;

import static org.junit.Assert.*;

public class ExpenseTest
{
    private Expense testExpense;

    @Before
    public void setUp() throws Exception
    {
        testExpense = new Expense(1000.0, "Car Insurance", "Car");
    }

    @After
    public void tearDown() throws Exception
    {

    }

    @Test
    public void getId()
    {
        assertEquals("Testing the getId() Method.",-1, testExpense.getId());
    }

    @Test
    public void setId()
    {
        testExpense.setId(2);
        assertEquals("Testing the setId() Method.", 2, testExpense.getId());
    }

    @Test
    public void getExpenseDescription()
    {
        assertEquals("Testing the getExpenseDescription() Method.", "Car Insurance", testExpense.getExpenseDescription());
    }

    @Test
    public void setExpenseDescription()
    {
        testExpense.setExpenseDescription("Home Insurance");
        assertEquals("Testing the setExpenseDescription() Method.", "Home Insurance", testExpense.getExpenseDescription());
    }

    @Test
    public void getExpenseName()
    {
        assertEquals("Testing the getExpenseName() Method.", "Car", testExpense.getExpenseName());
    }

    @Test
    public void setExpenseName()
    {
        testExpense.setExpenseName("Home");
        assertEquals("Testing the setExpenseName() Method.", "Home", testExpense.getExpenseName());
    }

    @Test
    public void getExpenseCost()
    {
        assertEquals("Testing the getExpenseCost() Method.", String.valueOf(-1), String.valueOf(testExpense.getExpenseCost()));
    }

    @Test
    public void setExpenseCost()
    {
        testExpense.setExpenseCost(10.0);
        assertEquals("Testing the setExpenseCost() Method.", String.valueOf(10.0), String.valueOf(testExpense.getExpenseCost()));
    }
}