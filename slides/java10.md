class: inverse, center, middle

# Újdonságok Java 10-től

---

## Tematika

* Local-Variable Type Inference
* Unmodifiable collections
* `Optional.orElseThrow()` metódus
* JVM változások
* Verziószámozás

---

class: inverse, center, middle

# Local-Variable Type Inference

---

## Egyszerű használat

```java
var message = "Hello, Java 10";
```

* Koncentráljunka váltózónévre, ne a típusra
* Csak lokális változóknál, ahol van értékadás
* A `var` nem kulcsszó, hanem speciális típus, ezért használható változónévként (visszafele kompatibilitás)
* Nem lett tőle a Java dinamikus típusú nyelv, típus következtetés fordítási időben történik, és később nem változtatható

---

## Kerülendő

* Ha a kód olvashatatlanabb lesz tőle
* Pl. streameknél

```java
// Kerülendő!

var names = employees.stream()
    .filter(employee -> employee.getYearOfBirth() >= 2001)
    .map(Employee::getName)
    .map(String::toUpperCase);
```

---

## var kollekciók esetén

Klasszikus esetben ezt használjuk:

```java
List<String> names = new ArrayList<>();
```

Java 10-től

```java
var names = new ArrayList<String>();
```

Ennek felel meg:

```java
ArrayList<String> names = new ArrayList<String>();
```

---

## Lambda kifejezéseknél

```java
var one = (Supplier<Integer>) () -> 1;
System.out.println(one.get());
```

---

## Intersection types

```java
public interface LogReader {}
public interface LogStreamer {}
public class DummyLogReaderStreamer implements LogReader, LogStreamer {}
public class DummyDummyLogReaderStreamer implements LogReader, LogStreamer {}

public static void main(String[] args) {
    var l = pick(new DummyLogReaderStreamer(), 
                new DummyDummyLogReaderStreamer()); // LogReader & LogStreamer
}

static <T> T pick(T l1, T l2) {
    return l2;
}

public static <T extends LogReader & LogStreamer>void streamRead(T readerAndStreamer) {
    
}
```

---

class: inverse, center, middle

# Unmodifiable collections

---

## Unmodifiable collections

* `List.copyOf()`, `Set.copyOf()`, és `Map.copyOf()`
* `Collectors` `toUnmodifiableList()`, `toUnmodifiableSet()`, `toUnmodifiableMap()` metódusok
* A módosító metódusok `UnsupportedOperationException` kivételt dobnak
* Nem feltétlenül immutable (ha az elemei módosíthatóak)

---

class: inverse, center, middle

# Optional.orElseThrow()

---

## Optional.orElseThrow()

```java
public Employee findById(long id) {
    return findEmployeeById(id)
            .orElseThrow();
}

public Optional<Employee> findEmployeeById(long id) {
    return Optional.empty();
}
```

```java
public T orElseThrow() {
    if (value == null) {
        throw new NoSuchElementException("No value present");
    }
    return value;
}
```

---

class: inverse, center, middle

# JVM változások

---

## Konténer támogatás

* Linux rendszereken a JVM nem a host CPU számát és memóriáját adja vissza, hanem a konténerét

```shell
# Kikapcsolás:
-XX:-UseContainerSupport
# Processzor magok számának explicit megadása
-XX:ActiveProcessorCount=3
# Java 8 update 191 óta
-XX:InitialRAMPercentage=50 -XX:MaxRAMPercentage=50 -XX:MinRAMPercentage=50
```

---

## Parallel Full GC for G1

* JDK 9 óta a G1 az alapértelmezett szemétgyűjtő
* Full GC a parallel mark-sweep-compact algoritmust használja

---

## Application Class-Data Sharing

* JDK 5-ben jelent meg, hogy előfeldolgozott osztályok egy megosztott fájlból betölthetőek
  * Gyorsabb indulás
  * JVM-ek között megosztott használat, így kevesebb memóriafoglalás
  * Csak JDK osztályaihoz volt használható, mert csak a bootstrap classloader volt képes a használatára
* Application CDS (AppCDS) használatával az alkalmazás osztályai is használhatóak
  * Többi classloader is támogatja

---

## Experimental Java-Based JIT Compiler

* Graal is a dynamic compiler written in Java 
* Használható JIT compilerként

---

class: inverse, center, middle

# Verziószámozás

---

## Verziószámozás

* Hat havonta új kiadás
* Long-term support verziószámában benne van az LTS, mely legalább 3 évig támogatott
* Feature release hat hónapig támogatott, a következő release-ig
* Java 11 LTS

```shell
$ java -version
openjdk version "10" 2018-03-20
OpenJDK Runtime Environment 18.3 (build 10+46)
OpenJDK 64-Bit Server VM 18.3 (build 10+46, mixed mode)
```

https://www.oracle.com/java/technologies/java-se-support-roadmap.html