package com.alura.literAlura.shared.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertData {
    public static <T> T convert(String json, Class<T> clase){
        ObjectMapper mapper = new ObjectMapper();
        try{
            return mapper.readValue(json, clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
