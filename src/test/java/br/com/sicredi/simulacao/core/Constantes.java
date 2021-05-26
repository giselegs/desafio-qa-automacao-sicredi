package br.com.sicredi.simulacao.core;

import io.restassured.http.ContentType;

public interface Constantes {

	String APP_BASE_URL = "http://localhost:8080/";
	Integer APP_PORT = 8080; 
	String APP_BASE_PATH = "/api/v1";
	
	ContentType APP_CONTENT_TYPE = ContentType.JSON;
	Long MAX_TIMEOUT = 5000L;
	
}
