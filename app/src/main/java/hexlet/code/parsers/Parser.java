package hexlet.code.parsers;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

public interface Parser {
    Map<String, Object> parse(String content) throws JsonProcessingException;
}
