package ru.sbrf.lesson.devices;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.sbrf.lesson.converters.Converter;
import ru.sbrf.lesson.messages.BalanceRequestData;
import ru.sbrf.lesson.messages.Request;
import ru.sbrf.lesson.messages.RequestTypes;
import ru.sbrf.lesson.processing.Host;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class ATM {
    private Host host;
    private int requestCounter = +1;
    private Converter converter;

    public ATM(Host host, Converter converter) {
        this.host = host;
        this.converter = converter;
    }

    public void setConverter(Converter converter) {
        this.converter = converter;
    }

    public int getBalance(int clientId, int accountId, int PIN, RequestTypes requestType) throws JAXBException, IOException {
//        requestCounter +=1;
        BalanceRequestData data = new BalanceRequestData(clientId, accountId, PIN);

//        String XMLData = JaxbParser.saveObject(data);  получение строки в хмл
        String XMLData = converter.convert(data);
        return host.getBalance(new Request(requestCounter++,XMLData, requestType)).getBalance();



//        Request request = new Request(clientId, accountId, PIN);        Дубликат кода выше
//        Response response = host.getBalance(request);
//        return response.getBalance();
    }

//    public int getBalance(int clientId, int accountId, int PIN){
////        Request request = new Request(clientId,accountId, PIN); пример написания разными строками, ниже расписано в 1 строку
////        Response response = host.getBalance((request));
////        return response.getBalance();
//        return host.getBalance(new Request(clientId, accountId,PIN)).getBalance();
    }


