package ru.sbrf.lesson.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import ru.sbrf.lesson.messages.BalanceRequestData;

import javax.xml.bind.JAXBException;

public class JSONConverter implements Converter {
    private ObjectMapper mapper;

    public JSONConverter() {
        mapper = new ObjectMapper();
        AnnotationIntrospector introspector
                = new JaxbAnnotationIntrospector();
        mapper.setAnnotationIntrospector(introspector);
    }

    @Override
    public String convert(BalanceRequestData data) throws JAXBException, JsonProcessingException {
        return mapper.writeValueAsString(data);
    }

    @Override
    public BalanceRequestData convertToBalanceRequestData(String data) throws JsonProcessingException {
        return mapper.readValue(data, BalanceRequestData.class);
    }

}
