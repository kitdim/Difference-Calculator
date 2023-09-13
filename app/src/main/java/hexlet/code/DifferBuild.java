package hexlet.code;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public final class DifferBuild {
    public static Map<String, LineDiff> getDiffer(Map<String, Object> data1, Map<String, Object> data2) {
        Map<String, LineDiff> diff = new TreeMap<>();
        Set<String> keys = new TreeSet<>(data1.keySet());
        keys.addAll(data2.keySet());
        for (var key : keys) {
            if (data1.containsKey(key)
                    && Objects.equals(data1.get(key), data2.get(key))) {
                diff.put(key, new LineDiff("nothing", key, data1.get(key), data2.get(key)));
            } else if (data1.containsKey(key)
                    && data2.containsKey(key)
                    && !Objects.equals(data1.get(key), data2.get(key))) {
                diff.put(key, new LineDiff("changed", key, data1.get(key), data2.get(key)));
            } else if (!data1.containsKey(key)
                    && data2.containsKey(key)) {
                diff.put(key, new LineDiff("added", key, data1.get(key), data2.get(key)));
            } else {
                diff.put(key, new LineDiff("remove", key, data1.get(key), data2.get(key)));
            }
        }
        return diff;
    }
}
