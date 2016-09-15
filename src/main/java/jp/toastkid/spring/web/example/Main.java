package jp.toastkid.spring.web.example;

import static org.springframework.web.client.reactive.ClientWebRequestBuilders.get;
import static org.springframework.web.client.reactive.ResponseExtractors.body;

import java.util.concurrent.CountDownLatch;

import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.client.reactive.WebClient;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        new WebClient(new ReactorClientHttpConnector())
            .perform(get("http://www.yahoo.co.jp").accept(MediaType.TEXT_PLAIN))
            .extract(body(String.class))
            .subscribe(body -> {
                System.out.println(body);
                latch.countDown();
            });
        latch.await();
    }
}
