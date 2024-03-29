package org.example;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    private Account account;
    private Clock fixedClock;
    @BeforeEach
    void setUp(){
        fixedClock = Clock.fixed(Instant.parse("2012-01-10T00:00:00Z"), ZoneOffset.UTC);
        account= new Account(0,fixedClock);
    }
    @Test
    public void depositIncreasesBalance() {
        account.deposit(1000);
        assertEquals(1000, account.getBalance());
        account.deposit(2000);
        assertEquals(3000, account.getBalance());
    }

    @Test
    public void withdrawDecreasesBalance() {
        account.deposit(5000);
        account.withdraw(500);
        assertEquals(4500, account.getBalance());

        account.withdraw(1000);
        assertEquals(3500, account.getBalance());
    }

    @Test
    public void testPrintStatement() {
        account.deposit(1000);
        account.setClock( Clock.offset(fixedClock, Duration.ofDays(3)));
        fixedClock=Clock.offset(fixedClock, Duration.ofDays(3));
        account.deposit(2000);
        account.setClock( Clock.offset(fixedClock, Duration.ofDays(1)));
        account.withdraw(500);

        StringBuilder expectedStatement =new StringBuilder()
                .append("Date || Amount || Balance\n")
                .append("14/01/2012 || -500 || 2500\n")
                .append("13/01/2012 || 2000 || 3000\n")
                .append("10/01/2012 || 1000 || 1000");

        assertEquals(expectedStatement.toString(), account.printStatement().toString());
    }

}