package com.qry.base.model.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Created by  2016/10/8.
 */
public class CustEnumToInt extends JsonSerializer<Enum> {


    /**
     * Method that can be called to ask implementation to serialize
     * values of type this serializer handles.
     *
     * @param value       Value to serialize; can <b>not</b> be null.
     * @param gen         Generator used to output resulting Json content
     * @param serializers Provider that can be used to get serializers for
     */
    @Override
    public void serialize(Enum value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        Integer aaa = value.ordinal();
        gen.writeNumber(aaa);
    }
}
