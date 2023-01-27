package com.dlucchesi.myglic.util.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Optional;

public class AsteriskSerializer extends StdSerializer<Object> implements ContextualSerializer {

    String asterisk;

    public AsteriskSerializer() {
        super(Object.class);
    }

    public AsteriskSerializer(String asterisk) {
        super(Object.class);
        this.asterisk = asterisk;
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty property) {
        Optional<MaskPasswd> optProperty = Optional.ofNullable(property)
                .map(prop -> prop.getAnnotation(MaskPasswd.class));
        return new AsteriskSerializer(optProperty.map(MaskPasswd::value).orElse(null));
    }

    @Override
    public void serialize(Object obj, JsonGenerator gen, SerializerProvider prov) throws IOException {
        gen.writeString(asterisk);
    }
}
