package com.pets.app;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
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
	HttpResponse<String> postResponse,getResponse, updateResponse, getResponse2, getResponse3, deleteResponse;
	String postResponseName, getResponseName, getResponseName2, targetName;
    @Before
    public void shouldPostNewPetSuccessfully() throws IOException, InterruptedException {            
    	pets = new PetRegistry();   
    	postResponse = pets.sendPost();
    	postResponseName = JsonPath.read(postResponse.body(), "$.name");
    	targetName = JsonPath.read(initialPost, "$.name");      	
    	getResponse = pets.sendGet();
    	getResponseName = JsonPath.read(getResponse.body(), "$.name");
    	targetName = JsonPath.read(initialPost, "$.name");  
    	updateResponse = pets.sendPostUpdate(); 
    	getResponse2 = pets.sendGet();
    	getResponseName2 = JsonPath.read(getResponse2.body(), "$.name");    
    	deleteResponse = pets.sendDelete();    	
    	getResponse3 = pets.sendGet();
    }
    
	@Test
    public void postAssertion() throws IOException, InterruptedException {    
		assertEquals(postResponse.statusCode(),200);
    	assertEquals(postResponse.body(),initialPost); 
    	assertEquals(postResponseName,targetName);
	}
	@Test
    public void getAssertion() throws IOException, InterruptedException {    
    	assertEquals(getResponse.statusCode(),200);
    	assertEquals(getResponse.body(),initialPost); 
    	assertEquals(getResponseName,targetName);
	}
	@Test
    public void updateAssertion() throws IOException, InterruptedException {    
    	assertEquals(updateResponse.statusCode(),200);
	}	
	@Test
    public void getAssertion2() throws IOException, InterruptedException {    
    	assertEquals(getResponse2.statusCode(),200);
    	assertEquals(getResponse2.body(),updatedPost);
	}
	@Test
    public void confirmDelete() throws IOException, InterruptedException {    
		assertEquals(deleteResponse.statusCode(),200);
		assertEquals(getResponse3.statusCode(),404);
	}
	
		
		
    	/*pets = new PetRegistry();   
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
    }*/
}
