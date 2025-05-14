package org.example.adapters;

import com.google.gson.*;

import java.lang.reflect.Type;

public class EnumAdapter<T extends Enum<T>> implements JsonDeserializer<T> {

    private final Class<T> aClass;

    public EnumAdapter(Class<T> aClass) {
        this.aClass = aClass;
    }

    @Override
    public T deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        String value = jsonElement.getAsString().toUpperCase();
        return Enum.valueOf(aClass, value);
    }
}
