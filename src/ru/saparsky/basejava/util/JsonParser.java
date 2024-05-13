package ru.saparsky.basejava.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.saparsky.basejava.model.Section;

import java.io.Reader;
import java.io.Writer;
import java.time.LocalDate;

public class JsonParser {
    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(Section.class, new JsonSectionAdapter())
            .registerTypeAdapter(LocalDate.class, new LocalDateGsonAdapter())
            .create();

    public static <T> T read(Reader reader, Class<T> clazz) {
        return GSON.fromJson(reader, clazz);
    }

    public static <T> void write(T object, Writer writer) {
        GSON.toJson(object, writer);
    }
}
