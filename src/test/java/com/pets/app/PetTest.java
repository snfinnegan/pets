package com.pets.app;

import static org.junit.Assert.*;
import org.junit.Test;
import com.pets.app.PetRegistry;
import java.net.http.HttpResponse;
import java.io.IOException;
import com.jayway.jsonpath.JsonPath;


/**
 * Test assertions on REST calls to https://petstore.swagger.io/
 */
public class PetTest 
{
	String initialPost = "{\"id\":3336,\"category\":{\"id\":0,\"name\":\"string\"},\"name\":\"doggie\",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":0,\"name\":\"string\"}],\"status\":\"available\"}";
	String updatedPost = "{\"id\":3336,\"category\":{\"id\":0,\"name\":\"string\"},\"name\":\"benji\",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":0,\"name\":\"string\"}],\"status\":\"unavailable\"}";

	PetRegistry pets;
	HttpResponse<String> response;
	String responseName, targetName;
    
	@Test
    public void shouldPostNewPetSuccessfully() throws IOException, InterruptedException {            
    	pets = new PetRegistry();   
    	response = pets.sendPost();
    	responseName = JsonPath.read(response.body(), "$.name");
    	targetName = JsonPath.read(initialPost, "$.name");        
    	assertEquals(response.statusCode(),200);
    	assertEquals(response.body(),initialPost); 
    	assertEquals(responseName,targetName);
    	response = pets.sendGet();
    	responseName = JsonPath.read(response.body(), "$.name");
    	targetName = JsonPath.read(initialPost, "$.name");        
    	assertEquals(response.statusCode(),200);
    	assertEquals(response.body(),initialPost); 
    	assertEquals(responseName,targetName);
    	response = pets.sendPostUpdate();    	     
    	assertEquals(response.statusCode(),200);
    	response = pets.sendGet();
    	responseName = JsonPath.read(response.body(), "$.name");
    	targetName = JsonPath.read(updatedPost, "$.name");   
    	assertEquals(response.statusCode(),200);
    	assertEquals(response.body(),updatedPost); 
    	assertEquals(responseName,targetName);
    	response = pets.sendDelete();
    	assertEquals(response.statusCode(),200);
    	response = pets.sendGet();
    	assertEquals(response.statusCode(),404);
    }
}
