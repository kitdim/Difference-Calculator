package hexlet.code;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(Map<String, Object> data1, Map<String, Object> data2) {
        StringBuilder result = new StringBuilder("{\n");
        Set<String> keys = new TreeSet<>(data1.keySet());
        keys.addAll(data2.keySet());

        for (var key : keys) {
            if (data1.containsKey(key)
                    && data1.get(key).equals(data2.get(key))) {
                result.append("    " + key + ": " + data1.get(key) + "\n");
            } else if (data1.containsKey(key)
                    && data2.containsKey(key)
                    && !data1.get(key).equals(data2.get(key))) {
                result.append("  - " + key + ": " + data1.get(key) + "\n");
                result.append("  + " + key + ": " + data2.get(key) + "\n");
            } else if (!data1.containsKey(key)
                    && data2.containsKey(key)) {
                result.append("  + " + key + ": " + data2.get(key) + "\n");
            } else {
                result.append("  - " + key + ": " + data1.get(key) + "\n");
            }
        }
        result.append("}");
        return result.toString();
    }
}
