package edu.miracosta.financialassistant.Model;

import org.junit.After;
import org.junit.Before;

import org.junit.Test;

import edu.miracosta.financialassistant.model.Account;

import static org.junit.Assert.*;

public class AccountTest
{
    private Account testAccount;
    @Before
    public void setUp() throws Exception
    {
        testAccount = new Account(1, "imurphy92064@gmail.com", "bobs", 1000.0, 500.0, 150.0, 50.0);
    }

    @After
    public void tearDown() throws Exception
    {

    }
    @Test
    public void getId()
    {
        assertEquals("Testing the getId() Method.", 1, testAccount.getId());
    }

    @Test
    public void setId()
    {
        testAccount.setId(2);
        assertEquals("Testing the setId() Method.", 2, testAccount.getId());
    }

    @Test
    public void getEmail()
    {
        assertEquals("Testing the getEmail() Method.", "imurphy92064@gmail.com", testAccount.getEmail());
    }

    @Test
    public void setEmail()
    {
        testAccount.setEmail("pstephen.murphy@gmail.com");
        assertEquals("Testing the setEmail() Method.", "pstephen.murphy@gmail.com", testAccount.getEmail());
    }

    @Test
    public void getPassword()
    {
        assertEquals("Testing the getPassword Method.", "bobs", testAccount.getPassword());
    }

    @Test
    public void setPassword()
    {
        testAccount.setPassword("bobs1");
        assertEquals("Testing setPassword() Method.", "bobs1", testAccount.getPassword());
    }

    @Test
    public void getMonthlyIncome()
    {
        assertEquals("Testing the getMonthlyIncome() Method.", String.valueOf(1000.0), String.valueOf(testAccount.getMonthlyIncome()));
    }

    @Test
    public void setMonthlyIncome()
    {
        testAccount.setMonthlyIncome(1200.0);
        assertEquals("Testing the setMonthlyIncome() Method.", String.valueOf(1200.0), String.valueOf(testAccount.getMonthlyIncome()));
    }

    @Test
    public void getBudget()
    {
        assertEquals("Testing the getBudget() Method.", String.valueOf(500.0), String.valueOf(testAccount.getBudget()));
    }

    @Test
    public void setBudget()
    {
        testAccount.setBudget(700.0);
        assertEquals("Testing the setBudget() Method.", String.valueOf(700.0), String.valueOf(testAccount.getBudget()));
    }

    @Test
    public void getEmergencyFundAmount()
    {
        assertEquals("Testing the getEmergencyFundAmount() Method.", String.valueOf(150.0), String.valueOf(testAccount.getEmergencyFundAmount()));
    }

    @Test
    public void setEmergencyFundAmount()
    {
        testAccount.setEmergencyFundAmount(350.0);
        assertEquals("Testing the setEmergencyFundAmount() Method.", String.valueOf(350.0), String.valueOf(testAccount.getEmergencyFundAmount()));
    }

    @Test
    public void getStudentFundAmount()
    {
        assertEquals("Testing the getStudentFundAmount() Method.", String.valueOf(50.0), String.valueOf(testAccount.getStudentFundAmount()));
    }

    @Test
    public void setStudentFundAmount()
    {
        testAccount.setStudentFundAmount(250.0);
        assertEquals("Testing the setStudentFundAmount() Method.", String.valueOf(250.0), String.valueOf(testAccount.getStudentFundAmount()));
    }
}