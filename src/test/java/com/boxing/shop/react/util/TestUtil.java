package com.boxing.shop.react.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;

public class TestUtil {
    public static Object leerJSONasDTO(String path, Class dtoClass) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(new File(path), dtoClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<Object> leerJSONasDTOList(String path, Class dtoClass) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(new File(path), mapper.getTypeFactory().constructCollectionType(List.class, dtoClass));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
