package net.neurowork.cenatic.centraldir.workers;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.Test;

public class RESTfulTest {
	@Test
	public void testHttpClient(){
		HttpClient client = new HttpClient();
		 //Instantiate a GET HTTP method
        HttpMethod method = new GetMethod(createTokenUrl());

        //Define name-value pairs to set into the QueryString
        NameValuePair nvp1= new NameValuePair("username","alejandrosanchezacosta@gmail.com");
        NameValuePair nvp2= new NameValuePair("password","netfilter");
        method.setQueryString(new NameValuePair[]{nvp1,nvp2});

        try{
            int statusCode = client.executeMethod(method);

            System.out.println("QueryString>>> "+method.getQueryString());
            System.out.println("Status Text>>>"
                  +HttpStatus.getStatusText(statusCode));

            //Get data as a String
            System.out.println(method.getResponseBodyAsString());

            //release connection
            method.releaseConnection();
        }
        catch(IOException e) {
            e.printStackTrace();
        }

	}

	private String createTokenUrl() {
		return "http://sld-melilla.cenatic.net/rest/getToken.php";
	}
}
