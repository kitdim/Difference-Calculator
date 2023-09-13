package hexlet.code.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.nio.file.Path;

public final class YmlParser implements Parser {
    @Override
    public ObjectMapper parse(Path path) {
        return new YAMLMapper();
    }
}
