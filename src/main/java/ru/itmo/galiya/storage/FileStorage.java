package ru.itmo.galiya.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;

public class FileStorage {
    private final Gson gson = new GsonBuilder().registerTypeAdapter(Instant.class, new InstantAdapter()).setPrettyPrinting().create();


    //TODO Files
    public void save(String path, AppData data) {
        try {
            Path p = Paths.get(path);
            Path parent = p.getParent();

            if (parent != null) {
                Files.createDirectories(parent);
            }
            try (FileWriter writer = new FileWriter(path)) {
                gson.toJson(data, writer);
            }
        } catch (IOException e) {
            throw new FileStorageException("Ошибка сохранения файла: " + path, e);
        }
    }

    public AppData load(String path) {
        try (FileReader reader = new FileReader(path)){
            AppData data = gson.fromJson(reader, AppData.class);//текст JSON из файла и превращается в объект AppData.

            if (data == null) {
                throw new FileStorageException("Файл пустой или содержит некорректные данные: " + path);
            }

            return data;
        } catch (IOException e) {
            throw new FileStorageException("Ошибка загрузки файла: " + path, e);
        }
    }
}
//data-это объект, который ты хочешь сохранить.
//writer-это куда сохранять.
//toJson(...)-это метод Gson, который означает: превратить объект в JSON