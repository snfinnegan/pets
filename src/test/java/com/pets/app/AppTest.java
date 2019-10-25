package com.pets.app;

import static org.junit.Assert.*;

import org.junit.Test;
import static org.junit.Assert.assertThat;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import com.pets.app.PetRegistry;
import java.net.http.HttpResponse;
import java.io.IOException;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
	String initialPost = "{\"id\":3336,\"category\":{\"id\":0,\"name\":\"string\"},\"name\":\"doggie\",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":0,\"name\":\"string\"}],\"status\":\"available\"}";
	String updatedPost = "{\"id\":3336,\"category\":{\"id\":0,\"name\":\"string\"},\"name\":\"benji\",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":0,\"name\":\"string\"}],\"status\":\"unavailable\"}";

	PetRegistry pets;
	HttpResponse<String> response;
    
    @Test
    public void shouldAnswerWithTrue() throws IOException, InterruptedException {
            
    	pets = new PetRegistry();   
    	response = pets.sendPOST();
    	assertEquals(response.statusCode(),200);
    	assertEquals(response.body(),initialPost); 
    	response = pets.sendGET();
    	assertEquals(response.statusCode(),200);
    	assertEquals(response.body(),initialPost); 
    	response = pets.sendPOSTUpdate();
    	assertEquals(response.statusCode(),200);
    	response = pets.sendGET();
    	assertEquals(response.statusCode(),200);
    	assertEquals(response.body(),updatedPost); 
    	response = pets.sendDELETE();
    	assertEquals(response.statusCode(),200);
    	response = pets.sendGET();
    	assertEquals(response.statusCode(),404);
    }
}
