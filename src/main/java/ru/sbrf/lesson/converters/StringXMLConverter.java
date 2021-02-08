package ru.sbrf.lesson.converters;

import ru.sbrf.lesson.messages.BalanceRequestData;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class StringXMLConverter implements Converter{

    @Override
    public String convert(BalanceRequestData data) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(data.getClass());
        Marshaller marshaller = context.createMarshaller();
        StringWriter writer = new StringWriter();
        marshaller.marshal(data, writer);

        return writer.toString();
    }

    @Override
    public BalanceRequestData convertToBalanceRequestData(String data) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(BalanceRequestData.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        StringReader reader = new StringReader(data);
        Object  object = unmarshaller.unmarshal(reader);

        return (BalanceRequestData) object;
    }
}
