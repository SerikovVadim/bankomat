package ru.sbrf.lesson.messages;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BalanceRequestData {
    @XmlElement
    private int clientId;
    @XmlElement
    private int accountId;
    @XmlElement(name = "pin")
    private int PIN;

    public BalanceRequestData() {
    }

    public BalanceRequestData(int clientId, int accountId, int PIN) {
        this.clientId = clientId;
        this.accountId = accountId;
        this.PIN = PIN;
    }

    public int getClientId() {
        return clientId;
    }

    public int getAccountId() {
        return accountId;
    }

    public int getPIN() {
        return PIN;
    }

}
