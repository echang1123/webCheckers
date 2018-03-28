/*
 * Wrapper Class for Gson
 *
 * @author: RIT
 */


package com.webcheckers.appl;


import com.google.gson.Gson;
import spark.ResponseTransformer;


public class JsonUtils {

    private static final Gson GSON = new Gson();


    /**
     * Converts a JSON string to a Java object
     * @param json the JSON string
     * @param clazz the object of the Java class
     * @param <T> the class
     * @return a Java object that represents a JSON string
     */
    public static <T> T fromJson( final String json, final Class< T > clazz ) {
        return GSON.fromJson( json, clazz );
    }


    /**
     * Converts a Java object to a JSON String
     * @param object the Java object to convert
     * @return the JSON String representation of the object
     */
    public static String toJson( Object object ) {
        return GSON.toJson( object );
    }



    public static ResponseTransformer json() {
        return JsonUtils::toJson;
    }
}