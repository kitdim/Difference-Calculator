package hexlet.code;

import java.util.*;

public class Differ {
    public static String generate(Map<String, Object> data1, Map<String, Object> data2, String format) {
        List<String> differ = new LinkedList<>();
        Set<String> keys = new TreeSet<>(data1.keySet());
        keys.addAll(data2.keySet());
        for (var key : keys) {
            if (data1.containsKey(key)
                    && Objects.equals(data1.get(key), data2.get(key))) {
                differ.add("    " + key + ": " + data1.get(key));
            } else if (data1.containsKey(key)
                    && data2.containsKey(key)
                    && !Objects.equals(data1.get(key), data2.get(key))) {
                differ.add("  - " + key + ": " + data1.get(key));
                differ.add("  + " + key + ": " + data2.get(key));
            } else if (!data1.containsKey(key)
                    && data2.containsKey(key)) {
                differ.add("  + " + key + ": " + data2.get(key));
            } else {
                differ.add("  - " + key + ": " + data1.get(key));
            }
        }
        Formatter formatter = new Formatter(differ, format);
        return formatter.getFormat();
    }
}
