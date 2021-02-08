package ru.sbrf.lesson.messages;

public class Request {
    private int id;
    private String data;
    private RequestTypes type;
//    private int clientId;
//    private int accountId;
//    private int PIN;

    public Request(int id, String data, RequestTypes type) {
        this.id = id;
        this.data = data;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public RequestTypes getType() {
        return type;
    }
}
