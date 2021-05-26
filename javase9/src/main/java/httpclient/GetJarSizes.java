package httpclient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GetJarSizes {

    private static final String URL_PREFIX = "https://repo1.maven.org/maven2/javax/javaee-api/";

//    HttpClient client = HttpClient.newBuilder()
//            //.executor(ForkJoinPool.commonPool())
////            .executor(Executors.newSingleThreadExecutor())
//            .executor(Executors.newFixedThreadPool(5))
//            .build();

    private Stream<String> processLines(Stream<String> lines) {
        System.out.println("processlines" + Thread.currentThread().getName());
        Pattern pattern = Pattern.compile("<a href=\"([^\\/]*\\/)\".*");

        return lines
                .map(s -> pattern.matcher(s))
                .filter(m -> m.matches())
                .map(m -> m.group(1))
                .filter(s -> !"../".equals(s));
    }

    private int size(Stream<String> lines) {
        System.out.println("call size " + Thread.currentThread().getName());
        Pattern pattern = Pattern.compile(".jar\"");
        Pattern pattern2 = Pattern.compile("\\d+(?=\\D*$)");
        int size =
         lines
//                 .peek(System.out::println)
                .filter(line -> pattern.matcher(line).find())
                .map(line -> pattern2.matcher(line))
                .filter(matcher -> matcher.find())
                .map(matcher -> matcher.group())
                .mapToInt(number -> Integer.parseInt(number))
                .findAny().orElseGet(() -> 0);
        System.out.println(size);
        return size;
    }

    private CompletableFuture<Integer> getSizeFromPage(String version) {
        System.out.println("Get for version: " + version);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_PREFIX + version))
                .build();
        var client = HttpClient.newBuilder().executor(Executors.newFixedThreadPool(10)).build();
        var future =  client.sendAsync(request, HttpResponse.BodyHandlers.ofLines())
                .thenApply(HttpResponse::body)
                .thenApply(this::size)
                ;
        return future;
    }

    private List<Integer> tasks(Stream<String> versions) {
        System.out.println("tasks" + Thread.currentThread().getName());
        List<CompletableFuture<Integer>> futures  = versions
                .map(version -> getSizeFromPage(version))
                .collect(Collectors.toList());

        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
                .thenApply(x -> futures.stream().
                        map(f -> f.join()).collect(Collectors.toList()))
                .join();



    }

    public void getLinks() {
//        var client = HttpClient.newHttpClient();

        var client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_PREFIX))
                .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofLines())
                .thenApply(HttpResponse::body)
                .thenApply(this::processLines)
                .thenApply(versions -> tasks(versions))
                .join();
    }

    public void getLinksSync() throws InterruptedException, IOException {
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder()
                .uri(URI.create(URL_PREFIX))
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofLines())
                .body().forEach(System.out::println);
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        //new GetJarSizes().getLinks();
        new GetJarSizes().getLinksSync();
//        new GetJarSizes().getSizeFromPage("6.0/").join();
    }
}
