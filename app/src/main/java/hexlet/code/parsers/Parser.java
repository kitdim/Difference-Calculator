package hexlet.code.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Path;

public interface Parser {
    ObjectMapper parse(Path path);
}
