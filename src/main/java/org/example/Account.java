package org.example;

import org.example.Utils.DateUtil;

import java.time.Clock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Account implements AccountService{
    private int balance ;
    private Clock clock;


    private List<Transaction> transactions;

    public Account(int balance, Clock clock) {
        this.balance = balance;
        this.clock = clock;
        this.transactions = new ArrayList<>();
    }

    @Override
    public void deposit(int amount) {
        balance += amount;
        transactions.add(new Transaction(amount));
    }

    @Override
    public void withdraw(int amount) {
        balance -= amount;
        transactions.add(new Transaction(-amount));
    }

    @Override
    public StringBuilder printStatement() {
        StringBuilder statement = new StringBuilder("Date || Amount || Balance\n");
        int currentBalance = 0;
        List<String> lines = new ArrayList<>();

        for (Transaction transaction : transactions) {

            currentBalance +=transaction.getAmount();
            String line = String.format("%s || %d || %d",
                    DateUtil.formatDate(transaction.getDate()),
                    transaction.getAmount() ,
                    currentBalance);
            lines.add(line);

        }

        Collections.reverse(lines);
        lines.forEach(line -> statement.append(line).append("\n"));
        statement.setLength(statement.length() - 1);
        return statement;
    }

    public int getBalance() {
        return balance;
    }


    public List<Transaction> getTransactions() {
        return transactions;
    }

    public Clock getClock() {
        return clock;
    }

    public void setClock(Clock clock) {
        this.clock = clock;
    }

}
