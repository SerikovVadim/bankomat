package ru.sbrf.lesson.processing;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private int clientId;
    private int PIN;
    private List<Account> account = new ArrayList<>();

    public Client(int clientId, int PIN, Account account) {
        this.clientId = clientId;
        this.PIN = PIN;
        this.account.add(account);
    }

    public int getClientId() {
        return clientId;
    }

    public int getPIN() {
        return PIN;
    }

    public Account getAccount(int accountId) {
        return account.get(accountId);
    }
}
