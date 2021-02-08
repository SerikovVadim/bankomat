package ru.sbrf.lesson;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.sbrf.lesson.converters.FileXLSXConverter;
import ru.sbrf.lesson.converters.JSONConverter;
import ru.sbrf.lesson.converters.StringXMLConverter;
import ru.sbrf.lesson.devices.ATM;
import ru.sbrf.lesson.messages.RequestTypes;
import ru.sbrf.lesson.processing.Host;
import ru.sbrf.lesson.processing.exceptions.BusinessException;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Host host = new Host();
        ATM atm = new ATM(host, new StringXMLConverter());

        try {
            System.out.println(atm.getBalance(1, 0, 123, RequestTypes.XML));
            atm.setConverter(new JSONConverter());
            System.out.println(atm.getBalance(1, 0, 123, RequestTypes.JSON));
            atm.setConverter(new FileXLSXConverter());
            System.out.println(atm.getBalance(1,0,123,RequestTypes.XLSX));
        }catch (JAXBException ex){
            ex.printStackTrace();
            System.exit(2);
        }

        catch (BusinessException ex) { {
//            e.printStackTrace();
            System.out.println("Some error");
            System.exit(1);
        }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            System.exit(3);

//    if(atm.isError(12,2,123)){
//        System.out.println("Some Error");}
//    else {
//        System.out.println(atm.getBalance());
//    }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }}
