package interfacestatic;

import java.util.Map;

public class PrintableMain {

    public static void main(String[] args) {
        Printable book = Printable.of("book,Java 9");
        System.out.println(book.toStringRepresentation());

        Printable paper = Printable.of(Map.of("type", "paper", "title", "Oracle Newsletter"));
        System.out.println(paper.toStringRepresentation());
    }
}
