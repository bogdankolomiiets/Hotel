package com.hotel.mariam.entity;

import java.text.DecimalFormat;

public class Payment {
    private int paymentId;
    private int paymentBankId;
    private double paymentAmount;
    private int paymentClientId;
    private int paymentStatus;

    public Payment() {
    }

    public Payment(int paymentId, int paymentBankId, double paymentAmount, int paymentClientId, int paymentStatus) {
        this.paymentId = paymentId;
        this.paymentBankId = paymentBankId;
        this.paymentAmount = paymentAmount;
        this.paymentClientId = paymentClientId;
        this.paymentStatus = paymentStatus;
    }

    public Payment(int paymentBankId, double paymentAmount, int paymentClientId, int paymentStatus) {
        this.paymentBankId = paymentBankId;
        this.paymentAmount = paymentAmount;
        this.paymentClientId = paymentClientId;
        this.paymentStatus = paymentStatus;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getPaymentBankId() {
        return paymentBankId;
    }

    public void setPaymentBankId(int paymentBankId) {
        this.paymentBankId = paymentBankId;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public String getStringPaymentAmount() {
        return new DecimalFormat("0.#").format(paymentAmount);
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public int getPaymentClientId() {
        return paymentClientId;
    }

    public void setPaymentClientId(int paymentClientId) {
        this.paymentClientId = paymentClientId;
    }

    public int getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(int paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", paymentBankId=" + paymentBankId +
                ", paymentAmount=" + paymentAmount +
                ", paymentClientId=" + paymentClientId +
                ", paymentStatus=" + paymentStatus +
                '}';
    }
}
