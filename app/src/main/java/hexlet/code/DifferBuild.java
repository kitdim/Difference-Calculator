package hexlet.code;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class DifferBuild {
    public static List<String> getDiffer(Map<String, Object> data1, Map<String, Object> data2) {
        List<String> differ = new LinkedList<>();
        Set<String> keys = new TreeSet<>(data1.keySet());
        keys.addAll(data2.keySet());
        for (var key : keys) {
            if (data1.containsKey(key)
                    && Objects.equals(data1.get(key), data2.get(key))) {
                differ.add(" " + "|" + key + "|" + data1.get(key) + "|" + data2.get(key));
            } else if (data1.containsKey(key)
                    && data2.containsKey(key)
                    && !Objects.equals(data1.get(key), data2.get(key))) {
                differ.add("-" + "|" + key + "|" + data1.get(key) + "|" + data2.get(key));
                differ.add("+" + "|" + key + "|" + data2.get(key) + "|" + data1.get(key) + "|" + "update");
            } else if (!data1.containsKey(key)
                    && data2.containsKey(key)) {
                differ.add("+" + "|" + key + "|" + data2.get(key) + "|" + data1.get(key) + "|" + "added");
            } else {
                differ.add("-" + "|" + key + "|" + data1.get(key) + "|" + data2.get(key) + "|" + "remove");
            }
        }
        return differ;
    }
}
