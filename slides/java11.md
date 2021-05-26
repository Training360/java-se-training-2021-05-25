class: inverse, center, middle

# Újdonságok Java 11-től

---

## Tematika

* `var` használata lambda kifejezésekben
* API módosítások
* HTTP Client API
* Java forráskód azonnali futtatása  (shebang)
* Pack200 és Unpack200 eltávolítása
* JVM újdonságok: Z és Epsilon Garbage Collector

---

class: inverse, center, middle

# Java 11 bevezetés

---

## Java 11

* 2018 szeptember
* Java 8 után az első LTS
* Oracle JDK production környezetben ingyenesen nem használható, fejlesztésre, oktatásra igen
* Open JDK használható
* [Alternatívák](https://en.wikipedia.org/wiki/OpenJDK):
  * AdoptOpenJDK -> Eclipse [Adoptium](https://adoptium.net/)
  * Amazon Corretto
  * Azul Zulu

---

class: inverse, center, middle

# Nyelvi újdonságok

---

## A var használata lambda kifejezésekben

```java
(s1, s2) -> s1 + s2
```

```java
(var s1, var s2) -> s1 + s2
```

```java
(@Nonnull var s1, @Nullable var s2) -> s1 + s2
```

(type annotation)

---
class: inverse, center, middle

# API módosítások

---

## String metódusok

* `isBlank()`, `lines()`, `repeat(int)`
* `strip()`, `stripLeading()`, `stripTrailing()`: Unicode aware

---

## Collections.toArray()

* `Collection` `toArray`

---

## Stream módosítások

* `Predicate` `not(Predicate)`
* `Optional*` `isEmpty()`
* `Pattern` `asMatchPredicate()`

```java
public Predicate<String> asMatchPredicate() {
    return s -> matcher(s).matches();
}
```

---
class: inverse, center, middle

# Fájlkezelés

---

## Fájlkezelés

* Files metódusok: `readString()`, `writeString()`, UTF-8
* `FileReader`, `FileWriter` `Charset` paraméterek bevezetése
* `Path.of` metódus a `Paths.get()` helyett

---
class: inverse, center, middle

# HTTP Client API

---

## Oldal letöltése

```java
var client = HttpClient.newHttpClient();
var request = HttpRequest.newBuilder()
        .uri(URI.create(URL_PREFIX))
        .build();
client.send(request, HttpResponse.BodyHandlers.ofLines())
        .body().forEach(System.out::println);
```

---

## HttpClient builderrel

```java
var client = HttpClient.newBuilder()
        .followRedirects(HttpClient.Redirect.ALWAYS)
        .build();
```

---

## Aszinkron módon

```java
var client = HttpClient.newHttpClient();
HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(URL_PREFIX))
        .build();
client.sendAsync(request, HttpResponse.BodyHandlers.ofLines())
        .thenApply(HttpResponse::body)
        .thenApply(this::processLines)
        .join();
```

---
class: inverse, center, middle

# További API módosítások

---

## JAXB és JAX-WS

Java EE és CORBA modulok eltávolítása: JAXB, JAX-WS

```java
<dependency>
    <groupId>org.glassfish.jaxb</groupId>
    <artifactId>jaxb-runtime</artifactId>
    <version>2.3.4</version>
</dependency>
```

---

## JavaFX és Nashorn

* JavaFX már nem része
* Nashorn deprecated

---

## Security módosítások

* TLS 1.3 részleges támogatás
* Curve25519 és Curve448 kulcsegyeztető algoritmusok: kevésbé támadhatóak
* ChaCha20 és Poly1305 kriptográfiai algoritmusok megvalósítása

---
class: inverse, center, middle

# Tools

---

## Java forráskód azonnali futtatása

```shell
java HelloWorld.java
```

---

## Shebang

* Ne legyen a kiterjesztése `.java`, helyette `HelloWorld.sh`

```bash
#!/opt/java/jdk-11/bin/java --source 11

public class HelloWorld
{
    public static void main(String[] args)
    {
        System.out.println("Hello World!");
    }
}
```

---

## Pack200 és Unpack200

* Pack200 és Unpack200 eltávolítása

---
class: inverse, center, middle

# JVM újdonságok

---

## Z Garbage Collector

* 11-ben még csak experimental
* Terabyte nagyságú heap-ek kezelésére
* 10 ms alatt a megállások
* Skálázható, memóriaterület nagyságának növekedésével ne nőjön az GC idő
* Max 15%-ot használjon a futási időből
* Felkészülés a multi-tiered heapre (flash és non-volatile memory elterjedésével, Non-volatile random-access memory (NVRAM))
* Színes pointerek
* Load barrier

```shell
XX:+UnlockExperimentalVMOptions -XX:+UseZGC
```

---

## Epsilon Garbage Collector

* NOP
* Felhasználási területei: benchmark, memóriaműveletek tesztelésére, apró (parancsori) programoknál, VM teszteléshez

```shell
-XX:+UnlockExperimentalVMOptions -XX:+UseEpsilonGC
```

---

## További módosítások

* CONSTANT_Dynamic
* Alacsony költségű heap profile - `SampledObjectAlloc` JVM TI
* Unicode 10.0.0 támogatás (új szkriptek és karakterek)
    * Szkript: betűk és írásjelek csoportja