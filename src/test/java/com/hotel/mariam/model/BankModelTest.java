package com.hotel.mariam.model;

import com.hotel.mariam.dao.BankDAO;
import com.hotel.mariam.entity.Bank;
import org.junit.*;

public class BankModelTest extends Assert {
    public static Bank testBank;
    public static BankDAO bankDAO;

    @BeforeClass
    public static void init(){
        testBank = new Bank("bankFullName", "bankShortName", 1, 111, "bankCardNumber");
        bankDAO = new BankModel();
    }

    @AfterClass
    public static void destroy(){
        bankDAO.deleteBankByBIC(testBank.getBankIdentifierCode());
        testBank = null;
        bankDAO = null;
    }

    @Before
    public void deleteBank(){
        bankDAO.deleteBankByBIC(testBank.getBankIdentifierCode());
    }

    @Test
    public void insertBankTest(){
        assertTrue(bankDAO.insertBank(testBank));
    }

    @Test
    public void insertNullBankTest(){
        assertFalse(bankDAO.insertBank(null));
    }

    @Test
    public void deleteBankByBICTest(){
        bankDAO.insertBank(testBank);
        assertNotNull(bankDAO.getBankByBIC(testBank.getBankIdentifierCode()));
    }

    @Test
    public void getBankByIdTest(){
        bankDAO.insertBank(testBank);
        Bank bank = bankDAO.getBankByBIC(testBank.getBankIdentifierCode());
        assertNotNull(bank);
        assertNotNull(bankDAO.getBankById(bank.getBankId()));
        assertNull(bank = bankDAO.getBankByBIC(0));
    }

    @Test
    public void getBankByBICTest(){
        bankDAO.insertBank(testBank);
        assertNotNull(bankDAO.getBankByBIC(testBank.getBankIdentifierCode()));
        assertNull(bankDAO.getBankByBIC(0));
    }
}
