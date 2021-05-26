package diamond;

public class CrateMain {

    public static void main(String[] args) {
        Crate<String> crate = new Crate<>() {
            @Override
            public void put(String content) {
                System.out.printf("Content: %s\n", content);
            }

            @Override
            public void empty() {
                System.out.println("Empty");
            }
        };
        crate.put("dog");
        crate.empty();
    }
}
