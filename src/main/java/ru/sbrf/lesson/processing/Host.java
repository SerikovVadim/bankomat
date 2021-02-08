package ru.sbrf.lesson.processing;
import com.fasterxml.jackson.core.JsonProcessingException;
import ru.sbrf.lesson.converters.Converter;
import ru.sbrf.lesson.converters.FileXLSXConverter;
import ru.sbrf.lesson.converters.JSONConverter;
import ru.sbrf.lesson.converters.StringXMLConverter;
import ru.sbrf.lesson.messages.BalanceRequestData;
import ru.sbrf.lesson.messages.Request;
import ru.sbrf.lesson.messages.RequestTypes;
import ru.sbrf.lesson.messages.Response;
import ru.sbrf.lesson.processing.exceptions.AccountIdNotFoundException;
import ru.sbrf.lesson.processing.exceptions.ClientIdNotFoundException;
import ru.sbrf.lesson.processing.exceptions.DuplicateRequestException;
import ru.sbrf.lesson.processing.exceptions.IncorrectPINException;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Host {
    private Map<Integer,Client> client = new HashMap <>();
    private Set <Integer> requestIDs = new HashSet<>();


    public Host(){ this(1,0,123,6);
    }


    public Host(int clientId, int accountId, int PIN, int balance){
        Account account = new Account(accountId, balance);
        client.put(clientId, new Client(clientId, PIN, account ));

    }
    public Response getBalance(Request request) throws JAXBException, IOException {

        if(!requestIDs.add(request.getId())){
            throw new DuplicateRequestException("Duplicate request");
        }

        System.out.println("request.getData() = " + request.getData());

        BalanceRequestData data;
        if(request.getType() == RequestTypes.XML) {
//           data = (BalanceRequestData) JaxbParser.getObject(request.getData(), BalanceRequestData.class);

            Converter converter = new StringXMLConverter();
            data = converter.convertToBalanceRequestData(request.getData());

        } else if ( request.getType() == RequestTypes.JSON){
            Converter converter = new JSONConverter();
            data = converter.convertToBalanceRequestData( request.getData());
        } else if( request.getType() == RequestTypes.XLSX){
            Converter converter = new FileXLSXConverter();
            data = converter.convertToBalanceRequestData(request.getData());

        } else {return null;}

        Client localClient = (client.get(data.getClientId()));
        Account localAccount = localClient.getAccount(data.getAccountId());

        if(data.getClientId()!=localClient.getClientId())
            throw new ClientIdNotFoundException("Клиент не найдет");

        if(data.getPIN()!=localClient.getPIN())
            throw new IncorrectPINException("Пин не найдет");

        if(data.getAccountId()!= localAccount.getAccountId())
            throw new AccountIdNotFoundException("Счет не найдет");

        return new Response(localAccount.getBalance());
    }
//        if(request.getClientId() == client.getClientId()){
//            if(request.getPIN() == client.getPIN()){
//              if(request.getAccountId() == client.getAccount().getAccountId()) {
//                  return new Response(false, client.getAccount().getBalance());
//              }
//            }
//        }
//        return new Response(true, "");
//    }
}
