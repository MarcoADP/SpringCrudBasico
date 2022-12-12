package com.example.usuariocrud.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.web.multipart.MultipartFile;

public class StorageService {

    public static void saveFileLocal(MultipartFile file, String filename) throws IOException {
        String baseFileSystemPath = "/home/marco/IdeaProjects/usuariocrud/src/main/resources";
        Path path = Paths.get(baseFileSystemPath);
        System.out.println(path);
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
        path = Paths.get(baseFileSystemPath + "/" + filename);
        Files.write(path, file.getBytes());

    }

    public static String getFileExtension(String fileName) {
        int lastIndexOf = fileName.lastIndexOf(".");

        if (lastIndexOf == -1) {
            return "";
        }

        if (lastIndexOf + 1 == fileName.length()) {
            return "";
        }

        return fileName.substring(lastIndexOf + 1);
    }

}
