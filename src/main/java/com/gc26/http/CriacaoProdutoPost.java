package com.gc26.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import com.google.gson.Gson;

public class CriacaoProdutoPost {

    private static final Duration TIMEOUT = Duration.ofSeconds(10);

    public static void main(String[] args) throws IOException, InterruptedException {
        ProdutoRequest produto = new ProdutoRequest();
        produto.setTitle("Pizza");
        String json = new Gson().toJson(produto);

        HttpClient client = HttpClient.newBuilder().connectTimeout(TIMEOUT).build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://dummyjson.com/products/add"))
                .timeout(TIMEOUT)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() >= 400) {
            System.err.println("Erro HTTP " + response.statusCode());
            return;
        }
        System.out.println(response.body());
    }
}
