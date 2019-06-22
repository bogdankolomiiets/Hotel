package com.hotel.mariam.entity;

public class Bank {
    private int bankId;
    private String bankFullName;
    private String bankShortName;
    private int bankIdentifierCode;
    private int bankTaxpayerIdentificationNumber;
    private String bankCardNumber;

    public Bank() {
    }

    public Bank(int bankId, String bankFullName, String bankShortName, int bankIdentifierCode, int bankTaxpayerIdentificationNumber, String bankCardNumber) {
        this.bankId = bankId;
        this.bankFullName = bankFullName;
        this.bankShortName = bankShortName;
        this.bankIdentifierCode = bankIdentifierCode;
        this.bankTaxpayerIdentificationNumber = bankTaxpayerIdentificationNumber;
        this.bankCardNumber = bankCardNumber;
    }

    public Bank(String bankFullName, String bankShortName, int bankIdentifierCode, int bankTaxpayerIdentificationNumber, String bankCardNumber) {
        this.bankFullName = bankFullName;
        this.bankShortName = bankShortName;
        this.bankIdentifierCode = bankIdentifierCode;
        this.bankTaxpayerIdentificationNumber = bankTaxpayerIdentificationNumber;
        this.bankCardNumber = bankCardNumber;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public String getBankFullName() {
        return bankFullName;
    }

    public void setBankFullName(String bankFullName) {
        this.bankFullName = bankFullName;
    }

    public String getBankShortName() {
        return bankShortName;
    }

    public void setBankShortName(String bankShortName) {
        this.bankShortName = bankShortName;
    }

    public int getBankIdentifierCode() {
        return bankIdentifierCode;
    }

    public void setBankIdentifierCode(int bankIdentifierCode) {
        this.bankIdentifierCode = bankIdentifierCode;
    }

    public int getBankTaxpayerIdentificationNumber() {
        return bankTaxpayerIdentificationNumber;
    }

    public void setBankTaxpayerIdentificationNumber(int bankTaxpayerIdentificationNumber) {
        this.bankTaxpayerIdentificationNumber = bankTaxpayerIdentificationNumber;
    }

    public String getBankCardNumber() {
        return bankCardNumber;
    }

    public void setBankCardNumber(String bankCardNumber) {
        this.bankCardNumber = bankCardNumber;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "bankId=" + bankId +
                ", bankFullName='" + bankFullName + '\'' +
                ", bankShortName='" + bankShortName + '\'' +
                ", bankIdentifierCode=" + bankIdentifierCode +
                ", bankTaxpayerIdentificationNumber=" + bankTaxpayerIdentificationNumber +
                ", bankCardNumber='" + bankCardNumber + '\'' +
                '}';
    }
}
