package org.coop.cotacao.rest;

import static io.restassured.RestAssured.given;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class CotacaoRESTTest {

	@Test
	void testGetCotacaoDia() {
		LocalDate data = LocalDate.of(2022, 03, 25);
		
		given()
			.when()
				.queryParam("data", data.toString())
				.get("/cotacao-dolar-dia")
			.then()
				.statusCode(200);
		
		data = LocalDate.of(2022, 03, 26);
		
		given()
		.when()
			.queryParam("data", data.toString())
			.get("/cotacao-dolar-dia")
		.then()
			.statusCode(200);
				
	}

}
