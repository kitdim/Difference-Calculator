package hexlet.code;

import java.util.*;

public class DifferBuild {
    public static Map<String, LineDiff> getDiffer(Map<String, Object> data1, Map<String, Object> data2) {
        List<String> differ = new LinkedList<>();
        Map<String, LineDiff> diffMap = new TreeMap<>();
        Set<String> keys = new TreeSet<>(data1.keySet());
        keys.addAll(data2.keySet());
        for (var key : keys) {
            if (data1.containsKey(key)
                    && Objects.equals(data1.get(key), data2.get(key))) {
//                differ.add(" " + "|" + key + "|" + data1.get(key) + "|" + data2.get(key))
                diffMap.put(key, new LineDiff("nothing", key, data1.get(key), data2.get(key)));
            } else if (data1.containsKey(key)
                    && data2.containsKey(key)
                    && !Objects.equals(data1.get(key), data2.get(key))) {
                diffMap.put(key, new LineDiff("changed", key, data1.get(key), data2.get(key)));
//                differ.add("-" + "|" + key + "|" + data1.get(key) + "|" + data2.get(key));
//                differ.add("+" + "|" + key + "|" + data2.get(key) + "|" + data1.get(key) + "|" + "update");
            } else if (!data1.containsKey(key)
                    && data2.containsKey(key)) {
                diffMap.put(key, new LineDiff("added", key, data2.get(key), data1.get(key)));
//                differ.add("+" + "|" + key + "|" + data2.get(key) + "|" + data1.get(key) + "|" + "added");
            } else {
                diffMap.put(key, new LineDiff("remove", key, data1.get(key), data2.get(key)));
//                differ.add("-" + "|" + key + "|" + data1.get(key) + "|" + data2.get(key) + "|" + "remove");
            }
        }
        return diffMap;
    }
}
