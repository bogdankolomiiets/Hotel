package com.hotel.mariam.dao;

import com.hotel.mariam.entity.Bank;

public interface BankDAO {
    boolean insertBank(Bank bank);
    boolean deleteBankByBIC(int bankIdentifierCode);
    Bank getBankById(int bankId);
    Bank getBankByBIC(int bankIdentifierCode);
}
