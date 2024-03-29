package org.example;

import java.time.Clock;
import java.util.Date;

public class Transaction {
    private int amount ;
    private Date date ;

    public Transaction(int amount, Clock clock ) {
        this.amount = amount;
        this.date = Date.from(clock.instant());

    }

    public Transaction(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
