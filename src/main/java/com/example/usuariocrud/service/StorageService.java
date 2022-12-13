package com.example.usuariocrud.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class StorageService {

    @Value("${storage.filesystem.base-path}")
    private String baseFileSystemPath;

    public void saveFileLocal(MultipartFile file, String filename) throws IOException {
        Path path = Paths.get(baseFileSystemPath);
        System.out.println(path);
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
        path = Paths.get(baseFileSystemPath + "/" + filename);
        Files.write(path, file.getBytes());

    }

    public InputStream getFileLocal(String filename) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        return classloader.getResourceAsStream(filename);
    }
}
