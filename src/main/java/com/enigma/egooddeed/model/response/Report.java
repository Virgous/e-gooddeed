package com.enigma.egooddeed.model.response;

import java.util.Date;

public class Report {
    private String financeCode;
    private Date transactionDate;

    private Double money;

    public String getFinanceCode() {
        return financeCode;
    }

    public void setFinanceCode(String financeCode) {
        this.financeCode = financeCode;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
