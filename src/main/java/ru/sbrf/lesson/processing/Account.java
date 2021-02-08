package ru.sbrf.lesson.processing;

import lombok.Data;
import lombok.Getter;

@Getter
public class Account {
    private int accountId;
    private int balance;

    public Account(int accountId, int balance) {
        this.accountId = accountId;
        this.balance = balance;
    }
//
    public int getAccountId() {
        return accountId;
    }

    public int getBalance() {
        return balance;
    }

}
