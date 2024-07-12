package com.connector.db.utils;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * Utility to assist common operations pertaining to json body and java objects.
 *
 * @author Aryak D.
 * @version 1.0
 * @since 25/10/2022
 */
public class JsonUtils {

    /*
     * this method is used to read heavily nested json objects. Just pass the map
     * and the path to the key which you want to read. e.g. {"A" :{ "B" : { "C" : ""
     * }}} and you want to read value of C then pass new String[]{"A", "B" ,"C"} as
     * the var-args.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T> T getNestedValue(Map map, String... keys) {
        Object value = map;
        for (String key : keys)
            value = ((Map) value).get(key);
        return (T) value;
    }

    /** this displays the json body in beautiful format, useful in case of loggers **/
    public static <T> String displayPrettyJSON(T obj) {

        String display = "";
        ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
        try {

            display = objectWriter.writeValueAsString(obj);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return display;
    }

    /*
     * Take json string as input and sort of typecasts it into a java class and
     * returns an object of that class-type.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static Object getJavaObjectFromJSON(String json, Class c) {

        try {

            return new ObjectMapper().readValue(json, c);

        } catch (JsonProcessingException e) {

            e.printStackTrace();
            return null;
        }

    }

    public static String transform(String line) {
        return line.replace("{", "").replace(",", "|").replace("}", "");
    }
}
