package hexlet.code.parsers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.util.Map;

public final class JsonParser implements Parser {
    @Override
    public Map<String, Object> parse(String content) throws JsonProcessingException {
        ObjectMapper mapper = new JsonMapper();
        return mapper.readValue(content, new TypeReference<>() {
        });
    }
}
