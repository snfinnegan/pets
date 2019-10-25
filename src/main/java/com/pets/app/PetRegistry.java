package com.pets.app;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
/*import com.google.gson.Gson;
import java.io.BufferedReader;
import java.lang.StringBuilder;
import java.io.InputStream;*/

public class PetRegistry {
	private String domain = "https://petstore.swagger.io/v2";
	private String endPoint = "/pet";	
	
	private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

	public PetRegistry() {
		
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		PetRegistry pets = new PetRegistry();
		pets.sendPOST();
		pets.sendGET();
		pets.sendPOSTUpdate();
		pets.sendGET();
		pets.sendDELETE();
		pets.sendGET();
	}
	
	public HttpResponse<String> sendPOST() throws IOException, InterruptedException {
        String body = "{ \"id\": 3336, \"category\": { \"id\": 0, \"name\": \"string\" }, \"name\": \"doggie\", \"photoUrls\": [ \"string\" ], \"tags\": [ { \"id\": 0, \"name\": \"string\" } ], \"status\": \"available\"}";

		// add json header
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .uri(URI.create(domain + endPoint))
                .setHeader("User-Agent", "HttpClient") // add request header
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());        
        return response;
    }
	
	public HttpResponse<String> sendPOSTUpdate() throws IOException, InterruptedException {

        String updatedName = "benji";
        String updatedStatus = "unavailable";
        String update = "name=" + updatedName + "&status=" + updatedStatus;
		// add json header
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(update))                
                .uri(URI.create(domain + endPoint + "/3336"))
                .setHeader("User-Agent", "HttpClient") // add request header
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response;

    }
	
	public HttpResponse<String> sendGET() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(domain + endPoint + "/3336"))
                .setHeader("User-Agent", "HttpClient") // add request header
                .build();


        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        // print response headers
        HttpHeaders headers = response.headers();
        headers.map().forEach((k, v) -> System.out.println(k + ":" + v));
        return response;

    }
	
	public HttpResponse<String> sendDELETE() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .DELETE()
                .uri(URI.create(domain + endPoint + "/3336"))
                .setHeader("User-Agent", "HttpClient") // add request header
                .build();


        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        // print response headers
        HttpHeaders headers = response.headers();
        headers.map().forEach((k, v) -> System.out.println(k + ":" + v));
        return response;

    }

}
