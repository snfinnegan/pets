import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import groovy.util.ConfigObject;
import groovy.util.ConfigSlurper;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Map;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PetsTest {

    static String apiKey;
    static Map envVariables;
    private static int petId;

    static {
        try {
            ConfigObject env = new ConfigSlurper().parse(new File("src/test/resources/env.properties").toURI().toURL());
            envVariables = (Map) env.get(env.getProperty("ENV"));


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    @BeforeAll
    static void setUp(){
        RestAssured.baseURI =
     apiKey = (String) envVariables.get("apiKey");
     petId = generatePetId();
    RestAssured.baseURI = envVariables.get("baseUrl").toString();
    }

    @Test
    @Order(2)
    void getPetByStatus(){
        Response response;
        response = RestAssured
                .given()
                .log().all()
                .when()
                .get("/pet/findByStatus?status=available");
        System.out.println(response.asPrettyString());
    }

    @Test
    @Order(1)
    void createPet(){
        Response response;
        String payload = new JSONObject("" +
                "{\n" +
                "  \"id\": \""+ petId + "\",\n" +
                "  \"category\": {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \"doggie123\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}")
                .toString();
        response = RestAssured
                .given()
                .log().all()
                .contentType("application/json")
                .body(payload)
                .when()
                .post("/pet");
        System.out.println(response.statusCode());

        Response response2 = RestAssured
                .given()
                .log().all()
                .when()
                .get("/pet/" + petId);
        response2.prettyPrint();
        System.out.println(response.statusCode());
    }

    private static int generatePetId(){
        return (int) (Math.random()*1000);
    }


}
