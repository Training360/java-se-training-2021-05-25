package interfacestatic;

public class Paper implements Printable {

    private String title;

    public Paper(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }
}
