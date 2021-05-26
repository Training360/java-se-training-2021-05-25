package typeinference;

public class LogMain {

    public static void main(String[] args) {
        var l = pick(new DummyLogReaderStreamer(), new DummyDummyLogReaderStreamer());
    }

    static <T> T pick(T l1, T l2) {
        return l2;
    }

    public static <T extends LogReader & LogStreamer>void streamRead(T readerAndStreamer) {

    }
}
