package hexlet.code;

import hexlet.code.parsers.ParserFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public final class DataSupplier {
    public static Map<String, Object> getData(String filepath) throws IOException {
        String extension = filepath.split("\\.")[1];
        Path path = Paths.get(filepath).toAbsolutePath().normalize();
        String content = Files.readString(path);
        return ParserFactory.getParser(extension, content);
    }

}
