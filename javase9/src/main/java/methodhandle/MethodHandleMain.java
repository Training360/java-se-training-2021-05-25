package methodhandle;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.VarHandle;

public class MethodHandleMain {

    public static void main(String[] args) throws Throwable {
        Employee employee = new Employee("John Doe", 20);

        MethodType mt = MethodType.methodType(String.class); // Visszetérési típus
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle mh = lookup.findVirtual(Employee.class, "getName", mt); // Osztály, metódusnév

        String nameFromGetter = (String) mh.invoke(employee);
        System.out.println(nameFromGetter);

        VarHandle vh = MethodHandles
                .privateLookupIn(Employee.class, MethodHandles.lookup())
                .findVarHandle(Employee.class, "name", String.class);
        String nameFromAttribute = (String) vh.get(employee);
        System.out.println(nameFromAttribute);

        new MethodHandleMain().increaseAge(employee);
        System.out.println(employee.getAge());

    }

    public void increaseAge(Employee employee) throws Throwable {
        VarHandle vh = MethodHandles
                .privateLookupIn(Employee.class, MethodHandles.lookup())
                .findVarHandle(Employee.class, "age", int.class);
        int age = (int) vh.getAndAdd(employee, 1);
        System.out.println(age);
    }
}
