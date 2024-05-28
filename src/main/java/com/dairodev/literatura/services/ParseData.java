package com.dairodev.literatura.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ParseData implements IParseData {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T getData(String json, Class<T> c) {
        try {
            return objectMapper.readValue(json, c);
        } catch (JsonProcessingException ex) {
            throw  new RuntimeException(ex);
        }
    }
}
