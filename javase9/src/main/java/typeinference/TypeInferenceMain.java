package typeinference;

import java.util.function.Supplier;

public class TypeInferenceMain {

    public static void main(String[] args) {
        var one = (Supplier<Integer>) () -> 1;
        System.out.println(one.get());
    }
}
