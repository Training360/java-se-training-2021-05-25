package interfacestatic;

public class Book implements Printable {

    private String title;

    public Book(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }
}
