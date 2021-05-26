package br.com.sicredi.simulacao.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

import br.com.sicredi.simulacao.core.BaseTest;

public class RestricaoTest extends BaseTest {

	@Test
    public void consultarCPFComRestricao(){
      
        String CPF_RESTRICAO = "97093236014";

        given()
                .pathParam("cpf", CPF_RESTRICAO)
        .when()
                .get("/restricoes/{cpf}")
        .then()
        		.log().all()
                .assertThat()
                	.statusCode(200)
                	.body("mensagem", is("O CPF 97093236014 tem problema"));
    }
	
	
	@Test
    public void consultarCPFSemRestricao(){
      
        String CPF_RESTRICAO = "64508448097";

        given()
                .pathParam("cpf", CPF_RESTRICAO)
        .when()
                .get("/restricoes/{cpf}")
        .then()
        		.log().all()
                .assertThat()
                	.statusCode(204);
	}
	
}
