package ru.sbrf.lesson.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.sbrf.lesson.messages.BalanceRequestData;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface Converter {
   String convert(BalanceRequestData data) throws JAXBException, IOException;
   BalanceRequestData convertToBalanceRequestData(String data) throws IOException, JAXBException;
}
