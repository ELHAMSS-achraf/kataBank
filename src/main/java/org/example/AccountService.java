package org.example;

public interface AccountService {
    void deposit(int amount);
    void withdraw(int amount);
    StringBuilder printStatement();
}