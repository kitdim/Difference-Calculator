package hexlet.code.exercise;

import hexlet.code.parsers.ParserFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public final class DataSupplier {
    public static Map<String, Object> getData(String filepath) {
        String extension = filepath.split("\\.")[1];
        Path path = Paths.get(filepath).toAbsolutePath().normalize();
        String content = null;
        try {
            content = Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ParserFactory.getParser(extension, content);
    }

}
