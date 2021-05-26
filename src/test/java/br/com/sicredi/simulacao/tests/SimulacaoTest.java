package br.com.sicredi.simulacao.tests;

import org.junit.Test;

import br.com.sicredi.simulacao.core.BaseTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;

public class SimulacaoTest extends BaseTest {
	
	public int idExistente;
	
	@Test
    public void criarSimulacaoComSucesso(){

       given()
               .body(" {\n" +
                       "    \"nome\": \"NomeTest\",\n" +
                       "    \"cpf\": 26276298085,\n" +
                       "    \"email\": \"nometest@email.com\",\n" +
                       "    \"valor\": 1690,\n" +
                       "    \"parcelas\": 3,\n" +
                       "    \"seguro\": true\n" +
                       "  }")
               .contentType(ContentType.JSON)
       .when()
               .post("/simulacoes")
       .then()
               .assertThat()
                    .statusCode(201);
    }
	
	
	@Test
    public void criarSimulacaoSemCPF(){

       given()
               .body(" {\n" +
                       "    \"nome\": \"NomeTest2\",\n" +
                       "    \"email\": \"nometest@test.com\",\n" +
                       "    \"valor\": 1500,\n" +
                       "    \"parcelas\": 3,\n" +
                       "    \"seguro\": true\n" +
                       "  }")
               .contentType(ContentType.JSON)
       .when()
               .post("/simulacoes")
       .then()
               .assertThat()
                    .statusCode(400)
       				.body("erros.cpf", is("CPF não pode ser vazio"));
    }
	
	
	@Test
    public void criarSimulacaoSemNome(){

       given()
       .body(" {\n" +
               "    \"cpf\": 58063164083,\n" +
               "    \"email\": \"nometest@teste.com\",\n" +
               "    \"valor\": 1500,\n" +
               "    \"parcelas\": 3,\n" +
               "    \"seguro\": true\n" +
               "  }")
               .contentType(ContentType.JSON)
       .when()
               .post("/simulacoes")
       .then()
               .assertThat()
                    .statusCode(400)
       				.body("erros.nome", is("Nome não pode ser vazio"));
    }
	
	@Test
    public void criarSimulacaoSemEmail(){

       given()
               .body(" {\n" +
                       "    \"nome\": \"NomeTest2\",\n" +
                       "    \"cpf\": 58063164083,\n" +
                       "    \"valor\": 1500,\n" +
                       "    \"parcelas\": 3,\n" +
                       "    \"seguro\": true\n" +
                       "  }")
               .contentType(ContentType.JSON)
       .when()
               .post("/simulacoes")
       .then()
               .assertThat()
                    .statusCode(400)
       				.body("erros.email", is("E-mail não deve ser vazio"));
    }
	
	@Test
    public void criarSimulacaoComEmailInvalido(){

       given()
               .body(" {\n" +
                       "    \"nome\": \"NomeTest2\",\n" +
                       "    \"cpf\": 58063164083,\n" +
                       "    \"email\": \"nometest@\",\n" +
                       "    \"valor\": 1500,\n" +
                       "    \"parcelas\": 3,\n" +
                       "    \"seguro\": true\n" +
                       "  }")
               .contentType(ContentType.JSON)
       .when()
               .post("/simulacoes")
       .then()
               .assertThat()
                    .statusCode(400)
       				.body("erros.email", is("E-mail deve ser um e-mail válido"));
    }

	
	
	@Test
    public void criarSimulacaoSemValor(){

       given()
               .body(" {\n" +
                       "    \"nome\": \"NomeTest2\",\n" +
                       "    \"cpf\": 58063164083,\n" +
                       "    \"email\": \"nometest@test.com\",\n" +
                       "    \"parcelas\": 3,\n" +
                       "    \"seguro\": true\n" +
                       "  }")
               .contentType(ContentType.JSON)
       .when()
               .post("/simulacoes")
       .then()
               .assertThat()
                    .statusCode(400)
       				.body("erros.valor", is("Valor não pode ser vazio"));
    }
	
	
	@Test
    public void criarSimulacaoComValorAbaixoDoMinimo(){
	/*este teste está falhando pois a API está permitindo inserir valores menores do que R$1.000,00*/

       given()
               .body(" {\n" +
                       "    \"nome\": \"NomeTest2\",\n" +
                       "    \"cpf\": 84809766080,\n" +
                       "    \"email\": \"nometest@test.com\",\n" +
                       "    \"valor\": 300,\n" +
                       "    \"parcelas\": 3,\n" +
                       "    \"seguro\": true\n" +
                       "  }")
               .contentType(ContentType.JSON)
       .when()
               .post("/simulacoes")
       .then()
       		   .log().all()	
               .assertThat()
                    .statusCode(400);
       				
    }
	
	@Test
    public void criarSimulacaoComValorAcimaDoMaximo(){

       given()
               .body(" {\n" +
                       "    \"nome\": \"NomeTest2\",\n" +
                       "    \"cpf\": 24094592008,\n" +
                       "    \"email\": \"nometest@test.com\",\n" +
                       "    \"valor\": 50000,\n" +
                       "    \"parcelas\": 3,\n" +
                       "    \"seguro\": true\n" +
                       "  }")
               .contentType(ContentType.JSON)
       .when()
               .post("/simulacoes")
       .then()
               .assertThat()
                    .statusCode(400)
       				.body("erros.valor", is ("Valor deve ser menor ou igual a R$ 40.000"));   				
    }
	
	@Test
    public void criarSimulacaoComParcelasAbaixoDoMinimo(){

       given()
               .body(" {\n" +
                       "    \"nome\": \"NomeTest2\",\n" +
                       "    \"cpf\": 24094592008,\n" +
                       "    \"email\": \"nometest@test.com\",\n" +
                       "    \"valor\": 1800,\n" +
                       "    \"parcelas\": 1,\n" +
                       "    \"seguro\": true\n" +
                       "  }")
               .contentType(ContentType.JSON)
       .when()
               .post("/simulacoes")
       .then()
               .assertThat()
                    .statusCode(400)
       				.body("erros.parcelas", is ("Parcelas deve ser igual ou maior que 2"));   				
    }
	
	@Test
    public void criarSimulacaoComParcelasAcimaDoMaximo(){
	/*Este teste está falhando pois a API está permitindo incluir mais de 48 parcelas*/	
		

       given()
               .body(" {\n" +
                       "    \"nome\": \"NomeTest2\",\n" +
                       "    \"cpf\": 62648716050,\n" +
                       "    \"email\": \"nometest@test.com\",\n" +
                       "    \"valor\": 1800,\n" +
                       "    \"parcelas\": 49,\n" +
                       "    \"seguro\": true\n" +
                       "  }")
               .contentType(ContentType.JSON)
       .when()
               .post("/simulacoes")
       .then()
       		   .log().all()	
               .assertThat()
                    .statusCode(400);
	}
	
	@Test
    public void criarSimulacaoSemSeguro(){
	/*A api não está retornando a mensagem "Uma das opções de Seguro devem ser selecionadas"*/
		
       given()
               .body(" {\n" +
                       "    \"nome\": \"NomeTest2\",\n" +
                       "    \"cpf\": 24094592008,\n" +
                       "    \"email\": \"nometest@test.com\",\n" +
                       "    \"valor\": 1800,\n" +
                       "    \"parcelas\": 49,\n" +
                       "  }")
               .contentType(ContentType.JSON)
       .when()
               .post("/simulacoes")
       .then()
       		   .log().all()
               .assertThat()
                    .statusCode(400)
       				.body("erros.seguro", is("Uma das opções de Seguro devem ser selecionadas"));
                   
	}
	
	@Test
    public void criarSimulacaoComCPFExistente(){
		
	/*Este teste está falahando, pois para uma nova simulação com um CPF já inserido,
	 *a API está retornando status code 400 ao invés de 409
	 *e a mensagem retornada está "CPF duplicado" ao invés de "CPF já existente"
	 */	
		
       given()
               .body(" {\n" +
                       "    \"nome\": \"NomeTest2\",\n" +
                       "    \"cpf\": 26276298085,\n" +
                       "    \"email\": \"nometes2@email.com\",\n" +
                       "    \"valor\": 1692,\n" +
                       "    \"parcelas\": 3,\n" +
                       "    \"seguro\": true\n" +
                       "  }")
               .contentType(ContentType.JSON)
       .when()
               .post("/simulacoes")
       .then()
               .log().all()
               .assertThat()
                    .statusCode(409);
    }
	
	@Test
    public void alterarSimulacaoExistente(){
		
	   String cpfExistente = "26276298085";

       given()
               .pathParam("cpf", cpfExistente)  
               .body(" {\n" +
                       "    \"nome\": \"NomeTest\",\n" +
                       "    \"cpf\": "+cpfExistente+",\n" +
                       "    \"email\": \"nometest@email.com\",\n" +
                       "    \"valor\": 1690,\n" +
                       "    \"parcelas\": 7,\n" +
                       "    \"seguro\": true\n" +
                       "  }")
               .contentType(ContentType.JSON)
       .when()
               .put("/simulacoes/{cpf}")
       .then()
               .assertThat()
                    .statusCode(200);
    }
	
	@Test
    public void alterarSimulacaoInexistente(){
		
	   String cpfInexistente = "24094592008";

       given()
               .pathParam("cpf", cpfInexistente)  
               .body(" {\n" +
                       "    \"nome\": \"NomeTestInexistente\",\n" +
                       "    \"cpf\": "+cpfInexistente+",\n" +
                       "    \"email\": \"nometestinexistente@email.com\",\n" +
                       "    \"valor\": 1690,\n" +
                       "    \"parcelas\": 3,\n" +
                       "    \"seguro\": true\n" +
                       "  }")
               .contentType(ContentType.JSON)
       .when()
               .put("/simulacoes/{cpf}")
       .then()
               .assertThat()
                    .statusCode(404)
                    .body("mensagem", is("CPF "+cpfInexistente+" não encontrado"));
    }
	
	
	@Test
    public void consultarUmaSimulacao(){
		
	   String cpfExistente = "26276298085";

       given()
               .pathParam("cpf", cpfExistente)  
               
       .when()
               .get("/simulacoes/{cpf}")
       .then()
       		   .statusCode(200)
       		   .extract().path("id");
    }
	
	@Test
    public void removerSimulacaoExistente(){
	   
       given()
               .pathParam("id", idExistente)  
                              
       .when()
               .delete("/simulacoes/{id}")
       .then()
               .assertThat()
                    .statusCode(200);               
    }
	
	@Test
    public void consultarTodasSimulacoes(){
	   
       given()
                              
       .when()
               .get("/simulacoes")
       .then()
               .assertThat()
                    .statusCode(200);               
    }
	
	@Test
    public void removerSimulacaoInexistente(){
	
	   /*A API está retornando status code igual a 200 mesmo com id inexistente, deveria retornar 404*/	
		
	   String idInexistente = "999999";
	   
       given()
               .pathParam("id", idInexistente)  
                              
       .when()
               .delete("/simulacoes/{id}")
       .then()
               .assertThat()
                    .statusCode(404);               
    }
	
	
	
	
       				


	
	
	
}
