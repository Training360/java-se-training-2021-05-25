package interfacestatic;

import java.util.Map;

public interface Printable {

    static Printable of(String csv) {
        String[] parts = csv.split(",");
        return create(parts[0], parts[1]);
    }

    static Printable of(Map<String, String> field) {
        return create(field.get("type"), field.get("title"));
    }

    private static Printable create(String type, String title) {
        if ("book".equalsIgnoreCase(type)) {
            return new Book(title);
        }
        else if ("paper".equalsIgnoreCase(type)) {
            return new Paper(title);
        }
        else {
            throw new IllegalArgumentException("Unknown type: " + type);
        }
    }

    String getTitle();

    default String getBody() {
        return "[no body]";
    }

    private String annotate(String fieldName, String content) {
        return String.format("%s: %s", fieldName, content);
    }

    default String toStringRepresentation() {
        return annotate(getTitle(), "title") +
                ", " +
                annotate(getBody(), "body");
    }
}
