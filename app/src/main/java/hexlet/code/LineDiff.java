package hexlet.code;

public class LineDiff {
    private String status;
    private String key;
    private Object value1;
    private Object value2;

    LineDiff(String status, String key, Object value1, Object value2) {
        this.status = status;
        this.key = key;
        this.value1 = value1;
        this.value2 = value2;
    }

    public String getStatus() {
        return status;
    }

    public String getKey() {
        return key;
    }

    public Object getValue1() {
        return value1;
    }

    public Object getValue2() {
        return value2;
    }
}
